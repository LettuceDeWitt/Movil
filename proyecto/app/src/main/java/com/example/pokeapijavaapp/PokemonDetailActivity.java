package com.example.pokeapijavaapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PokemonDetailActivity extends Activity {

    TextView txtDetalleNombre, txtDetalleNumero, txtDetalleTipos;
    ImageView imgDetallePokemon;
    Button btnDetalleRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        txtDetalleNombre = findViewById(R.id.txtDetalleNombre);
        txtDetalleNumero = findViewById(R.id.txtDetalleNumero);
        txtDetalleTipos = findViewById(R.id.txtDetalleTipos);
        imgDetallePokemon = findViewById(R.id.imgDetallePokemon);
        btnDetalleRegresar = findViewById(R.id.btnDetalleRegresar);

        String nombre = getIntent().getStringExtra("nombre");
        String imagenUrl = getIntent().getStringExtra("imagenUrl");
        String tipos = getIntent().getStringExtra("tipos");
        int numero = getIntent().getIntExtra("numero", 0);

        txtDetalleNombre.setText(capitalizar(nombre));
        txtDetalleNumero.setText("Número de Pokédex: #" + numero);
        txtDetalleTipos.setText("Tipo: " + tipos);

        ImageLoader.cargarImagen(imagenUrl, imgDetallePokemon);

        btnDetalleRegresar.setOnClickListener(v -> finish());
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}
