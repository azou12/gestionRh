package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "user_diplome")
public class UserDiplome {
    @EmbeddedId
    private UserDiplomeId id;

    public UserDiplomeId getId() {
        return id;
    }

    public void setId(UserDiplomeId id) {
        this.id = id;
    }
}