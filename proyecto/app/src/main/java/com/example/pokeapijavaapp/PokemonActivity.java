package com.example.pokeapijavaapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokemonActivity extends Activity {

    EditText edtBuscar;
    Spinner spinnerTipo;
    ListView listaPokemon;
    ProgressBar progressBar;

    ArrayList<Pokemon> pokemonOriginal = new ArrayList<>();
    ArrayList<Pokemon> pokemonFiltrado = new ArrayList<>();

    PokemonAdapter adapter;

    Handler handler = new Handler(Looper.getMainLooper());
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    String tipoSeleccionado = "Todos";

    String[] tiposPokemon = {
            "Todos", "normal", "fire", "water", "grass", "electric", "ice",
            "fighting", "poison", "ground", "flying", "psychic", "bug",
            "rock", "ghost", "dragon", "dark", "steel", "fairy"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        edtBuscar = findViewById(R.id.edtBuscar);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        listaPokemon = findViewById(R.id.listaPokemon);
        progressBar = findViewById(R.id.progressBar);

        adapter = new PokemonAdapter(this, pokemonFiltrado);
        listaPokemon.setAdapter(adapter);

        configurarSpinner();
        configurarBuscador();
        configurarClickPokemon();
        cargarPokemones();
    }

    private void configurarSpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                tiposPokemon
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(spinnerAdapter);

        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoSeleccionado = tiposPokemon[position];
                filtrarPokemon();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void configurarBuscador() {
        edtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence texto, int start, int before, int count) {
                filtrarPokemon();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    private void configurarClickPokemon() {
        listaPokemon.setOnItemClickListener((parent, view, position, id) -> {
            Pokemon pokemon = pokemonFiltrado.get(position);

            Intent intent = new Intent(PokemonActivity.this, PokemonDetailActivity.class);
            intent.putExtra("nombre", pokemon.getNombre());
            intent.putExtra("numero", pokemon.getId());
            intent.putExtra("tipos", pokemon.getTipos());
            intent.putExtra("imagenUrl", pokemon.getImagenUrl());
            startActivity(intent);
        });
    }

    private void cargarPokemones() {
        progressBar.setVisibility(View.VISIBLE);

        executorService.execute(() -> {
            try {
                ArrayList<Pokemon> listaTemporal = new ArrayList<>();

                for (int i = 1; i <= 151; i++) {
                    String json = obtenerJson("https://pokeapi.co/api/v2/pokemon/" + i);
                    JSONObject response = new JSONObject(json);

                    int id = response.getInt("id");
                    String nombre = response.getString("name");

                    JSONObject sprites = response.getJSONObject("sprites");
                    JSONObject other = sprites.getJSONObject("other");
                    JSONObject officialArtwork = other.getJSONObject("official-artwork");
                    String imagenUrl = officialArtwork.getString("front_default");

                    JSONArray tiposArray = response.getJSONArray("types");
                    StringBuilder tipos = new StringBuilder();

                    for (int j = 0; j < tiposArray.length(); j++) {
                        JSONObject tipoObj = tiposArray.getJSONObject(j);
                        JSONObject tipoInfo = tipoObj.getJSONObject("type");
                        String nombreTipo = tipoInfo.getString("name");

                        tipos.append(nombreTipo);
                        if (j < tiposArray.length() - 1) {
                            tipos.append(", ");
                        }
                    }

                    listaTemporal.add(new Pokemon(id, nombre, imagenUrl, tipos.toString()));
                }

                handler.post(() -> {
                    progressBar.setVisibility(View.GONE);
                    pokemonOriginal.clear();
                    pokemonOriginal.addAll(listaTemporal);
                    filtrarPokemon();
                });

            } catch (Exception e) {
                handler.post(() -> {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Error al conectar con PokéAPI", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private String obtenerJson(String urlTexto) throws Exception {
        URL url = new URL(urlTexto);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder respuesta = new StringBuilder();
        String linea;

        while ((linea = reader.readLine()) != null) {
            respuesta.append(linea);
        }

        reader.close();
        connection.disconnect();

        return respuesta.toString();
    }

    private void filtrarPokemon() {
        String textoBusqueda = edtBuscar.getText().toString().toLowerCase().trim();
        pokemonFiltrado.clear();

        for (Pokemon pokemon : pokemonOriginal) {
            boolean coincideNombre = pokemon.getNombre().toLowerCase().contains(textoBusqueda);
            boolean coincideTipo = tipoSeleccionado.equals("Todos") ||
                    pokemon.getTipos().toLowerCase().contains(tipoSeleccionado.toLowerCase());

            if (coincideNombre && coincideTipo) {
                pokemonFiltrado.add(pokemon);
            }
        }

        adapter.notifyDataSetChanged();
    }
}
