package com.cct.microcct.com.cct.model.Entité;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "Badge" , schema = "public")
public class Badge implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_badge")
    private int id;

    @Column(name = "Titre")
    private String titre;

    @Column(name="Detail")
    private String detail;

    @Column(name="Icone")
    private String icone;

    public Badge() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
