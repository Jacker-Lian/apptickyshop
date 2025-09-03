package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class NotasActivity : AppCompatActivity() {

    private lateinit var etNota: EditText
    private lateinit var btnGuardarNota: Button
    private lateinit var listViewNotas: ListView
    private val notasList = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        etNota = findViewById(R.id.etNota)
        btnGuardarNota = findViewById(R.id.btnGuardarNota)
        listViewNotas = findViewById(R.id.listViewNotas)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notasList)
        listViewNotas.adapter = adapter

        btnGuardarNota.setOnClickListener {
            val nota = etNota.text.toString().trim()
            if (nota.isNotEmpty()) {
                notasList.add(nota)
                adapter.notifyDataSetChanged()
                etNota.text.clear()
                Toast.makeText(this, "Nota guardada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Escribe una nota antes de guardar", Toast.LENGTH_SHORT).show()
            }
        }

        val exit = findViewById<ImageView>(R.id.exit)
        exit.setOnClickListener {
            finish()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_historial -> {
                    startActivity(Intent(this, HistorialActivity::class.java))
                    true
                }
                R.id.nav_compras -> {
                    startActivity(Intent(this, ComprasActivity::class.java))
                    true
                }
                R.id.nav_lista -> {
                    startActivity(Intent(this, ListaTareasActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
