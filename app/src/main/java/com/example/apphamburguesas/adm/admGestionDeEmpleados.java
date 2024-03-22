package com.example.apphamburguesas.adm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.apphamburguesas.R;
import com.example.apphamburguesas.adm.gestionDeEmpleados.admControlDePagos;
import com.example.apphamburguesas.adm.gestionDeEmpleados.admControlDePersonal;

import java.util.HashMap;
import java.util.Map;

public class admGestionDeEmpleados extends AppCompatActivity {

    private Map<String, Class<?>> activityMap = new HashMap<>();
    private CardView cardControlDeEmpleados, cardControlDePagos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_gestion_de_empleados);

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
        activityMap.put("ControlDeEmpleados", admControlDePersonal.class);
        activityMap.put("ControlDePagos", admControlDePagos.class);
    }

    private void initializeCardViews() {
        cardControlDeEmpleados = findViewById(R.id.cardControlDeEmpleados);
        cardControlDePagos = findViewById(R.id.cardControlDePagos);

        cardControlDeEmpleados.setTag("ControlDeEmpleados");
        cardControlDePagos.setTag("ControlDePagos");
    }

    private void setOnClickListeners() {
        cardControlDeEmpleados.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardControlDePagos.setOnClickListener(v -> launchActivity(v.getTag().toString()));
    }

    private void launchActivity(String activityName) {
        Intent intent = new Intent(this, activityMap.get(activityName));
        startActivity(intent);
    }
}
