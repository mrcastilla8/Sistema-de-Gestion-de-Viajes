
package com.mycompany.holamundo;

public class Viaje {
    private int idViaje;
    private Bus busAsignado;
    private Conductor conductorAsignado;
    private Ruta rutaAsignada;

    // Constructor
    public Viaje(int idViaje, Bus busAsignado, Conductor conductorAsignado, Ruta rutaAsignada) {
        this.idViaje = idViaje;
        this.busAsignado = busAsignado;
        this.conductorAsignado = conductorAsignado;
        this.rutaAsignada = rutaAsignada;
    }

    // Métodos
    public void addPreferencias() {
        // Implementación
    }

    public void verDetalles() {
        // Implementación
    }

    public void elegirAsientos() {
        // Implementación
    }

    public void borrarViaje() {
        // Implementación
    }

    // Getters y Setters
    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public Bus getBusAsignado() {
        return busAsignado;
    }

    public void setBusAsignado(Bus busAsignado) {
        this.busAsignado = busAsignado;
    }

    public Conductor getConductorAsignado() {
        return conductorAsignado;
    }

    public void setConductorAsignado(Conductor conductorAsignado) {
        this.conductorAsignado = conductorAsignado;
    }

    public Ruta getRutaAsignada() {
        return rutaAsignada;
    }

    public void setRutaAsignada(Ruta rutaAsignada) {
        this.rutaAsignada = rutaAsignada;
    }
}

