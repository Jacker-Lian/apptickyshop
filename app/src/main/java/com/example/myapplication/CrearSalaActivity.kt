package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearSalaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_sala)

        val etNombreSala = findViewById<EditText>(R.id.etNombreSala)
        val etPasswordSala = findViewById<EditText>(R.id.etPasswordSala)
        val btnCrear = findViewById<Button>(R.id.btnCrear)

        btnCrear.setOnClickListener {
            val nombre = etNombreSala.text.toString().trim()
            val password = etPasswordSala.text.toString().trim()

            if (nombre.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sala '$nombre' creada con éxito", Toast.LENGTH_SHORT).show()
                // Aquí podrías navegar a otra Activity, como ListaTareas o Salas
            }
        }
    }
}
