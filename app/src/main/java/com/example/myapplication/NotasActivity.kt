package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NotasActivity : AppCompatActivity() {

    private lateinit var edtNota: EditText
    private lateinit var btnGuardar: Button
    private lateinit var tvNotasGuardadas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        edtNota = findViewById(R.id.edtNota)
        btnGuardar = findViewById(R.id.btnGuardar)
        tvNotasGuardadas = findViewById(R.id.tvNotasGuardadas)

        btnGuardar.setOnClickListener {
            val nota = edtNota.text.toString().trim()
            if (nota.isNotEmpty()) {
                val textoActual = tvNotasGuardadas.text.toString()
                tvNotasGuardadas.text = "$textoActual\n- $nota"
                edtNota.text.clear()
            }
        }
    }
}
