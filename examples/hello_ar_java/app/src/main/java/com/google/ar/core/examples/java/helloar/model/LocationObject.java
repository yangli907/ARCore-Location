package com.google.ar.core.examples.java.helloar.model;

import java.io.Serializable;

public class LocationObject implements Serializable {
    private float lat;
    private float lon;
    private String name;
    private String type;
    private int rate;
    private boolean hasAudioGuide = true;

    public LocationObject() {
    }

    public LocationObject(float lat, float lon, String name, String type, int rate, boolean hasAudioGuide) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.type = type;
        this.rate = rate;
        this.hasAudioGuide = hasAudioGuide;
    }

    public float getLat() {
        return lat;
    }

    public LocationObject setLat(float lat) {
        this.lat = lat;
        return this;
    }

    public float getLon() {
        return lon;
    }

    public LocationObject setLon(float lon) {
        this.lon = lon;
        return this;
    }

    public String getName() {
        return name;
    }

    public LocationObject setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public LocationObject setType(String type) {
        this.type = type;
        return this;
    }

    public int getRate() {
        return rate;
    }

    public LocationObject setRate(int rate) {
        this.rate = rate;
        return this;
    }

    public boolean isHasAudioGuide() {
        return hasAudioGuide;
    }

    public LocationObject setHasAudioGuide(boolean hasAudioGuide) {
        this.hasAudioGuide = hasAudioGuide;
        return this;
    }

    public LocationObject createLocationObject() {
        return new LocationObject(lat, lon, name, type, rate, hasAudioGuide);
    }
}
