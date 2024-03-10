package com.example.apphamburguesas.Interfaces;

import com.example.apphamburguesas.Modelos.EmpresaResponse;
import com.example.apphamburguesas.Modelos.LoginRequest;
import com.example.apphamburguesas.Modelos.LoginResponse;
import com.example.apphamburguesas.Modelos.RespuestaEmpresa;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/Login/iniciar_sesion/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("empresa/infoEmpresa/")
    Call<RespuestaEmpresa> obtenerInfoEmpresa();

}