package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HistorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial)

        val exit = findViewById<ImageView>(R.id.exit)

        // Cerrar pantalla
        exit.setOnClickListener {
            Toast.makeText(this, "Cerrando historial", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
