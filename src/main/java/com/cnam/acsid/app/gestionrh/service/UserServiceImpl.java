package com.cnam.acsid.app.gestionrh.service;

import java.util.*;

import com.cnam.acsid.app.gestionrh.model.Role;
import com.cnam.acsid.app.gestionrh.model.User;
import com.cnam.acsid.app.gestionrh.repository.RoleRepository;
import com.cnam.acsid.app.gestionrh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service("roleService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        userRepository.save(user);
    }

    /**
     * Retourne tous les comptes actifs
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        List<User> listeUsers = userRepository.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<User> listeUsersActif =  new ArrayList<>();
        for(User user: listeUsers){
            if(user.getActive() == 1){
                listeUsersActif.add(user);
            }
        }
        return listeUsersActif;
    }


    @Override
    public void deleteById(Long id) {
        List<Long> idsLong = new ArrayList<>();
        idsLong.add(id);
        List<User> users = userRepository.findAllById(idsLong);
        for(User user: users){
            user.setActive(0);
            userRepository.save(user);
        }
    }

    /**
     * Retourne tous les comptes actifs sauf les comptes admin
     * @return
     */
    @Override
    public List<User> getComptesNonAmin() {
        List<User> listeUsers = userRepository.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<User> users =  new ArrayList<>();
        for(User user: listeUsers){
            Set<Role> userRoles = user.getRoles();
            boolean isAdmin = false;
            for(Role role: userRoles){
                if(role.getRole().equals("ADMIN")){
                    isAdmin = true;
                }
            }
            if(user.getActive() == 1 && !isAdmin){
                users.add(user);
            }
        }
        return users;
    }

    public User findUserById(long id) {
        Optional<User> result =  userRepository.findById(id);
        return result.get();
    }


}