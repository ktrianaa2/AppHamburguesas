package com.example.apphamburguesas.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apphamburguesas.Modelos.TipoProducto;
import com.example.apphamburguesas.R;
import java.util.List;

public class TipoProductoAdapter extends RecyclerView.Adapter<TipoProductoAdapter.TipoProductoViewHolder> {

    private List<TipoProducto> tiposProductos;
    private OnEditarClickListener editarClickListener;

    public TipoProductoAdapter(List<TipoProducto> tiposProductos, OnEditarClickListener editarClickListener) {
        this.tiposProductos = tiposProductos;
        this.editarClickListener = editarClickListener;
    }

    @NonNull
    @Override
    public TipoProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tipo_producto, parent, false);
        return new TipoProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipoProductoViewHolder holder, int position) {
        TipoProducto tipoProducto = tiposProductos.get(position);
        holder.nombreTextView.setText(tipoProducto.getTpnombre());
        holder.descripcionTextView.setText(tipoProducto.getDescripcion());

        holder.editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editarClickListener != null) {
                    editarClickListener.onEditarClick(tipoProducto);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return tiposProductos.size();
    }

    static class TipoProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, descripcionTextView;
        Button editarButton;

        TipoProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
            editarButton = itemView.findViewById(R.id.editarButton);
        }
    }

    // Interfaz para manejar los clics de edición
    public interface OnEditarClickListener {
        void onEditarClick(TipoProducto tipoProducto);
    }
}
