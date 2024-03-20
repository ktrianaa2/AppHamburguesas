package com.example.apphamburguesas.Interfaces;

import com.example.apphamburguesas.Modelos.LoginRequest;
import com.example.apphamburguesas.Modelos.LoginResponse;
import com.example.apphamburguesas.Modelos.RespuestaEmpresa;
import com.example.apphamburguesas.Modelos.RolResponse;
import com.example.apphamburguesas.Modelos.TokenRequest;
import com.example.apphamburguesas.Modelos.UnidadMedidaResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/Login/iniciar_sesion/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("Login/rol/")
    Call<RolResponse> verificarRol(@Body TokenRequest tokenRequest);

    @POST("empresa/infoEmpresa/")
    Call<RespuestaEmpresa> obtenerInfoEmpresa();

    @GET("producto/listarum/")
    Call<UnidadMedidaResponse> listarUnidadesMedida();


}