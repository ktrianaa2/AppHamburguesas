package com.example.apphamburguesas.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.apphamburguesas.Modelos.TipoProducto;
import com.example.apphamburguesas.R;
import com.example.apphamburguesas.adm.gestionarProductos.ControlProductos.admTipoProducto;

public class EditarTipoProductoDialog extends DialogFragment {
    private TipoProducto tipoProducto;

    public EditarTipoProductoDialog(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_editar_tipo_producto, null);

        EditText nombreEditText = view.findViewById(R.id.editarTextNombreTipoProducto);
        EditText descripcionEditText = view.findViewById(R.id.editarTextDescripcionTipoProducto);

        // Establecer los valores actuales del tipo de producto en el diálogo
        nombreEditText.setText(tipoProducto.getTpnombre());
        descripcionEditText.setText(tipoProducto.getDescripcion());

        builder.setView(view)
                .setTitle("Editar Tipo de Producto")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nuevoNombre = nombreEditText.getText().toString().trim();
                        String nuevaDescripcion = descripcionEditText.getText().toString().trim();

                        // Aquí llamamos al método editarTipoProducto
                        if (getActivity() instanceof admTipoProducto) {
                            admTipoProducto activity = (admTipoProducto) getActivity();
                            int tipoProductoId = tipoProducto.getId(); // Supongamos que tienes un método getId() en tu clase TipoProducto para obtener el ID
                            activity.editarTipoProducto(tipoProductoId, nuevoNombre, nuevaDescripcion);
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Cierra el diálogo
                        dismiss();
                    }
                });
        return builder.create();
    }
}
