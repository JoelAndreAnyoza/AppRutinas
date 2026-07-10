package com.sise.apprutinas.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sise.apprutinas.R;
import com.sise.apprutinas.model.Ejercicio;
import com.sise.apprutinas.utils.Rutinas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    TextView tvWelcome, tvPremiumBloqueado;
    LinearLayout layoutBienvenida;
    View layoutInicio;

    LinearLayout btnInicio, btnSeguimiento, btnPerfil;
    TextView tvInicioNav;
    View lineInicio;

    String userName, userType;

    TextView tvFechaHoy, tvSaludoUsuario, tvNombreRutinaHoy;
    TextView tvDetalleRutinaHoy, tvDiasCompletados;
    TextView tvPorcentajeProgreso, tvListaDiasSemana;

    Button btnIniciarEntrenamiento;
    ProgressBar pbProgresoSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvPremiumBloqueado = findViewById(R.id.tvPremiumBloqueado);
        layoutBienvenida = findViewById(R.id.layoutBienvenida);
        layoutInicio = findViewById(R.id.layoutInicio);

        btnInicio = findViewById(R.id.btnInicio);
        btnSeguimiento = findViewById(R.id.btnSeguimiento);
        btnPerfil = findViewById(R.id.btnPerfil);

        tvInicioNav = findViewById(R.id.tvInicioNav);
        lineInicio = findViewById(R.id.lineInicio);

        tvInicioNav.setTextColor(getResources().getColor(R.color.principal));
        lineInicio.setVisibility(View.VISIBLE);

        tvFechaHoy = findViewById(R.id.tvFechaHoy);
        tvSaludoUsuario = findViewById(R.id.tvSaludoUsuario);
        tvNombreRutinaHoy = findViewById(R.id.tvNombreRutinaHoy);
        tvDetalleRutinaHoy = findViewById(R.id.tvDetalleRutinaHoy);
        tvDiasCompletados = findViewById(R.id.tvDiasCompletados);
        tvPorcentajeProgreso = findViewById(R.id.tvPorcentajeProgreso);
        tvListaDiasSemana = findViewById(R.id.tvListaDiasSemana);
        btnIniciarEntrenamiento = findViewById(R.id.btnIniciarEntrenamiento);
        pbProgresoSemana = findViewById(R.id.pbProgresoSemana);

        userName = getIntent().getStringExtra("USER_NAME");
        userType = getIntent().getStringExtra("USER_TYPE");

        SharedPreferences prefs = getSharedPreferences("perfil", MODE_PRIVATE);

        if (userName == null) {
            userName = prefs.getString("USER_NAME", "Usuario");
        }

        if (userType == null) {
            userType = prefs.getString("USER_TYPE", prefs.getString("tipoUsuario", "GRATUITO")
            );
        }

        tvWelcome.setText("¡Hola, " + userName + "!\nCuenta: " + userType);

        if ("GRATUITO".equals(userType)) {
            tvPremiumBloqueado.setVisibility(View.VISIBLE);
        } else {
            tvPremiumBloqueado.setVisibility(View.GONE);
        }

        cargarDatosDashboard(prefs);

        new Handler().postDelayed(() -> {
            layoutBienvenida.setVisibility(View.GONE);
            layoutInicio.setVisibility(View.VISIBLE);
        }, 3000);

        btnInicio.setOnClickListener(v -> {
        });

        btnSeguimiento.setOnClickListener(v -> {
            Intent intent = new Intent(
                    HomeActivity.this,
                    SeguimientoActivity.class
            );

            intent.putExtra("USER_TYPE", userType);
            startActivity(intent);
        });

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent(
                    HomeActivity.this,
                    PerfilActivity.class
            );

            intent.putExtra("USER_TYPE", userType);
            startActivity(intent);
        });

        btnIniciarEntrenamiento.setOnClickListener(v -> {
            Intent intent = new Intent(
                    HomeActivity.this,
                    SeguimientoActivity.class
            );

            intent.putExtra("USER_TYPE", userType);
            startActivity(intent);
        });
    }

    private void cargarDatosDashboard(SharedPreferences prefs) {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat(
                "EEEE, d 'de' MMMM",
                new Locale("es", "ES")
        );

        String fechaFormateada = sdf.format(calendar.getTime());

        fechaFormateada =
                fechaFormateada.substring(0, 1).toUpperCase()
                        + fechaFormateada.substring(1);

        tvFechaHoy.setText(fechaFormateada);
        tvSaludoUsuario.setText("¡Hola, " + userName + "!");

        SimpleDateFormat sdfDia = new SimpleDateFormat(
                "EEEE",
                new Locale("es", "ES")
        );

        String diaSemana = sdfDia.format(calendar.getTime());

        diaSemana =
                diaSemana.substring(0, 1).toUpperCase()
                        + diaSemana.substring(1);

        int nivelRutina = prefs.getInt("nivelRutina", 1);

        ArrayList<Ejercicio> rutinaHoy =
                Rutinas.obtenerRutina(nivelRutina, diaSemana);

        if (rutinaHoy != null && !rutinaHoy.isEmpty()) {

            if (rutinaHoy.get(0).getNombre().equals("Día de descanso")) {

                tvNombreRutinaHoy.setText("Día de descanso");

                tvDetalleRutinaHoy.setText(
                        "Aprovecha hoy para recuperar energías."
                );

                btnIniciarEntrenamiento.setEnabled(false);
                btnIniciarEntrenamiento.setText("Día libre");

            } else {

                tvNombreRutinaHoy.setText("Rutina de " + diaSemana);

                tvDetalleRutinaHoy.setText(
                        rutinaHoy.size() + " ejercicios programados"
                );

                btnIniciarEntrenamiento.setEnabled(true);
                btnIniciarEntrenamiento.setText("Iniciar entrenamiento");
            }
        }

        String dias[] = {
                "Lunes",
                "Martes",
                "Miércoles",
                "Jueves",
                "Viernes",
                "Sábado",
                "Domingo"
        };

        int diasCompletadosCount = 0;
        StringBuilder detalleDias = new StringBuilder();

        for (String d : dias) {

            boolean estaCompletado =
                    prefs.getBoolean("completado_" + d, false);

            if (estaCompletado) {

                diasCompletadosCount++;
                detalleDias.append(d).append(":  Completado\n");

            } else if (d.equalsIgnoreCase(diaSemana)) {

                detalleDias.append(d).append(":  En progreso\n");

            } else {

                detalleDias.append(d).append(":  Pendiente\n");
            }
        }

        int porcentaje =
                (int) ((diasCompletadosCount / 7.0) * 100);

        tvDiasCompletados.setText(
                "Completados: " + diasCompletadosCount + " de 7 días"
        );

        tvPorcentajeProgreso.setText(porcentaje + "%");
        pbProgresoSemana.setProgress(porcentaje);
        tvListaDiasSemana.setText(detalleDias.toString().trim());
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs =
                getSharedPreferences("perfil", MODE_PRIVATE);

        cargarDatosDashboard(prefs);
    }
}