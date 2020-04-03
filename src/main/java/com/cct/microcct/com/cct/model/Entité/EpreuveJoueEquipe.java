package com.cct.microcct.com.cct.model.Entité;

import com.cct.microcct.com.cct.model.Association.EpreuveJoueEquipePK;
import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Equipe;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EpreuveJoueEquipe", schema = "public")
public class EpreuveJoueEquipe implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EpreuveJoueEquipePK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_epreuve", insertable = false, updatable = false)
    private Epreuve epreuve;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipe", insertable = false, updatable = false)
    private Equipe equipe;

    public EpreuveJoueEquipe() {
    }

    public EpreuveJoueEquipePK getId() {
        return id;
    }

    public void setId(EpreuveJoueEquipePK id) {
        this.id = id;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
