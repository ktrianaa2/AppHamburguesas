package com.example.apphamburguesas.Interfaces;

import com.example.apphamburguesas.Modelos.EmpresaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServiceEmpresa {
    @POST("/empresa/infoEmpresa/")
    Call<EmpresaResponse> obtenerEmpresa();
}