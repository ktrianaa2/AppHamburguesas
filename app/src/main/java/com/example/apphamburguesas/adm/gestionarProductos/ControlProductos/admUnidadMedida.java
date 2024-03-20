package com.example.apphamburguesas.adm.gestionarProductos.ControlProductos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphamburguesas.Adaptadores.UnidadMedidaAdapter;
import com.example.apphamburguesas.Fragment.CrearUnidadMedidaDialog;
import com.example.apphamburguesas.Fragment.EditarUnidadMedidaDialog;
import com.example.apphamburguesas.Interfaces.ApiService;
import com.example.apphamburguesas.Modelos.EditarUnidadMedidaRequest;
import com.example.apphamburguesas.Modelos.RetrofitClient;
import com.example.apphamburguesas.Modelos.UnidadMedida;
import com.example.apphamburguesas.Modelos.UnidadMedidaResponse;
import com.example.apphamburguesas.R;

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
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new UnidadMedidaAdapter();
        recyclerView.setAdapter(adapter);

        ImageView imageViewFlecha = findViewById(R.id.flechaRetroceder);
        imageViewFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button crearUnidadMedidaButton = findViewById(R.id.button);
        crearUnidadMedidaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoCrearUnidadMedida();
            }
        });

        adapter.setOnItemClickListener(new UnidadMedidaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UnidadMedida unidadMedida) {
                mostrarDialogoEditarUnidadMedida(unidadMedida);
            }
        });

        actualizarListaUnidadesMedida();
    }

    public void mostrarDialogoCrearUnidadMedida() {
        CrearUnidadMedidaDialog dialog = new CrearUnidadMedidaDialog();
        dialog.show(getSupportFragmentManager(), "CrearUnidadMedidaDialog");
    }

    public void mostrarDialogoEditarUnidadMedida(UnidadMedida unidadMedida) {
        Log.d("EditarUnidadMedida", "Mostrar diálogo de edición de unidad de medida");
        DialogFragment dialogFragment = EditarUnidadMedidaDialog.newInstance(unidadMedida.getIdUm(), unidadMedida.getNombreUm());
        dialogFragment.show(getSupportFragmentManager(), "EditarUnidadMedidaDialog");
    }



    public void editarUnidadMedida(int unidadId, String nuevoNombre) {
        Log.d("EditarUnidadMedida", "Editando unidad de medida con ID: $unidadId y nuevo nombre: $nuevoNombre");
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.editarUnidadMedida(unidadId, nuevoNombre);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("EditarUnidadMedida", "Código de respuesta: " + response.code());
                    Log.d("EditarUnidadMedida", "Mensaje de respuesta: " + response.message());
                    actualizarListaUnidadesMedida();
                    Toast.makeText(admUnidadMedida.this, "Unidad de medida editada con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("EditarUnidadMedida", "Error al editar la unidad de medida: " + response.code() + " " + response.message());
                    Toast.makeText(admUnidadMedida.this, "Error al editar la unidad de medida", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(admUnidadMedida.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onUnidadMedidaCreated(String nombreUnidadMedida) {
        crearUnidadMedida(nombreUnidadMedida);
    }

    public void crearUnidadMedida(String nombreUnidadMedida) {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        UnidadMedida nuevaUnidadMedida = new UnidadMedida(0, nombreUnidadMedida);
        Call<Void> call = apiService.crearUnidadMedida(nuevaUnidadMedida);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    actualizarListaUnidadesMedida();
                } else {
                    Toast.makeText(admUnidadMedida.this, "Error al crear la unidad de medida", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(admUnidadMedida.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void actualizarListaUnidadesMedida() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<UnidadMedidaResponse> call = apiService.listarUnidadesMedida();
        call.enqueue(new Callback<UnidadMedidaResponse>() {
            @Override
            public void onResponse(Call<UnidadMedidaResponse> call, Response<UnidadMedidaResponse> response) {
                if (response.isSuccessful()) {
                    List<UnidadMedida> unidadesMedida = response.body().getUnidadesMedida();
                    adapter.setUnidadesMedida(unidadesMedida);
                    adapter.notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
                } else {
                    Toast.makeText(admUnidadMedida.this, "Error al obtener la lista de unidades de medida", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UnidadMedidaResponse> call, Throwable t) {
                Toast.makeText(admUnidadMedida.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
