package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ListaTareasActivity : AppCompatActivity() {

    private lateinit var listViewTareas: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tareas)

        val exit = findViewById<ImageView>(R.id.exit)
        exit.setOnClickListener {
            finish()
        }

        listViewTareas = findViewById(R.id.listViewTareas)

        // Lista de ejemplo
        val tareas = listOf(
            "Comprar leche",
            "Hacer tarea de matemáticas",
            "Preparar presentación",
            "Revisar correo",
            "Ir al gimnasio"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tareas
        )
        listViewTareas.adapter = adapter

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_historial -> {
                    startActivity(Intent(this, HistorialActivity::class.java))
                    true
                }
                R.id.nav_compras -> {
                    startActivity(Intent(this, ComprasActivity::class.java))
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
