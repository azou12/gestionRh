package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.UserCompetence;
import com.cnam.acsid.app.gestionrh.model.UserDiplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userDiplomeRepository")
public interface UserDiplomeRepository extends JpaRepository<UserDiplome, Integer>{

}