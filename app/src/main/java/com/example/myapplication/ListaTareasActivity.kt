package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import android.widget.EditText
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.Tarea
import com.example.myapplication.data.TareaDao
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListaTareasActivity : AppCompatActivity() {

    private lateinit var etNuevaTarea: EditText
    private lateinit var btnAgregarTarea: Button
    private lateinit var recyclerViewTareas: RecyclerView
    private lateinit var tareaAdapter: TareaAdapter
    private lateinit var tareaDao: TareaDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tareas)

        val exit = findViewById<ImageView>(R.id.exit)
        etNuevaTarea = findViewById(R.id.etNuevaTarea) // Nuevo EditText
        btnAgregarTarea = findViewById(R.id.btnAgregarTarea) // Nuevo botón
        recyclerViewTareas = findViewById(R.id.recyclerViewTareas) // Nuevo RecyclerView

        // Inicializar Room DAO
        val db = AppDatabase.getDatabase(this)
        tareaDao = db.tareaDao()

        // Configurar RecyclerView
        tareaAdapter = TareaAdapter { tarea ->
            // Listener para cuando se cambia el estado del checkbox de una tarea
            lifecycleScope.launch {
                tareaDao.update(tarea)
                Toast.makeText(this@ListaTareasActivity, "Estado de '" + tarea.descripcion + "' actualizado", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerViewTareas.layoutManager = LinearLayoutManager(this)
        recyclerViewTareas.adapter = tareaAdapter

        // Observar cambios en la base de datos y actualizar la UI
        lifecycleScope.launch {
            tareaDao.getAllTareas().collect { tareas ->
                tareaAdapter.submitList(tareas) // Usamos submitList para DiffUtil
            }
        }

        // Cerrar pantalla
        exit.setOnClickListener {
            Toast.makeText(this, "Cerrando lista de tareas", Toast.LENGTH_SHORT).show()
            finish()
        }

        // Botón para agregar nueva tarea
        btnAgregarTarea.setOnClickListener {
            val descripcionTarea = etNuevaTarea.text.toString().trim()
            if (descripcionTarea.isNotEmpty()) {
                val nuevaTarea = Tarea(descripcion = descripcionTarea, completada = false)
                lifecycleScope.launch {
                    tareaDao.insert(nuevaTarea)
                    etNuevaTarea.setText("") // Limpiar el campo de texto
                    Toast.makeText(this@ListaTareasActivity, "'" + descripcionTarea + "' agregada a la lista", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa una descripción para la tarea", Toast.LENGTH_SHORT).show()
            }
        }

        // Navegación inferior (ya existente)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_historial -> {
                    startActivity(android.content.Intent(this, HistorialActivity::class.java))
                    true
                }
                R.id.nav_compras -> {
                    startActivity(android.content.Intent(this, ComprasActivity::class.java))
                    true
                }
                R.id.nav_lista -> {
                    Toast.makeText(this, "Ya estás en Lista de Tareas", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}