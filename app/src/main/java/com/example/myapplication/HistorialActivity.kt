package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val exit = findViewById<ImageView>(R.id.exit)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Botón salir
        exit.setOnClickListener {
            finish()
        }

        // Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_historial -> {
                    Toast.makeText(this, "Ya estás en Historial", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_compras -> {
                    startActivity(Intent(this, ComprasActivity::class.java))
                    true
                }
                R.id.nav_lista -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
