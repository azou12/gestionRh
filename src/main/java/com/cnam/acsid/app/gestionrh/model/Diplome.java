package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "diplome")
public class Diplome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="diplome_id")
    private int id;
    @Column(name="nom")
    private String nom;
    @Column(name="annee")
    private int annee;
    @Column(name="ecole")
    private String ecole;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }
}