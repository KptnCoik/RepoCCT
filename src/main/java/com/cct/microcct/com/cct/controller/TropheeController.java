package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Trophee;
import com.cct.microcct.com.cct.repository.EpreuveRepository;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import com.cct.microcct.com.cct.repository.TournoiRepository;
import com.cct.microcct.com.cct.repository.TropheeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TropheeController {

    @Autowired
    TropheeRepository tropheeRepository;

    @Autowired
    JoueurRepository joueurRepository;

    @Autowired
    EpreuveRepository epreuveRepository;

    @Autowired
    TournoiRepository tournoiRepository;

    @RequestMapping(value = "/Trophee/{idTrophee}", method = RequestMethod.GET)
    Epreuve finEpreuveByTrophe (@PathVariable int idTrophee){
        return tropheeRepository.findEpreuveByTrophee(tropheeRepository.getOne(idTrophee));

    }

    @RequestMapping(value = "/Trophee/{idTournoi}/{idJoueur}", method = RequestMethod.GET)
    List<Trophee> findTropheeByTournoiByJoueur(@PathVariable int idTournoi, @PathVariable int idJoueur) {
        return tropheeRepository.findTropheeByTournoiByJoueur(
                tournoiRepository.getOne(idTournoi), joueurRepository.getOne(idJoueur)
        );
    }

    @RequestMapping(value = "/Trophee/Joueur/{idJoueur}", method = RequestMethod.GET)
    List<Trophee> findTropheeByJoueur(@PathVariable int idJoueur) {
        return tropheeRepository.findTropheeByJoueur(joueurRepository.getOne(idJoueur));
    }

    @RequestMapping(value = "/Trophee/Gagnant/{idTrophee}", method = RequestMethod.GET)
    List<Joueur> findJoueurByTrophee (@PathVariable int idTrophee) {
        return tropheeRepository.findJoueurByTrophee(tropheeRepository.getOne(idTrophee));
    }

    @RequestMapping(value = "/Trophee/Epreuve/{idEpreuve}", method = RequestMethod.GET)
    Trophee findTropheeByEpreuve (@PathVariable int idEpreuve) {
        return tropheeRepository.findTropheeByEpreuve(epreuveRepository.getOne(idEpreuve));
    }
}
