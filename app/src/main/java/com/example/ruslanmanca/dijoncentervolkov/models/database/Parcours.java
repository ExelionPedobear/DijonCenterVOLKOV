package com.example.ruslanmanca.dijoncentervolkov.models.database;

import com.example.ruslanmanca.dijoncentervolkov.models.Poi;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class Parcours {
    long id;
    String idCinema;
    String idRestaurant;
    String dateCreation;
    String dateRealisation;
    String accompagnant;
    long idStatut;
    Statut statut;
    Poi poiCinema;
    Poi poiRestaurant;

    public Parcours(){}

    public Parcours(long id, String idCinema, String idRestaurant, String dateCreation, String dateRealisation, String accompagnant, long idStatut) {
        this.id = id;
        this.idCinema = idCinema;
        this.idRestaurant = idRestaurant;
        this.dateCreation = dateCreation;
        this.dateRealisation = dateRealisation;
        this.accompagnant = accompagnant;
        this.idStatut = idStatut;
    }

    public Parcours(long id, String idCinema, String idRestaurant, String dateCreation, String dateRealisation, String accompagnant, long idStatut, Statut statut) {
        this.id = id;
        this.idCinema = idCinema;
        this.idRestaurant = idRestaurant;
        this.dateCreation = dateCreation;
        this.dateRealisation = dateRealisation;
        this.accompagnant = accompagnant;
        this.idStatut = idStatut;
        this.statut = statut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(String idCinema) {
        this.idCinema = idCinema;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(String dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public String getAccompagnant() {
        return accompagnant;
    }

    public void setAccompagnant(String accompagnant) {
        this.accompagnant = accompagnant;
    }

    public long getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(long idStatut) {
        this.idStatut = idStatut;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Poi getPoiCinema() {
        return poiCinema;
    }

    public void setPoiCinema(Poi poiCinema) {
        this.poiCinema = poiCinema;
    }

    public Poi getPoiRestaurant() {
        return poiRestaurant;
    }

    public void setPoiRestaurant(Poi poiRestaurant) {
        this.poiRestaurant = poiRestaurant;
    }
}
