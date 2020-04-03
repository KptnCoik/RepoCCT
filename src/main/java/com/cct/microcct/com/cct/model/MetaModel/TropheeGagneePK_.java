package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Association.TropheeGagneePK;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TropheeGagneePK.class)
public abstract class TropheeGagneePK_ {

    public static volatile SingularAttribute<
            TropheeGagneePK, Integer> idTrophee;

    public static volatile SingularAttribute<
            TropheeGagneePK, Integer> idJoueur;

    public static volatile SingularAttribute<
            TropheeGagneePK, Integer> idTournoi;

    public static volatile SingularAttribute<
            TropheeGagneePK, Integer> idEpreuve;
}
