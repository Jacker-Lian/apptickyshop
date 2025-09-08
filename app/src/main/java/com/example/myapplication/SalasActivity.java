package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.Sala;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import android.view.ViewGroup;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class SalasActivity extends AppCompatActivity {

    private LinearLayout layoutSalas;
    private ImageView btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas);

        layoutSalas = findViewById(R.id.layoutSalas);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(v -> {
            Toast.makeText(SalasActivity.this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SalasActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Aquí debes obtener el email del usuario actual (ejemplo con SharedPreferences)
        String emailUsuario = getSharedPreferences("user", MODE_PRIVATE).getString("email", "");

        new Thread(() -> {
            AppDatabase db = AppDatabase.getDatabase(SalasActivity.this);
            List<Sala> salasCreadas = db.salaDao().getSalasCreadasPorUsuario(emailUsuario);
            List<Sala> salasUnidas = db.salaDao().getSalasUnidasPorUsuario(emailUsuario);

            // Unir ambas listas sin duplicados usando el id de la sala
            Set<Integer> ids = new HashSet<>();
            List<Sala> salasFinales = new java.util.ArrayList<>();

            for (Sala sala : salasCreadas) {
                if (ids.add(sala.getId())) {
                    salasFinales.add(sala);
                }
            }
            for (Sala sala : salasUnidas) {
                if (ids.add(sala.getId())) {
                    salasFinales.add(sala);
                }
            }

            new Handler(Looper.getMainLooper()).post(() -> {
                layoutSalas.removeAllViews();
                for (Sala sala : salasFinales) {
                    Button btnSala = crearBotonSala(SalasActivity.this, sala.getNombre());
                    btnSala.setOnClickListener(view -> {
                        Intent intent = new Intent(SalasActivity.this, HistorialActivity.class);
                        intent.putExtra("nombre_sala", sala.getNombre());
                        startActivity(intent);
                    });
                    layoutSalas.addView(btnSala);
                }
            });
        }).start();
    }

    private Button crearBotonSala(Context context, String nombreSala) {
        Button btn = new Button(context);
        btn.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        btn.setText(nombreSala);
        btn.setBackgroundTintList(getResources().getColorStateList(R.color.gray_666666, null));
        btn.setTextColor(getResources().getColor(android.R.color.white, null));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btn.getLayoutParams();
        params.setMargins(0, 0, 0, dpToPx(10));
        btn.setLayoutParams(params);
        return btn;
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}