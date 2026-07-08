package com.sise.apprutinas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeMockActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mock);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        Button btnPremiumFeature = findViewById(R.id.btnPremiumFeature);

        String userName = getIntent().getStringExtra("USER_NAME");
        String userType = getIntent().getStringExtra("USER_TYPE");

        tvWelcome.setText("¡Hola, " + userName + " (" + userType + ")!");

        if ("GRATUITO".equals(userType)) {
            btnPremiumFeature.setEnabled(false);
            btnPremiumFeature.setText("Función Premium 🔒 Bloqueada");
        }
    }
}
