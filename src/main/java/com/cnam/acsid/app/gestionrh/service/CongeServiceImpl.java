package com.cnam.acsid.app.gestionrh.service;

import com.cnam.acsid.app.gestionrh.model.Conge;
import com.cnam.acsid.app.gestionrh.model.Role;
import com.cnam.acsid.app.gestionrh.model.User;
import com.cnam.acsid.app.gestionrh.model.UserConge;
import com.cnam.acsid.app.gestionrh.repository.RoleRepository;
import com.cnam.acsid.app.gestionrh.repository.UserCongeRepository;
import com.cnam.acsid.app.gestionrh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("congeService")
public class CongeServiceImpl implements CongeService {

    @Autowired
    private UserCongeRepository userCongeRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserLieAuConge(long id) {
        List<UserConge> conges = userCongeRepository.findAll();
        User user = new User();
        for (UserConge userConge : conges) {
            if (userConge.getId().getCongeId() == id) {
                try {
                    if (userRepository.findById((long) userConge.getId().getUserId()) != null && userRepository.findById((long) userConge.getId().getUserId()).get() != null) {
                        user = userRepository.findById((long) userConge.getId().getUserId()).get();
                        break;
                    }
                }catch (NoSuchElementException e){

                }
            }
        }
        return user;
    }


}