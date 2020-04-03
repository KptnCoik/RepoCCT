package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Association.JoueurJoueTournoiPK;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(JoueurJoueTournoiPK.class)
public abstract class JoueurJoueTournoiPK_ {

    public static volatile SingularAttribute<
            JoueurJoueTournoiPK, Integer> idJoueur;

    public static volatile SingularAttribute<
            JoueurJoueTournoiPK, Integer> idTournoi;

    public static volatile SingularAttribute<Float, Float> points;
}
