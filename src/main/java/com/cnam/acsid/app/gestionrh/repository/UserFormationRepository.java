package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.UserDiplome;
import com.cnam.acsid.app.gestionrh.model.UserFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userFormationRepository")
public interface UserFormationRepository extends JpaRepository<UserFormation, Integer>{

}