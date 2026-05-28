package com.example.pokeapijavaapp;

public class Pokemon {
    private int id;
    private String nombre;
    private String imagenUrl;
    private String tipos;

    public Pokemon(int id, String nombre, String imagenUrl, String tipos) {
        this.id = id;
        this.nombre = nombre;
        this.imagenUrl = imagenUrl;
        this.tipos = tipos;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public String getTipos() {
        return tipos;
    }
}
