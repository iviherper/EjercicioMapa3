package com.example.ejerciciomapa3;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedirCiudad {
    static List<Ciudad> ciu;
    public static List<Ciudad> pedirCiudades(ActualizacionB a) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://simplemaps.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PedirCiudades service = retrofit.create(PedirCiudades.class);
        Call<List<Ciudad>> llamada = service.listaCiudades();
        llamada.enqueue(new Callback<List<Ciudad>>() {
            @Override
            public void onResponse(Call<List<Ciudad>> call, Response<List<Ciudad>> response) {
                ciu = response.body();
                a.recuperarCiudades(ciu);
            }

            @Override
            public void onFailure(Call<List<Ciudad>> call, Throwable t) {
                Log.d("error",t.getLocalizedMessage());
            }
        });
        return ciu;
    }
    public interface ActualizacionB
    {
        public void recuperarCiudades(List<Ciudad> c);
    }
}
