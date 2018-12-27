package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "user_formation")
public class UserFormation {
    @EmbeddedId
    private UserFormationId id;

    public UserFormationId getId() {
        return id;
    }

    public void setId(UserFormationId id) {
        this.id = id;
    }
}