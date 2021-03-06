package com.cct.microcct.com.cct.model.Association;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EpreuveTournoiPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id_tournoi")
    private int idTournoi;

    @Column(name = "id_epreuve")
    private int idEpreuve;

    public EpreuveTournoiPK() {
    }

    public int getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }

    public int getIdEpreuve() {
        return idEpreuve;
    }

    public void setIdEpreuve(int idEpreuve) {
        this.idEpreuve = idEpreuve;
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
