package com.example.apphamburguesas.adm.gestionarProductos.ControlProductos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphamburguesas.Adaptadores.UnidadMedidaAdapter;
import com.example.apphamburguesas.Fragment.CrearUnidadMedidaDialog;
import com.example.apphamburguesas.Interfaces.ApiService;
import com.example.apphamburguesas.Modelos.UnidadMedida;
import com.example.apphamburguesas.Modelos.UnidadMedidaResponse;
import com.example.apphamburguesas.R;
import com.example.apphamburguesas.Modelos.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class admUnidadMedida extends AppCompatActivity implements CrearUnidadMedidaDialog.CrearUnidadMedidaListener {

    private RecyclerView recyclerView;
    private UnidadMedidaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_controlproductos_unidad_medida);

        recyclerView = findViewById(R.id.recyclerViewUnidadesMedida);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1)); // Cambiar a un GridLayoutManager con 2 columnas
        adapter = new UnidadMedidaAdapter();
        recyclerView.setAdapter(adapter);

        ImageView imageViewFlecha = findViewById(R.id.flechaRetroceder);
        imageViewFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y regresa a la anterior
            }
        });

        Button crearUnidadMedidaButton = findViewById(R.id.button);
        crearUnidadMedidaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoCrearUnidadMedida();
            }
        });

        // Llama a la API para obtener la lista de unidades de medida al iniciar la actividad
        actualizarListaUnidadesMedida();
    }

    private void mostrarDialogoCrearUnidadMedida() {
        CrearUnidadMedidaDialog dialog = new CrearUnidadMedidaDialog();
        dialog.show(getSupportFragmentManager(), "CrearUnidadMedidaDialog");
    }

    @Override
    public void onUnidadMedidaCreated(String nombreUnidadMedida) {
        // Aquí puedes manejar la creación de la nueva unidad de medida
        // por ejemplo, puedes enviar los datos al servidor
        // y luego actualizar la lista de unidades de medida en el RecyclerView
        // Llama a la API para crear una nueva unidad de medida
        // Después, actualiza la lista de unidades de medida llamando a actualizarListaUnidadesMedida()
    }

    private void actualizarListaUnidadesMedida() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<UnidadMedidaResponse> call = apiService.listarUnidadesMedida();
        call.enqueue(new Callback<UnidadMedidaResponse>() {
            @Override
            public void onResponse(Call<UnidadMedidaResponse> call, Response<UnidadMedidaResponse> response) {
                if (response.isSuccessful()) {
                    List<UnidadMedida> unidadesMedida = response.body().getUnidadesMedida();
                    adapter.setUnidadesMedida(unidadesMedida);
                } else {
                    // Manejar error
                }
            }

            @Override
            public void onFailure(Call<UnidadMedidaResponse> call, Throwable t) {
                // Manejar error de conexión
            }
        });
    }
}
