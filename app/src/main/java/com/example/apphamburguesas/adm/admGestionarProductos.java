package com.example.apphamburguesas.adm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.apphamburguesas.R;
import com.example.apphamburguesas.adm.gestionarProductos.admBodegas;
import com.example.apphamburguesas.adm.gestionarProductos.admCocina;
import com.example.apphamburguesas.adm.gestionarProductos.admCombos;
import com.example.apphamburguesas.adm.gestionarProductos.admInventario;
import com.example.apphamburguesas.adm.gestionarProductos.admProductos;

import java.util.HashMap;
import java.util.Map;

public class admGestionarProductos extends AppCompatActivity {

    private Map<String, Class<?>> activityMap = new HashMap<>();
    private CardView cardBodegas, cardCocina, cardProductos, cardInventario, cardCombos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_gestionar_productos);

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
        activityMap.put("Bodegas", admBodegas.class);
        activityMap.put("Cocina", admCocina.class);
        activityMap.put("Productos", admProductos.class);
        activityMap.put("Inventario", admInventario.class);
        activityMap.put("Combos", admCombos.class);
    }

    private void initializeCardViews() {
        cardBodegas = findViewById(R.id.cardBodegas);
        cardCocina = findViewById(R.id.cardCocina);
        cardProductos = findViewById(R.id.cardProductos);
        cardInventario = findViewById(R.id.cardInventario);
        cardCombos = findViewById(R.id.cardCombos);

        cardBodegas.setTag("Bodegas");
        cardCocina.setTag("Cocina");
        cardProductos.setTag("Productos");
        cardInventario.setTag("Inventario");
        cardCombos.setTag("Combos");
    }

    private void setOnClickListeners() {
        cardBodegas.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardCocina.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardProductos.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardInventario.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardCombos.setOnClickListener(v -> launchActivity(v.getTag().toString()));
    }

    private void launchActivity(String activityName) {
        Intent intent = new Intent(this, activityMap.get(activityName));
        startActivity(intent);
    }
}
