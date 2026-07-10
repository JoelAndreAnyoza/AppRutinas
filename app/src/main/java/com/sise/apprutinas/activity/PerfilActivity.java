package com.sise.apprutinas.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.LinearLayout;

import com.sise.apprutinas.R;

public class PerfilActivity extends AppCompatActivity {
    TextView tvPeso, tvAltura;
    Button btnPersonalizarRutina, btnCerrarSesion;
    LinearLayout btnInicio, btnSeguimiento, btnPerfil;
    TextView tvPerfilNav;
    View linePerfil;

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
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnInicio = findViewById(R.id.btnInicio);
        btnSeguimiento = findViewById(R.id.btnSeguimiento);
        btnPerfil = findViewById(R.id.btnPerfil);

        tvPerfilNav = findViewById(R.id.tvPerfilNav);
        linePerfil = findViewById(R.id.linePerfil);

        tvPerfilNav.setTextColor(getResources().getColor(R.color.principal));
        linePerfil.setVisibility(View.VISIBLE);

        btnInicio.setOnClickListener(v -> {
            finish();
        });
        btnSeguimiento.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, SeguimientoActivity.class);
            startActivity(intent);
        });

        btnPerfil.setOnClickListener(v -> {

        });

        btnPersonalizarRutina.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilActivity.this, PersonalizarActivity.class);
            startActivity(intent);
        });

        btnCerrarSesion.setOnClickListener(v -> {
            getSharedPreferences("perfil", MODE_PRIVATE)
                    .edit()
                    .remove("USER_NAME")
                    .remove("USER_TYPE")
                    .remove("tipoUsuario")
                    .apply();

            Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}