package com.example.apphamburguesas.Interfaces;

import com.example.apphamburguesas.Modelos.EditarUnidadMedidaRequest;
import com.example.apphamburguesas.Modelos.LoginRequest;
import com.example.apphamburguesas.Modelos.LoginResponse;
import com.example.apphamburguesas.Modelos.RespuestaEmpresa;
import com.example.apphamburguesas.Modelos.RolResponse;
import com.example.apphamburguesas.Modelos.TokenRequest;
import com.example.apphamburguesas.Modelos.UnidadMedida;
import com.example.apphamburguesas.Modelos.UnidadMedidaResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/Login/iniciar_sesion/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("Login/rol/")
    Call<RolResponse> verificarRol(@Body TokenRequest tokenRequest);

    @POST("empresa/infoEmpresa/")
    Call<RespuestaEmpresa> obtenerInfoEmpresa();

    @GET("producto/listarum/")
    Call<UnidadMedidaResponse> listarUnidadesMedida();

    @POST("producto/crearum/")
    Call<Void> crearUnidadMedida(@Body UnidadMedida unidadMedida);

    @FormUrlEncoded
    @POST("producto/editarum/{unidad_id}/")
    Call<Void> editarUnidadMedida(@Path("unidad_id") int unidadId, @Field("nombreUm") String nuevoNombre);
}