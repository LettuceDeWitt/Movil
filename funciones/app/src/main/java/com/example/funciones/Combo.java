package com.example.funciones;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Combo extends Activity {

    Spinner spinnerColores;
    EditText textoUno;

    @Override
    public void onCreate(Bundle si) {
        super.onCreate(si);
        setContentView(R.layout.activity_main);

        spinnerColores = findViewById(R.id.textospinner);
        textoUno = findViewById(R.id.textouno);

        String[] colores = {"Rojo", "Verde", "Azul"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                colores
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerColores.setAdapter(adapter);

        spinnerColores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long id) {

                String colorSeleccionado = colores[posicion];

                if (colorSeleccionado.equals("Rojo")) {
                    textoUno.setTextColor(Color.RED);
                } else if (colorSeleccionado.equals("Verde")) {
                    textoUno.setTextColor(Color.GREEN);
                } else if (colorSeleccionado.equals("Azul")) {
                    textoUno.setTextColor(Color.BLUE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}