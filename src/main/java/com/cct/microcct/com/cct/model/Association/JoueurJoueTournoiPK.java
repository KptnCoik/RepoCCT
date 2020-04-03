package com.cct.microcct.com.cct.model.Association;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class JoueurJoueTournoiPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id_joueur")
    private int idJoueur;

    @Column(name = "id_tournoi")
    private int idTournoi;

    @Column(name = "points")
    private float points;

    public JoueurJoueTournoiPK(int idJoueur, int idTournoi) {
        this.idJoueur = idJoueur;
        this.idTournoi = idTournoi;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public JoueurJoueTournoiPK() {
        super();
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
