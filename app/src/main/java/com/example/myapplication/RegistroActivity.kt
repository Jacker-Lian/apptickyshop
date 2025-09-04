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
import com.example.myapplication.data.User
class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val tvGoLogin = findViewById<TextView>(R.id.tvGoLogin)

        // Botón registrarse (guardar en Room)
        btnRegister.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val db = AppDatabase.getDatabase(this)
                val userDao = db.userDao()

                lifecycleScope.launch {
                    // Guardamos en la BD
                    userDao.insert(User(nombre = nombre, email = email, password = password))
                    Toast.makeText(this@RegistroActivity, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()

                    // Volver al login
                    startActivity(Intent(this@RegistroActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }

        // Ir a login
        tvGoLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
