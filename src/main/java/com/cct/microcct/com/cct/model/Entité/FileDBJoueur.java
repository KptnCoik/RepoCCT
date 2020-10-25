package com.cct.microcct.com.cct.model.Entité;

import com.cct.microcct.com.cct.model.Association.FileDBJoueurPK;

import javax.persistence.*;
import java.io.Serializable;

@Entity@Table(name="FileDBJoueur", schema="public")
public class FileDBJoueur implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FileDBJoueurPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_joueur", insertable = false, updatable = false)
    private Joueur joueur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_image", insertable = false, updatable = false)
    private FileDB fileDB;

    public FileDBJoueur() {
    }

    public FileDBJoueurPK getId() {
        return id;
    }

    public void setId(FileDBJoueurPK id) {
        this.id = id;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public FileDB getFileDB() {
        return fileDB;
    }

    public void setFileDB(FileDB fileDB) {
        this.fileDB = fileDB;
    }
}
