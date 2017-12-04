package com.example.ruslanmanca.dijoncentervolkov.models.contentproviders;

import com.example.ruslanmanca.dijoncentervolkov.dictionaries.HealthDictionary;

import java.io.Serializable;

/**
 * Created by RuslanManca on 04/12/2017.
 */

public class Person implements Serializable {
    long idPerson;
    String nomPerson;
    String prenomPerson;
    long agePerson;
    Float poidsPerson;
    String dateMajPerson;
    String loginPerson;
    Integer corpulence;

    public Person(){}

    public Person(long idPerson, String nomPerson, String prenomPerson, long agePerson, Float poidsPerson, String dateMajPerson, String loginPerson) {
        this.idPerson = idPerson;
        this.nomPerson = nomPerson;
        this.prenomPerson = prenomPerson;
        this.agePerson = agePerson;
        this.poidsPerson = poidsPerson;
        this.dateMajPerson = dateMajPerson;
        this.loginPerson = loginPerson;
    }

    public long getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public String getNomPerson() {
        return nomPerson;
    }

    public void setNomPerson(String nomPerson) {
        this.nomPerson = nomPerson;
    }

    public String getPrenomPerson() {
        return prenomPerson;
    }

    public void setPrenomPerson(String prenomPerson) {
        this.prenomPerson = prenomPerson;
    }

    public long getAgePerson() {
        return agePerson;
    }

    public void setAgePerson(long agePerson) {
        this.agePerson = agePerson;
    }

    public Float getPoidsPerson() {
        return poidsPerson;
    }

    public void setPoidsPerson(Float poidsPerson) {
        this.poidsPerson = poidsPerson;
    }

    public String getDateMajPerson() {
        return dateMajPerson;
    }

    public void setDateMajPerson(String dateMajPerson) {
        this.dateMajPerson = dateMajPerson;
    }

    public String getLoginPerson() {
        return loginPerson;
    }

    public void setLoginPerson(String loginPerson) {
        this.loginPerson = loginPerson;
    }

    public Integer getCorpulence() {
        return corpulence;
    }

    public void setCorpulence() {
        if (this.getPoidsPerson() < 50) {
            this.corpulence = HealthDictionary.ANOREXIA;
        }
        else if (this.getPoidsPerson() >= 50 && this.getPoidsPerson() < 65) {
            this.corpulence = HealthDictionary.SLIM;
        }
        else if (this.getPoidsPerson() >= 65 && this.getPoidsPerson() < 91) {
            this.corpulence = HealthDictionary.FIT;
        }
        else if (this.getPoidsPerson() >= 91 && this.getPoidsPerson() < 100) {
            this.corpulence = HealthDictionary.FAT;
        }
        else if (this.getPoidsPerson() >= 100) {
            this.corpulence = HealthDictionary.OBESITY;
        }
        else {
            this.corpulence = null;
        }
    }
}
