package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Equipe;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;
import com.cct.microcct.com.cct.repository.EpreuveRepository;
import com.cct.microcct.com.cct.repository.EquipeRepository;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import com.cct.microcct.com.cct.repository.TournoiRepository;
import com.cct.microcct.com.cct.service.EpreuveService;
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
    EpreuveService epreuveService;

    @RequestMapping(value = "/Epreuve/{idEpreuve}", method = RequestMethod.GET)
    public Optional<Epreuve> findEpreuveById(@PathVariable int idEpreuve) {
        return epreuveRepository.findById(idEpreuve);
    }

    @RequestMapping(value = "/Epreuve/Classement/{idEpreuve}", method = RequestMethod.GET)
    public List<Joueur> findJoueurByEpreuve(@PathVariable int idEpreuve) {
        return epreuveService.findJoueurByEpreuve(idEpreuve);
    }

    @RequestMapping(value = "/Epreuve/Malus/{idEpreuve}", method = RequestMethod.GET)
    List<Joueur> findJoueurMalusByEpreuve(@PathVariable int idEpreuve) {
        return epreuveRepository.findJoueurMalusByEpreuve(epreuveRepository.getOne(idEpreuve));
    }

    @RequestMapping(value = "/Epreuve/Bonus/{idEpreuve}", method = RequestMethod.GET)
    List<Joueur> findJoueurBonusByEpreuve(@PathVariable int idEpreuve) {
        return epreuveService.findJoueurBonusByEpreuve(idEpreuve);
    }

    @RequestMapping(value = "/Epreuve/Gagnant/{idEpreuve}", method = RequestMethod.GET)
    List<Joueur> findJoueurGagnant(@PathVariable int idEpreuve) {
        return epreuveRepository.findGagantByEpreuve(epreuveRepository.getOne(idEpreuve));
    }

    @RequestMapping(value = "/Epreuve/Perdant/{idEpreuve}", method = RequestMethod.GET)
    List<Joueur> findJoueurPerdant(@PathVariable int idEpreuve) {
        return epreuveService.findJoueurPerdant(idEpreuve);
    }

    @RequestMapping(value = "/Epreuve/ListPointsIndividuel/{idEpreuve}", method = RequestMethod.GET)
    List<Float> findListePointsEpreuveIndividuel(@PathVariable int idEpreuve) {
        return epreuveService.findListePointsEpreuveIndividuel(idEpreuve);
    }

    @RequestMapping(value = "/Epreuve/ListPointsEquipe/{idEpreuve}", method = RequestMethod.GET)
    List<Float> findListePointsEpreuveEquipe(@PathVariable int idEpreuve) {
        return epreuveService.findListePointsEpreuveEquipe(idEpreuve);
    }

    @RequestMapping(value= "/Epreuve/ListPositionEpreuveIndividuelle/{idEpreuve}", method = RequestMethod.GET)
    List<Integer> findListPositionEpreuveIndividuelle(@PathVariable int idEpreuve) {
        return epreuveService.findListPositionEpreuveIndividuelle(idEpreuve);
    }

    @RequestMapping(value= "/Epreuve/ListPositionEpreuveEquipe/{idEpreuve}", method = RequestMethod.GET)
    List<Integer> findListPositionEpreuveEquipe(@PathVariable int idEpreuve) {
        return epreuveService.findListPositionEpreuveEquipe(idEpreuve);
    }

    @RequestMapping(value= "/Epreuve/ListBonusEpreuve/{idEpreuve}", method = RequestMethod.GET)
    List<Boolean> findListBonusEpreuveIndividuelle(@PathVariable int idEpreuve) {
        return epreuveService.findListBonusEpreuveIndividuelle(idEpreuve);
    }

    @RequestMapping(value= "/Epreuve/ListMalusEpreuve/{idEpreuve}", method = RequestMethod.GET)
    List<Boolean> findListMalusEpreuveIndividuelle(@PathVariable int idEpreuve) {
        return epreuveService.findListMalusEpreuveIndividuelle(idEpreuve);
    }

    @RequestMapping(value= "/Epreuve/Tournoi/{idEpreuve}", method = RequestMethod.GET)
    Tournoi findTournoiByEpreuve(@PathVariable int idEpreuve) {
        return epreuveRepository.findTournoiByEpreuve(epreuveRepository.getOne(idEpreuve));
    }
}
