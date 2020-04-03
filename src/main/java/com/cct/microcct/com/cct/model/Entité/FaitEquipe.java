package com.cct.microcct.com.cct.model.Entité;

import com.cct.microcct.com.cct.model.Association.FaitEquipePK;
import com.cct.microcct.com.cct.model.Entité.Equipe;
import com.cct.microcct.com.cct.model.Entité.Joueur;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FaitEquipe", schema = "public")
public class FaitEquipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FaitEquipePK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_joueur", insertable = false, updatable = false)
    private Joueur joueur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipe", insertable = false, updatable = false)
    private Equipe equipe;

    public FaitEquipe() {
    }

    public FaitEquipePK getId() {
        return id;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
