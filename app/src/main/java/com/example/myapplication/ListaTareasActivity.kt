package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListaTareasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tareas)

        val exit = findViewById<ImageView>(R.id.exit)

        // Cerrar pantalla
        exit.setOnClickListener {
            Toast.makeText(this, "Cerrando lista de tareas", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
