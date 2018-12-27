package com.cnam.acsid.app.gestionrh.service;

import com.cnam.acsid.app.gestionrh.model.User;

import java.util.List;

public interface UserService {
    public User findUserById(long id);
    public User findUserByEmail(String email);
    public void saveUser(User user);
    public List<User> findAll();
    public void deleteById(Long id);
    public List<User> getComptesNonAmin();
}