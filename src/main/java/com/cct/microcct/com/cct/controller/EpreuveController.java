package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Equipe;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;
import com.cct.microcct.com.cct.repository.EpreuveRepository;
import com.cct.microcct.com.cct.repository.EquipeRepository;
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
public class EpreuveController {

    @Autowired
    EpreuveRepository epreuveRepository;

    @Autowired
    EquipeRepository equipeRepository;

    @Autowired
    TournoiRepository tournoiRepository;

    @Autowired
    JoueurRepository joueurRepository;

    @Autowired
    JoueurController joueurController;

    @RequestMapping(value = "/Epreuve/{idEpreuve}", method = RequestMethod.GET)
    public Optional<Epreuve> findEpreuveById(@PathVariable int idEpreuve) {
        return epreuveRepository.findById(idEpreuve);
    }

    @RequestMapping(value = "/Epreuve/Classement/{idEpreuve}", method = RequestMethod.GET)
    public List<Joueur> findJoueurByEpreuve(@PathVariable int idEpreuve) {
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {

            return epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));
        }
        else {
            return epreuveRepository.findJoueursByEpreuveEquipe(epreuveRepository.getOne(idEpreuve));
        }
    }

    @RequestMapping(value = "/Epreuve/Malus/{idEpreuve}", method = RequestMethod.GET)
    List<Joueur> findJoueurMalusByEpreuve(@PathVariable int idEpreuve) {
        return epreuveRepository.findJoueurMalusByEpreuve(epreuveRepository.getOne(idEpreuve));
    }

    @RequestMapping(value = "/Epreuve/Bonus/{idEpreuve}", method = RequestMethod.GET)
    List<Joueur> findJoueurBonusByEpreuve(@PathVariable int idEpreuve) {
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {
            return epreuveRepository.findJoueurBonusByEpreuveIndividuelle(epreuveRepository.getOne(idEpreuve));
        } else {
            return epreuveRepository.findJoueurBonusByEpreuveEquipe(epreuveRepository.getOne(idEpreuve));
        }

    }

    @RequestMapping(value = "/Epreuve/Gagnant/{idEpreuve}", method = RequestMethod.GET)
    List<Joueur> findJoueurGagnant(@PathVariable int idEpreuve) {
        return epreuveRepository.findGagantByEpreuve(epreuveRepository.getOne(idEpreuve));
    }

    @RequestMapping(value = "/Epreuve/Perdant/{idEpreuve}", method = RequestMethod.GET)
    List<Joueur> findJoueurPerdant(@PathVariable int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {
            List<Joueur> joueurs1 = epreuveRepository.findJoueursByEpreuveIndividuelOrdreInverse(epreuveRepository.getOne(idEpreuve));
            Joueur perdant = joueurs1.get(0);
            joueurs.add(perdant);
        } else {
            List<Equipe> equipe = epreuveRepository.findOrdeInverseEquipeByEpreuve(epreuveRepository.getOne(idEpreuve));
            joueurs = equipeRepository.findJoueursByEquipe(equipeRepository.getOne(equipe.get(0).getId()));
        }
        return joueurs;
    }

    @RequestMapping(value = "/Epreuve/ListPointsIndividuel/{idEpreuve}", method = RequestMethod.GET)
    List<Float> findListePointsEpreuveIndividuel(@PathVariable int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));

        List<Float> listePoints = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listePoints.add(joueurController.findPointsJoueurByEpreuve(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listePoints;
    }

    @RequestMapping(value = "/Epreuve/ListPointsEquipe/{idEpreuve}", method = RequestMethod.GET)
    List<Float> findListePointsEpreuveEquipe(@PathVariable int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveEquipe(epreuveRepository.getOne(idEpreuve));

        List<Float> listePoints = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listePoints.add(joueurController.findPointsJoueurByEpreuve(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listePoints;
    }

    @RequestMapping(value= "/Epreuve/ListPositionEpreuveIndividuelle/{idEpreuve}", method = RequestMethod.GET)
    List<Integer> findListPositionEpreuveIndividuelle(@PathVariable int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));

        List<Integer> listePosition = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listePosition.add(joueurController.finClassementByEpreuveIndividuel(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listePosition;

    }

    @RequestMapping(value= "/Epreuve/ListPositionEpreuveEquipe/{idEpreuve}", method = RequestMethod.GET)
    List<Integer> findListPositionEpreuveEquipe(@PathVariable int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveEquipe(epreuveRepository.getOne(idEpreuve));

        List<Integer> listePosition = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listePosition.add(joueurController.finClassementByEpreuveEquipe(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listePosition;

    }

    @RequestMapping(value= "/Epreuve/ListBonusEpreuve/{idEpreuve}", method = RequestMethod.GET)
    List<Boolean> findListBonusEpreuveIndividuelle(@PathVariable int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();

        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {
            joueurs = epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));
        } else {
            joueurs = epreuveRepository.findJoueursByEpreuveEquipe(epreuveRepository.getOne(idEpreuve));
        }
        List<Boolean> listeBonus = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listeBonus.add(joueurController.isEpreuveBonus(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listeBonus;

    }

    @RequestMapping(value= "/Epreuve/ListMalusEpreuve/{idEpreuve}", method = RequestMethod.GET)
    List<Boolean> findListMalusEpreuveIndividuelle(@PathVariable int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));

        List<Boolean> listeMalus = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listeMalus.add(joueurController.isEpreuveMalus(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listeMalus;

    }

    @RequestMapping(value= "/Epreuve/Tournoi/{idEpreuve}", method = RequestMethod.GET)
    Tournoi findTournoiByEpreuve(@PathVariable int idEpreuve) {return epreuveRepository.findTournoiByEpreuve(epreuveRepository.getOne(idEpreuve));

    }
}
