package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "sexe")
public class Sexe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sexe_id")
    private int id;
    @Column(name="sexe")
    private String sexe;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


}