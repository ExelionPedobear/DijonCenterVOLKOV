package com.example.ruslanmanca.dijoncentervolkov.models.database;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class Statut {
    long id;
    String libelle;

    public Statut(){}

    public Statut(long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
