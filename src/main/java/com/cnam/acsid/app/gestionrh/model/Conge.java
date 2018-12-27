package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "conge")
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="conge_id")
    private long id;
    @Column(name="type")
    private String type;
    @Column(name="dateDebut")
    private String dateDebut;
    @Column(name="dateFin")
    private String dateFin;
    @Column(name="statut")
    private String statut;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

}