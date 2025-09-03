package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegistrar = findViewById<TextView>(R.id.tvRegistrar)

        btnLogin.setOnClickListener {
            val user = etUsuario.text.toString()
            val pass = etPassword.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                // ✅ Aquí podrías validar con base de datos o login real
                Toast.makeText(this, "Bienvenido $user", Toast.LENGTH_SHORT).show()

                // Ejemplo: Ir a pantalla principal
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegistrar.setOnClickListener {
            // ✅ Ir a la pantalla de registro
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}

