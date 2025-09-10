package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.SalaDao
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collect // Asegúrate de que esta importación esté presente y no haya otras importaciones conflictivas de 'collect'
import kotlinx.coroutines.launch

class HistorialActivity : AppCompatActivity() {

    private lateinit var recyclerViewHistorial: RecyclerView
    private lateinit var compraAdapter: CompraAdapter // Renombrado para reflejar el contenido
    private lateinit var salaDao: SalaDao // Usaremos SalaDao para obtener las salas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val exit = findViewById<ImageView>(R.id.exit)
        recyclerViewHistorial = findViewById(R.id.recyclerViewHistorial) // Nuevo RecyclerView

        // Inicializar Room DAO
        val db = AppDatabase.getDatabase(this)
        salaDao = db.salaDao()

        // Configurar RecyclerView
        compraAdapter = CompraAdapter() // No necesitamos listener de click por ahora
        recyclerViewHistorial.layoutManager = LinearLayoutManager(this)
        recyclerViewHistorial.adapter = compraAdapter

        // Observar cambios en la base de datos y actualizar la UI
        lifecycleScope.launch {
            salaDao.getAllSalas().collect { salas ->
                compraAdapter.submitList(salas)
            }
        }


        // Cerrar pantalla
        exit.setOnClickListener {
            Toast.makeText(this, "Cerrando historial", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Navegación inferior (si aplica a esta actividad)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_historial -> {
                    Toast.makeText(this, "Ya estás en Historial", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_compras -> {
                    startActivity(android.content.Intent(this, ComprasActivity::class.java))
                    true
                }
                R.id.nav_lista -> {
                    startActivity(android.content.Intent(this, ListaTareasActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}