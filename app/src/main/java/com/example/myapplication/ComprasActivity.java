package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ComprasActivity extends AppCompatActivity {

    CheckBox accesorio1, accesorio2, accesorio3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        accesorio1 = findViewById(R.id.accesorio1);
        accesorio2 = findViewById(R.id.accesorio2);
        accesorio3 = findViewById(R.id.accesorio3);

        // ✅ Acciones de los checkboxes
        accesorio1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) Toast.makeText(this, "Accesorio 1 agregado", Toast.LENGTH_SHORT).show();
        });
        accesorio2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) Toast.makeText(this, "Accesorio 2 agregado", Toast.LENGTH_SHORT).show();
        });
        accesorio3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) Toast.makeText(this, "Accesorio 3 agregado", Toast.LENGTH_SHORT).show();
        });

        // ✅ Navegación inferior
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_historial) {
                startActivity(new Intent(this, SalasActivity.class));
                return true;
            } else if (id == R.id.nav_compras) {
                Toast.makeText(this, "Ya estás en Compras", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_lista) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            }
            return false;
        });

    }
}
