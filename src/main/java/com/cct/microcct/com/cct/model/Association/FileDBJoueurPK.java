package com.cct.microcct.com.cct.model.Association;

import javax.persistence.Column;
import java.io.Serializable;

public class FileDBJoueurPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name="id_joueur")
    private int id_joueur;

    @Column(name="id_image")
    private int id_image;

    public FileDBJoueurPK() {}

    public FileDBJoueurPK(int id_joueur, int id_image) {
        this.id_joueur = id_joueur;
        this.id_image = id_image;
    }

    public int getId_joueur() {
        return id_joueur;
    }

    public void setId_joueur(int id_joueur) {
        this.id_joueur = id_joueur;
    }

    public int getId_image() {
        return id_image;
    }

    public void setId_image(int id_image) {
        this.id_image = id_image;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
