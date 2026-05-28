package com.example.pokeapijavaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Pokemon> listaPokemon;

    public PokemonAdapter(Context context, ArrayList<Pokemon> listaPokemon) {
        this.context = context;
        this.listaPokemon = listaPokemon;
    }

    @Override
    public int getCount() {
        return listaPokemon.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPokemon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaPokemon.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false);
        }

        ImageView imgPokemon = convertView.findViewById(R.id.imgPokemon);
        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtNumero = convertView.findViewById(R.id.txtNumero);
        TextView txtTipos = convertView.findViewById(R.id.txtTipos);

        Pokemon pokemon = listaPokemon.get(position);

        txtNombre.setText(capitalizar(pokemon.getNombre()));
        txtNumero.setText("Número: #" + pokemon.getId());
        txtTipos.setText("Tipo: " + pokemon.getTipos());

        ImageLoader.cargarImagen(pokemon.getImagenUrl(), imgPokemon);

        return convertView;
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1);
    }
}
