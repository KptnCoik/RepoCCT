package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Association.EpreuveTournoiPK;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EpreuveTournoiPK.class)
public abstract class EpreuveTournoiPK_ {

    public static volatile SingularAttribute<
            EpreuveTournoiPK, Integer> idTournoi;

    public static volatile SingularAttribute<
            EpreuveTournoiPK, Integer> idEpreuve;
}
