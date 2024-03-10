package com.example.apphamburguesas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apphamburguesas.msr.msrPedidos;

public class Mesero extends AppCompatActivity {

    CardView Pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesero);

        Pedidos = findViewById(R.id.cardPedidos);

        Pedidos.setTag("Pedidos");

        Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(v.getTag().toString());
            }
        });
    }

    private void launchActivity(String activityName) {
        Intent intent = new Intent(this, getActivityClass(activityName));
        startActivity(intent);
    }

    private Class<?> getActivityClass(String activityName) {
        switch (activityName) {
            case "Pedidos":
                return msrPedidos.class;
            default:
                return null;
        }
    }
}