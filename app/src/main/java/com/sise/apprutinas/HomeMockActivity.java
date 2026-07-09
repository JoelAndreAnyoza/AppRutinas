package com.sise.apprutinas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sise.apprutinas.activity.PerfilActivity;
import com.sise.apprutinas.activity.SeguimientoActivity;

public class HomeMockActivity extends AppCompatActivity {

    TextView tvWelcome, tvMensajeInicio, tvPremiumBloqueado;
    LinearLayout layoutBienvenida, layoutInicio;
    Button btnInicio, btnSeguimiento, btnPerfil;
    String userName, userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mock);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvMensajeInicio = findViewById(R.id.tvMensajeInicio);
        tvPremiumBloqueado = findViewById(R.id.tvPremiumBloqueado);
        layoutBienvenida = findViewById(R.id.layoutBienvenida);
        layoutInicio = findViewById(R.id.layoutInicio);
        btnInicio = findViewById(R.id.btnInicio);
        btnSeguimiento = findViewById(R.id.btnSeguimiento);
        btnPerfil = findViewById(R.id.btnPerfil);

        userName = getIntent().getStringExtra("USER_NAME");
        userType = getIntent().getStringExtra("USER_TYPE");

        tvWelcome.setText("¡Hola, " + userName + "!\nCuenta: " + userType);

        if ("GRATUITO".equals(userType)) {
            tvMensajeInicio.setText("Tu cuenta tiene funciones limitadas.");
            tvPremiumBloqueado.setVisibility(View.VISIBLE);
        } else {
            tvMensajeInicio.setText("Tienes acceso completo a tus rutinas.");
            tvPremiumBloqueado.setVisibility(View.GONE);
        }

        new Handler().postDelayed(() -> {
            layoutBienvenida.setVisibility(View.GONE);
            layoutInicio.setVisibility(View.VISIBLE);
        }, 3000);

        btnInicio.setOnClickListener(v -> {
        });

        btnSeguimiento.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMockActivity.this, SeguimientoActivity.class);
            intent.putExtra("USER_TYPE", userType);
            startActivity(intent);
        });

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(HomeMockActivity.this, PerfilActivity.class);
            intent.putExtra("USER_TYPE", userType);
            startActivity(intent);
        });
    }
}