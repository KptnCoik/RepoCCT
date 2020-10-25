package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.*;
import com.cct.microcct.com.cct.repository.EpreuveRepository;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import com.cct.microcct.com.cct.repository.TournoiRepository;
import com.cct.microcct.com.cct.repository.UtilsRepository;
import com.cct.microcct.com.cct.security.AppAuthProvider;
import com.cct.microcct.com.cct.security.BCryptManagerUtil;
import com.cct.microcct.com.cct.security.PasswordEncrypt1;
import com.cct.microcct.com.cct.service.JoueurService;
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
    JoueurService joueurService;

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
        return joueurService.findPointsJoueurByEpreuve(idJoueur,idEpreuve);
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
        return joueurService.findBonusJoueur(idJoueur,idTournoi);
    }

    @RequestMapping(value = "/Joueur/Tournois/{idJoueur}", method = RequestMethod.GET)
    public List<Tournoi> findTournoisByJoueur(@PathVariable int idJoueur) {
        return joueurRepository.findTournoisByJoueur(joueurRepository.getOne(idJoueur));
    }

    @RequestMapping(value = "/Joueur/PositionTournoi/{idJoueur}/{idTournoi}", method = RequestMethod.GET)
    public int finClassementByTournoi(@PathVariable int idJoueur, @PathVariable int idTournoi) {
        return joueurService.finClassementByTournoi(idJoueur, idTournoi);
    }

    @RequestMapping(value = "/Joueur/PositionEpreuveIndividuel/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public int finClassementByEpreuveIndividuel(@PathVariable int idJoueur, @PathVariable int idEpreuve) {
        return joueurService.finClassementByEpreuveIndividuel(idJoueur,idEpreuve);
    }

    @RequestMapping(value = "/Joueur/PositionEpreuveEquipe/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public int finClassementByEpreuveEquipe(@PathVariable int idJoueur, @PathVariable int idEpreuve) {
        return joueurService.finClassementByEpreuveEquipe(idJoueur,idEpreuve);
    }

    @RequestMapping(value = "/Joueur/isBonus/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public boolean isEpreuveBonus(@PathVariable int idJoueur, @PathVariable int idEpreuve) {
        return joueurService.isEpreuveBonus(idJoueur,idEpreuve);
    }

    @RequestMapping(value = "/Joueur/isMalus/{idJoueur}/{idEpreuve}", method = RequestMethod.GET)
    public boolean isEpreuveMalus(@PathVariable int idJoueur, @PathVariable int idEpreuve) {
        return joueurService.isEpreuveMalus(idJoueur,idEpreuve);
    }

    @RequestMapping(value = "/Joueur/TournoiGagne/{idJoueur}", method = RequestMethod.GET)
    public Tournoi tournoiGagne(@PathVariable int idJoueur) {
        return joueurService.tournoiGagne(idJoueur);
    }

    @RequestMapping(value = "/Joueur/TournoiPerd/{idJoueur}", method = RequestMethod.GET)
    public Tournoi tournoiPerd(@PathVariable int idJoueur) {
        return joueurService.tournoiPerd(idJoueur);
    }
}


