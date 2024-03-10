package com.example.apphamburguesas.adm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apphamburguesas.Administrador;
import com.example.apphamburguesas.Interfaces.ApiServiceEmpresa;
import com.example.apphamburguesas.Modelos.EmpresaResponse;
import com.example.apphamburguesas.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admEmpresa extends AppCompatActivity {

    private TextView nombreTextView, direccionTextView, telefonoTextView, correoTextView,
            fechaFundacionTextView, sitioWebTextView, esloganTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_empresa);

        nombreTextView = findViewById(R.id.nombreEmpresaTextView);
        Log.d("admEmpresa", "nombreTextView: " + nombreTextView);
        direccionTextView = findViewById(R.id.direccionTextView);
        Log.d("admEmpresa", "direccionTextView: " + direccionTextView);
        telefonoTextView = findViewById(R.id.telefonoTextView);
        correoTextView = findViewById(R.id.correoTextView);
        fechaFundacionTextView = findViewById(R.id.fechaFundacionTextView);
        sitioWebTextView = findViewById(R.id.sitioWebTextView);
        esloganTextView = findViewById(R.id.esloganTextView);

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

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admEmpresa.this, admEmpresaEditar.class);
                startActivity(intent);
            }
        });

        obtenerDatosEmpresa();
    }

    private void obtenerDatosEmpresa() {
        ApiServiceEmpresa apiService = RetrofitClient.getRetrofitInstance().create(ApiServiceEmpresa.class);
        Call<EmpresaResponse> call = apiService.obtenerEmpresa();
        call.enqueue(new Callback<EmpresaResponse>() {
            @Override
            public void onResponse(Call<EmpresaResponse> call, Response<EmpresaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    EmpresaResponse empresa = response.body();
                    Log.d("admEmpresa", "onResponse called");
                    Log.d("admEmpresa", "Empresa response: " + empresa.toString());
                    nombreTextView.setText(empresa.getEnombre());
                    direccionTextView.setText(empresa.getDireccion());
                    telefonoTextView.setText(empresa.getEtelefono());
                    correoTextView.setText(empresa.getCorreoelectronico());
                    fechaFundacionTextView.setText(empresa.getFechafundacion());
                    sitioWebTextView.setText(empresa.getSitioweb());
                    esloganTextView.setText(empresa.getEslogan());
                    // Aquí puedes manejar la imagen del logo si lo necesitas
                }
            }

            @Override
            public void onFailure(Call<EmpresaResponse> call, Throwable t) {
                // Manejar el error
            }
        });
    }
}
