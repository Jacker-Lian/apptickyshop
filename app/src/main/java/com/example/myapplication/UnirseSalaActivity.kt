package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.SalaUsuario
import kotlinx.coroutines.launch

class UnirseSalaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unirse_sala)

        val etName = findViewById<EditText>(R.id.etName)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnUnirse = findViewById<Button>(R.id.btnUnirse)

        val db = AppDatabase.getDatabase(this)
        val emailUsuario = getSharedPreferences("user", MODE_PRIVATE).getString("email", "") ?: ""

        btnUnirse.setOnClickListener {
            val nombre = etName.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (nombre.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val sala = db.salaDao().getSala(nombre, password)
                    runOnUiThread {
                        if (sala != null) {
                            lifecycleScope.launch {
                                db.salaDao().insertSalaUsuario(
                                    SalaUsuario(
                                        salaId = sala.id,
                                        usuarioEmail = emailUsuario
                                    )
                                )
                            }
                            Toast.makeText(this@UnirseSalaActivity, "Unido a la sala", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@UnirseSalaActivity, HistorialActivity::class.java))
                        } else {
                            Toast.makeText(this@UnirseSalaActivity, "Sala no encontrada", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}