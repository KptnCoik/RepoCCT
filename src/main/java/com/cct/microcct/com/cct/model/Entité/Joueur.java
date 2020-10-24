package com.cct.microcct.com.cct.model.Entité;

import com.cct.microcct.com.cct.security.BCryptManagerUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "Joueur", schema = "public")
public class Joueur implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id_joueur")
    private int id;

    @Column(name = "Nom")
    private String username;

    @Column(name="Pseudo")
    private String pseudo;

    @Column(name="MotDePasse")
    private String password;

    public Joueur(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = BCryptManagerUtil.passwordencoder().encode(password);
    }

    public Joueur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPseudo() {return pseudo;}

    public void setPseudo(String pseudo) {this.pseudo = pseudo;}

    public String getPassword() {return password;}

    public void setPassword(String password) {
        if (!password.isEmpty()) {
            this.password = BCryptManagerUtil.passwordencoder().encode(password);
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    @Override
    public boolean isEnabled() {
        return false;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
