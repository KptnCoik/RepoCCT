package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Association.FaitEquipePK;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FaitEquipePK.class)
public abstract class FaitEquipePK_ {

    public static volatile SingularAttribute<
            FaitEquipePK, Integer> idJoueur;

    public static volatile SingularAttribute<
            FaitEquipePK, Integer> idEquipe;

    public static volatile SingularAttribute<Boolean, Boolean> bonus;
}
