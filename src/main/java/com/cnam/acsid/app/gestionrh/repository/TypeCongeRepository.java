package com.cnam.acsid.app.gestionrh.repository;


import com.cnam.acsid.app.gestionrh.model.TypeConge;
import com.cnam.acsid.app.gestionrh.model.TypeContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("typecongeRepository")
public interface TypeCongeRepository extends JpaRepository<TypeConge, Integer>{
}