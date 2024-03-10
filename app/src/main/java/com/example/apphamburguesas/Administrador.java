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
    private CardView[] cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        initializeActivityMap();
        initializeCardViews();

        setOnClickListeners();
    }

    private void initializeActivityMap() {
        activityMap.put("ManejarEmpresa", admManejarEmpresa.class);
        activityMap.put("GestionDeEmpleados", admGestionDeEmpleados.class);
        activityMap.put("GestionDeProductos", admGestionarProductos.class);
        activityMap.put("GestionarDeReversiones", admGestionDeReversiones.class);
        activityMap.put("Reportes", admReportes.class);
        activityMap.put("AppWeb", admAppWeb.class);
        activityMap.put("PuntosDeRecompensa", admPuntosDeRecompensa.class);
        activityMap.put("Facturacion", admFacturacion.class);

    }

    private void initializeCardViews() {
        cards = new CardView[]{
                findViewById(R.id.cardManejarEmpresa),
                findViewById(R.id.cardGestionDeEmpleados),
                findViewById(R.id.cardGestionarProductos),
                findViewById(R.id.cardGestionDeReversiones),
                findViewById(R.id.cardReportes),
                findViewById(R.id.cardAppWeb),
                findViewById(R.id.cardPuntosDeRecompensa),
                findViewById(R.id.cardFacturacion)
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
