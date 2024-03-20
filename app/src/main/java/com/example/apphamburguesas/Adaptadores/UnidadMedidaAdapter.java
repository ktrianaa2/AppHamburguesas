package com.example.apphamburguesas.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphamburguesas.Modelos.UnidadMedida;
import com.example.apphamburguesas.R;

import java.util.ArrayList;
import java.util.List;

public class UnidadMedidaAdapter extends RecyclerView.Adapter<UnidadMedidaAdapter.ViewHolder> {
    private List<UnidadMedida> unidadesMedida = new ArrayList<>();
    private OnItemClickListener listener;

    public void setUnidadesMedida(List<UnidadMedida> unidadesMedida) {
        this.unidadesMedida = new ArrayList<>(unidadesMedida);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_unidad_medida, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UnidadMedida unidadMedida = unidadesMedida.get(position);
        holder.bind(unidadMedida);

        // Configuración del OnClickListener para el botón editarButton
        holder.editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(unidadMedida);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return unidadesMedida.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView idUmTextView;
        private TextView nombreUmTextView;
        private Button editarButton;

        public ViewHolder(@NonNull View itemView) {
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

    public interface OnItemClickListener {
        void onItemClick(UnidadMedida unidadMedida);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}