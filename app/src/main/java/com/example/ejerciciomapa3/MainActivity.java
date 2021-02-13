package com.example.ejerciciomapa3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PedirCiudad.ActualizacionB {

    private GoogleMap mMap;
    ArrayList<Ciudad> ciudades = new ArrayList();
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        spinner = findViewById(R.id.spinner);
        PedirCiudad p = new PedirCiudad();
        p.pedirCiudades(new PedirCiudad.ActualizacionB() {
            @Override
            public void recuperarCiudades(List<Ciudad> c) {
                actualizarCiudades(c);
                actualizarMapa();
            }
        });

       // spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        //    @Override
          //  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //    Ciudad ci = ciudades.get(position);
              //  actualizarMapa(ci);
            //}

           // @Override
           // public void onNothingSelected(AdapterView<?> parent) {

            //}
        //});
    }

    private void actualizarMapa() {
        for (Ciudad ci: ciudades) {
            LatLng ciudadAct = new LatLng(ci.getLat(), ci.getLng());
            Marker marcador = mMap.addMarker(new MarkerOptions().position(ciudadAct).title("Marker in " + ci.getNombre()));
            marcador.setTag(ci);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ciudadAct));
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Ciudad ciu = (Ciudad) marker.getTag();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("nombre", ciu.getNombre());
                startActivity(intent);
                return false;
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.clear();
    }

    @Override
    public void recuperarCiudades(List<Ciudad> c) {
        actualizarCiudades(c);
    }

    private void actualizarCiudades(List<Ciudad> c) {
        ciudades = (ArrayList<Ciudad>) c;
       // List<String> cius = new ArrayList<>();
       // for (Ciudad ciu:
           // c ) {
         //   cius.add(ciu.getNombre());
       // }
        //spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cius));
    }
}