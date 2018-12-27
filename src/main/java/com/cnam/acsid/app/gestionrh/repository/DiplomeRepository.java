package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.Diplome;
import com.cnam.acsid.app.gestionrh.model.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("diplomeRepository")
public interface DiplomeRepository extends JpaRepository<Diplome, Integer>{
    Diplome findByNom(String diplome);

    Optional<Diplome> findById(Integer integer);

    List<Diplome> findAll();

    @Override
    void deleteById(Integer integer);
}