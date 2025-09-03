package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class PerfilActivity : AppCompatActivity() {

    private lateinit var btnEditar: Button
    private lateinit var btnCerrarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        btnEditar = findViewById(R.id.btnEditar)
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)

        btnEditar.setOnClickListener {
            Toast.makeText(this, "Editar perfil en construcción...", Toast.LENGTH_SHORT).show()
        }

        btnCerrarSesion.setOnClickListener {
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            finish() // Cierra esta pantalla
        }
    }
}
