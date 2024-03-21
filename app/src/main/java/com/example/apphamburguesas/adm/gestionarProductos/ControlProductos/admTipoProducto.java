package com.example.apphamburguesas.adm.gestionarProductos.ControlProductos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphamburguesas.Adaptadores.TipoProductoAdapter;
import com.example.apphamburguesas.Interfaces.ApiService;
import com.example.apphamburguesas.Modelos.TipoProducto;
import com.example.apphamburguesas.Modelos.TipoProductoResponse;
import com.example.apphamburguesas.R;
import com.example.apphamburguesas.Modelos.RetrofitClient;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admTipoProducto extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TipoProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_controlproductos_tipo_producto);

        recyclerView = findViewById(R.id.recyclerViewTiposProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
                            adapter = new TipoProductoAdapter(tiposProductos);
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
            }
        });
    }
}

