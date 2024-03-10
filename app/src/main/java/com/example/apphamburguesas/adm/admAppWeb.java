package com.example.apphamburguesas.adm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apphamburguesas.Administrador;
import com.example.apphamburguesas.R;
import com.example.apphamburguesas.adm.appWeb.admAdministrarAnuncios;

import java.util.HashMap;
import java.util.Map;

public class admAppWeb extends AppCompatActivity {

    private Map<String, Class<?>> activityMap = new HashMap<>();
    private CardView[] cards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_app_web);

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
        activityMap.put("AdministrarAnuncios", admAdministrarAnuncios.class);

    }

    private void initializeCardViews() {
        cards = new CardView[]{
                findViewById(R.id.cardVisitar),
                findViewById(R.id.cardAnuncios)
        };

        for (int i = 0; i < cards.length && i < activityMap.size(); i++) {
            String key = (String) activityMap.keySet().toArray()[i];
            cards[i].setTag(key);
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
        if (activityName.equals("Visitar")) {
            // Si el CardView clicado es el de "Visitar" Aqui se redirigirá
            showToast("Yendo al sitio web");
        } else {
            // Si no, inicia la actividad correspondiente
            Intent intent = new Intent(this, activityMap.get(activityName));
            startActivity(intent);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}