package com.cnam.acsid.app.gestionrh.service;

import com.cnam.acsid.app.gestionrh.model.Role;
import com.cnam.acsid.app.gestionrh.model.User;
import com.cnam.acsid.app.gestionrh.repository.RoleRepository;
import com.cnam.acsid.app.gestionrh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("userService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleByRole(String role){
        return roleRepository.findByRole(role);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }


}