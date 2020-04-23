package com.cct.microcct.com.cct.repository;

import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournoiRepository extends JpaRepository<Tournoi, Integer> {

    public List<Tournoi> findAll();

    public Optional<Tournoi> findByAnnee(String annee);

    @Query("select e from Epreuve e inner join EpreuveTournoi et on e.id = et.epreuve " +
            "where et.tournoi = :idTournoi")
    List<Epreuve> findEpreuvesByTournoi(Tournoi idTournoi);

    @Query("select j from Joueur j inner join JoueurJoueTournoi jjt on j.id = jjt.joueur " +
            "where jjt.tournoi = :idTournoi ORDER BY points DESC")
    List<Joueur> findJoueurOrdeByTournoi(Tournoi idTournoi);

    @Query("select j from Joueur j inner join JoueurJoueTournoi jjt on j.id = jjt.joueur " +
                  "where jjt.tournoi = :idTournoi ORDER BY points ASC")
    List<Joueur> findJoueurOrdeInverseByTournoi(Tournoi idTournoi);

    @Query("select count(*) from JoueurJoueTournoi where tournoi = :idTournoi")
    int findNombreJoueurTournoi(Tournoi idTournoi);

}
