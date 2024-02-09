package com.example.apphamburguesas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apphamburguesas.adm.admAnuncios;
import com.example.apphamburguesas.adm.admBodegas;
import com.example.apphamburguesas.adm.admCocina;
import com.example.apphamburguesas.adm.admCombos;
import com.example.apphamburguesas.adm.admEmpleados;
import com.example.apphamburguesas.adm.admEmpresa;
import com.example.apphamburguesas.adm.admInventario;
import com.example.apphamburguesas.adm.admMesas;
import com.example.apphamburguesas.adm.admProductos;
import com.example.apphamburguesas.adm.admProveedores;
import com.example.apphamburguesas.adm.admRecompensas;
import com.example.apphamburguesas.adm.admSucursales;

public class Administrador extends AppCompatActivity {

    CardView Empresa;
    CardView Empleados;
    CardView Productos;
    CardView Combos;
    CardView Anuncios;
    CardView Mesas;
    CardView Recompensas;
    CardView Sucursales;
    CardView Bodegas;
    CardView Proveedores;
    CardView Inventario;
    CardView Cocina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        Empresa = findViewById(R.id.cardEmpresa);
        Empleados = findViewById(R.id.cardEmpleados);
        Productos = findViewById(R.id.cardProductos);
        Combos = findViewById(R.id.cardCombos);
        Anuncios = findViewById(R.id.cardAnuncios);
        Mesas = findViewById(R.id.cardMesas);
        Recompensas = findViewById(R.id.cardRecompensas);
        Sucursales = findViewById(R.id.cardSucursales);
        Bodegas = findViewById(R.id.cardBodega);
        Proveedores = findViewById(R.id.cardProveedores);
        Inventario = findViewById(R.id.cardInventario);
        Cocina = findViewById(R.id.cardCocina);

        Empresa.setTag("Empresa");
        Empleados.setTag("Empleados");
        Productos.setTag("Productos");
        Combos.setTag("Combos");
        Anuncios.setTag("Anuncios");
        Mesas.setTag("Mesas");
        Recompensas.setTag("Recompensas");
        Sucursales.setTag("Sucursales");
        Bodegas.setTag("Bodegas");
        Proveedores.setTag("Proveedores");
        Inventario.setTag("Inventario");
        Cocina.setTag("Cocina");

        Empresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Empleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Combos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Anuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Mesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Recompensas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Sucursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Bodegas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Proveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Inventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });

        Cocina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });
    }

    private void launchActivity(String activityName) {
        Intent intent = new Intent(this, getActivityClass(activityName));
        startActivity(intent);
    }

    private Class<?> getActivityClass(String activityName) {
        switch (activityName) {
            case "Empresa":
                return admEmpresa.class;
            case "Empleados":
                return admEmpleados.class;
            case "Productos":
                return admProductos.class;
            case "Combos":
                return admCombos.class;
            case "Anuncios":
                return admAnuncios.class;
            case "Mesas":
                return admMesas.class;
            case "Recompensas":
                return admRecompensas.class;
            case "Sucursales":
                return admSucursales.class;
            case "Bodegas":
                return admBodegas.class;
            case "Proveedores":
                return admProveedores.class;
            case "Inventario":
                return admInventario.class;
            case "Cocina":
                return admCocina.class;
            default:
                return null;
        }
    }
}
