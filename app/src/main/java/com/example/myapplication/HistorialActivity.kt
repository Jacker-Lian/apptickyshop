package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView // Import BottomNavigationView

// Import your other Activity classes (make sure the package and names are correct)
// Example:
// import com.example.myapplication.ComprasActivity
// import com.example.myapplication.ListaActivity // or ListaTareasActivity if that's the name

class HistorialActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial) // Assuming this layout contains your BottomNavigationView

        val exit = findViewById<ImageView>(R.id.exit)
        bottomNavigationView = findViewById(R.id.bottom_navigation) // Initialize it

        // Cerrar pantalla (Exit button logic)
        exit.setOnClickListener {
            Toast.makeText(this, "Cerrando historial", Toast.LENGTH_SHORT).show()
            finish() // This will close HistorialActivity
        }

        // --- BottomNavigationView Setup ---

        // Set the 'Historial' item as selected when HistorialActivity starts
        bottomNavigationView.selectedItemId = R.id.nav_historial

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_historial -> {
                    // Already in HistorialActivity, do nothing or refresh content if needed
                    true // Must return true
                }
                R.id.nav_compras -> {
                    // Navigate to ComprasActivity
                    // Make sure you have ComprasActivity created and imported
                    // if (this !is ComprasActivity) { // Check if not already ComprasActivity
                    //    val intent = Intent(this, ComprasActivity::class.java)
                    //    startActivity(intent)
                    //    // Optional: finish() // if you don't want HistorialActivity in back stack
                    // }
                    Toast.makeText(this, "Compras Seleccionado (Implementar Intent)", Toast.LENGTH_SHORT).show() // Placeholder
                    true
                }
                R.id.nav_lista -> {
                    // Navigate to ListaActivity (or ListaTareasActivity)
                    // Make sure you have ListaActivity/ListaTareasActivity created and imported
                    // if (this !is ListaActivity) { // Check if not already ListaActivity
                    //    val intent = Intent(this, ListaActivity::class.java) // Use correct Activity name
                    //    startActivity(intent)
                    //    // Optional: finish()
                    // }
                    Toast.makeText(this, "Lista Seleccionado (Implementar Intent)", Toast.LENGTH_SHORT).show() // Placeholder
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Ensure the correct item is selected if the user navigates back using the system back button
        // or if the activity is brought to the foreground.
        if (::bottomNavigationView.isInitialized) { // Check if bottomNavigationView has been initialized
            bottomNavigationView.menu.findItem(R.id.nav_historial)?.isChecked = true
        }
    }
}
