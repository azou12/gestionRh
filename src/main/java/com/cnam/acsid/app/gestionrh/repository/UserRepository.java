package com.cnam.acsid.app.gestionrh.repository;

import com.cnam.acsid.app.gestionrh.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAll();

    @Override
    List<User> findAllById(Iterable<Long> iterable);

    @Override
    void deleteById(Long aLong);

}