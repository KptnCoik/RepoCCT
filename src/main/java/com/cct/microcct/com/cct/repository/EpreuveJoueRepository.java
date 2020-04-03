package com.cct.microcct.com.cct.repository;

import com.cct.microcct.com.cct.model.Association.EpreuveJouePK;
import com.cct.microcct.com.cct.model.Entité.EpreuveJoue;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Repository
public interface EpreuveJoueRepository extends JpaRepository<EpreuveJoue,Integer> {


}
