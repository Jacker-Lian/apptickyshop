package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListaTareasActivity : AppCompatActivity() {

    private lateinit var listViewTareas: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tareas)

        listViewTareas = findViewById(R.id.listViewTareas)

        // Lista de ejemplo (puedes luego traer datos de BD o API)
        val tareas = listOf(
            "Comprar leche",
            "Hacer tarea de matemáticas",
            "Preparar presentación",
            "Revisar correo",
            "Ir al gimnasio"
        )

        // Adaptador simple
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tareas
        )

        listViewTareas.adapter = adapter
    }
}
