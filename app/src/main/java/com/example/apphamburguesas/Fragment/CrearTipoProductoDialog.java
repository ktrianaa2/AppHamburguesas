package com.example.apphamburguesas.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.apphamburguesas.R;

public class CrearTipoProductoDialog extends DialogFragment {

    public interface CrearTipoProductoListener {
        void onTipoProductoCreated(String nombreTipoProducto, String descripcionTipoProducto);
    }

    private EditText nombreTipoProductoEditText;
    private EditText descripcionTipoProductoEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_crear_tipo_producto, null);

        nombreTipoProductoEditText = view.findViewById(R.id.editTextNombreTipoProducto);
        descripcionTipoProductoEditText = view.findViewById(R.id.editTextDescripcionTipoProducto);

        builder.setView(view)
                .setTitle("Crear Tipo de Producto")
                .setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombreTipoProducto = nombreTipoProductoEditText.getText().toString();
                        String descripcionTipoProducto = descripcionTipoProductoEditText.getText().toString();
                        CrearTipoProductoListener listener = (CrearTipoProductoListener) getActivity();
                        listener.onTipoProductoCreated(nombreTipoProducto, descripcionTipoProducto);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        return builder.create();
    }
}

