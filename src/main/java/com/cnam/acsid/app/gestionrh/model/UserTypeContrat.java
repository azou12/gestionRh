package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_contrat")
public class UserTypeContrat {
    @EmbeddedId
    private UserTypeContratId id;

    public UserTypeContratId getId() {
        return id;
    }

    public void setId(UserTypeContratId id) {
        this.id = id;
    }
}