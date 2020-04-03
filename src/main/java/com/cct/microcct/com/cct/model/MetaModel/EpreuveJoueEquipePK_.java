package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Association.EpreuveJoueEquipePK;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EpreuveJoueEquipePK.class)
public abstract class EpreuveJoueEquipePK_ {

    public static volatile SingularAttribute<
            EpreuveJoueEquipePK, Integer> idEpreuve;

    public static volatile SingularAttribute<
            EpreuveJoueEquipePK, Integer> idEquipe;

    public static volatile SingularAttribute<Float, Float> points;
}
