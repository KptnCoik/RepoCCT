package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Entité.Epreuve;
import com.cct.microcct.com.cct.model.Entité.EpreuveTournoi;
import com.cct.microcct.com.cct.model.Association.EpreuveTournoiPK;
import com.cct.microcct.com.cct.model.Entité.Tournoi;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EpreuveTournoi.class)
public abstract class EpreuveTournoi_ {

    public static volatile SingularAttribute<
            EpreuveTournoi, EpreuveTournoiPK> id;

    public static volatile SingularAttribute<
            Tournoi, Tournoi> tournoi;

    public static volatile SingularAttribute<
            Epreuve, Epreuve> epreuve;

}


