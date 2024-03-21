package com.example.apphamburguesas.adm.gestionarProductos.ControlProductos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphamburguesas.Adaptadores.TipoProductoAdapter;
import com.example.apphamburguesas.Fragment.CrearTipoProductoDialog;
import com.example.apphamburguesas.Fragment.EditarTipoProductoDialog;
import com.example.apphamburguesas.Interfaces.ApiService;
import com.example.apphamburguesas.Modelos.NuevoTipoProducto;
import com.example.apphamburguesas.Modelos.TipoProducto;
import com.example.apphamburguesas.Modelos.TipoProductoResponse;
import com.example.apphamburguesas.Modelos.RetrofitClient;
import com.example.apphamburguesas.Modelos.EditarTipoProductoRequest;
import com.example.apphamburguesas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admTipoProducto extends AppCompatActivity implements CrearTipoProductoDialog.CrearTipoProductoListener, TipoProductoAdapter.OnEditarClickListener {

    private RecyclerView recyclerView;
    private TipoProductoAdapter adapter;
    private Button crearTipoProductoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_controlproductos_tipo_producto);

        recyclerView = findViewById(R.id.recyclerViewTiposProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cargarTiposProductos();

        crearTipoProductoButton = findViewById(R.id.button2);
        crearTipoProductoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoCrearTipoProducto();
            }
        });
    }

    private void cargarTiposProductos() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<TipoProductoResponse> call = apiService.listarTiposProductos();
        call.enqueue(new Callback<TipoProductoResponse>() {
            @Override
            public void onResponse(Call<TipoProductoResponse> call, Response<TipoProductoResponse> response) {
                if (response.isSuccessful()) {
                    TipoProductoResponse tipoProductoResponse = response.body();
                    if (tipoProductoResponse != null) {
                        List<TipoProducto> tiposProductos = tipoProductoResponse.getTiposProductos();
                        if (tiposProductos != null && !tiposProductos.isEmpty()) {
                            adapter = new TipoProductoAdapter(tiposProductos, admTipoProducto.this);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.e("Error", "La lista de tipos de productos está vacía o nula");
                        }
                    } else {
                        Log.e("Error", "Respuesta de la API es nula");
                    }
                } else {
                    Log.e("Error", "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TipoProductoResponse> call, Throwable t) {
                // Manejar fallos en la conexión
                Log.e("Error", "Error al cargar los tipos de productos: " + t.getMessage());
            }
        });
    }

    private void mostrarDialogoCrearTipoProducto() {
        DialogFragment dialog = new CrearTipoProductoDialog();
        dialog.show(getSupportFragmentManager(), "CrearTipoProductoDialog");
    }

    @Override
    public void onTipoProductoCreated(String nombreTipoProducto, String descripcionTipoProducto) {
        // Validar entrada
        if (nombreTipoProducto.isEmpty()) {
            Toast.makeText(this, "Debe ingresar el nombre del tipo de producto", Toast.LENGTH_SHORT).show();
            return;
        }

        // Enviar datos al servidor para crear el tipo de producto
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.crearTipoProducto(new NuevoTipoProducto(nombreTipoProducto, descripcionTipoProducto));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Tipo de producto creado exitosamente
                    Log.d("TipoProducto", "Tipo de producto creado con éxito");
                    // Mostrar mensaje de éxito al usuario
                    Toast.makeText(admTipoProducto.this, "Tipo de producto creado con éxito", Toast.LENGTH_SHORT).show();
                    // Actualizar la interfaz de usuario si es necesario (por ejemplo, volver a cargar la lista de tipos de productos)
                } else {
                    // Manejar errores de la API
                    Log.e("Error", "Error en la respuesta: " + response.code());
                    Toast.makeText(admTipoProducto.this, "Error al crear el tipo de producto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar fallos en la conexión
                Log.e("Error", "Error al crear el tipo de producto: " + t.getMessage());
                Toast.makeText(admTipoProducto.this, "Error al crear el tipo de producto", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onEditarClick(TipoProducto tipoProducto) {
        // Mostrar el diálogo de edición al hacer clic en el botón "Editar"
        DialogFragment dialog = new EditarTipoProductoDialog(tipoProducto);
        dialog.show(getSupportFragmentManager(), "EditarTipoProductoDialog");
    }

    // Método para editar el tipo de producto
    public void editarTipoProducto(int tipoProductoId, String nombre, String descripcion) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.editarTipoProducto(tipoProductoId, new EditarTipoProductoRequest(nombre, descripcion));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Tipo de producto editado exitosamente
                    Log.d("TipoProducto", "Tipo de producto editado con éxito");
                    // Mostrar mensaje de éxito al usuario
                    Toast.makeText(admTipoProducto.this, "Tipo de producto editado con éxito", Toast.LENGTH_SHORT).show();
                    // Actualizar la interfaz de usuario si es necesario
                } else {
                    // Manejar errores de la API
                    Log.e("Error", "Error en la respuesta: " + response.code());
                    Toast.makeText(admTipoProducto.this, "Error al editar el tipo de producto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar fallos en la conexión
                Log.e("Error", "Error al editar el tipo de producto: " + t.getMessage());
                Toast.makeText(admTipoProducto.this, "Error al editar el tipo de producto", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
