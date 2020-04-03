package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Association.EpreuveJouePK;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EpreuveJouePK.class)
public abstract class EpreuveJouePK_ {

    public static volatile SingularAttribute<
            EpreuveJouePK, Integer> idEpreuve;

    public static volatile SingularAttribute<
            EpreuveJouePK, Integer> idJoueur;

    public static volatile SingularAttribute<Float, Float> points;

    public static volatile SingularAttribute<Boolean, Boolean> bonus;

    public static volatile SingularAttribute<Boolean, Boolean> malus;


}
