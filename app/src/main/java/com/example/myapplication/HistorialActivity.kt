package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.SalaDao
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistorialActivity : AppCompatActivity() {

    private lateinit var recyclerViewHistorial: RecyclerView
    private lateinit var compraAdapter: CompraAdapter
    private lateinit var salaDao: SalaDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val exit = findViewById<ImageView>(R.id.exit)
        recyclerViewHistorial = findViewById(R.id.recyclerViewHistorial)

        // Inicializar Room DAO
        val db = AppDatabase.getDatabase(this)
        salaDao = db.salaDao()

        // Configurar RecyclerView
        compraAdapter = CompraAdapter()
        recyclerViewHistorial.layoutManager = LinearLayoutManager(this)
        recyclerViewHistorial.adapter = compraAdapter

        // Observar cambios en la base de datos
        lifecycleScope.launch {
            salaDao.getAllSalas().collect { salas ->
                compraAdapter.submitList(salas)
            }
        }

        // 🔹 Exit ahora regresa a PerfilActivity
        exit.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Navegación inferior
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_historial // 🔥 Pinta "Historial"

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_historial -> {
                    // Ya estamos aquí → no hace nada más
                    true
                }
                R.id.nav_compras -> {
                    startActivity(Intent(this, ComprasActivity::class.java))
                    true
                }
                R.id.nav_lista -> {
                    startActivity(Intent(this, ListaTareasActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
