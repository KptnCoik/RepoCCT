package com.cct.microcct.com.cct.model.Entité;

import com.cct.microcct.com.cct.model.Association.EpreuveTournoiPK;
import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Tournoi;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EpreuveTournoi", schema = "public")
public class EpreuveTournoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EpreuveTournoiPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tournoi", insertable = false, updatable = false)
    private Tournoi tournoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_epreuve", insertable = false, updatable = false)
    private Epreuve epreuve;

    public EpreuveTournoi() {
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
