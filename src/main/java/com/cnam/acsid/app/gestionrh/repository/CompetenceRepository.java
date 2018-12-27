package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.Competence;
import com.cnam.acsid.app.gestionrh.model.Diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("competenceRepository")
public interface CompetenceRepository extends JpaRepository<Competence, Integer>{
    Competence findByIntitule(String intitule);

    Optional<Competence> findById(Integer integer);

    List<Competence> findAll();

    @Override
    void deleteById(Integer integer);
}