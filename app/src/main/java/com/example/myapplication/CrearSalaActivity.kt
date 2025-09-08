package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.Sala
import kotlinx.coroutines.launch

class CrearSalaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_sala)

        val etName = findViewById<EditText>(R.id.etName)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnCrear = findViewById<Button>(R.id.btnCrear)

        val db = AppDatabase.getDatabase(this)

        // Obtener el email del usuario actual (ejemplo con SharedPreferences)
        val emailUsuario = getSharedPreferences("user", MODE_PRIVATE).getString("email", "") ?: ""

        btnCrear.setOnClickListener {
            val nombre = etName.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nombre.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    db.salaDao().insert(
                        Sala(
                            nombre = nombre,
                            password = password,
                            creadorEmail = emailUsuario // ← Nuevo campo obligatorio
                        )
                    )
                    runOnUiThread {
                        Toast.makeText(this@CrearSalaActivity, "Sala creada", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@CrearSalaActivity, HistorialActivity::class.java))
                    }
                }
            }
        }
    }
}