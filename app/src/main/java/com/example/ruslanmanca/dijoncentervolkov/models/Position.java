package com.example.ruslanmanca.dijoncentervolkov.models;

import java.io.Serializable;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class Position implements Serializable {
    private double lat;
    private double lon;

    public Position(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
