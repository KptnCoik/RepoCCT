package com.cct.microcct.com.cct.model.Entité;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Trophee", schema = "public")
public class Trophee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_trophee")
    private int id;

    @Column(name="icone")
    private String icone;

    @Column(name="detail")
    private String detail;

    public Trophee() {
    }

    public int getId() {return id;}

    public String getIcone() {return icone;}

    public void setIcone(String icone) {this.icone = icone;}

    public String getDetail() {return detail;}

    public void setDetail(String detail) {this.detail = detail;}
}
