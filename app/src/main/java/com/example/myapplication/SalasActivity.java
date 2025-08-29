package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;

public class SalasActivity extends AppCompatActivity {

    Button sala1, sala2, sala3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas);

        sala1 = findViewById(R.id.sala1);
        sala2 = findViewById(R.id.sala2);
        sala3 = findViewById(R.id.sala3);

        sala1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SalasActivity.this, "Entrando a Sala 1", Toast.LENGTH_SHORT).show();
            }
        });

        sala2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SalasActivity.this, "Entrando a Sala 2", Toast.LENGTH_SHORT).show();
            }
        });

        sala3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SalasActivity.this, "Entrando a Sala 3", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
