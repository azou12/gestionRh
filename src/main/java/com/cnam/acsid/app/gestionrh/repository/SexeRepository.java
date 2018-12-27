package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.Role;
import com.cnam.acsid.app.gestionrh.model.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("sexeRepository")
public interface SexeRepository extends JpaRepository<Sexe, Integer>{
    Sexe findBySexe(String sexe);
    List<Sexe> findAll();

}