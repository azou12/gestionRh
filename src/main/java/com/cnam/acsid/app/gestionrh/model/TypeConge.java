package com.cnam.acsid.app.gestionrh.model;


import javax.persistence.*;

@Entity
@Table(name = "typeconge")
public class TypeConge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="type_id")
    private int id;
    @Column(name="type")
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}