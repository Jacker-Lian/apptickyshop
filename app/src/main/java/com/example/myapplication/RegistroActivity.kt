package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()

            if (nombre.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()) {
                Toast.makeText(this, "Usuario $nombre registrado", Toast.LENGTH_SHORT).show()

                // Ejemplo: ir al login después de registrar
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogin.setOnClickListener {
            // Ir al login si ya tiene cuenta
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
