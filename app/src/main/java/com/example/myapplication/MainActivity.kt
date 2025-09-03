package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 👇 Aquí decides cuál pantalla mostrar primero
        // En este caso mandamos siempre al Login
        startActivity(Intent(this, LoginActivity::class.java))

        // Cierra MainActivity para que no quede en el stack
        finish()
    }
}
