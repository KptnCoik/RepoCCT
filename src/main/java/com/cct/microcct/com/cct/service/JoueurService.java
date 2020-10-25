package com.cct.microcct.com.cct.service;

import com.cct.microcct.com.cct.controller.TournoiController;
import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.EpreuveJoueEquipe;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;
import com.cct.microcct.com.cct.repository.EpreuveRepository;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import com.cct.microcct.com.cct.repository.TournoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JoueurService {

    @Autowired
    JoueurRepository joueurRepository;

    @Autowired
    EpreuveRepository epreuveRepository;

    @Autowired
    TournoiRepository tournoiRepository;

    @Autowired
    TournoiController tournoiController;

    public float findPointsJoueurByEpreuve (int idJoueur, int idEpreuve) {
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {
            if (epreuveRepository.isBonus(epreuveRepository.getOne(idEpreuve), joueurRepository.getOne(idJoueur)).getId().isBonus()) {
                float points = joueurRepository.findPointsJoueurByEpreuve(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve)).getId().getPoints();
                points = points * 2;
                return points;
            } else if(epreuveRepository.isBonus(epreuveRepository.getOne(idEpreuve), joueurRepository.getOne(idJoueur)).getId().isMalus()) {
                float points = joueurRepository.findPointsJoueurByEpreuve(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve)).getId().getPoints();
                points = points / 2;
                return points;
            } else {
                return joueurRepository.findPointsJoueurByEpreuve(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve)).getId().getPoints();

            }
        } else if (epreuveRepository.isBonusEquipe(epreuveRepository.getOne(idEpreuve), joueurRepository.getOne(idJoueur)).getId().isBonus()) {
            float points = joueurRepository.findPointsJoueurByEpreuveEquipe(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve)).getId().getPoints();
            points = points * 2;
            return points;
        } else {
            return joueurRepository.findPointsJoueurByEpreuveEquipe(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve)).getId().getPoints();
        }
    }

    public int finClassementByTournoi (int idJoueur, int idTournoi) {
        List<Joueur> classementTournoi = new ArrayList<>();
        classementTournoi = tournoiRepository.findJoueurOrdeByTournoi(tournoiRepository.getOne(idTournoi));

        float tableauPoints[] = new float[classementTournoi.size()];

        float pointJoueur = joueurRepository.findPointsJoueurByTournoi(joueurRepository.getOne(idJoueur), tournoiRepository
                .getOne(idTournoi)).getId().getPoints();

        int cpt = 1;

        for (int i = 0; i < classementTournoi.size(); i++) {
            tableauPoints[i] = (joueurRepository.findPointsJoueurByTournoi(joueurRepository.getOne(classementTournoi.get(i).getId()), tournoiRepository.getOne(idTournoi)))
                    .getId().getPoints();
        }

        for (int i = 0; i < tableauPoints.length; i++) {
            if (tableauPoints[i] > pointJoueur) {
                cpt++;
            }
        }

        return cpt;
    }

    public int finClassementByEpreuveIndividuel(int idJoueur, int idEpreuve) {
        List<Joueur> classementEpreuve = new ArrayList<>();
        classementEpreuve = epreuveRepository.findJoueursByEpreuveIndividuel(epreuveRepository.getOne(idEpreuve));

        float tableauPoints[] = new float[classementEpreuve.size()];

        float pointJoueur = joueurRepository.findPointsJoueurByEpreuve(joueurRepository.getOne(idJoueur), epreuveRepository
                .getOne(idEpreuve)).getId().getPoints();

        int cpt = 1;

        for (int i = 0; i < classementEpreuve.size(); i++) {
            tableauPoints[i] = (joueurRepository.findPointsJoueurByEpreuve(joueurRepository.getOne(classementEpreuve.get(i).getId()), epreuveRepository.getOne(idEpreuve)))
                    .getId().getPoints();
        }

        for (int i = 0; i < tableauPoints.length; i++) {
            if (tableauPoints[i] > pointJoueur) {
                cpt++;
            }
        }

        return cpt;
    }

    public int finClassementByEpreuveEquipe(int idJoueur, int idEpreuve) {
        List<EpreuveJoueEquipe> classementEpreuveEquipe = new ArrayList<>();
        classementEpreuveEquipe = epreuveRepository.getPoints(epreuveRepository.getOne(idEpreuve));

        float tableauPoints[] = new float[classementEpreuveEquipe.size()];

        float pointJoueur = joueurRepository.findPointsJoueurByEpreuveEquipe(joueurRepository.getOne(idJoueur), epreuveRepository
                .getOne(idEpreuve)).getId().getPoints();

        int cpt = 1;

        for (int i = 0; i < classementEpreuveEquipe.size(); i++) {
            tableauPoints[i] = classementEpreuveEquipe.get(i).getId().getPoints();
        }

        for (int i = 0; i < classementEpreuveEquipe.size(); i++) {
            if (tableauPoints[i] > pointJoueur) {
                cpt++;
            }
        }

        return cpt;
    }

    public Tournoi tournoiGagne(int idJoueur) {
        List<Tournoi> tournois = new ArrayList<>();
        tournois = tournoiRepository.findAll();

        Tournoi tournoiGagne = new Tournoi();

        for(Tournoi t : tournois) {
            if(tournoiController.findGagantByTournoi(t.getId()).getId()==idJoueur) {
                tournoiGagne = t;
            }
        }

        return tournoiGagne;
    }

    public Tournoi tournoiPerd(int idJoueur) {
        List<Tournoi> tournois = new ArrayList<>();
        tournois = tournoiRepository.findAll();

        Tournoi tournoiGagne = new Tournoi();

        for(Tournoi t : tournois) {
            if(tournoiController.findPerdantByTournoi(t.getId()).getId()==idJoueur) {
                tournoiGagne = t;
            }
        }

        return tournoiGagne;
    }

    public Epreuve findBonusJoueur(int idJoueur, int idTournoi) {
        Epreuve epreuve = null;
        epreuve = joueurRepository.findBonusEquipeJoueur(joueurRepository.getOne(idJoueur), tournoiRepository.getOne(idTournoi));
        if (epreuve == null) {
            return joueurRepository.findBonusJoueur(joueurRepository.getOne(idJoueur), tournoiRepository.getOne(idTournoi));
        } else {
            return epreuve;
        }
    }

    public boolean isEpreuveBonus(int idJoueur, int idEpreuve) {
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {
            return epreuveRepository.isBonus(epreuveRepository.getOne(idEpreuve), joueurRepository.getOne(idJoueur)).getId().isBonus();
        } else {
            return epreuveRepository.isBonusEquipe(epreuveRepository.getOne(idEpreuve), joueurRepository.getOne(idJoueur)).getId().isBonus();
        }
    }

    public boolean isEpreuveMalus(int idJoueur, int idEpreuve) {
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {
            return epreuveRepository.isBonus(epreuveRepository.getOne(idEpreuve), joueurRepository.getOne(idJoueur)).getId().isMalus();
        } else {
            return false;
        }
    }
}
