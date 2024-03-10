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
import com.example.apphamburguesas.Modelos.RolResponse;
import com.example.apphamburguesas.Modelos.TokenRequest;

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

        // Obtener referencias a los campos de texto de usuario y contraseña
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        // Configurar Retrofit para la comunicación con la API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wv7jhxv6-8000.brs.devtunnels.ms/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        // Obtener referencias a los botones de inicio de sesión, contraseña olvidada y registro
        TextView loginButton = findViewById(R.id.loginbtn);
        TextView forgotPasswordText = findViewById(R.id.forgotpass);
        TextView registerText = findViewById(R.id.textregister);

        // Configurar el listener de clic para el botón de inicio de sesión
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validar que los campos no estén vacíos
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(IniciarSesion.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Llamar al método de inicio de sesión de la API
                    Call<LoginResponse> call = apiService.login(new LoginRequest(username, password));
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                LoginResponse loginResponse = response.body();
                                if (loginResponse != null) {
                                    final String token = loginResponse.getToken();

                                    // Verificar el rol con otra llamada a la API
                                    Call<RolResponse> callRol = apiService.verificarRol(new TokenRequest(token));
                                    callRol.enqueue(new Callback<RolResponse>() {
                                        @Override
                                        public void onResponse(Call<RolResponse> call, Response<RolResponse> response) {
                                            if (response.isSuccessful()) {
                                                RolResponse rolResponse = response.body();
                                                if (rolResponse != null && "S".equals(rolResponse.getRol())) {
                                                    String token = loginResponse.getToken();
                                                    String nombreUsuario = loginResponse.getNombreusuario();
                                                    int idCuenta = loginResponse.getId_cuenta();

                                                    mostrarNotificacion();

                                                    // Navegar a la actividad principal si el rol es correcto
                                                    Intent intent = new Intent(IniciarSesion.this, Administrador.class);
                                                    intent.putExtra("idCliente", idCuenta); // Pasar el idCuenta
                                                    startActivity(intent);
                                                    finish(); // Cerrar esta actividad

                                                    // Guardar el idCuenta en SharedPreferences
                                                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                                    myEdit.putInt("id_cuenta", loginResponse.getId_cuenta());
                                                    myEdit.apply();
                                                } else {
                                                    // Rol no permitido
                                                    Toast.makeText(IniciarSesion.this, "Esta cuenta no tiene el rol para acceder.", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(IniciarSesion.this, "Error al verificar el rol", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<RolResponse> call, Throwable t) {
                                            Toast.makeText(IniciarSesion.this, "Error en la red", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(IniciarSesion.this, "Error en inicio de sesión", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(IniciarSesion.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        // Configurar el listener de clic para el botón de contraseña olvidada
/*        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarSesionActivity.this, OlvidoContrasenaActivity.class);
                startActivity(intent);
            }
        });*/

        // Configurar el listener de clic para el botón de registro
       /* registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IniciarSesionActivity.this, RegistrarseActivity.class);
                startActivity(intent);
            }
        });*/
    }

    // Método para mostrar una notificación al usuario
    private void mostrarNotificacion() {
        Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
    }
}