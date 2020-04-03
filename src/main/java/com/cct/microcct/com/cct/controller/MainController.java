package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

    @Autowired
    JoueurRepository joueurRepository;


    @GetMapping("/")
    public String index(Joueur joueur) {

        joueur = joueurRepository.getOne(1);

        return "index";
    }
}
