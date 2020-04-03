package com.cct.microcct.com.cct.model.Entité;

import com.cct.microcct.com.cct.model.Association.TropheeGagneePK;
import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;
import com.cct.microcct.com.cct.model.Entité.Trophee;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Trophee_Gagnee", schema = "public")
public class TropheeGagne implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "id_joueur", column = @Column(name = "id_joueur")),
            @AttributeOverride(name = "id_trophee", column = @Column(name = "id_trophee"))
    })
    private TropheeGagneePK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trophee", insertable = false, updatable = false)
    private Trophee trophee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_joueur", insertable = false, updatable = false)
    private Joueur joueur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tournoi", insertable = false, updatable = false)
    private Tournoi tournoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_epreuve", insertable = false, updatable = false)
    private Epreuve epreuve;

    public TropheeGagne() {
    }

    public TropheeGagneePK getId() {
        return id;
    }

    public void setId(TropheeGagneePK id) {
        this.id = id;
    }

    public Trophee getTrophee() {
        return trophee;
    }

    public void setTrophee(Trophee trophee) {
        this.trophee = trophee;
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

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }
}
