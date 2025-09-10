package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Intent;

import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.ItemCompra;
import com.example.myapplication.data.ItemCompraDao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComprasActivity extends AppCompatActivity {

    Button btnAgregar;
    ImageView exit;
    EditText etNuevoItem;
    RecyclerView recyclerViewItems;
    ItemCompraAdapter itemCompraAdapter;
    List<ItemCompra> itemCompraList;
    ItemCompraDao itemCompraDao;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        exit = findViewById(R.id.exit);
        btnAgregar = findViewById(R.id.btnAgregarAccesorio);
        etNuevoItem = findViewById(R.id.etNuevoItem);
        recyclerViewItems = findViewById(R.id.recyclerViewItems);

        AppDatabase db = AppDatabase.Companion.getDatabase(this); // o AppDatabase.getDatabase(this) si usas @JvmStatic
        itemCompraDao = db.itemCompraDao();
        executorService = Executors.newSingleThreadExecutor();

        itemCompraList = new ArrayList<>();
        itemCompraAdapter = new ItemCompraAdapter(itemCompraList, item -> {
            executorService.execute(() -> {
                itemCompraDao.update(item);
                runOnUiThread(() -> Toast.makeText(ComprasActivity.this, "Estado de '" + item.getNombre() + "' actualizado", Toast.LENGTH_SHORT).show());
            });
        });

        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewItems.setAdapter(itemCompraAdapter);

        // Observamos LiveData para actualizar la lista automáticamente
        itemCompraDao.getAllItemsCompra().observe(this, new Observer<List<ItemCompra>>() {
            @Override
            public void onChanged(List<ItemCompra> items) {
                itemCompraList.clear();
                if (items != null) {
                    itemCompraList.addAll(items);
                }
                itemCompraAdapter.notifyDataSetChanged();
            }
        });

        exit.setOnClickListener(v -> {
            Toast.makeText(this, "Salir de Compras", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnAgregar.setOnClickListener(v -> {
            String nombreItem = etNuevoItem.getText().toString().trim();
            if (!nombreItem.isEmpty()) {
                ItemCompra newItem = new ItemCompra(0, nombreItem, false);
                executorService.execute(() -> {
                    itemCompraDao.insert(newItem);
                    runOnUiThread(() -> {
                        etNuevoItem.setText("");
                        Toast.makeText(ComprasActivity.this, "'" + nombreItem + "' agregado a la lista", Toast.LENGTH_SHORT).show();
                    });
                });
            } else {
                Toast.makeText(this, "Por favor, ingresa un nombre para el ítem", Toast.LENGTH_SHORT).show();
            }
        });

        // Configuración de navegación inferior (igual que antes)
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_historial) {
                startActivity(new Intent(this, HistorialActivity.class));
                return true;
            } else if (id == R.id.nav_compras) {
                Toast.makeText(this, "Ya estás en Compras", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.nav_lista) {
                startActivity(new Intent(this, ListaTareasActivity.class));
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}