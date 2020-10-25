package com.cct.microcct.com.cct.service;

import com.cct.microcct.com.cct.controller.JoueurController;
import com.cct.microcct.com.cct.controller.TournoiController;
import com.cct.microcct.com.cct.model.Entité.Equipe;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.repository.EpreuveRepository;
import com.cct.microcct.com.cct.repository.EquipeRepository;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import com.cct.microcct.com.cct.repository.TournoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EpreuveService {

    @Autowired
    EpreuveRepository epreuveRepository;

    @Autowired
    EquipeRepository equipeRepository;

    @Autowired
    JoueurController joueurController;


    public List<Joueur> findJoueurByEpreuve(int idEpreuve) {
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {

            return epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));
        }
        else {
            return epreuveRepository.findJoueursByEpreuveEquipe(epreuveRepository.getOne(idEpreuve));
        }
    }

    public List<Joueur> findJoueurBonusByEpreuve(int idEpreuve) {
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {
            return epreuveRepository.findJoueurBonusByEpreuveIndividuelle(epreuveRepository.getOne(idEpreuve));
        } else {
            return epreuveRepository.findJoueurBonusByEpreuveEquipe(epreuveRepository.getOne(idEpreuve));
        }
    }

    public List<Joueur> findJoueurPerdant(int idEpreuve) {
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

    public List<Float> findListePointsEpreuveIndividuel(int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));

        List<Float> listePoints = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listePoints.add(joueurController.findPointsJoueurByEpreuve(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listePoints;
    }

    public List<Float> findListePointsEpreuveEquipe(int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveEquipe(epreuveRepository.getOne(idEpreuve));

        List<Float> listePoints = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listePoints.add(joueurController.findPointsJoueurByEpreuve(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listePoints;
    }

    public List<Integer> findListPositionEpreuveIndividuelle(int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));

        List<Integer> listePosition = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listePosition.add(joueurController.finClassementByEpreuveIndividuel(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listePosition;
    }

    public List<Integer> findListPositionEpreuveEquipe(int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveEquipe(epreuveRepository.getOne(idEpreuve));

        List<Integer> listePosition = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listePosition.add(joueurController.finClassementByEpreuveEquipe(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listePosition;
    }

    public List<Boolean> findListBonusEpreuveIndividuelle(int idEpreuve) {
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

    public List<Boolean> findListMalusEpreuveIndividuelle(int idEpreuve) {
        List<Joueur> joueurs = new ArrayList<>();
        joueurs = epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));

        List<Boolean> listeMalus = new ArrayList<>();

        for(Joueur joueur : joueurs) {
            listeMalus.add(joueurController.isEpreuveMalus(joueur.getId(),epreuveRepository.getOne(idEpreuve).getId()));
        }
        return listeMalus;
    }
}
