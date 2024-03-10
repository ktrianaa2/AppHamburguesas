package com.example.apphamburguesas.adm;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.apphamburguesas.Administrador;
import com.example.apphamburguesas.Interfaces.ApiService;

import com.example.apphamburguesas.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//admEmpresa


import com.example.apphamburguesas.Modelos.EmpresaInfo;
import com.example.apphamburguesas.Modelos.RespuestaEmpresa;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class admEmpresa extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_empresa);




        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admEmpresa.this, admEmpresaEditar.class);
                startActivity(intent);
            }
        });



        ImageView imageViewFlecha = findViewById(R.id.imageView2);


        imageViewFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admEmpresa.this, Administrador.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wv7jhxv6-8000.brs.devtunnels.ms/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<RespuestaEmpresa> call = apiService.obtenerInfoEmpresa();
        call.enqueue(new Callback<RespuestaEmpresa>() {
            @Override
            public void onResponse(Call<RespuestaEmpresa> call, Response<RespuestaEmpresa> response) {
                if (response.isSuccessful()) {
                    RespuestaEmpresa respuesta = response.body();
                    if (respuesta != null && respuesta.getEmpresaInfo() != null) {
                        EmpresaInfo empresaInfo = respuesta.getEmpresaInfo();
                        Log.d("EmpresaActivity", "Datos de la empresa: " + empresaInfo.toString());
                        actualizarUI(empresaInfo);
                    } else {
                        Toast.makeText(admEmpresa.this, "La respuesta está vacía", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("EmpresaActivity", "Error en la respuesta: " + response.errorBody());
                    Toast.makeText(admEmpresa.this, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespuestaEmpresa> call, Throwable t) {
                Log.e("EmpresaActivity", "Error en la solicitud: " + t.getMessage());
                Toast.makeText(admEmpresa.this, "Error al obtener datos de la empresa", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarUI(EmpresaInfo info) {
        ((TextView) findViewById(R.id.nombreEmpresaTextView)).setText(info.getNombre());
        ((TextView) findViewById(R.id.direccionTextView)).setText(info.getDireccion());
        ((TextView) findViewById(R.id.telefonoTextView)).setText(info.getTelefono());
        ((TextView) findViewById(R.id.correoTextView)).setText(info.getCorreoElectronico());
        ((TextView) findViewById(R.id.fechaFundacionTextView)).setText(info.getFechaFundacion());
        ((TextView) findViewById(R.id.sitioWebTextView)).setText(info.getSitioWeb());
        ((TextView) findViewById(R.id.esloganTextView)).setText(info.getEslogan());
    }
}
