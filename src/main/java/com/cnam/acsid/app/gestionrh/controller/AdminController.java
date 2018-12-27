package com.cnam.acsid.app.gestionrh.controller;

import com.cnam.acsid.app.gestionrh.forms.UserForm;
import com.cnam.acsid.app.gestionrh.model.Role;
import com.cnam.acsid.app.gestionrh.model.Sexe;
import com.cnam.acsid.app.gestionrh.model.User;
import com.cnam.acsid.app.gestionrh.repository.SexeRepository;
import com.cnam.acsid.app.gestionrh.service.RoleService;
import com.cnam.acsid.app.gestionrh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SexeRepository sexeRepository;

    @RequestMapping(value="/admin/listerComptes", method = RequestMethod.GET)
    public ModelAndView listerComptesNonAdmin(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.getComptesNonAmin();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Bienvenue " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin/liste-comptes.html");
        return modelAndView;
    }

    @RequestMapping(value="/admin/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") String id){
        userService.deleteById(Long.parseLong(id));
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Bienvenue " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin/liste-comptes.html");
        return "redirect:/admin/listerComptes";
    }

    @RequestMapping(value="/admin/ajouterCompte", method = RequestMethod.GET)
    public ModelAndView afficherCreationUser(@ModelAttribute("errors") String errors, @ModelAttribute("userForm") UserForm userForm){
        ModelAndView modelAndView = new ModelAndView();
        UserForm user = new UserForm();
        if(userForm != null){
            user = userForm;
        }
        modelAndView.addObject("user", user);
        modelAndView.addObject("roles", getNonAdminRoles());
        modelAndView.addObject("sexes", sexeRepository.findAll());
        String[] sErrors = errors.split(";");
        List<String> listErrors = Arrays.asList(sErrors);
        if(listErrors.size() == 1 && listErrors.get(0).equals("")){
            listErrors = new ArrayList<>();
        }
        modelAndView.addObject("errors", listErrors);
        modelAndView.setViewName("/admin/ajouter-compte.html");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/ajouterCompte", method = RequestMethod.POST)
    public ModelAndView creerNouveauUtilisateur(UserForm user, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        String errors = generateCreationCompteErrors("", user);
        if(!errors.isEmpty()){
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/admin/ajouterCompte");
        }
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null || !errors.isEmpty()) {
            errors = updateErrors(errors, "Cet utilisateur existe déjà");
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/admin/ajouterCompte");
        } else {
            userService.saveUser(convertirDeFormVersEntite(user));
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            return new ModelAndView("redirect:/admin/listerComptes");

        }
    }

    @RequestMapping(value="/admin/modifierCompte", method = RequestMethod.GET)
    public ModelAndView afficherModificationUser(@RequestParam("id") String id, @ModelAttribute("errors") String errors){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(Long.parseLong(id));
        UserForm userForm = convertirDeEntiteVersForm(user);
        String[] sErrors = errors.split(";");
        List<String> listErrors = Arrays.asList(sErrors);
        if(listErrors.size() == 1 && listErrors.get(0).equals("")){
            listErrors = new ArrayList<>();
        }
        modelAndView.addObject("errors", listErrors);
        modelAndView.addObject("user", userForm);
        modelAndView.addObject("roles", getNonAdminRoles());
        modelAndView.addObject("sexes", sexeRepository.findAll());
        modelAndView.setViewName("/admin/modifier-compte.html");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/modifierCompte", method = RequestMethod.POST)
    public ModelAndView modifierUtilisateur(@Valid UserForm user, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        String errors = generateCreationCompteErrors("", user);
        if(!errors.isEmpty()){
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/admin/modifierCompte?id=" + user.getId());
        }
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists == null) {
            errors = updateErrors(errors, "Le compte a été supprimé");
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/admin/modifierCompte?id=" + user.getId());
        } else {
            user.setId(userExists.getId());
            userService.saveUser(convertirDeFormVersEntite(user));
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            return new ModelAndView("redirect:/admin/listerComptes");

        }
    }


    @RequestMapping(value="/admin/modifierMonCompte", method = RequestMethod.GET)
    public ModelAndView afficherModificationMonCompte(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserForm userForm = convertirDeEntiteVersForm(user);
        modelAndView.addObject("user", userForm);
        modelAndView.addObject("sexes", sexeRepository.findAll());
        modelAndView.setViewName("/admin/modifier-moncompte.html");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/modifierMonCompte", method = RequestMethod.POST)
    public ModelAndView modifierMonCompte(@Valid UserForm user, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        user.setRole("GESTIONNAIRE");
        String errors = generateCreationCompteErrors("", user);
        if(!errors.isEmpty()){
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/admin/modifierMonCompte");
        }
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists == null) {
            errors = updateErrors(errors, "Le compte a été supprimé");

            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/admin/modifierMonCompte");
        } else {
            user.setId(userExists.getId());
            user.setRole("ADMIN");
            userService.saveUser(convertirDeFormVersEntite(user));
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            return new ModelAndView("redirect:/admin/listerComptes");

        }
    }



    private User convertirDeFormVersEntite(UserForm userForm) {
        User user = new User();
        user.setActive(1);
        user.setId(userForm.getId());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setName(userForm.getName());
        user.setLastName(userForm.getLastName());
        user.setTel(userForm.getTel());
        user.setVille(userForm.getVille());
        user.setCodePostal(userForm.getCodePostal());
        user.setAdresse(userForm.getAdresse());
        user.setDateNaissance(userForm.getDateNaissance());
        Role userRole = roleService.findRoleByRole(userForm.getRole());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        Sexe sexe = sexeRepository.findBySexe(userForm.getSexe());
        user.setSexes( new HashSet<Sexe>(Arrays.asList(sexe)));

        return user;
    }

    private UserForm convertirDeEntiteVersForm(User user) {
        UserForm userForm = new UserForm();
        userForm.setId(user.getId());
        userForm.setEmail(user.getEmail());
        userForm.setLastName(user.getLastName());
        userForm.setName(user.getName());
        userForm.setTel(user.getTel());
        userForm.setVille(user.getVille());
        userForm.setCodePostal(user.getCodePostal());
        userForm.setAdresse(user.getAdresse());
        userForm.setDateNaissance(user.getDateNaissance());
        Set<Role> roles = user.getRoles();
        Iterator<Role> iterator = roles.iterator();
        while(iterator.hasNext()){
            userForm.setRole(iterator.next().getRole());
        }
        Set<Sexe> sexes = user.getSexes();
        Iterator<Sexe> iterator1 = sexes.iterator();
        while(iterator1.hasNext()){
            userForm.setSexe(iterator1.next().getSexe());
        }

        return userForm;
    }

    private List<Role> getNonAdminRoles(){
        List<Role> roles = roleService.findAll();
        List<Role> sRoles = new ArrayList<>();
        for(Role role: roles) {
            if(!role.getRole().equals("ADMIN")){
                sRoles.add(role);
            }
        }
        return sRoles;
    }

    private String generateCreationCompteErrors(String errors, UserForm user) {

        if(!user.getPassword().equals(user.getPasswordConfirm())){
            errors = updateErrors(errors,"Les mots de passe ne sont pas identiques");
        }

        if(user.getPassword().equals("") || user.getPasswordConfirm().equals("")){
            errors = updateErrors(errors,"Remplissez le mot de passe");
        }

        if(user.getRole().equals("")){
            errors = updateErrors(errors,"Il faut choisir un type");
        }
        if(user.getEmail().equals("")){
            errors = updateErrors(errors,"L'email est obligatoire");
        }
        if(user.getLastName().equals("")){
            errors = updateErrors(errors,"Le nom est obligatoire");
        }
        if(user.getName().equals("")){
            errors = updateErrors(errors,"Le prénom est obligatoire");
        }
        if(!"".equals(user.getTel()) && !Pattern.matches("[0-9]{10}", user.getTel())){
            errors = updateErrors(errors,"Le format du numéro de téléphone n'est pas correct");
        }
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if(!"".equals(user.getEmail()) && !pattern.matcher(user.getEmail()).matches()){
            errors = updateErrors(errors,"Le format de l'email n'est pas correct");
        }
        if(!"".equals(user.getCodePostal()) && !Pattern.matches("[0-9]{5}", user.getCodePostal())){
            errors = updateErrors(errors,"Le format du code postal n'est pas correct");
        }
        return errors;
    }

    private String updateErrors(String errors, String message) {
        errors += (errors.equals("") ? message : ";" + message);
        return errors;
    }

}