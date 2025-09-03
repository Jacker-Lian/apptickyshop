package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var btnCrearSala: Button? = null
    var btnUnirse: Button? = null
    var btnMisSalas: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        btnCrearSala = findViewById<Button?>(R.id.btnCrearSala)
        btnUnirse = findViewById<Button?>(R.id.btnUnirse)
        btnMisSalas = findViewById<Button?>(R.id.btnMisSalas)

        // Ir a Lista de Salas
        btnUnirse!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent: Intent = Intent(this@MainActivity, SalasActivity::class.java)
                startActivity(intent)
            }
        })

        // Ir a Lista de Compras
        btnMisSalas!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent: Intent = Intent(this@MainActivity, ComprasActivity::class.java)
                startActivity(intent)
            }
        })

        // Crear sala (ejemplo simple → también va a SalasActivity)
        btnCrearSala!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent: Intent = Intent(this@MainActivity, SalasActivity::class.java)
                startActivity(intent)
            }
        })
    }
}