package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val nombreUsuario = intent.getStringExtra("nombre_usuario") ?: "Nickname"
        val txtNickname = findViewById<TextView>(R.id.txtNickname)
        txtNickname.text = nombreUsuario

        val btnCrearSala = findViewById<Button>(R.id.btnCrearSala)
        val btnUnirse = findViewById<Button>(R.id.btnUnirse)
        val btnMisSalas = findViewById<Button>(R.id.btnMisSalas)
        val btnCerrarSesion = findViewById<ImageView>(R.id.btnCerrarSesion)
        val btnEditar = findViewById<ImageView>(R.id.btnEditar)

        btnCrearSala.setOnClickListener {
            startActivity(Intent(this, CrearSalaActivity::class.java))
        }

        btnUnirse.setOnClickListener {
            startActivity(Intent(this, UnirseSalaActivity::class.java))
        }

        btnMisSalas.setOnClickListener {
            startActivity(Intent(this, SalasActivity::class.java))
        }

        btnCerrarSesion.setOnClickListener {
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnEditar.setOnClickListener {
            Toast.makeText(this, "Función editar perfil en construcción", Toast.LENGTH_SHORT).show()
        }
    }
}