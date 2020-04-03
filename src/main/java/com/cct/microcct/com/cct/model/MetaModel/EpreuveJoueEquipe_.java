package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.EpreuveJoueEquipe;
import com.cct.microcct.com.cct.model.Association.EpreuveJoueEquipePK;
import com.cct.microcct.com.cct.model.Entité.Equipe;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EpreuveJoueEquipe.class)
public abstract class EpreuveJoueEquipe_ {

    public static volatile SingularAttribute<
            EpreuveJoueEquipe, EpreuveJoueEquipePK> id;

    public static volatile SingularAttribute<
            Epreuve, Epreuve> epreuve;

    public static volatile SingularAttribute<
            Equipe, Equipe> equipe;
}
