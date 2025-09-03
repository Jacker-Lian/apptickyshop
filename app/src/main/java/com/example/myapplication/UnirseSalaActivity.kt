package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UnirseSalaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unirse_sala)

        val etNombre = findViewById<EditText>(R.id.etNombreSalaUnirse)
        val etPassword = findViewById<EditText>(R.id.etPasswordSalaUnirse)
        val btnUnirse = findViewById<Button>(R.id.btnUnirseSalaConfirmar)
        val exit = findViewById<ImageView>(R.id.exit)

        btnUnirse.setOnClickListener {
            val nombre = etNombre.text.toString()
            val password = etPassword.text.toString()

            if (nombre.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Unido a sala '$nombre'", Toast.LENGTH_SHORT).show()
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