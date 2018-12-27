package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.UserConge;
import com.cnam.acsid.app.gestionrh.model.UserFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userCongeRepository")
public interface UserCongeRepository extends JpaRepository<UserConge, Integer>{

}