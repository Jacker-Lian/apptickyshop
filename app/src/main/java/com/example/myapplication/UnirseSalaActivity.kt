package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UnirseSalaActivity : AppCompatActivity() {

    private lateinit var edtCodigoSala: EditText
    private lateinit var btnUnirse: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_unirse_sala)

        edtCodigoSala = findViewById(R.id.edtCodigoSala)
        btnUnirse = findViewById(R.id.btnUnirse)
        btnCancelar = findViewById(R.id.btnCancelar)

        btnUnirse.setOnClickListener {
            val codigo = edtCodigoSala.text.toString().trim()
            if (codigo.isEmpty()) {
                Toast.makeText(this, "Ingrese un código de sala", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Uniéndose a la sala con código: $codigo", Toast.LENGTH_SHORT).show()
                // Aquí iría la lógica para verificar el código y entrar a la sala
            }
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }
}
