package com.cnam.acsid.app.gestionrh.service;

import com.cnam.acsid.app.gestionrh.model.Conge;
import com.cnam.acsid.app.gestionrh.model.User;

import java.util.List;

public interface CongeService {
    public User findUserLieAuConge(long id);
}