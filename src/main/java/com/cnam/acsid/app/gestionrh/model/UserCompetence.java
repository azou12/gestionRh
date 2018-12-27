package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "user_competence")
public class UserCompetence {
    @EmbeddedId
    private UserCompetenceId id;

    public UserCompetenceId getId() {
        return id;
    }

    public void setId(UserCompetenceId id) {
        this.id = id;
    }
}