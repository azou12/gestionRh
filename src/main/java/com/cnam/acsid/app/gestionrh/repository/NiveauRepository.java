package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.Formation;
import com.cnam.acsid.app.gestionrh.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("niveauRepository")
public interface NiveauRepository extends JpaRepository<Niveau, Integer>{
    List<Niveau> findAll();
}