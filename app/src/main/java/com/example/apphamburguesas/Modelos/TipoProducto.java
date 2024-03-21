package com.example.apphamburguesas.Modelos;

public class TipoProducto {
    private int id_tipoproducto;
    private String tpnombre;
    private String descripcion;

    public TipoProducto(int id_tipoproducto, String tpnombre, String descripcion) {
        this.id_tipoproducto = id_tipoproducto;
        this.tpnombre = tpnombre;
        this.descripcion = descripcion;
    }

    public int getId_tipoproducto() {
        return id_tipoproducto;
    }

    public void setId_tipoproducto(int id_tipoproducto) {
        this.id_tipoproducto = id_tipoproducto;
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
