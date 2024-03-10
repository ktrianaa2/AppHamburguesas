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

import java.util.HashMap;
import java.util.Map;

public class admManejarEmpresa extends AppCompatActivity {

    private Map<String, Class<?>> activityMap = new HashMap<>();
    private CardView[] cards;
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


        initializeActivityMap();
        initializeCardViews();

        setOnClickListeners();
    }


    private void initializeActivityMap() {
        activityMap.put("DatosGenerales", admDatosGenerales.class);
        activityMap.put("GestionarMesas", admGestionarMesas.class);
        activityMap.put("GestionarProveedores", admGestionarProveedores.class);
        activityMap.put("GestionarSucursales", admGestionarSucursales.class);
        activityMap.put("SRI", admSri.class);
        activityMap.put("DatosBancarios", admDatosBancarios.class);
    }

    private void initializeCardViews() {
        cards = new CardView[]{
                findViewById(R.id.cardDatosEmpresa),
                findViewById(R.id.cardGestionarMesas),
                findViewById(R.id.cardGestionarProveedores),
                findViewById(R.id.cardGestionarSucursales),
                findViewById(R.id.cardSri),
                findViewById(R.id.cardDatosBancarios)
        };

        for (int i = 0; i < cards.length; i++) {
            cards[i].setTag(activityMap.keySet().toArray()[i]);
        }
    }

    private void setOnClickListeners() {
        for (CardView card : cards) {
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchActivity(v.getTag().toString());
                }
            });
        }
    }

    private void launchActivity(String activityName) {
        Intent intent = new Intent(this, activityMap.get(activityName));
        startActivity(intent);
    }
}