package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import com.cct.microcct.com.cct.repository.TournoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TournoiController {

    @Autowired
    TournoiRepository tournoiRepository;

    @Autowired
    JoueurRepository joueurRepository;

    @RequestMapping(value = "/Tournoi" , method = RequestMethod.GET)
    public List<Tournoi> findAll() {
        return tournoiRepository.findAll();
    }

    @RequestMapping(value = "/Tournoi/{idTournoi}" , method = RequestMethod.GET)
    public Optional<Tournoi> findById(@PathVariable int idTournoi) {
        return tournoiRepository.findById(idTournoi);
    }

    @RequestMapping(value = "/Tournoi/Epreuves/{idTournoi}" , method = RequestMethod.GET)
    public List<Epreuve> findEpreuvesByTournoi(@PathVariable int idTournoi) {
        return tournoiRepository.findEpreuvesByTournoi(tournoiRepository.getOne(idTournoi));
    }

    @RequestMapping(value = "/Tournoi/Gagnant/{idTournoi}" , method = RequestMethod.GET)
    public Joueur findGagantByTournoi(@PathVariable int idTournoi) {
        List<Joueur> joueurs = tournoiRepository.findJoueurOrdeByTournoi(tournoiRepository.getOne(idTournoi));
        return joueurs.get(0);
    }

    @RequestMapping(value = "/Tournoi/Perdant/{idTournoi}" , method = RequestMethod.GET)
    public Joueur findPerdantByTournoi(@PathVariable int idTournoi) {
        List<Joueur> joueurs = tournoiRepository.findJoueurOrdeInverseByTournoi(tournoiRepository.getOne(idTournoi));
        return joueurs.get(0);
    }

    @RequestMapping(value = "/Tournoi/Classement/{idTournoi}" , method = RequestMethod.GET)
    public List<Joueur> finClassementByTournoi(@PathVariable int idTournoi) {
        return tournoiRepository.findJoueurOrdeByTournoi(tournoiRepository.getOne(idTournoi));
    }

    @RequestMapping(value = "/Tournoi/NombreJoueur/{idTournoi}" , method = RequestMethod.GET)
    public int findNombreJoueur(@PathVariable int idTournoi) {
        return tournoiRepository.findNombreJoueurTournoi(tournoiRepository.getOne(idTournoi));
    }

    @RequestMapping(value= "/Tournoi/ListePoints/{idTournoi}" , method = RequestMethod.GET)
    public List<Float> findListePointsTournoi(@PathVariable int idTournoi) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = tournoiRepository.findJoueurOrdeByTournoi(tournoiRepository.getOne(idTournoi));

        List<Float> listePoints = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listePoints.add(joueurRepository.findPointsJoueurByTournoi(joueur, tournoiRepository.getOne(idTournoi)).getId().getPoints());
        }
        return listePoints;
    }

}
