package com.sise.apprutinas.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.sise.apprutinas.R;

public class PerfilActivity extends AppCompatActivity {
    TextView tvPeso, tvAltura;
    Button btnPersonalizarRutina, btnInicio, btnSeguimiento, btnPerfil;

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sp = getSharedPreferences("perfil", MODE_PRIVATE);

        String peso = sp.getString("peso", "-");
        String altura = sp.getString("altura", "-");

        tvPeso.setText(peso + " kg");
        tvAltura.setText(altura + " m");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        tvPeso = findViewById(R.id.tvPeso);
        tvAltura = findViewById(R.id.tvAltura);
        btnPersonalizarRutina = findViewById(R.id.btnPersonalizarRutina);

        btnInicio = findViewById(R.id.btnInicio);
        btnSeguimiento = findViewById(R.id.btnSeguimiento);
        btnPerfil = findViewById(R.id.btnPerfil);

        btnPerfil.setTextColor(getResources().getColor(R.color.principal));

        btnInicio.setOnClickListener(v -> {
            finish();
        });
        btnSeguimiento.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, SeguimientoActivity.class);
            startActivity(intent);
        });

        btnPerfil.setOnClickListener(v -> {

        });

        String tipoUsuario = getSharedPreferences("perfil", MODE_PRIVATE)
                .getString("tipoUsuario", "GRATUITO");
        if (tipoUsuario.equals("GRATUITO")) {
            btnPersonalizarRutina.setEnabled(false);
            btnPersonalizarRutina.setText("Personalización Premium 🔒");
        }

        btnPersonalizarRutina.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, PersonalizarActivity.class);
            startActivity(intent);
        });
    }
}