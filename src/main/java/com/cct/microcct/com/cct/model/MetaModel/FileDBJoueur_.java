package com.cct.microcct.com.cct.model.MetaModel;

import com.cct.microcct.com.cct.model.Association.FileDBJoueurPK;
import com.cct.microcct.com.cct.model.Entité.FileDB;
import com.cct.microcct.com.cct.model.Entité.FileDBJoueur;
import com.cct.microcct.com.cct.model.Entité.Joueur;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FileDBJoueur.class)
public abstract class FileDBJoueur_ {

    public static volatile SingularAttribute<
            FileDBJoueur, FileDBJoueurPK> id;

    public static volatile SingularAttribute<
            Joueur, Joueur> joueur;

    public static volatile SingularAttribute<
                FileDB, FileDB> fileDB;
}
