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

    public Trophee() {
    }

    public int getId() {
        return id;
    }
}
