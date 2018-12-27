package com.cnam.acsid.app.gestionrh.repository;

import com.cnam.acsid.app.gestionrh.model.Conge;
import com.cnam.acsid.app.gestionrh.model.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("statutRepository")
public interface StatutRepository extends JpaRepository<Statut, Long> {
    List<Statut> findAll();
}