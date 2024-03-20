package com.example.apphamburguesas.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.apphamburguesas.R;
import com.example.apphamburguesas.adm.gestionarProductos.ControlProductos.admUnidadMedida;

public class EditarUnidadMedidaDialog extends DialogFragment {

    private static final String ARG_UNIDAD_ID = "unidadId";
    private static final String ARG_NOMBRE_UNIDAD = "nombreUnidad";

    private int unidadId;
    private String nombreUnidad;

    private EditText editTextNombre;

    public static EditarUnidadMedidaDialog newInstance(int unidadId, String nombreUnidad) {
        EditarUnidadMedidaDialog fragment = new EditarUnidadMedidaDialog();
        Bundle args = new Bundle();
        args.putInt(ARG_UNIDAD_ID, unidadId);
        args.putString(ARG_NOMBRE_UNIDAD, nombreUnidad);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            unidadId = getArguments().getInt(ARG_UNIDAD_ID);
            nombreUnidad = getArguments().getString(ARG_NOMBRE_UNIDAD);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_editar_unidad_medida, null);
        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextNombre.setText(nombreUnidad);

        builder.setView(view)
                .setTitle("Editar Unidad de Medida")
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String nuevoNombre = editTextNombre.getText().toString().trim();
                    int unidadId = getArguments().getInt(ARG_UNIDAD_ID);
                    editarUnidadMedidaDesdeActividad(unidadId, nuevoNombre);
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dismiss());

        return builder.create();
    }

    private void editarUnidadMedidaDesdeActividad(int unidadId, String nuevoNombre) {
        if (getActivity() instanceof admUnidadMedida) {
            ((admUnidadMedida) getActivity()).editarUnidadMedida(unidadId, nuevoNombre);
        } else {
            Toast.makeText(getContext(), "Error: La actividad no es instancia de admUnidadMedida", Toast.LENGTH_SHORT).show();
        }
    }
}
