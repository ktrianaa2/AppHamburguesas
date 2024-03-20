package com.example.apphamburguesas.Modelos;

public class UnidadMedida {
    private int id_um;
    private String nombre_um;

    public UnidadMedida(int id_um, String nombre_um) {
        this.id_um = id_um;
        this.nombre_um = nombre_um;
    }

    public int getIdUm() {
        return id_um;
    }

    public String getNombreUm() {
        return nombre_um;
    }
}
