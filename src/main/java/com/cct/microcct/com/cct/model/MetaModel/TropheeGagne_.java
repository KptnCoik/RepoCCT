package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Entité.TropheeGagne;
import com.cct.microcct.com.cct.model.Association.TropheeGagneePK;
import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.Joueur;
import com.cct.microcct.com.cct.model.Entité.Tournoi;
import com.cct.microcct.com.cct.model.Entité.Trophee;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TropheeGagne.class)
public abstract class TropheeGagne_ {

    public static volatile SingularAttribute<
            TropheeGagne, TropheeGagneePK> id;

    public static volatile SingularAttribute<
            Trophee, Trophee> trophee;

    public static volatile SingularAttribute<
            Joueur, Joueur> joueur;

    public static volatile SingularAttribute<
            Tournoi, Tournoi> tournoi;

    public static volatile SingularAttribute<
            Epreuve, Epreuve> epreuve;
}
