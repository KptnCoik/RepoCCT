package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Association.FileDBJoueurPK;
import com.cct.microcct.com.cct.model.Entité.FileDBJoueur;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FileDBJoueur.class)
public abstract class FileDBJoueurPK_ {

    public static volatile SingularAttribute<
                FileDBJoueurPK, Integer> id_joueur;

    public static volatile SingularAttribute<
            FileDBJoueurPK, Integer> id_image;
}
