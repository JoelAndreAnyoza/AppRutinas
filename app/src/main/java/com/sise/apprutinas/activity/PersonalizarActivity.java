package com.sise.apprutinas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.sise.apprutinas.R;

public class PersonalizarActivity extends AppCompatActivity {
    TextView tvNivel;
    RadioButton rbHombre, rbMujer;
    EditText etPeso, etAltura;
    SeekBar sbNivel;
    Spinner spObjetivo;
    RadioGroup rgSexo;
    Button btnGuardar;
    int nivelSeleccionado = 1;

    private String userType = "GRATUITO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar);

        if (getIntent().hasExtra("USER_TYPE")) {
            userType = getIntent().getStringExtra("USER_TYPE");
        } else {

            userType = getSharedPreferences("perfil", MODE_PRIVATE).getString("USER_TYPE", "GRATUITO");
        }

        tvNivel = findViewById(R.id.tvNivel);

        rbHombre = findViewById(R.id.rbHombre);
        rbMujer = findViewById(R.id.rbMujer);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        sbNivel = findViewById(R.id.sbNivel);
        spObjetivo = findViewById(R.id.spObjetivo);
        rgSexo = findViewById(R.id.rgSexo);
        btnGuardar = findViewById(R.id.btnGuardar);
        tvNivel.setTextColor(Color.BLACK);
        etPeso.setTextColor(Color.BLACK);
        etAltura.setTextColor(Color.BLACK);
        etPeso.setHintTextColor(Color.DKGRAY);
        etAltura.setHintTextColor(Color.DKGRAY);
        rbHombre.setTextColor(Color.BLACK);
        rbMujer.setTextColor(Color.BLACK);

        cargarObjetivos();

        sbNivel.setMax(9);
        sbNivel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int nivelIntentado = progress + 1;


                if (!"PREMIUM".equalsIgnoreCase(userType) && nivelIntentado > 6) {
                    seekBar.setProgress(5);
                    nivelSeleccionado = 6;
                    tvNivel.setText("Nivel: 6");

                    if (fromUser) {
                        Toast.makeText(PersonalizarActivity.this,
                                "Debes ser usuario PREMIUM para acceder a los niveles +6",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    nivelSeleccionado = nivelIntentado;
                    tvNivel.setText("Nivel: " + nivelSeleccionado);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        btnGuardar.setOnClickListener(v -> mostrarResumen());
    }
    private void cargarObjetivos() {
        String[] objetivos = {
                "Bajar de peso",
                "Mantenerme",
                "Ganar masa muscular"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, objetivos) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView texto = (TextView) super.getView(position, convertView, parent);
                texto.setTextColor(Color.BLACK);
                texto.setTextSize(16);
                return texto;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView texto = (TextView) super.getDropDownView(position, convertView, parent);
                texto.setTextColor(Color.BLACK);
                texto.setBackgroundColor(Color.WHITE);
                return texto;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spObjetivo.setAdapter(adapter);
    }

    private void mostrarResumen() {
        String peso = etPeso.getText().toString().trim();
        String altura = etAltura.getText().toString().trim();
        String objetivo = spObjetivo.getSelectedItem().toString();

        int idSexo = rgSexo.getCheckedRadioButtonId();
        if (peso.equals("") || altura.equals("") || idSexo == -1) {
            Toast.makeText(this, "Completa todos los datos.", Toast.LENGTH_SHORT).show();
            return;

        }

        RadioButton radio = findViewById(idSexo);
        String sexo = radio.getText().toString();
        String mensaje = "Peso: " + peso + " kg\n" +
                        "Altura: " + altura + " m\n" +
                        "Sexo: " + sexo + "\n" +
                        "Objetivo: " + objetivo + "\n" +
                        "Nivel elegido: " + nivelSeleccionado + "\n\n" +
                        "¿Listo para iniciar tu rutina personalizada?";

        new AlertDialog.Builder(this)
                .setTitle("Resumen de personalización")
                .setMessage(mensaje)
                .setPositiveButton("Comenzar rutina", (dialog, which) -> {
                    guardarDatos(peso, altura, sexo, objetivo);

                    Intent intent = new Intent(PersonalizarActivity.this, SeguimientoActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Volver a personalizar", null)
                .show();
    }
    private void guardarDatos(String peso, String altura, String sexo, String objetivo) {
        int nivelRutina;
        if (nivelSeleccionado <= 4) {
            nivelRutina = 1;
        } else if (nivelSeleccionado <= 7) {
            nivelRutina = 2;
        } else {
            nivelRutina = 3;
        }
        getSharedPreferences("perfil", MODE_PRIVATE)
                .edit()
                .putString("peso", peso)
                .putString("altura", altura)
                .putString("sexo", sexo)
                .putString("objetivo", objetivo)
                .putInt("nivelRutina", nivelRutina)
                .putInt("nivelSlider", nivelSeleccionado)
                .apply();
        Toast.makeText(this, "Personalización guardada.", Toast.LENGTH_SHORT).show();
    }
}