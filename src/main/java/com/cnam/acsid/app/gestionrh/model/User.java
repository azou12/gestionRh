package com.cnam.acsid.app.gestionrh.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private long id;
    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @Transient
    private String password;
    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;
    @Column(name =  "tel")
    private String tel;
    @Column(name =  "ville")
    private String ville;
    @Column(name =  "codePostal")
    private String codePostal;
    @Column(name =  "adresse")
    private String adresse;
    @Column(name =  "dateNaissance")
    private String dateNaissance;
    @Column(name = "active")
    private int active;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_sexe", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "sexe_id"))
    private Set<Sexe> sexes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_diplome", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "diplome_id"))
    private Set<Diplome> diplomes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_competence", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "competence_id"))
    private Set<Competence> competences;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_formation", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "formation_id"))
    private Set<Formation> formations;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_contrat", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "contrat_id"))
    private Set<Contrat> contrats;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_conge", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "conge_id"))
    private Set<Conge> conges;

    public Set<Conge> getConges() {
        return conges;
    }

    public void setConges(Set<Conge> conges) {
        this.conges = conges;
    }

    public Set<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(Set<Contrat> contrats) {
        this.contrats = contrats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Sexe> getSexes() {
        return sexes;
    }

    public void setSexes(Set<Sexe> sexes) {
        this.sexes = sexes;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Set<Diplome> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(Set<Diplome> diplomes) {
        this.diplomes = diplomes;
    }

    public Set<Competence> getCompetences() {
        return competences;
    }

    public void setCompetences(Set<Competence> competences) {
        this.competences = competences;
    }

    public Set<Formation> getFormations() {
        return formations;
    }

    public void setFormations(Set<Formation> formations) {
        this.formations = formations;
    }
}