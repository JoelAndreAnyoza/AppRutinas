package com.sise.apprutinas.model;
public class Ejercicio {
    private String nombre;
    private String tiempo;
    private String consejo;
    private int videoHombre;
    private int videoMujer;
    private boolean completado;

    public Ejercicio(String nombre, String tiempo, String consejo, int videoHombre, int videoMujer) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.consejo = consejo;
        this.videoHombre = videoHombre;
        this.videoMujer = videoMujer;
        this.completado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public String getConsejo() {
        return consejo;
    }

    public int getVideoHombre() {
        return videoHombre;
    }

    public int getVideoMujer() {
        return videoMujer;
    }
    public boolean isCompletado() {
        return completado;
    }
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}


