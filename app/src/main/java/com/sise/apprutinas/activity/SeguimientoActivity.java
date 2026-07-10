package com.sise.apprutinas.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.ViewGroup;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;


import com.sise.apprutinas.model.Ejercicio;
import com.sise.apprutinas.R;
import com.sise.apprutinas.utils.Rutinas;

public class SeguimientoActivity extends AppCompatActivity {
    Spinner spinnerDia;
    TextView tvNivelSeleccionado;
    TextView tvCompletado, tvProgreso;
    Button btnAgregarEjercicio;
    LinearLayout btnInicio, btnSeguimiento, btnPerfil;
    TextView tvSeguimientoNav;
    View lineSeguimiento;
    LinearLayout contEjercicios;
    int nivelSeleccionado;
    String sexo, tipoUsuario;
    ArrayList<Ejercicio> rutina = new ArrayList<>();
    ArrayList<Ejercicio> listaEjerciciosCompletos = new ArrayList<>();
    ArrayList<String> listaEjercicios = new ArrayList<>();
    HashMap<String, ArrayList<Ejercicio>> rutinasGuardadas = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento);

        spinnerDia = findViewById(R.id.spinnerDia);
        tvNivelSeleccionado = findViewById(R.id.tvNivelSeleccionado);
        tvCompletado = findViewById(R.id.tvCompletado);
        tvProgreso = findViewById(R.id.tvProgreso);
        btnAgregarEjercicio = findViewById(R.id.btnAgregarEjercicio);

        btnInicio = findViewById(R.id.btnInicio);
        btnSeguimiento = findViewById(R.id.btnSeguimiento);
        btnPerfil = findViewById(R.id.btnPerfil);

        tvSeguimientoNav = findViewById(R.id.tvSeguimientoNav);
        lineSeguimiento = findViewById(R.id.lineSeguimiento);

        tvSeguimientoNav.setTextColor(getResources().getColor(R.color.principal));
        lineSeguimiento.setVisibility(View.VISIBLE);

        btnInicio.setOnClickListener(v -> {
            finish();
        });

        btnSeguimiento.setOnClickListener(v -> {
        });

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(SeguimientoActivity.this, PerfilActivity.class);
            startActivity(intent);
        });

        tipoUsuario = getSharedPreferences("perfil", MODE_PRIVATE).getString("tipoUsuario", "GRATUITO");
        if (tipoUsuario.equals("GRATUITO")) {
            btnAgregarEjercicio.setEnabled(false);
            btnAgregarEjercicio.setText("Agregar ejercicio Premium 🔒");
        }

        contEjercicios = findViewById(R.id.contEjercicios);

        String dias[] = {
                "Lunes",
                "Martes",
                "Miércoles",
                "Jueves",
                "Viernes",
                "Sábado",
                "Domingo"
        };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dias) {
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

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDia.setAdapter(adaptador);

        nivelSeleccionado = getSharedPreferences("perfil", MODE_PRIVATE).getInt("nivelRutina",1);
        sexo = getSharedPreferences("perfil", MODE_PRIVATE).getString("sexo", "Mujer");

        seleccionarDiaActual();

        cargarEjercicios();

        tvNivelSeleccionado.setText(Rutinas.obtenerNombreNivel(nivelSeleccionado));
        spinnerDia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String diaSeleccionado = dias[position];
                String clave = nivelSeleccionado + "-" + diaSeleccionado;
                if (rutinasGuardadas.containsKey(clave)) {
                    rutina = rutinasGuardadas.get(clave);
                } else {
                    rutina = Rutinas.obtenerRutina(nivelSeleccionado, diaSeleccionado);
                    cargarEjerciciosPersonales(diaSeleccionado);
                    rutinasGuardadas.put(clave, rutina);
                }

                mostrarRutina();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAgregarEjercicio.setOnClickListener(v -> mostrarBuscador());
    }
    private void seleccionarDiaActual() {
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_WEEK);
        switch (dia) {
            case Calendar.MONDAY:
                spinnerDia.setSelection(0);
                break;

            case Calendar.TUESDAY:
                spinnerDia.setSelection(1);
                break;

            case Calendar.WEDNESDAY:
                spinnerDia.setSelection(2);
                break;

            case Calendar.THURSDAY:
                spinnerDia.setSelection(3);
                break;

            case Calendar.FRIDAY:
                spinnerDia.setSelection(4);
                break;

            case Calendar.SATURDAY:
                spinnerDia.setSelection(5);
                break;

            case Calendar.SUNDAY:
                spinnerDia.setSelection(6);
                break;
        }

    }

    private void mostrarRutina() {

        contEjercicios.removeAllViews();
        tvCompletado.setText("");
        final String diaSeleccionado = spinnerDia.getSelectedItem().toString();

        int etapa = 1;
        for (Ejercicio ejercicio : rutina) {
            View vista = getLayoutInflater().inflate(R.layout.item_ejercicio, null);

            TextView tvNombre = vista.findViewById(R.id.tvNombreEjercicio);
            TextView tvTiempo = vista.findViewById(R.id.tvTiempo);
            TextView tvTips = vista.findViewById(R.id.tvTips);
            VideoView video = vista.findViewById(R.id.videoEjercicio);
            CheckBox check = vista.findViewById(R.id.cbEjercicioCompletado);
            TextView tvEliminar = vista.findViewById(R.id.tvEliminar);
            SharedPreferences preferencias = getSharedPreferences("perfil", MODE_PRIVATE);
            boolean marcado = preferencias.getBoolean("check_" + nivelSeleccionado + "_" + diaSeleccionado + "_" + ejercicio.getNombre(), false );

            ejercicio.setCompletado(marcado);
            check.setChecked(marcado);
            if (ejercicio.isCompletado()) {
                tvNombre.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            } else {
                tvNombre.setTextColor(getResources().getColor(R.color.textoPrincipal));
            }

            tvNombre.setText("Etapa " + etapa + " - " + ejercicio.getNombre());
            tvTiempo.setText("Tiempo: " + ejercicio.getTiempo());
            tvTips.setText("Consejo: " + ejercicio.getConsejo());

            if (ejercicio.isPersonalizado()) {
                tvEliminar.setVisibility(View.VISIBLE);
                tvEliminar.setOnClickListener(v -> {
                    String diaActual = spinnerDia.getSelectedItem().toString();
                    Set<String> guardados = preferencias.getStringSet("ejerciciosPersonales_" + tipoUsuario + "_" + diaActual, new HashSet<>());
                    Set<String> actualizados = new HashSet<>(guardados);
                    actualizados.remove(ejercicio.getNombre());
                    preferencias.edit().putStringSet("ejerciciosPersonales_" + tipoUsuario + "_" + diaActual, actualizados)
                            .putInt("totalEjercicios_" + tipoUsuario + "_" + diaActual, rutina.size() - 1)
                            .remove("check_" + nivelSeleccionado + "_" + diaActual + "_" + ejercicio.getNombre())
                            .apply();

                    rutina.remove(ejercicio);
                    mostrarRutina();

                    Toast.makeText(SeguimientoActivity.this, "Has eliminado: " + ejercicio.getNombre(), Toast.LENGTH_SHORT)
                            .show();
                });

            } else {
                tvEliminar.setVisibility(View.GONE);
            }

            int videoEjercicio;

            if (sexo.equals("Hombre")) {
                videoEjercicio = ejercicio.getVideoHombre();
            } else {
                videoEjercicio = ejercicio.getVideoMujer();
            }

            String ruta = "android.resource://" + getPackageName() + "/" + videoEjercicio;
            video.setVideoURI(Uri.parse(ruta));
            video.setOnPreparedListener(mp -> {
                mp.setLooping(true);
                video.start();
            });

            check.setOnCheckedChangeListener((buttonView, isChecked) -> {
                ejercicio.setCompletado(isChecked);
                String diaActual = spinnerDia.getSelectedItem().toString();
                getSharedPreferences("perfil", MODE_PRIVATE)
                        .edit()
                        .putBoolean("check_" + nivelSeleccionado + "_" + diaActual + "_" + ejercicio.getNombre(), isChecked )
                        .apply();

                if (isChecked) {
                    tvNombre.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                } else {
                    tvNombre.setTextColor(getResources().getColor(R.color.textoPrincipal));
                }

                verificarRutinaCompletada();
            });

            contEjercicios.addView(vista);
            etapa++;
        }

        getSharedPreferences("perfil", MODE_PRIVATE)
                .edit()
                .putInt("totalEjercicios_" + tipoUsuario + "_" + diaSeleccionado, rutina.size())
                .apply();

        verificarRutinaCompletada();
    }
    private void verificarRutinaCompletada() {
        int completados = 0;
        int total = contEjercicios.getChildCount();

        for (int i = 0; i < total; i++) {
            View vista = contEjercicios.getChildAt(i);
            CheckBox check = vista.findViewById(R.id.cbEjercicioCompletado);
            if (check.isChecked()) {
                completados++;
            }
        }

        tvProgreso.setText(completados + "/" + total + " ejercicios completados");

        if (completados == total && total > 0) {
            tvCompletado.setText("🎉 ¡Rutina completada!");
            Toast.makeText(this, "¡Rutina completada!", Toast.LENGTH_LONG).show();

            String diaSeleccionado = spinnerDia.getSelectedItem().toString();
            getSharedPreferences("perfil", MODE_PRIVATE)
                    .edit()
                    .putBoolean("completado_" + diaSeleccionado, true)
                    .apply();
        } else {
            tvCompletado.setText("");
            String diaSeleccionado = spinnerDia.getSelectedItem().toString();
            getSharedPreferences("perfil", MODE_PRIVATE)
                    .edit()
                    .putBoolean("completado_" + diaSeleccionado, false)
                    .apply();
        }
    }
    private void cargarEjercicios() {
        listaEjerciciosCompletos.clear();
        listaEjercicios.clear();
        listaEjerciciosCompletos = Rutinas.obtenerTodosLosEjercicios();
        for (Ejercicio e : listaEjerciciosCompletos) {
            listaEjercicios.add(e.getNombre());
        }
    }
    private void mostrarBuscador() {
        AlertDialog.Builder ventana = new AlertDialog.Builder(this);
        ventana.setTitle("Agregar ejercicio");
        LinearLayout contenedor = new LinearLayout(this);
        contenedor.setOrientation(LinearLayout.VERTICAL);
        contenedor.setPadding(30, 30, 30, 30);
        EditText txtBuscar = new EditText(this);
        txtBuscar.setHint("Buscar ejercicio");
        ListView lista = new ListView(this);
        contenedor.addView(txtBuscar);
        contenedor.addView(lista);
        ventana.setView(contenedor);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaEjercicios);
        lista.setAdapter(adaptador);
        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adaptador.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        AlertDialog dialogo= ventana.create();
        lista.setOnItemClickListener((parent, view, position, id) -> {
            String nombre = parent.getItemAtPosition(position).toString();
            agregarEjercicioPersonal(nombre);
            Toast.makeText(this, "Ejercicio agregado", Toast.LENGTH_SHORT).show();
            dialogo.dismiss();
        });
        dialogo.show();
    }
    private void agregarEjercicioPersonal(String nombre) {
        Ejercicio ejercicioBase = buscarEjercicio(nombre);
        if (ejercicioBase != null) {
            Ejercicio ejercicio = new Ejercicio(
                    ejercicioBase.getNombre(),
                    ejercicioBase.getTiempo(),
                    ejercicioBase.getConsejo(),
                    ejercicioBase.getVideoHombre(),
                    ejercicioBase.getVideoMujer()
            );

            ejercicio.setPersonalizado(true);
            rutina.add(ejercicio);
            String diaSeleccionado = spinnerDia.getSelectedItem().toString();
            SharedPreferences preferencias = getSharedPreferences("perfil", MODE_PRIVATE);
            Set<String> guardados = preferencias.getStringSet("ejerciciosPersonales_" + tipoUsuario + "_" + diaSeleccionado, new HashSet<>());
            Set<String> nuevosGuardados = new HashSet<>(guardados);
            nuevosGuardados.add(nombre);

            preferencias.edit().putStringSet("ejerciciosPersonales_" + tipoUsuario + "_" + diaSeleccionado, nuevosGuardados)
                    .putInt("totalEjercicios_" + diaSeleccionado, rutina.size())
                    .apply();
            mostrarRutina();
        }
    }
    private void cargarEjerciciosPersonales(String dia) {
        SharedPreferences preferencias = getSharedPreferences("perfil", MODE_PRIVATE);
        Set<String> guardados = preferencias.getStringSet("ejerciciosPersonales_" + tipoUsuario + "_" + dia, new HashSet<>());
        for (String nombre : guardados) {
            Ejercicio ejercicioBase = buscarEjercicio(nombre);
            if (ejercicioBase != null) {
                Ejercicio ejercicio = new Ejercicio(
                        ejercicioBase.getNombre(),
                        ejercicioBase.getTiempo(),
                        ejercicioBase.getConsejo(),
                        ejercicioBase.getVideoHombre(),
                        ejercicioBase.getVideoMujer()
                );

                ejercicio.setPersonalizado(true);
                rutina.add(ejercicio);
            }
        }
    }
    private Ejercicio buscarEjercicio(String nombre) {
        for (Ejercicio e : listaEjerciciosCompletos) {
            if (e.getNombre().equals(nombre)) {
                return e;
            }
        }
        return null;
    }
}