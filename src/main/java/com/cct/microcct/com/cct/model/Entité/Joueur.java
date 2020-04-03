package com.cct.microcct.com.cct.model.Entité;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Joueur", schema = "public")
public class Joueur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_joueur")
    private int id;

    @Column(name = "Nom")
    private String nom;

    public Joueur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Joueur() {
    }

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


}
