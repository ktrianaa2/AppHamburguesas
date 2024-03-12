package com.example.apphamburguesas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import com.example.apphamburguesas.adm.*;

public class Administrador extends AppCompatActivity {

    private Map<String, Class<?>> activityMap = new HashMap<>();
    private CardView cardManejarEmpresa, cardGestionDeEmpleados, cardGestionarProductos, cardGestionDeReversiones,
            cardReportes, cardAppWeb, cardPuntosDeRecompensa, cardFacturacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        initializeActivityMap();
        initializeCardViews();
        setOnClickListeners();
    }

    private void initializeActivityMap() {
        activityMap.put("ManejarEmpresa", com.example.apphamburguesas.adm.admManejarEmpresa.class);
        activityMap.put("GestionDeEmpleados", com.example.apphamburguesas.adm.admGestionDeEmpleados.class);
        activityMap.put("GestionDeProductos", com.example.apphamburguesas.adm.admGestionarProductos.class);
        activityMap.put("GestionarDeReversiones", com.example.apphamburguesas.adm.admGestionDeReversiones.class);
        activityMap.put("Reportes", com.example.apphamburguesas.adm.admReportes.class);
        activityMap.put("AppWeb", com.example.apphamburguesas.adm.admAppWeb.class);
        activityMap.put("PuntosDeRecompensa", com.example.apphamburguesas.adm.admPuntosDeRecompensa.class);
        activityMap.put("Facturacion", com.example.apphamburguesas.adm.admFacturacion.class);
    }

    private void initializeCardViews() {
        cardManejarEmpresa = findViewById(R.id.cardManejarEmpresa);
        cardGestionDeEmpleados = findViewById(R.id.cardGestionDeEmpleados);
        cardGestionarProductos = findViewById(R.id.cardGestionarProductos);
        cardGestionDeReversiones = findViewById(R.id.cardGestionDeReversiones);
        cardReportes = findViewById(R.id.cardReportes);
        cardAppWeb = findViewById(R.id.cardAppWeb);
        cardPuntosDeRecompensa = findViewById(R.id.cardPuntosDeRecompensa);
        cardFacturacion = findViewById(R.id.cardFacturacion);

        cardManejarEmpresa.setTag("ManejarEmpresa");
        cardGestionDeEmpleados.setTag("GestionDeEmpleados");
        cardGestionarProductos.setTag("GestionDeProductos");
        cardGestionDeReversiones.setTag("GestionarDeReversiones");
        cardReportes.setTag("Reportes");
        cardAppWeb.setTag("AppWeb");
        cardPuntosDeRecompensa.setTag("PuntosDeRecompensa");
        cardFacturacion.setTag("Facturacion");
    }

    private void setOnClickListeners() {
        cardManejarEmpresa.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardGestionDeEmpleados.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardGestionarProductos.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardGestionDeReversiones.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardReportes.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardAppWeb.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardPuntosDeRecompensa.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardFacturacion.setOnClickListener(v -> launchActivity(v.getTag().toString()));
    }

    private void launchActivity(String activityName) {
        Intent intent = new Intent(this, activityMap.get(activityName));
        startActivity(intent);
    }
}