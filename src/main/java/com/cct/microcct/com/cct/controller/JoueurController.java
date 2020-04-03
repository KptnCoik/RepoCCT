package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.*;
import com.cct.microcct.com.cct.repository.EpreuveRepository;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import com.cct.microcct.com.cct.repository.TournoiRepository;
import com.cct.microcct.com.cct.repository.UtilsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class JoueurController {

    @Autowired
    JoueurRepository joueurRepository;

    @Autowired
    EpreuveRepository epreuveRepository;

    @Autowired
    TournoiRepository tournoiRepository;

    @RequestMapping(value = "/Joueur/{id}", method = RequestMethod.GET)
    public Optional<Joueur> findJoueurById(@PathVariable int id) {
        return joueurRepository.findById(id);
    }

    @RequestMapping(value = "/Joueur/PointsTournoi/{idJoueur}/{idTournoi}", method = RequestMethod.GET)
    public float findPointsJoueurByTournoi(@PathVariable int idJoueur,@PathVariable int idTournoi) {
        return joueurRepository.findPointsJoueurByTournoi(joueurRepository.getOne(idJoueur), tournoiRepository.getOne(idTournoi)).getId().getPoints();
    }

    @RequestMapping(value = "/Joueur/PointsEpreuve/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public float findPointsJoueurByEpreuve(@PathVariable int idJoueur, @PathVariable int idEpreuve) {
        if(epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")){
            if(epreuveRepository.isBonus(epreuveRepository.getOne(idEpreuve),joueurRepository.getOne(idJoueur)).getId().isBonus()){
                float points = joueurRepository.findPointsJoueurByEpreuve(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve)).getId().getPoints();
                points = points*2;
                return points;
            } else {
                return joueurRepository.findPointsJoueurByEpreuve(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve)).getId().getPoints();

            }
        } else if (epreuveRepository.isBonusEquipe(epreuveRepository.getOne(idEpreuve),joueurRepository.getOne(idJoueur)).getId().isBonus()){
            float points = joueurRepository.findPointsJoueurByEpreuveEquipe(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve)).getId().getPoints();
            points = points *2;
            return points;
        } else {
            return joueurRepository.findPointsJoueurByEpreuveEquipe(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve)).getId().getPoints();
        }
    }

    @RequestMapping(value = "/Joueur/EquipeEpreuve/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public List<Joueur> findEquipe(@PathVariable int idJoueur,@PathVariable int idEpreuve) {
        return joueurRepository.findEquipeJoueurByEpreuve(joueurRepository.getOne(idJoueur),epreuveRepository.getOne(idEpreuve));
    }

    @RequestMapping(value = "/Joueur/Malus/{idJoueur}/{idTournoi}", method = RequestMethod.GET)
    public Epreuve findMalusJoueur(@PathVariable int idJoueur,@PathVariable int idTournoi) {
        return joueurRepository.findMalusJoueur(joueurRepository.getOne(idJoueur), tournoiRepository.getOne(idTournoi));
    }

    @RequestMapping(value = "/Joueur/Bonus/{idJoueur}/{idTournoi}", method = RequestMethod.GET)
    public Epreuve findBonusJoueur(@PathVariable int idJoueur,@PathVariable int idTournoi) {
            Epreuve epreuve = null;
            epreuve = joueurRepository.findBonusEquipeJoueur(joueurRepository.getOne(idJoueur),tournoiRepository.getOne(idTournoi));
                if(epreuve == null) {
                    return joueurRepository.findBonusJoueur(joueurRepository.getOne(idJoueur), tournoiRepository.getOne(idTournoi));
                } else {
                    return epreuve;
                }
    }

    @RequestMapping(value = "/Joueur/Tournois/{idJoueur}", method = RequestMethod.GET)
    public List<Tournoi> findTournoisByJoueur(@PathVariable int idJoueur) {
        return joueurRepository.findTournoisByJoueur(joueurRepository.getOne(idJoueur));
    }

}
