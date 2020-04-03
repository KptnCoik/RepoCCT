package com.cct.microcct.com.cct.model.Entité;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Epreuve", schema = "public")
public class Epreuve implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_epreuve")
    private int id;

    @Column
    private String nom;

    @Column
    private String type;

    public Epreuve() {
    }

    public Epreuve(int id, String nom, String type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}
