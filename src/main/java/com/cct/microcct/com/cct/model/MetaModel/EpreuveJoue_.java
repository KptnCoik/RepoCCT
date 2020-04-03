package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.EpreuveJoue;
import com.cct.microcct.com.cct.model.Association.EpreuveJouePK;
import com.cct.microcct.com.cct.model.Entité.Joueur;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EpreuveJoue.class)
public abstract class EpreuveJoue_ {

    public static volatile SingularAttribute<
            EpreuveJoue, EpreuveJouePK> id;

    public static volatile SingularAttribute<
            Epreuve, Epreuve> epreuve;

    public static volatile SingularAttribute<
            Joueur, Joueur> joueur;
}
