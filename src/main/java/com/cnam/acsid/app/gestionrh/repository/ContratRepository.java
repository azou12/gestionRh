package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.Contrat;
import com.cnam.acsid.app.gestionrh.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("contratRepository")
public interface ContratRepository extends JpaRepository<Contrat, Integer>{
    List<Contrat> findAll();

}