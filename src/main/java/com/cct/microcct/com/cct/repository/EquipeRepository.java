package com.cct.microcct.com.cct.repository;

import com.cct.microcct.com.cct.model.Entité.Equipe;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe,Integer> {

    @Query("select j from Joueur j inner join FaitEquipe fe on j.id = fe.joueur " +
            "where fe.equipe = :idEquipe")
    List<Joueur> findJoueursByEquipe(Equipe idEquipe);
}
