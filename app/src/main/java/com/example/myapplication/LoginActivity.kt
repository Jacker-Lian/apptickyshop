package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.myapplication.data.AppDatabase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvGoRegister = findViewById<TextView>(R.id.tvGoRegister)

        // Botón ingresar (ahora validando con Room)
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val db = AppDatabase.getDatabase(this)
                val userDao = db.userDao()

                // Como Room usa corrutinas → lanzamos en un hilo aparte
                lifecycleScope.launch {
                    val user = userDao.login(email, password)
                    if (user != null) {
                        Toast.makeText(this@LoginActivity, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, PerfilActivity::class.java))
                        finish() // Opcional: cerrar pantalla de login
                    } else {
                        Toast.makeText(this@LoginActivity, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Ir a registro
        tvGoRegister.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}
