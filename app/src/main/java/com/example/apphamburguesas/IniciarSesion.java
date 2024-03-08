package com.example.apphamburguesas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        TextView user = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);

        MaterialButton loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = user.getText().toString();
                String passwordInput = password.getText().toString();

                if (validateCredentials(usernameInput, passwordInput)) {
                    openCorrespondingActivity(usernameInput);
                } else {
                    Toast.makeText(IniciarSesion.this, "Error en las credenciales", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateCredentials(String username, String password) {
        if ((username.equals("admin") || username.equals("ayudante") || username.equals("mesero") || username.equals("repartidor"))
                && password.equals("123456")) {
            return true;
        }
        return false;
    }

    private void openCorrespondingActivity(String username) {
        Intent intent;
        switch (username) {
            case "admin":
                intent = new Intent(IniciarSesion.this, Administrador.class);
                break;
            case "ayudante":
                intent = new Intent(IniciarSesion.this, AyudanteDeCocina.class);
                break;
            case "mesero":
                intent = new Intent(IniciarSesion.this, Mesero.class);
                break;
            case "repartidor":
                intent = new Intent(IniciarSesion.this, Repartidor.class);
                break;
            default:
                intent = null;
                break;
        }

        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(IniciarSesion.this, "Error en las credenciales", Toast.LENGTH_SHORT).show();
        }
    }

}