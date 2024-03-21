package com.example.apphamburguesas.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apphamburguesas.Modelos.TipoProducto;
import com.example.apphamburguesas.R;

import java.util.List;

public class TipoProductoAdapter extends RecyclerView.Adapter<TipoProductoAdapter.TipoProductoViewHolder> {
    private List<TipoProducto> tiposProductos;

    public TipoProductoAdapter(List<TipoProducto> tiposProductos) {
        this.tiposProductos = tiposProductos;
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
    }

    @Override
    public int getItemCount() {
        return tiposProductos.size();
    }

    public static class TipoProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, descripcionTextView;

        public TipoProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
        }
    }
}
