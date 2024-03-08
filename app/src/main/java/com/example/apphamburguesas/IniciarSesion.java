package com.example.apphamburguesas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import android.content.SharedPreferences;

import android.text.TextUtils;

import android.widget.EditText;


import com.example.apphamburguesas.Interfaces.ApiService;
import com.example.apphamburguesas.Modelos.LoginRequest;
import com.example.apphamburguesas.Modelos.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IniciarSesion extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000/") // Cambia esta URL por la URL base de tu API de Django
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        TextView loginButton = findViewById(R.id.loginbtn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(IniciarSesion.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Call<LoginResponse> call = apiService.login(new LoginRequest(username, password));
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                LoginResponse loginResponse = response.body();
                                if (loginResponse != null) {
                                    String token = loginResponse.getToken();
                                    String nombreUsuario = loginResponse.getNombreusuario();
                                    int idCuenta = loginResponse.getId_cuenta();

                                    // Guardar el token y el idCuenta en SharedPreferences
                                    guardarDatosEnSharedPreferences(token, idCuenta);

                                    // Mostrar notificación de inicio de sesión correcto
                                    mostrarNotificacion();

                                    // Preparar el intent con idCuenta
                                    Intent intent = new Intent(IniciarSesion.this, Administrador.class);
                                    intent.putExtra("idCliente", idCuenta); // Pasar el idCuenta
                                    startActivity(intent);
                                    finish(); // Cerrar esta actividad
                                }
                            } else {
                                Toast.makeText(IniciarSesion.this, "Error en inicio de sesión", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(IniciarSesion.this, "Error en inicio de sesión", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void mostrarNotificacion() {
        Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
    }

    private void guardarDatosEnSharedPreferences(String token, int idCuenta) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.putInt("id_cuenta", idCuenta);
        editor.apply();
    }
}