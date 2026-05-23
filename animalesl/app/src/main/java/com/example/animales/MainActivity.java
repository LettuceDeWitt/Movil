package com.example.animales;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    ListView listaAnimales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAnimales = findViewById(R.id.listaAnimales);

        String[] animales = {"Gato", "Perro", "Vaca"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                animales
        );

        listaAnimales.setAdapter(adapter);

        remplazarFragmento(new GatoFragment());

        listaAnimales.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    remplazarFragmento(new GatoFragment());
                    break;

                case 1:
                    remplazarFragmento(new PerroFragment());
                    break;

                case 2:
                    remplazarFragmento(new VacaFragment());
                    break;
            }
        });
    }

    public void remplazarFragmento(Fragment fragmento) {
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.contenedorFragmentos, fragmento)
                .commit();
    }
}