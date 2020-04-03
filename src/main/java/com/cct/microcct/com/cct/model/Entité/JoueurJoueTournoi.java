package com.cct.microcct.com.cct.model.Entité;

import com.cct.microcct.com.cct.model.Association.JoueurJoueTournoiPK;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "JoueurJoueTournoi", schema = "public")
public class JoueurJoueTournoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private JoueurJoueTournoiPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_joueur", insertable = false, updatable = false)
    private Joueur joueur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tournoi", insertable = false, updatable = false)
    private Tournoi tournoi;

    public JoueurJoueTournoi() {
    }

    public JoueurJoueTournoi(Joueur joueur) {
        this.joueur = joueur;
    }

    public JoueurJoueTournoiPK getId() {
        return id;
    }

    public void setId(JoueurJoueTournoiPK id) {
        this.id = id;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }


}
