
package com.mycompany.holamundo;

public class Conductor extends Persona {
    private int idConductor;
    private String numLicencia;

    // Constructor

    public Conductor(int idConductor, String numLicencia, String dni, String nombre, String numero, int edad) {
        super(dni, nombre, numero, edad);
        this.idConductor = idConductor;
        this.numLicencia = numLicencia;
    }
    

    // Métodos de Conductor
    public void addChofer() {
        // Implementación del método
    }

    public void verificar() {
        // Implementación del método
    }

    public void eliminarChofer() {
        // Implementación del método
    }

    public void modificarInfo() {
        // Implementación del método
    }

    public void alertaSobrecargaDeTrabajo() {
        // Implementación del método
    }

    // Getters y Setters
    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNumLicencia() {
        return numLicencia;
    }

    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }
}
