package com.cnam.acsid.app.gestionrh.controller;

import com.cnam.acsid.app.gestionrh.forms.*;
import com.cnam.acsid.app.gestionrh.model.*;
import com.cnam.acsid.app.gestionrh.repository.*;
import com.cnam.acsid.app.gestionrh.service.CongeService;
import com.cnam.acsid.app.gestionrh.service.RoleService;
import com.cnam.acsid.app.gestionrh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;
import java.util.regex.Pattern;

@Controller
public class GestionnaireController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SexeRepository sexeRepository;

    @Autowired
    private DiplomeRepository diplomeRepository;

    @Autowired
    private FormationRepository formationRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private NiveauRepository niveauRepository;

    @Autowired
    private UserCompetenceRepository userCompetenceRepository;

    @Autowired
    private UserDiplomeRepository userDiplomeRepository;

    @Autowired
    private UserFormationRepository userFormationRepository;

    @Autowired
    TypeContratRepository typeContratRepository;

    @Autowired
    ContratRepository contratRepository;

    @Autowired
    UserTypeContratRepository userTypeContratRepository;

    @Autowired
    CongeRepository congeRepository;

    @Autowired
    UserCongeRepository userCongeRepository;

    @Autowired
    CongeService congeService;

    @RequestMapping(value="/gestionnaire/listerComptes", method = RequestMethod.GET)
    public ModelAndView listerComptes(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.getComptesNonAmin();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Bienvenue " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("users", users);
        modelAndView.setViewName("gestionnaire/liste-comptes.html");
        return modelAndView;
    }

    @RequestMapping(value="/gestionnaire/rejeterConge", method = RequestMethod.GET)
    public String rejeterConges(@RequestParam("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        Conge conge = congeRepository.findById(Long.parseLong(id)).get();
        conge.setStatut("REJETE");
        congeRepository.save(conge);

        return "redirect:/gestionnaire/listerConges";
    }

    @RequestMapping(value="/gestionnaire/validerConge", method = RequestMethod.GET)
    public String validerConges(@RequestParam("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        Conge conge = congeRepository.findById(Long.parseLong(id)).get();
        conge.setStatut("VALIDE");
        congeRepository.save(conge);

        return "redirect:/gestionnaire/listerConges";
    }

    @RequestMapping(value="/gestionnaire/listerConges", method = RequestMethod.GET)
    public ModelAndView listerConges(){
        ModelAndView modelAndView = new ModelAndView();
        List<Conge> conges = congeRepository.findAll();
        List<CongeForm> congesForm = new ArrayList<>();
        for(Conge conge: conges){
            User user = congeService.findUserLieAuConge(conge.getId());
            CongeForm congeForm = convertCongeToForm(conge, user);
            congesForm.add(congeForm);
        }
        modelAndView.addObject("conges", congesForm);
        modelAndView.setViewName("gestionnaire/liste-conges.html");
        return modelAndView;
    }


    @RequestMapping(value="/gestionnaire/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") String id){
        userService.deleteById(Long.parseLong(id));
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Bienvenue " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("users", users);
        return "redirect:/gestionnaire/listerComptes";
    }

    @RequestMapping(value="/gestionnaire/supprimerDiplome", method = RequestMethod.GET)
    public String deleteDiplome(@RequestParam("id") String id, @RequestParam("userId") String userId){
        diplomeRepository.deleteById(Integer.parseInt(id));
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(Long.parseLong(userId));
        modelAndView.setViewName("admin/liste-comptes.html");
        return "redirect:/gestionnaire/details?id=" + userId;
    }

    @RequestMapping(value="/gestionnaire/ajouterDiplome", method = RequestMethod.POST)
    public String ajouterDiplome(DiplomeForm diplomeForm){
        ModelAndView modelAndView = new ModelAndView();
        Diplome diplome = convertDiplomeToEntity(diplomeForm);
        Diplome diplomeSaved = diplomeRepository.save(diplome);
        UserDiplome userDiplome = new UserDiplome();
        UserDiplomeId userDiplomeId = new UserDiplomeId();
        userDiplomeId.setDiplomeId(diplomeSaved.getId());
        userDiplomeId.setUserId(Integer.parseInt(diplomeForm.getUserId()));
        userDiplome.setId(userDiplomeId);
        userDiplomeRepository.save(userDiplome);
        modelAndView.setViewName("admin/liste-comptes.html");
        return "redirect:/gestionnaire/details?id=" + diplomeForm.getUserId();
    }

    @RequestMapping(value="/gestionnaire/supprimerFormation", method = RequestMethod.GET)
    public String deleteFormation(@RequestParam("id") String id, @RequestParam("userId") String userId){
        formationRepository.deleteById(Integer.parseInt(id));
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(Long.parseLong(userId));
        modelAndView.setViewName("admin/liste-comptes.html");
        return "redirect:/gestionnaire/details?id=" + userId;
    }

    @RequestMapping(value="/gestionnaire/supprimerCompetence", method = RequestMethod.GET)
    public String deleteCompetence(@RequestParam("id") String id, @RequestParam("userId") String userId){
        competenceRepository.deleteById(Integer.parseInt(id));
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(Long.parseLong(userId));
        modelAndView.setViewName("admin/liste-comptes.html");
        return "redirect:/gestionnaire/details?id=" + userId;
    }

    @RequestMapping(value="/gestionnaire/ajouterCompetence", method = RequestMethod.POST)
    public String ajouterCompetence(CompetenceForm competenceForm){
        ModelAndView modelAndView = new ModelAndView();
        Competence competence = convertCompetenceToEntity(competenceForm);
        Competence competenceSaved = competenceRepository.save(competence);
        UserCompetence userCompetence = new UserCompetence();
        UserCompetenceId userCompetenceId = new UserCompetenceId();
        userCompetenceId.setCompetenceId(competenceSaved.getId());
        userCompetenceId.setUserId(Integer.parseInt(competenceForm.getUserId()));
        userCompetence.setId(userCompetenceId);
        userCompetenceRepository.save(userCompetence);
        modelAndView.setViewName("admin/liste-comptes.html");
        return "redirect:/gestionnaire/details?id=" + competenceForm.getUserId();
    }

    @RequestMapping(value="/gestionnaire/ajouterFormation", method = RequestMethod.POST)
    public String ajouterFormation(FormationForm formationForm){
        ModelAndView modelAndView = new ModelAndView();
        Formation formation = convertFormationToEntity(formationForm);
        Formation formationSaved = formationRepository.save(formation);
        UserFormation userFormation = new UserFormation();
        UserFormationId userFormationId = new UserFormationId();
        userFormationId.setFormationId(formationSaved.getId());
        userFormationId.setUserId(Integer.parseInt(formationForm.getUserId()));
        userFormation.setId(userFormationId);
        userFormationRepository.save(userFormation);
        modelAndView.setViewName("admin/liste-comptes.html");
        return "redirect:/gestionnaire/details?id=" + formationForm.getUserId();
    }

    @RequestMapping(value="/gestionnaire/ajouterContrat", method = RequestMethod.POST)
    public String ajouterContrat(ContratForm contratForm){
        ModelAndView modelAndView = new ModelAndView();
        Contrat contrat = convertContratToEntity(contratForm);
        Contrat contratSaved = contratRepository.save(contrat);
        UserTypeContrat userTypeContrat = new UserTypeContrat();
        UserTypeContratId userTypeContratId = new UserTypeContratId();
        userTypeContratId.setContratId(contratSaved.getId());
        userTypeContratId.setUserId(Integer.parseInt(contratForm.getUserId()));
        userTypeContrat.setId(userTypeContratId);
        userTypeContratRepository.save(userTypeContrat);
        modelAndView.setViewName("admin/liste-comptes.html");
        return "redirect:/gestionnaire/details?id=" + contratForm.getUserId();
    }

    @RequestMapping(value="/gestionnaire/modifierContrat", method = RequestMethod.POST)
    public String modifierContrat(ContratForm contratForm){
        ModelAndView modelAndView = new ModelAndView();
        Contrat contrat = convertContratToEntity(contratForm);
        Contrat contratSaved = contratRepository.save(contrat);
        modelAndView.setViewName("admin/liste-comptes.html");
        return "redirect:/gestionnaire/details?id=" + contratForm.getUserId();
    }

    @RequestMapping(value="/gestionnaire/details", method = RequestMethod.GET)
    public ModelAndView detail(@RequestParam("id") String id){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(Long.parseLong(id));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelAndView.addObject("user", user);

        CompetenceForm competenceForm = new CompetenceForm();
        competenceForm.setUserId(id);
        DiplomeForm diplomeForm = new DiplomeForm();
        diplomeForm.setUserId(id);
        FormationForm formationForm = new FormationForm();
        formationForm.setUserId(id);
        ContratForm contratForm = new ContratForm();
        contratForm.setUserId(id);
        if(!user.getContrats().isEmpty()){
            Iterator iterator = user.getContrats().iterator();
            while(iterator.hasNext()){
                Contrat contrat = (Contrat) iterator.next();
                contratForm.setType(contrat.getType());
                contratForm.setDateDebut(contrat.getDateDebut());
                contratForm.setDateFin(contrat.getDateFin());
                contratForm.setContratId(Integer.toString(contrat.getId()));
            }
        }
        modelAndView.addObject("contrat", contratForm);
        modelAndView.addObject("competence", competenceForm);
        modelAndView.addObject("formation", formationForm);
        modelAndView.addObject("diplome", diplomeForm);
        modelAndView.addObject("niveaux", niveauRepository.findAll());
        modelAndView.addObject("types", typeContratRepository.findAll());
        modelAndView.setViewName("gestionnaire/details-compte.html");

        return modelAndView;
    }

    @RequestMapping(value="/gestionnaire/ajouterCompte", method = RequestMethod.GET)
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
        modelAndView.setViewName("/gestionnaire/ajouter-compte.html");
        return modelAndView;
    }

    @RequestMapping(value = "/gestionnaire/ajouterCompte", method = RequestMethod.POST)
    public ModelAndView creerNouveauUtilisateur(UserForm user, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        String errors = generateCreationCompteErrors("", user);
        if(!errors.isEmpty()){
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/gestionnaire/ajouterCompte");
        }
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null || !errors.isEmpty()) {
            errors = updateErrors(errors, "Cet utilisateur existe déjà");
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/gestionnaire/ajouterCompte");
        } else {
            userService.saveUser(convertirDeFormVersEntite(user));
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            return new ModelAndView("redirect:/gestionnaire/listerComptes");

        }
    }

    @RequestMapping(value="/gestionnaire/modifierCompte", method = RequestMethod.GET)
    public ModelAndView afficherModificationUser(@RequestParam("id") String id, @ModelAttribute("errors") String errors){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserById(Long.parseLong(id));
        UserForm userForm = convertirDeEntiteVersForm(user);
        String[] sErrors = errors.split(";");
        List<String> listErrors = Arrays.asList(sErrors);
        if(listErrors.size() == 1 && listErrors.get(0).equals("")){
            listErrors = new ArrayList<>();
        }
        modelAndView.addObject("user", userForm);
        modelAndView.addObject("errors", listErrors);
        modelAndView.addObject("roles", getNonAdminRoles());
        modelAndView.addObject("sexes", sexeRepository.findAll());
        modelAndView.setViewName("/gestionnaire/modifier-compte.html");
        return modelAndView;
    }

    @RequestMapping(value = "/gestionnaire/modifierCompte", method = RequestMethod.POST)
    public ModelAndView modifierUtilisateur(@Valid UserForm user, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        String errors = generateCreationCompteErrors("", user);
        if(!errors.isEmpty()){
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/gestionnaire/modifierCompte?id=" + user.getId());
        }
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists == null) {
            errors = updateErrors(errors, "Le compte a été supprimé");
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/gestionnaire/modifierCompte?id=" + user.getId());
        } else {
            user.setId(userExists.getId());
            userService.saveUser(convertirDeFormVersEntite(user));
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            return new ModelAndView("redirect:/gestionnaire/listerComptes");

        }
    }


    @RequestMapping(value="/gestionnaire/modifierMonCompte", method = RequestMethod.GET)
    public ModelAndView afficherModificationMonCompte(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserForm userForm = convertirDeEntiteVersForm(user);
        modelAndView.addObject("user", userForm);
        modelAndView.addObject("sexes", sexeRepository.findAll());
        modelAndView.setViewName("/gestionnaire/modifier-moncompte.html");
        return modelAndView;
    }

    @RequestMapping(value = "/gestionnaire/modifierMonCompte", method = RequestMethod.POST)
    public ModelAndView modifierMonCompte(@Valid UserForm user, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        user.setRole("GESTIONNAIRE");
        String errors = generateCreationCompteErrors("", user);
        if(!errors.isEmpty()){
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/gestionnaire/modifierMonCompte");
        }
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists == null) {
            errors = updateErrors(errors, "Le compte a été supprimé");

            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("userForm", user);
            return new ModelAndView("redirect:/gestionnaire/modifierMonCompte");
        } else {
            user.setId(userExists.getId());
            user.setRole("GESTIONNAIRE");
            userService.saveUser(convertirDeFormVersEntite(user));
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            return new ModelAndView("redirect:/gestionnaire/home");

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
        userForm.setPasswordConfirm("");
        userForm.setPassword("");
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

    private Competence convertCompetenceToEntity(CompetenceForm competenceForm) {
        Competence competence = new Competence();
        competence.setIntitule(competenceForm.getIntitule());
        competence.setNiveau(competenceForm.getNiveau());
        return competence;
    }

    private Formation convertFormationToEntity(FormationForm formationForm){
        Formation formation = new Formation();
        formation.setAnnee(Integer.parseInt(formationForm.getAnnee()));
        formation.setCodeFormation(formationForm.getCodeFormation());
        formation.setOrganisme(formationForm.getOrganisme());
        formation.setIntitule(formationForm.getIntitule());
        return formation;
    }

    private Diplome convertDiplomeToEntity(DiplomeForm diplomeForm){
        Diplome diplome = new Diplome();
        diplome.setAnnee(Integer.parseInt(diplomeForm.getAnnee()));
        diplome.setNom(diplomeForm.getNom());
        diplome.setEcole(diplomeForm.getEcole());
        return diplome;
    }

    private Contrat convertContratToEntity(ContratForm contratForm){
        Contrat contrat = new Contrat();
        contrat.setDateDebut(contratForm.getDateDebut());
        contrat.setDateFin(contratForm.getDateFin());
        contrat.setType(contratForm.getType());
        if(contratForm.getContratId() != null && !contratForm.getContratId().equals("") && Integer.parseInt(contratForm.getContratId()) > 0){
            contrat.setId(Integer.parseInt(contratForm.getContratId()));
        }
        return contrat;
    }

    private CongeForm convertCongeToForm(Conge conge, User user){
        CongeForm congeForm = new CongeForm();
        congeForm.setDateDebut(conge.getDateDebut());
        congeForm.setDateFin(conge.getDateFin());
        congeForm.setType(conge.getType());
        congeForm.setStatut(conge.getStatut());
        congeForm.setUserEmail(user.getEmail());
        congeForm.setId(Long.toString(conge.getId()));

        return congeForm;
    }


}