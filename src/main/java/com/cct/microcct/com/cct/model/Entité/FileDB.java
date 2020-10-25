package com.cct.microcct.com.cct.model.Entité;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="FileDB", schema="public")
public class FileDB implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="id_image")
    private int id;

    @Lob
    private byte[] data;

    private String type;

    private String name;

    public FileDB(){}

    public FileDB(String type, byte[] data, String name) {
        this.type = type;
        this.data = data;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
