package com.example.apphamburguesas.Modelos;

import com.google.gson.annotations.SerializedName;

public class EditarTipoProductoRequest {
    @SerializedName("tp_nombre")
    private String nombre;

    @SerializedName("descripcion")
    private String descripcion;

    public EditarTipoProductoRequest(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

