package com.example.apphamburguesas.adm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.apphamburguesas.Administrador;
import com.example.apphamburguesas.R;

public class admEmpresa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_empresa);

        ImageView imageViewFlecha = findViewById(R.id.imageView2);

        imageViewFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent explícito para la actividad Administrador
                Intent intent = new Intent(admEmpresa.this, Administrador.class);

                // Agrega la bandera FLAG_ACTIVITY_CLEAR_TOP para eliminar todas las actividades de la pila
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Inicia la actividad Administrador
                startActivity(intent);

                // Finaliza la actividad actual
                finish();
            }
        });

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la actividad de edición al hacer clic en el botón de editar
                Intent intent = new Intent(admEmpresa.this, admEmpresaEditar.class);
                startActivity(intent);
            }
        });

    }
}