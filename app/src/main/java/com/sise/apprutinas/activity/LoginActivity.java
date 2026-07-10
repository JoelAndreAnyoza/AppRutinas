package com.sise.apprutinas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.sise.apprutinas.R;
import com.sise.apprutinas.model.UserDatabase;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToRegister = findViewById(R.id.tvGoToRegister);

        tvGoToRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            loginUser(email, password);
        });
    }

    private void loginUser(String email, String password) {
        try {
            boolean found = false;

            if (UserDatabase.users == null) {
                Toast.makeText(this, "Error: Base de datos en RAM no inicializada", Toast.LENGTH_SHORT).show();
                return;
            }

            for (UserDatabase.User user : UserDatabase.users) {
                if (user.email.equals(email) && user.password.equals(password)) {
                    found = true;

                    getSharedPreferences("perfil", MODE_PRIVATE)
                            .edit()
                            .putString("tipoUsuario", user.type)
                            .putString("USER_TYPE", user.type)
                            .apply();

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("USER_TYPE", user.type);
                    intent.putExtra("USER_NAME", user.name);

                    startActivity(intent);
                    finish();
                    break;
                }
            }

            if (!found) {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error en Login: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}