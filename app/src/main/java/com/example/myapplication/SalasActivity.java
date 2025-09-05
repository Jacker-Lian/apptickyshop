package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class SalasActivity extends AppCompatActivity {

    Button sala1, sala2, sala3,btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas);

        sala1 = findViewById(R.id.sala1);
        sala2 = findViewById(R.id.sala2);
        sala3 = findViewById(R.id.sala3);

        // 👉 Al hacer clic en Sala 1, abrir HistorialActivity
        sala1.setOnClickListener(v -> {
            Intent intent = new Intent(SalasActivity.this, HistorialActivity.class);
            startActivity(intent);
        });

        // Sala 2 y Sala 3 siguen igual (puedes editarlas después)
        sala2.setOnClickListener(v ->
                Toast.makeText(SalasActivity.this, "Entrando a Sala 2", Toast.LENGTH_SHORT).show()
        );

        sala3.setOnClickListener(v ->
                Toast.makeText(SalasActivity.this, "Entrando a Sala 3", Toast.LENGTH_SHORT).show()
        );
        btnCerrarSesion.setOnClickListener(v -> {
            Toast.makeText(SalasActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SalasActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Cierra la activity actual
        });

    }
}
