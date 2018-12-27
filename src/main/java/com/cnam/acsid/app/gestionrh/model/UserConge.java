package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_conge")
public class UserConge {
    @EmbeddedId
    private UserCongeId id;

    public UserCongeId getId() {
        return id;
    }

    public void setId(UserCongeId id) {
        this.id = id;
    }
}