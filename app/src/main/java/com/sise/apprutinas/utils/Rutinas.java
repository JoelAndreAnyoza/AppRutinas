package com.sise.apprutinas.utils;

import com.sise.apprutinas.R;
import com.sise.apprutinas.model.Ejercicio;

import java.util.ArrayList;
public class Rutinas {
    public static ArrayList<Ejercicio> obtenerRutina(int nivel, String dia){
        ArrayList<Ejercicio> rutina = new ArrayList<>();
        switch (nivel) {

            // NIVEL 1
            case 1:
                switch (dia) {
                    case "Lunes":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Respira lentamente durante cada movimiento.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Trotar", "10 min", "Mantén un ritmo cómodo sin detenerte.", R.raw.trotar_h, R.raw.trotar_m));
                        rutina.add(new Ejercicio("Sentadilla básica", "3 x 12", "Mantén la espalda recta.", R.raw.sentadilla_basica_h, R.raw.sentadilla_basica_m));
                        rutina.add(new Ejercicio("Extensión alterna de brazo y pierna", "3 x 10", "Aprieta el abdomen en cada repetición.", R.raw.bird_dog_h, R.raw.bird_dog_m));
                        break;

                    case "Martes":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Prepara el cuerpo antes de iniciar.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Saltos de tijera", "3 x 20", "Conserva una postura erguida.", R.raw.jumping_jacks_h, R.raw.jumping_jacks_m));
                        rutina.add(new Ejercicio("Elevación de cadera", "3 x 15", "Eleva la cadera y mantén la espalda recta.", R.raw.puente_cadera_h, R.raw.puente_cadera_m));
                        rutina.add(new Ejercicio("Abdominales", "3 x 15", "Respira al subir e inhala al bajar.", R.raw.abdominal_crunch_h, R.raw.abdominal_crunch_m));
                        break;

                    case "Miércoles":
                        rutina.add(new Ejercicio("Día de descanso", "Libre", "Realiza estiramientos suaves y recupera energías.", R.raw.descanso_h, R.raw.descanso_m));
                        break;

                    case "Jueves":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Activa el cuerpo antes de entrenar.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Trotar", "12 min", "Controla la respiración.", R.raw.trotar_h, R.raw.trotar_m));
                        rutina.add(new Ejercicio("Paso lateral", "3 x 20", "Coordina tus movimientos sin perder el ritmo.", R.raw.paso_lateral_h, R.raw.paso_lateral_m));
                        rutina.add(new Ejercicio("Plancha con rodillas apoyadas", "30 segundos", "No hundas la espalda.", R.raw.plancha_rodillas_h, R.raw.plancha_rodillas_m));
                        break;

                    case "Viernes":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Respira lento antes de empezar.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Rodillas al pecho", "3 x 20", "Evita inclinar demasiado el cuerpo hacia atrás.", R.raw.rodillas_al_pecho_h, R.raw.rodillas_al_pecho_m));
                        rutina.add(new Ejercicio("Sentadilla básica", "3 x 15", "Controla la bajada.", R.raw.sentadilla_basica_h, R.raw.sentadilla_basica_m));
                        rutina.add(new Ejercicio("Elevación de cadera", "3 x 15", "Eleva la cadera y mantén la espalda recta.", R.raw.puente_cadera_h, R.raw.puente_cadera_m));
                        break;

                    case "Sábado":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Calienta antes de moverte.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Saltos de tijera", "3 x 25", "Mantén el ritmo.", R.raw.jumping_jacks_h, R.raw.jumping_jacks_m));
                        rutina.add(new Ejercicio("Escaladores cruzados", "3 x 20", "Lleva la rodilla hacia el codo contrario.", R.raw.escaladores_cruzados_h, R.raw.escaladores_cruzados_m));
                        rutina.add(new Ejercicio("Paso lateral", "3 x 20", "Coordina tus movimientos sin perder el ritmo.", R.raw.paso_lateral_h, R.raw.paso_lateral_m));
                        break;
                    case "Domingo":
                        rutina.add(new Ejercicio("Día de descanso", "Libre", "Recupera energías para la siguiente semana.", R.raw.descanso_h, R.raw.descanso_m));
                        break;
                }

                break;

            // NIVEL 2
            case 2:
                switch (dia) {
                    case "Lunes":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Activa el cuerpo antes de empezar.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Burpees", "3 x 8", "Hazlo con calma y cuida la postura.", R.raw.burpees_h, R.raw.burpees_m));

                        rutina.add(new Ejercicio("Mountain Climbers", "3 x 20", "Mantén las manos firmes en el piso.", R.raw.mountain_climbers_h, R.raw.mountain_climbers_m));


                        rutina.add(new Ejercicio("Plancha lateral", "30 segundos", "Mantén el cuerpo alineado en todo momento.", R.raw.plancha_lateral_h, R.raw.plancha_lateral_m));
                        break;

                    case "Martes":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Prepara piernas y brazos.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Flexiones de pecho", "3 x 10", "Baja sin doblar la espalda.", R.raw.flexiones_pecho_h, R.raw.flexiones_pecho_m));
                        rutina.add(new Ejercicio("Zancadas alternas", "3 x 12", "Da un paso firme y controla la bajada.", R.raw.zancadas_alternas_h, R.raw.zancadas_alternas_m));
                        rutina.add(new Ejercicio("Fondos de tríceps en silla", "3 x 10", "Usa una silla estable.", R.raw.fondos_silla_h, R.raw.fondos_silla_m));
                        break;

                    case "Miércoles":
                        rutina.add(new Ejercicio("Día de descanso", "Libre", "Descansa y realiza estiramientos suaves.", R.raw.descanso_h, R.raw.descanso_m));
                        break;

                    case "Jueves":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Calienta antes de subir la intensidad.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Sentadilla con salto", "3 x 10", "Aterriza suave para cuidar las rodillas.", R.raw.sentadilla_salto_h, R.raw.sentadilla_salto_m));
                        rutina.add(new Ejercicio("Abdominal bicicleta", "3 x 20", "Lleva el codo hacia la rodilla contraria.", R.raw.abdominal_bicicleta_h, R.raw.abdominal_bicicleta_m));
                        rutina.add(new Ejercicio("Planchas con toque de hombros", "3 x 12", "Evita mover mucho la cadera.", R.raw.plancha_toque_hombros_h, R.raw.plancha_toque_hombros_m));
                        break;

                    case "Viernes":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Respira y activa el cuerpo.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Escaladores cruzados", "3 x 20", "Lleva la rodilla hacia el codo contrario.", R.raw.escaladores_cruzados_h, R.raw.escaladores_cruzados_m));
                        rutina.add(new Ejercicio("Biceps con banda", "3 x 12", "Mantén los codos pegados al cuerpo durante todo el movimiento.", R.raw.biceps_con_banda_h, R.raw.biceps_con_banda_m));
                        rutina.add(new Ejercicio("Elevación lateral de pierna con banda", "3 x 15", "Eleva la pierna sin mover la cadera.", R.raw.elevacion_con_banda_h, R.raw.elevacion_con_banda_m));
                        break;

                    case "Sábado":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Calienta antes del circuito.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Giro ruso", "3 x 20", "Gira el torso sin mover las piernas.", R.raw.giro_ruso_h, R.raw.giro_ruso_m));
                        rutina.add(new Ejercicio("Crunch inverso", "3 x 15", "Sube las piernas sin impulsarte.", R.raw.crunch_inverso_h, R.raw.crunch_inverso_m));
                        rutina.add(new Ejercicio("Rodillas al pecho en plancha", "3 x 15", "Lleva las rodillas al pecho con control.", R.raw.rodillas_pecho_plancha_h, R.raw.rodillas_pecho_plancha_m));
                        break;

                    case "Domingo":
                        rutina.add(new Ejercicio("Día de descanso", "Libre", "Recupera energías para continuar.", R.raw.descanso_h, R.raw.descanso_m));
                        break;
                }

                break;

            // NIVEL 3
            case 3:
                switch (dia) {
                    case "Lunes":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Prepara el cuerpo antes del entrenamiento.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Sentadilla con barra", "4 x 10", "Mantén la espalda recta durante todo el movimiento.", R.raw.sentadilla_barra_h, R.raw.sentadilla_barra_m));
                        rutina.add(new Ejercicio("Press de banca", "4 x 10", "Baja la barra de forma controlada.", R.raw.press_banca_h, R.raw.press_banca_m));
                        rutina.add(new Ejercicio("Curl de bíceps con mancuernas", "3 x 12", "Evita balancear el cuerpo.", R.raw.curl_biceps_h, R.raw.curl_biceps_m));
                        break;

                    case "Martes":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Calienta hombros y piernas.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Peso muerto con barra", "4 x 8", "Empuja con las piernas al levantar.", R.raw.peso_muerto_h, R.raw.peso_muerto_m));
                        rutina.add(new Ejercicio("Remo con barra", "4 x 10", "Lleva la barra hacia el abdomen.", R.raw.remo_barra_h, R.raw.remo_barra_m));
                        rutina.add(new Ejercicio("Elevaciones laterales con mancuernas", "3 x 12", "Levanta los brazos hasta la altura de los hombros.", R.raw.elevaciones_laterales_h, R.raw.elevaciones_laterales_m));
                        break;

                    case "Miércoles":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Descansa y realiza movilidad ligera.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Giro ruso con mancuernas", "3 x 15", "Realiza giros de un lado a otro sin perder el equilibrio.", R.raw.giro_ruso_mancuernas_h, R.raw.giro_ruso_mancuernas_m));
                        rutina.add(new Ejercicio("Curl martillo con mancuernas", "3 x 12", "Sube las mancuernas sin mover los codos.", R.raw.curl_martillo_h, R.raw.curl_martillo_m));
                        rutina.add(new Ejercicio("Extensión de tríceps en polea", "3 x 12", "No separes los codos del cuerpo.", R.raw.extension_triceps_h, R.raw.extension_triceps_m));
                        break;

                    case "Jueves":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Activa el cuerpo antes de comenzar.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Prensa de piernas", "4 x 12", "Empuja con ambos pies por igual.", R.raw.prensa_piernas_h, R.raw.prensa_piernas_m));
                        rutina.add(new Ejercicio("Jalón al pecho en polea", "4 x 10", "Lleva la barra hasta el pecho.", R.raw.jalon_pecho_h, R.raw.jalon_pecho_m));
                        rutina.add(new Ejercicio("Extensión de tríceps en polea", "3 x 12", "No separes los codos del cuerpo.", R.raw.extension_triceps_h, R.raw.extension_triceps_m));
                        break;

                    case "Viernes":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Respira profundo antes de iniciar.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Press militar con mancuernas", "4 x 10", "No arquees la espalda.", R.raw.press_militar_h, R.raw.press_militar_m));
                        rutina.add(new Ejercicio("Hip Thrust con barra", "4 x 12", "Eleva la cadera y controla la bajada.", R.raw.hip_thrust_h, R.raw.hip_thrust_m));
                        rutina.add(new Ejercicio("Giro ruso con mancuernas", "3 x 15", "Realiza giros de un lado a otro sin perder el equilibrio.", R.raw.giro_ruso_mancuernas_h, R.raw.giro_ruso_mancuernas_m));
                        break;

                    case "Sábado":
                        rutina.add(new Ejercicio("Estiramiento de cuerpo completo", "5 min", "Prepara el cuerpo para el circuito.", R.raw.estiramiento_cuerpo_completo_h, R.raw.estiramiento_cuerpo_completo_m));
                        rutina.add(new Ejercicio("Zancada búlgara", "3 x 10", "Controla la bajada y mantén el equilibrio.", R.raw.zancada_bulgara_h, R.raw.zancada_bulgara_m));
                        rutina.add(new Ejercicio("Sentadilla sumo con mancuerna", "3 x 12", "Baja manteniendo una postura amplia y estable.", R.raw.sentadilla_sumo_h, R.raw.sentadilla_sumo_m));
                        rutina.add(new Ejercicio("Curl martillo con mancuernas", "3 x 12", "Sube las mancuernas sin mover los codos.", R.raw.curl_martillo_h, R.raw.curl_martillo_m));
                        break;

                        case "Domingo":
                            rutina.add(new Ejercicio("Día de descanso", "Libre", "Permite que tus músculos se recuperen.", R.raw.descanso_h, R.raw.descanso_m));
                            break;
                }
                break;
        }
        return rutina;
    }
    public static String obtenerNombreNivel(int nivel) {
        if (nivel == 1) {
            return "Nivel Principiante";
        } else if (nivel == 2) {
            return "Nivel Activo";
        } else {
            return "Nivel Avanzado";
        }
    }

    public static ArrayList<Ejercicio> obtenerTodosLosEjercicios() {
        ArrayList<Ejercicio> todos = new ArrayList<>();
        String dias[] = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        for (int nivel = 1; nivel <= 3; nivel++) {
            for (String dia : dias) {
                ArrayList<Ejercicio> rutina = obtenerRutina(nivel, dia);
                for (Ejercicio e : rutina) {
                    boolean existe = false;
                    for (Ejercicio x : todos) {
                        if (x.getNombre().equals(e.getNombre())) {
                            existe = true;
                        }
                    }
                    if (!existe && !e.getNombre().equals("Día de descanso")) {
                        todos.add(e);
                    }
                }
            }
        }
        return todos;
    }

}