package com.example.apphamburguesas.adm.gestionarProductos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.apphamburguesas.R;
import com.example.apphamburguesas.adm.gestionarProductos.ControlProductos.admArticulos;
import com.example.apphamburguesas.adm.gestionarProductos.ControlProductos.admCategoriaProducto;
import com.example.apphamburguesas.adm.gestionarProductos.ControlProductos.admProducto;
import com.example.apphamburguesas.adm.gestionarProductos.ControlProductos.admTipoProducto;
import com.example.apphamburguesas.adm.gestionarProductos.ControlProductos.admUnidadMedida;

import java.util.HashMap;
import java.util.Map;

public class admProductos extends AppCompatActivity {

    private Map<String, Class<?>> activityMap = new HashMap<>();
    private CardView cardProducto, cardCategorias, cardTipoProducto, cardUM, cardArticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_productos);

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
        activityMap.put("Producto", admProducto.class);
        activityMap.put("Categorias", admCategoriaProducto.class);
        activityMap.put("TipoProducto", admTipoProducto.class);
        activityMap.put("UM", admUnidadMedida.class);
        activityMap.put("Articulos", admArticulos.class);
    }

    private void initializeCardViews() {
        cardProducto = findViewById(R.id.cardProducto);
        cardCategorias = findViewById(R.id.cardCategorias);
        cardTipoProducto = findViewById(R.id.cardTipoProducto);
        cardUM = findViewById(R.id.cardUM);
        cardArticulos = findViewById(R.id.cardArticulos);

        cardProducto.setTag("Producto");
        cardCategorias.setTag("Categorias");
        cardTipoProducto.setTag("TipoProducto");
        cardUM.setTag("UM");
        cardArticulos.setTag("Articulos");
    }

    private void setOnClickListeners() {
        cardProducto.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardCategorias.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardTipoProducto.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardUM.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardArticulos.setOnClickListener(v -> launchActivity(v.getTag().toString()));
    }

    private void launchActivity(String activityName) {
        Intent intent = new Intent(this, activityMap.get(activityName));
        startActivity(intent);
    }
}