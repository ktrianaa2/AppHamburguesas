package com.example.apphamburguesas.adm.gestionDeEmpleados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.apphamburguesas.R;
import com.example.apphamburguesas.adm.gestionDeEmpleados.ControlPersonal.admControlAdministrador;
import com.example.apphamburguesas.adm.gestionDeEmpleados.ControlPersonal.admControlCrearEmpleado;
import com.example.apphamburguesas.adm.gestionDeEmpleados.ControlPersonal.admControlJefeCocina;
import com.example.apphamburguesas.adm.gestionDeEmpleados.ControlPersonal.admControlMesero;
import com.example.apphamburguesas.adm.gestionDeEmpleados.ControlPersonal.admControlMotorizado;
import com.example.apphamburguesas.adm.gestionarProductos.admBodegas;
import com.example.apphamburguesas.adm.gestionarProductos.admCocina;
import com.example.apphamburguesas.adm.gestionarProductos.admCombos;
import com.example.apphamburguesas.adm.gestionarProductos.admInventario;
import com.example.apphamburguesas.adm.gestionarProductos.admProductos;

import java.util.HashMap;
import java.util.Map;

public class admControlDePersonal extends AppCompatActivity {

    private Map<String, Class<?>> activityMap = new HashMap<>();
    private CardView cardControlAdministrador, cardControlMotorizado, cardControlMeseros, cardControlJefeCocinas, cardAgregarEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_control_de_personal);

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
        activityMap.put("ControlAdministrador", admControlAdministrador.class);
        activityMap.put("ControlMotorizado", admControlMotorizado.class);
        activityMap.put("ControlMeseros", admControlMesero.class);
        activityMap.put("JefeCocinas", admControlJefeCocina.class);
        activityMap.put("AgregarEmpleados", admControlCrearEmpleado.class);
    }

    private void initializeCardViews() {
        cardControlAdministrador = findViewById(R.id.cardControlAdministrador);
        cardControlMotorizado = findViewById(R.id.cardControlMotorizado);
        cardControlMeseros = findViewById(R.id.cardControlMeseros);
        cardControlJefeCocinas = findViewById(R.id.cardControlJefeCocinas);
        cardAgregarEmpleados = findViewById(R.id.cardAgregarEmpleados);

        cardControlAdministrador.setTag("ControlAdministrador");
        cardControlMotorizado.setTag("ControlMotorizado");
        cardControlMeseros.setTag("ControlMeseros");
        cardControlJefeCocinas.setTag("JefeCocinas");
        cardAgregarEmpleados.setTag("AgregarEmpleados");
    }

    private void setOnClickListeners() {
        cardControlAdministrador.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardControlMotorizado.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardControlMeseros.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardControlJefeCocinas.setOnClickListener(v -> launchActivity(v.getTag().toString()));
        cardAgregarEmpleados.setOnClickListener(v -> launchActivity(v.getTag().toString()));
    }

    private void launchActivity(String activityName) {
        Intent intent = new Intent(this, activityMap.get(activityName));
        startActivity(intent);
    }
}