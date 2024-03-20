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

public class CrearUnidadMedidaDialog extends DialogFragment {

    public interface CrearUnidadMedidaListener {
        void onUnidadMedidaCreated(String nombreUnidadMedida);
    }

    private EditText nombreUnidadMedidaEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_crear_unidad_medida, null);
        builder.setView(view)
                .setTitle("Crear Unidad de Medida")
                .setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombreUnidadMedida = nombreUnidadMedidaEditText.getText().toString();
                        CrearUnidadMedidaListener listener = (CrearUnidadMedidaListener) getActivity();
                        listener.onUnidadMedidaCreated(nombreUnidadMedida);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        nombreUnidadMedidaEditText = view.findViewById(R.id.nombreUnidadMedidaEditText);

        return builder.create();
    }
}
