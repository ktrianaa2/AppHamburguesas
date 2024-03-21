package com.example.apphamburguesas.Modelos;

public class TipoProducto {
    private int id;
    private String tpnombre;
    private String descripcion;

    public TipoProducto(int id, String tpnombre, String descripcion) {
        this.id = id;
        this.tpnombre = tpnombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTpnombre() {
        return tpnombre;
    }

    public void setTpnombre(String tpnombre) {
        this.tpnombre = tpnombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
