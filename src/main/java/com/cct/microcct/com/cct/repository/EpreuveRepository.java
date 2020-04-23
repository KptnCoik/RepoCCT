package com.cct.microcct.com.cct.repository;

import com.cct.microcct.com.cct.model.Entité.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve,Integer> {

    public Optional<Epreuve> findByNom(String nom);

    @Query("select j from Joueur j inner join EpreuveJoue ej on j.id = ej.joueur " +
            "where ej.epreuve = :idEpreuve ORDER BY points DESC")
    List<Joueur> findJoueursByEpreuveIndividuel(Epreuve idEpreuve);

    @Query("select j from Joueur j inner join EpreuveJoue ej on j.id = ej.joueur " +
            "where ej.epreuve = :idEpreuve ORDER BY points ASC")
    List<Joueur> findJoueursByEpreuveIndividuelOrdreInverse(Epreuve idEpreuve);

    @Query("select j from Joueur j inner join FaitEquipe fe on j.id = fe.joueur inner join EpreuveJoueEquipe eje on fe.equipe = eje.equipe " +
            "where eje.epreuve = :idEpreuve  ORDER BY points DESC")
    List<Joueur> findJoueursByEpreuveEquipe(Epreuve idEpreuve);

    @Query("select j from Joueur j inner join TropheeGagne tg on j.id = tg.joueur where tg.epreuve = :idEpreuve")
    List<Joueur> findGagantByEpreuve(Epreuve idEpreuve);

    @Query("select e from Equipe e inner join FaitEquipe fe on e.id = fe.equipe inner join EpreuveJoueEquipe eje on fe.equipe = eje.equipe " +
            "where eje.epreuve = :idEpreuve ORDER BY points DESC")
    List<Equipe> findOrdeEquipeByEpreuve(Epreuve idEpreuve);

    @Query("select e from Equipe e inner join FaitEquipe fe on e.id = fe.equipe inner join EpreuveJoueEquipe eje on fe.equipe = eje.equipe " +
            "where eje.epreuve = :idEpreuve ORDER BY points ASC")
    List<Equipe> findOrdeInverseEquipeByEpreuve(Epreuve idEpreuve);

    @Query("select j from Joueur j inner join EpreuveJoue ej on j.id = ej.joueur " +
            "where ej.epreuve = :idEpreuve and malus = true")
    List<Joueur> findJoueurMalusByEpreuve(Epreuve idEpreuve);

    @Query("select j from Joueur j inner join EpreuveJoue ej on j.id = ej.joueur " +
            "where ej.epreuve = :idEpreuve and bonus = true")
    List<Joueur> findJoueurBonusByEpreuveIndividuelle(Epreuve idEpreuve);

    @Query("select j from Joueur j inner join FaitEquipe fe on j.id = fe.joueur inner join EpreuveJoueEquipe eje on fe.equipe = eje.equipe " +
            "where eje.epreuve = :idEpreuve and bonus = true")
    List<Joueur> findJoueurBonusByEpreuveEquipe(Epreuve idEpreuve);

    @Query("select fe from FaitEquipe fe inner join EpreuveJoueEquipe eje on fe.equipe = eje.equipe " +
            "where fe.joueur = :idJoueur and eje.epreuve = :idEpreuve")
    FaitEquipe isBonusEquipe(Epreuve idEpreuve, Joueur idJoueur);

    @Query("select ej from EpreuveJoue ej where ej.epreuve = :idEpreuve and ej.joueur = :idJoueur")
    EpreuveJoue isBonus(Epreuve idEpreuve, Joueur idJoueur);

    @Query("select eje from EpreuveJoueEquipe eje where eje.epreuve = :idEpreuve")
    List<EpreuveJoueEquipe> getPoints(Epreuve idEpreuve);

    @Query("select t from Tournoi t inner join EpreuveTournoi et on t.id = et.tournoi where et.epreuve = :idEpreuve")
    Tournoi findTournoiByEpreuve(Epreuve idEpreuve);
}
