package com.cnam.acsid.app.gestionrh.repository;

import com.cnam.acsid.app.gestionrh.model.Conge;
import com.cnam.acsid.app.gestionrh.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("congeRepository")
public interface CongeRepository extends JpaRepository<Conge, Long> {
    List<Conge> findAll();
}