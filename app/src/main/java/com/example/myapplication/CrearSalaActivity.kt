package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CrearSalaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_sala)

        val etNombre = findViewById<EditText>(R.id.etNombreSala)
        val etPassword = findViewById<EditText>(R.id.etPasswordSala)
        val btnCrear = findViewById<Button>(R.id.btnCrearSalaConfirmar)
        val exit = findViewById<ImageView>(R.id.exit)

        btnCrear.setOnClickListener {
            val nombre = etNombre.text.toString()
            val password = etPassword.text.toString()

            if (nombre.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Sala '$nombre' creada", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        exit.setOnClickListener {
            finish()
        }
    }
}
