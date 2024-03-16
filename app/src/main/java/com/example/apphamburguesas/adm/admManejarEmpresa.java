package com.example.apphamburguesas.adm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.apphamburguesas.R;
import com.example.apphamburguesas.adm.gestionDeEmpleados.admControlDePagos;
import com.example.apphamburguesas.adm.manejarEmpresa.admDatosBancarios;
import com.example.apphamburguesas.adm.manejarEmpresa.admDatosGenerales;
import com.example.apphamburguesas.adm.manejarEmpresa.admGestionarMesas;
import com.example.apphamburguesas.adm.manejarEmpresa.admGestionarProveedores;
import com.example.apphamburguesas.adm.manejarEmpresa.admGestionarSucursales;
import com.example.apphamburguesas.adm.manejarEmpresa.admSri;

public class admManejarEmpresa extends AppCompatActivity {

    private CardView cardDatosEmpresa, cardGestionarMesas, cardGestionarProveedores,
            cardGestionarSucursales, cardSri, cardDatosBancarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_manejar_empresa);

        ImageView imageViewFlecha = findViewById(R.id.flechaRetroceder);
        imageViewFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la anterior
            }
        });

        initializeCardViews();
        setOnClickListeners();
    }

    private void initializeCardViews() {
        cardDatosEmpresa = findViewById(R.id.cardDatosEmpresa);
        cardGestionarMesas = findViewById(R.id.cardGestionarMesas);
        cardGestionarProveedores = findViewById(R.id.cardGestionarProveedores);
        cardGestionarSucursales = findViewById(R.id.cardGestionarSucursales);
        cardSri = findViewById(R.id.cardSri);
        cardDatosBancarios = findViewById(R.id.cardDatosBancarios);

        cardDatosEmpresa.setTag("DatosGenerales");
        cardGestionarMesas.setTag("GestionarMesas");
        cardGestionarProveedores.setTag("GestionarProveedores");
        cardGestionarSucursales.setTag("GestionarSucursales");
        cardSri.setTag("SRI");
        cardDatosBancarios.setTag("DatosBancarios");
    }

    private void setOnClickListeners() {
        cardDatosEmpresa.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardGestionarMesas.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardGestionarProveedores.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardGestionarSucursales.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardSri.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardDatosBancarios.setOnClickListener(v -> launchActivity(v.getTag().toString()));
    }

    private void launchActivity(String activityName) {
        Class<?> activityClass = null;
        switch (activityName) {
            case "DatosGenerales":
                activityClass = admDatosGenerales.class;
                break;
            case "GestionarMesas":
                activityClass = admGestionarMesas.class;
                break;
            case "GestionarProveedores":
                activityClass = admGestionarProveedores.class;
                break;
            case "GestionarSucursales":
                activityClass = admGestionarSucursales.class;
                break;
            case "SRI":
                activityClass = admSri.class;
                break;
            case "DatosBancarios":
                activityClass = admDatosBancarios.class;
                break;
        }
        if (activityClass != null) {
            Intent intent = new Intent(this, activityClass);
            startActivity(intent);
        }
    }
}
