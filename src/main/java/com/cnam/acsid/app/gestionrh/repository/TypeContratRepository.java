package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.Niveau;
import com.cnam.acsid.app.gestionrh.model.TypeContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("typecontratRepository")
public interface TypeContratRepository extends JpaRepository<TypeContrat, Integer>{
    List<TypeContrat> findAll();
}