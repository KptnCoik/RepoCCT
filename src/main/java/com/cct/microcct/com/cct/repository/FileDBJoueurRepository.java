package com.cct.microcct.com.cct.repository;

import com.cct.microcct.com.cct.model.Entité.FileDBJoueur;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface FileDBJoueurRepository extends JpaRepository<FileDBJoueur,Integer> {

    @Modifying
    @Query(value="insert into FileDBJoueur (id_image,id_joueur) VALUES (:idImage,:idJoueur)",nativeQuery = true)
    void insertImage(int idImage, int idJoueur);

    @Query("select f from FileDBJoueur f where f.joueur = :idJoueur")
    FileDBJoueur selectImageByJoueur(Joueur idJoueur);
}
