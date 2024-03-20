package com.example.apphamburguesas.Modelos;

public class EditarUnidadMedidaRequest {
    private String nombreUm;

    public EditarUnidadMedidaRequest(String nombreUm) {
        this.nombreUm = nombreUm;
    }

    public String getNombreUm() {
        return nombreUm;
    }

    public void setNombreUm(String nombreUm) {
        this.nombreUm = nombreUm;
    }
}

