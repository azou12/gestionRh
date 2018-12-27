package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "niveau")
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="niveau_id")
    private int id;
    @Column(name="intitule")
    private String intitule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
}