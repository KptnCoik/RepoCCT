package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.*;
import com.cct.microcct.com.cct.repository.EpreuveRepository;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import com.cct.microcct.com.cct.repository.TournoiRepository;
import com.cct.microcct.com.cct.repository.UtilsRepository;
import com.cct.microcct.com.cct.security.AppAuthProvider;
import com.cct.microcct.com.cct.security.BCryptManagerUtil;
import com.cct.microcct.com.cct.security.PasswordEncrypt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

    @Autowired
    TournoiController tournoiController;

    @RequestMapping(value = "/Joueur/{id}", method = RequestMethod.GET)
    public Optional<Joueur> findJoueurById(@PathVariable int id) {
        return joueurRepository.findById(id);
    }

    @RequestMapping(value = "/Joueur/all", method = RequestMethod.GET)
    public List<Joueur> findAllJoueur() {
        return joueurRepository.findAll();
    }

    @RequestMapping(value = "/loginBis", method = RequestMethod.GET)
    public HttpServletResponse loginGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpServletResponse response = null;

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return response;
        }

        response.setStatus(HttpServletResponse.SC_OK);
        return response;
    }

    @RequestMapping(value = "/Joueur/SignIn/{login}/{password}", method = RequestMethod.GET)
    public Joueur findJoueurByLoginPassword(@PathVariable String login, @PathVariable String password, HttpServletResponse response) {
        Joueur joueur = new Joueur();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.toString());
        System.out.println(auth.getDetails().toString());
        System.out.println(auth.getPrincipal().toString());
        System.out.println(auth.getName());
        joueur = joueurRepository.findJoueurByLoginPassword(login, password);
        if (!joueur.equals(null)) {
            response.setStatus(HttpServletResponse.SC_OK);
            return joueurRepository.findJoueurByLoginPassword(login, password);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return joueur;
        }

    }

    @RequestMapping(value = "/Joueur/SignInS/{login}/{password}", method = RequestMethod.GET)
    public Joueur findJoueurByLoginPasswordEncrypt(@PathVariable String login, @PathVariable String password, HttpServletResponse response) {
        Joueur joueur = new Joueur();
        joueur = joueurRepository.findUserWithName(login).get();
        String passwordSecure = joueur.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncrypted = encoder.encode(password);
        PasswordEncrypt1 passwordEncrypt1 = new PasswordEncrypt1();
        //if (BCryptManagerUtil.passwordencoder().matches(passwordEncrypted,passwordSecure)) {
        if(passwordEncrypt1.decrypt(passwordSecure).equals(password)){
            response.setStatus(HttpServletResponse.SC_OK);
            return joueur;
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

    }

    @Transactional
    @RequestMapping(value = "/Joueur/UpdatePassword/{idJoueur}/{newPassword}", method = RequestMethod.POST)
    public HttpStatus updatePasswordJoueur(@PathVariable int idJoueur, @PathVariable String newPassword) {
        Joueur joueur = joueurRepository.getOne(idJoueur);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Joueur joueurSecure = new Joueur(joueur.getId(),joueur.getUsername(),joueur.getPassword());
        PasswordEncrypt1 passwordEncrypt1 = new PasswordEncrypt1();
        joueurRepository.updatePasswordJoueur(idJoueur,passwordEncrypt1.encrypt(newPassword));
        return HttpStatus.ACCEPTED;
    }

    @RequestMapping(value = "/Joueur/PointsTournoi/{idJoueur}/{idTournoi}", method = RequestMethod.GET)
    public float findPointsJoueurByTournoi(@PathVariable int idJoueur, @PathVariable int idTournoi) {
        return joueurRepository.findPointsJoueurByTournoi(joueurRepository.getOne(idJoueur), tournoiRepository.getOne(idTournoi)).getId().getPoints();
    }

    @RequestMapping(value = "/Joueur/PointsEpreuve/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public float findPointsJoueurByEpreuve(@PathVariable int idJoueur, @PathVariable int idEpreuve) {
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

    @RequestMapping(value = "/Joueur/EquipeEpreuve/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public List<Joueur> findEquipe(@PathVariable int idJoueur, @PathVariable int idEpreuve) {
        return joueurRepository.findEquipeJoueurByEpreuve(joueurRepository.getOne(idJoueur), epreuveRepository.getOne(idEpreuve));
    }

    @RequestMapping(value = "/Joueur/Malus/{idJoueur}/{idTournoi}", method = RequestMethod.GET)
    public Epreuve findMalusJoueur(@PathVariable int idJoueur, @PathVariable int idTournoi) {
        return joueurRepository.findMalusJoueur(joueurRepository.getOne(idJoueur), tournoiRepository.getOne(idTournoi));
    }

    @RequestMapping(value = "/Joueur/Bonus/{idJoueur}/{idTournoi}", method = RequestMethod.GET)
    public Epreuve findBonusJoueur(@PathVariable int idJoueur, @PathVariable int idTournoi) {
        Epreuve epreuve = null;
        epreuve = joueurRepository.findBonusEquipeJoueur(joueurRepository.getOne(idJoueur), tournoiRepository.getOne(idTournoi));
        if (epreuve == null) {
            return joueurRepository.findBonusJoueur(joueurRepository.getOne(idJoueur), tournoiRepository.getOne(idTournoi));
        } else {
            return epreuve;
        }
    }

    @RequestMapping(value = "/Joueur/Tournois/{idJoueur}", method = RequestMethod.GET)
    public List<Tournoi> findTournoisByJoueur(@PathVariable int idJoueur) {
        return joueurRepository.findTournoisByJoueur(joueurRepository.getOne(idJoueur));
    }

    @RequestMapping(value = "/Joueur/PositionTournoi/{idJoueur}/{idTournoi}", method = RequestMethod.GET)
    public int finClassementByTournoi(@PathVariable int idJoueur, @PathVariable int idTournoi) {

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

    @RequestMapping(value = "/Joueur/PositionEpreuveIndividuel/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public int finClassementByEpreuveIndividuel(@PathVariable int idJoueur, @PathVariable int idEpreuve) {

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

    @RequestMapping(value = "/Joueur/PositionEpreuveEquipe/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public int finClassementByEpreuveEquipe(@PathVariable int idJoueur, @PathVariable int idEpreuve) {

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

    @RequestMapping(value = "/Joueur/isBonus/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public boolean isEpreuveBonus(@PathVariable int idJoueur, @PathVariable int idEpreuve) {
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {
            return epreuveRepository.isBonus(epreuveRepository.getOne(idEpreuve), joueurRepository.getOne(idJoueur)).getId().isBonus();
        } else {
            return epreuveRepository.isBonusEquipe(epreuveRepository.getOne(idEpreuve), joueurRepository.getOne(idJoueur)).getId().isBonus();
        }

    }

    @RequestMapping(value = "/Joueur/isMalus/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public boolean isEpreuveMalus(@PathVariable int idJoueur, @PathVariable int idEpreuve) {
        if (epreuveRepository.findById(idEpreuve).get().getType().equals("Individuel")) {
            return epreuveRepository.isBonus(epreuveRepository.getOne(idEpreuve), joueurRepository.getOne(idJoueur)).getId().isMalus();
        } else {
            return false;
        }

    }

    @RequestMapping(value = "/Joueur/TournoiGagne/{idJoueur}", method = RequestMethod.GET)
    public Tournoi tournoiGagne(@PathVariable int idJoueur) {
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

    @RequestMapping(value = "/Joueur/TournoiPerd/{idJoueur}", method = RequestMethod.GET)
    public Tournoi tournoiPerd(@PathVariable int idJoueur) {
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

}


