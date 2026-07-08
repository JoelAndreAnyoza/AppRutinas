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

import com.sise.apprutinas.R;

public class PersonalizarActivity extends AppCompatActivity {
    TextView tvNivel;
    EditText etPeso, etAltura;
    SeekBar sbNivel;
    Spinner spObjetivo;
    RadioGroup rgSexo;
    Button btnGuardar;
    int nivelSeleccionado = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar);
        tvNivel = findViewById(R.id.tvNivel);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        sbNivel = findViewById(R.id.sbNivel);
        spObjetivo = findViewById(R.id.spObjetivo);
        rgSexo = findViewById(R.id.rgSexo);
        btnGuardar = findViewById(R.id.btnGuardar);

        cargarObjetivos();

        sbNivel.setMax(9);
        sbNivel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                nivelSeleccionado = progress + 1;
                tvNivel.setText("Nivel: " + nivelSeleccionado);
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, objetivos);
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