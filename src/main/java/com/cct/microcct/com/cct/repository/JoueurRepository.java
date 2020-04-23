package com.cct.microcct.com.cct.repository;

import com.cct.microcct.com.cct.model.Association.JoueurJoueTournoiPK;
import com.cct.microcct.com.cct.model.Entité.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoueurRepository extends JpaRepository<Joueur,Integer> {

    public Optional<Joueur> findByNom(String nom);


    public List<Joueur> findAll();

    @Query("select j from Joueur j where j.pseudo = :login and j.password = :password")
    Joueur findJoueurByLoginPassword(String login, String password);

    @Query("select jjt from JoueurJoueTournoi jjt where jjt.joueur = :idJoueur and jjt.tournoi = :idTournoi")
    JoueurJoueTournoi findPointsJoueurByTournoi(Joueur idJoueur, Tournoi idTournoi);

    @Query("select ej from EpreuveJoue ej inner join EpreuveTournoi et on ej.epreuve = et.epreuve where " +
            "ej.joueur = :idJoueur and ej.epreuve = :idEpreuve")
    EpreuveJoue findPointsJoueurByEpreuve(Joueur idJoueur, Epreuve idEpreuve);

    @Query("select eje from EpreuveJoueEquipe eje inner join FaitEquipe fe on eje.equipe = fe.equipe where " +
            "fe.joueur = :idJoueur and eje.epreuve = :idEpreuve")
    EpreuveJoueEquipe findPointsJoueurByEpreuveEquipe(Joueur idJoueur, Epreuve idEpreuve);

    @Query("select j from Joueur j inner join FaitEquipe fe on j.id = fe.joueur inner join EpreuveJoueEquipe eje on fe.equipe = eje.equipe " +
            "where fe.equipe in (select f.equipe from FaitEquipe f where f.joueur = :idJoueur) and fe.joueur not in :idJoueur " +
            "and eje.epreuve = :idEpreuve")
    List<Joueur> findEquipeJoueurByEpreuve(Joueur idJoueur, Epreuve idEpreuve);

    @Query("select e from Epreuve e inner join EpreuveJoue ej on e.id = ej.epreuve inner join EpreuveTournoi et on ej.epreuve = et.epreuve " +
            "where ej.joueur = :idJoueur and malus = true and et.tournoi = :idTournoi")
    Epreuve findMalusJoueur(Joueur idJoueur, Tournoi idTournoi);

    @Query("select e from Epreuve e inner join EpreuveJoue ej on e.id = ej.epreuve inner join EpreuveTournoi et on ej.epreuve = et.epreuve " +
            "where ej.joueur = :idJoueur and bonus = true and et.tournoi = :idTournoi")
    Epreuve findBonusJoueur(Joueur idJoueur, Tournoi idTournoi);

    @Query("select e from Epreuve e inner join EpreuveJoueEquipe eje on e.id = eje.epreuve inner join EpreuveTournoi et on eje.epreuve = et.epreuve " +
            "inner join FaitEquipe fe on eje.equipe = fe.equipe where fe.joueur = :idJoueur and bonus = true and et.tournoi = :idTournoi")
    Epreuve findBonusEquipeJoueur(Joueur idJoueur, Tournoi idTournoi);

    @Query("select t from Tournoi t inner join JoueurJoueTournoi jjt on t.id = jjt.tournoi " +
            "where jjt.joueur = :idJoueur order by t.id" )
    List<Tournoi> findTournoisByJoueur(Joueur idJoueur);

}


