package com.example.ruslanmanca.dijoncentervolkov.models;

import java.io.Serializable;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class Location implements Serializable {
    private String adress;
    private String postalCode;
    private String city;
    private Position position;

    public Location(String adress, String postalCode, String city, Position position) {
        this.adress = adress;
        this.postalCode = postalCode;
        this.city = city;
        this.position = position;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
