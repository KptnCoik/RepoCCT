package com.cct.microcct.com.cct.repository;

import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;
import com.cct.microcct.com.cct.model.Entité.Trophee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TropheeRepository extends JpaRepository<Trophee,Integer> {

    @Query("select e from Epreuve e inner join TropheeGagne tg on e.id = tg.epreuve where tg.trophee = :idTrophee")
    Epreuve findEpreuveByTrophee(Trophee idTrophee);

    @Query("select j from Joueur j inner join TropheeGagne tg on j.id = tg.joueur where tg.trophee = :idTrophee")
    List<Joueur> findJoueurByTrophee(Trophee idTrophee);

    @Query("select t from Trophee t inner join TropheeGagne tg on t.id = tg.trophee where tg.joueur = :idJoueur and tg.tournoi = :idTournoi")
    List<Trophee> findTropheeByTournoiByJoueur(Tournoi idTournoi, Joueur idJoueur);

    @Query("select t from Trophee t inner join TropheeGagne tg on t.id = tg.trophee where tg.joueur = :idJoueur")
    List<Trophee> findTropheeByJoueur(Joueur idJoueur);

    @Query("select t from Trophee t inner join TropheeGagne tg on t.id = tg.trophee where tg.epreuve = :idEpreuve")
    Trophee findTropheeByEpreuve (Epreuve idEpreuve);
}
