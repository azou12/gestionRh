package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.UserFormation;
import com.cnam.acsid.app.gestionrh.model.UserTypeContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userTypecontratRepository")
public interface UserTypeContratRepository extends JpaRepository<UserTypeContrat, Integer>{

}