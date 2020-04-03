package com.cct.microcct.com.cct.model.Entité;

import com.cct.microcct.com.cct.model.Association.EpreuveJouePK;
import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Joueur;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EpreuveJoue", schema = "public")
public class EpreuveJoue implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EpreuveJouePK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_epreuve", insertable = false, updatable = false)
    private Epreuve epreuve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_joueur", insertable = false, updatable = false)
    private Joueur joueur;


    public EpreuveJoue() {
    }

    public EpreuveJouePK getId() {
        return id;
    }

    public void setId(EpreuveJouePK id) {
        this.id = id;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }


}
