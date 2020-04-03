package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Entité.Equipe;
import com.cct.microcct.com.cct.model.Entité.FaitEquipe;
import com.cct.microcct.com.cct.model.Association.FaitEquipePK;
import com.cct.microcct.com.cct.model.Entité.Joueur;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FaitEquipe.class)
public abstract class FaitEquipe_ {

    public static volatile SingularAttribute<
            FaitEquipe, FaitEquipePK> id;

    public static volatile SingularAttribute<
            Joueur, Joueur> joueur;

    public static volatile SingularAttribute<
            Equipe, Equipe> equipe;
}
