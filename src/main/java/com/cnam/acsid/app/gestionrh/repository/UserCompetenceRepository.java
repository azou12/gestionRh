package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.Competence;
import com.cnam.acsid.app.gestionrh.model.UserCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("userCompetenceRepository")
public interface UserCompetenceRepository extends JpaRepository<UserCompetence, Integer>{

}