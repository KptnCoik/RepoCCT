package com.cct.microcct.com.cct.controller;

import com.cct.microcct.com.cct.model.Entité.Joueur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SpringController {

    @GetMapping
    public ResponseEntity<Joueur> getUser(){

        Joueur user = (Joueur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return  new ResponseEntity<Joueur>(user, HttpStatus.OK);
    }
}