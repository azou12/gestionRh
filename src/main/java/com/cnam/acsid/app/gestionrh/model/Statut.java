package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "statut")
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="statut_id")
    private int id;
    @Column(name="statut")
    private String statut;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}