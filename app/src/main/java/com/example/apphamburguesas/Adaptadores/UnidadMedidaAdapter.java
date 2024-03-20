package com.example.apphamburguesas.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphamburguesas.Modelos.UnidadMedida;
import com.example.apphamburguesas.R;

import java.util.List;

public class UnidadMedidaAdapter extends RecyclerView.Adapter<UnidadMedidaAdapter.UnidadMedidaViewHolder> {
    private List<UnidadMedida> unidadesMedida;

    public void setUnidadesMedida(List<UnidadMedida> unidadesMedida) {
        this.unidadesMedida = unidadesMedida;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UnidadMedidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unidad_medida, parent, false);
        return new UnidadMedidaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnidadMedidaViewHolder holder, int position) {
        UnidadMedida unidadMedida = unidadesMedida.get(position);
        holder.bind(unidadMedida);
        holder.editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí manejas la acción de editar, por ejemplo, puedes abrir una nueva actividad
                // para la edición pasando la ID de la unidad de medida a la actividad.
                Toast.makeText(v.getContext(), "Editar unidad de medida: " + unidadMedida.getIdUm(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return unidadesMedida != null ? unidadesMedida.size() : 0;
    }

    static class UnidadMedidaViewHolder extends RecyclerView.ViewHolder {
        private TextView idUmTextView;
        private TextView nombreUmTextView;
        private Button editarButton;

        public UnidadMedidaViewHolder(@NonNull View itemView) {
            super(itemView);
            idUmTextView = itemView.findViewById(R.id.idUmTextView);
            nombreUmTextView = itemView.findViewById(R.id.nombreUmTextView);
            editarButton = itemView.findViewById(R.id.editarButton);
        }

        public void bind(UnidadMedida unidadMedida) {
            idUmTextView.setText("ID: " + unidadMedida.getIdUm());
            nombreUmTextView.setText("Nombre: " + unidadMedida.getNombreUm());
        }
    }
}
