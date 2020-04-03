package com.cct.microcct.com.cct.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


public class UtilsRepository {

    @Autowired
    EpreuveRepository epreuveRepository;

    @Autowired
    JoueurRepository joueurRepository;

    @Autowired
    TournoiRepository tournoiRepository;

    @Autowired
    TropheeRepository tropheeRepository;

    public boolean isBonusEquipe(int idEpreuve, int idJoueur) {
        return epreuveRepository.isBonus(epreuveRepository.getOne(idEpreuve),joueurRepository.getOne(idJoueur)).getId().isBonus();
    }
}
