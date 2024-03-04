package com.example.apphamburguesas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.apphamburguesas.cli.cliCarrito;
import com.example.apphamburguesas.cli.cliPerfil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Cliente extends AppCompatActivity {

    BottomNavigationView bottomNav;

    private static final int ITEM_INICIO = R.id.item_inicio;
    private static final int ITEM_CARRITO = R.id.item_carrito;
    private static final int ITEM_PERFIL = R.id.item_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int itemId = item.getItemId();

                    if (itemId == ITEM_INICIO) {
                        startActivity(new Intent(Cliente.this, Cliente.class));
                        return true;
                    } else if (itemId == ITEM_CARRITO) {
                        startActivity(new Intent(Cliente.this, cliCarrito.class));
                        return true;
                    } else if (itemId == ITEM_PERFIL) {
                        startActivity(new Intent(Cliente.this, cliPerfil.class));
                        return true;
                    }

                    return false;
                }
            };
}
