package com.example.apphamburguesas.Modelos;


import com.google.gson.annotations.SerializedName;

public class EmpresaResponse {

    @SerializedName("id_empresa")
    private int idEmpresa;

    @SerializedName("enombre")
    private String enombre;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("etelefono")
    private String etelefono;

    @SerializedName("correoelectronico")
    private String correoelectronico;

    @SerializedName("fechafundacion")
    private String fechafundacion;

    @SerializedName("sitioweb")
    private String sitioweb;

    @SerializedName("eslogan")
    private String eslogan;

    @SerializedName("logo_url")
    private String logoUrl; // Agregar este campo para la URL del logo

    // Otros campos que puedas necesitar

    // Constructor, getters y setters

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getEnombre() {
        return enombre;
    }

    public void setEnombre(String enombre) {
        this.enombre = enombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEtelefono() {
        return etelefono;
    }

    public void setEtelefono(String etelefono) {
        this.etelefono = etelefono;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getFechafundacion() {
        return fechafundacion;
    }

    public void setFechafundacion(String fechafundacion) {
        this.fechafundacion = fechafundacion;
    }

    public String getSitioweb() {
        return sitioweb;
    }

    public void setSitioweb(String sitioweb) {
        this.sitioweb = sitioweb;
    }

    public String getEslogan() {
        return eslogan;
    }

    public void setEslogan(String eslogan) {
        this.eslogan = eslogan;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
