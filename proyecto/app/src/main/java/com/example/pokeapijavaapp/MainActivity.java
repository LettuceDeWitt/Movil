package com.example.pokeapijavaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {

    Button btnAbrirApp, btnCreditos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrirApp = findViewById(R.id.btnAbrirApp);
        btnCreditos = findViewById(R.id.btnCreditos);

        btnAbrirApp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PokemonActivity.class);
            startActivity(intent);
        });

        btnCreditos.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreditsActivity.class);
            startActivity(intent);
        });
    }
}
