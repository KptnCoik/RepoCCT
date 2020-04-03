package com.cct.microcct.com.cct.model.Entité;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Tournoi", schema = "public")
public class Tournoi implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_tournoi")
    private int id;

    @Column(name = "annee")
    private String annee;

    public Tournoi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
}
