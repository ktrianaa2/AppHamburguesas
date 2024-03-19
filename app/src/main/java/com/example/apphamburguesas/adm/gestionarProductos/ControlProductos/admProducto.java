package com.example.apphamburguesas.adm.gestionarProductos.ControlProductos;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apphamburguesas.R;

public class admProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_controlproductos_producto);

        ImageView imageViewFlecha = findViewById(R.id.flechaRetroceder);
        imageViewFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la anterior
            }
        });

    }
}
