package com.example.ejerciciomapa3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PedirCiudades {
    @GET("static/data/country-cities/es/es.json")
    Call<List<Ciudad>> listaCiudades();
}
