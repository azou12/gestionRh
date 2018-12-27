package com.cnam.acsid.app.gestionrh.service;

import com.cnam.acsid.app.gestionrh.model.Role;
import com.cnam.acsid.app.gestionrh.model.User;

import java.util.List;

public interface RoleService {
    public Role findRoleByRole(String role);
    public List<Role> findAll();

}