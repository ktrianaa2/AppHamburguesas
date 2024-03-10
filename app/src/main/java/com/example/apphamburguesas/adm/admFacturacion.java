package com.example.apphamburguesas.adm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.apphamburguesas.R;
import com.example.apphamburguesas.adm.facturacion.admValidarPagos;
import com.example.apphamburguesas.adm.gestionDeEmpleados.admControlDePagos;

import java.util.HashMap;
import java.util.Map;

public class admFacturacion extends AppCompatActivity {

    private Map<String, Class<?>> activityMap = new HashMap<>();
    private CardView[] cards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_facturacion);

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
        activityMap.put("ValidarPagos", admValidarPagos.class);
    }

    private void initializeCardViews() {
        cards = new CardView[]{
                findViewById(R.id.cardValidarPagos),
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