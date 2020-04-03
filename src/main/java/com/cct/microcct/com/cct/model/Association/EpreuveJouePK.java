package com.cct.microcct.com.cct.model.Association;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EpreuveJouePK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id_epreuve")
    private int idEpreuve;

    @Column(name = "id_joueur")
    private int idJoueur;

    @Column(name = "Points")
    private float points;

    @Column(name = "Bonus")
    private boolean bonus;

    @Column(name = "Malus")
    private boolean malus;

    public EpreuveJouePK() {
    }

    public int getIdEpreuve() {
        return idEpreuve;
    }

    public void setIdEpreuve(int idEpreuve) {
        this.idEpreuve = idEpreuve;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    public boolean isMalus() {
        return malus;
    }

    public void setMalus(boolean malus) {
        this.malus = malus;
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
