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
        activityMap.put("Empresa", admEmpresa.class);
        activityMap.put("Empleados", admEmpleados.class);
        activityMap.put("Productos", admProductos.class);
        activityMap.put("Combos", admCombos.class);
        activityMap.put("Anuncios", admAnuncios.class);
        activityMap.put("Mesas", admMesas.class);
        activityMap.put("Recompensas", admRecompensas.class);
        activityMap.put("Sucursales", admSucursales.class);
        activityMap.put("Bodegas", admBodegas.class);
        activityMap.put("Proveedores", admProveedores.class);
        activityMap.put("Inventario", admInventario.class);
        activityMap.put("Cocina", admCocina.class);
        activityMap.put("Pagos", admPagos.class);
        activityMap.put("Reversos", admReversos.class);
        activityMap.put("SRI", admSri.class);
        activityMap.put("Reportes", admReportes.class);
        activityMap.put("ValidarPagos", admValidarPagos.class);
        activityMap.put("Facturacion", admFacturacion.class);
        activityMap.put("DatosBancarios", admDatosBancarios.class);
    }

    private void initializeCardViews() {
        cards = new CardView[]{
                findViewById(R.id.cardProductos),
                findViewById(R.id.cardEmpleados),
                findViewById(R.id.cardEmpresa),
                findViewById(R.id.cardCombos),
                findViewById(R.id.cardAnuncios),
                findViewById(R.id.cardMesas),
                findViewById(R.id.cardRecompensas),
                findViewById(R.id.cardSucursales),
                findViewById(R.id.cardBodega),
                findViewById(R.id.cardProveedores),
                findViewById(R.id.cardInventario),
                findViewById(R.id.cardCocina),
                findViewById(R.id.cardPagos),
                findViewById(R.id.cardReversos),
                findViewById(R.id.cardSRI),
                findViewById(R.id.cardReportes),
                findViewById(R.id.cardValidarPagos),
                findViewById(R.id.cardFacturacion),
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
