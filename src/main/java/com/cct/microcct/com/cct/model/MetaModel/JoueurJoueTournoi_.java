package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.JoueurJoueTournoi;
import com.cct.microcct.com.cct.model.Association.JoueurJoueTournoiPK;
import com.cct.microcct.com.cct.model.Entité.Tournoi;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(JoueurJoueTournoi.class)
public abstract class JoueurJoueTournoi_ {

    public static volatile SingularAttribute<
            JoueurJoueTournoi, JoueurJoueTournoiPK> id;

    public static volatile SingularAttribute<
            Joueur, Joueur> joueur;

    public static volatile SingularAttribute<
            Tournoi, Tournoi> tournoi;
}
