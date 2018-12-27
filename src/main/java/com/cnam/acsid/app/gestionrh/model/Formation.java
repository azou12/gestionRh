package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "formation")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="formation_id")
    private int id;
    @Column(name="codeFormation")
    private String codeFormation;
    @Column(name="intitule")
    private String intitule;
    @Column(name="annee")
    private int annee;
    @Column(name="organisme")
    private String organisme;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeFormation() {
        return codeFormation;
    }

    public void setCodeFormation(String codeFormation) {
        this.codeFormation = codeFormation;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }
}