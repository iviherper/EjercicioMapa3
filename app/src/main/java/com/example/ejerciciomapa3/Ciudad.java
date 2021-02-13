package com.example.ejerciciomapa3;

public class Ciudad {
    private String city;
    private Double lat,lng;

    public Ciudad(String nombre, Double lat, Double lng) {
        this.city = nombre;
        this.lat = lat;
        this.lng = lng;
    }

    public String getNombre() {
        return city;
    }

    public void setNombre(String nombre) {
        this.city = nombre;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
