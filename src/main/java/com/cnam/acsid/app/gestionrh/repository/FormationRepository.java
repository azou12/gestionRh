package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.Competence;
import com.cnam.acsid.app.gestionrh.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("formationRepository")
public interface FormationRepository extends JpaRepository<Formation, Integer>{
    Formation findByIntitule(String intitule);

    Optional<Formation> findById(Integer integer);

    List<Formation> findAll();

    @Override
    void deleteById(Integer integer);
}