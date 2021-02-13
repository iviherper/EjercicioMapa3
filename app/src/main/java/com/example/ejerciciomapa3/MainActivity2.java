package com.example.ejerciciomapa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class MainActivity2 extends AppCompatActivity {
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        wv= (WebView) findViewById(R.id.webView);
        String nombre = getIntent().getStringExtra("nombre");
        wv.loadUrl("https://en.wikipedia.org/wiki/"+nombre);
    }
}