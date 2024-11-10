
package Modelo;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Conductor extends Persona implements Serializable{
    private int idConductor;
    private String numLicencia;
    
    // Constructor
    public Conductor() {
    }
    
    public Conductor(int idConductor, String numLicencia, String dni, String nombre, String apellido, String numero, int edad) {
        super(dni, nombre, apellido, numero, edad);
        this.idConductor = idConductor;
        this.numLicencia = numLicencia;
    }
    

    // Métodos de Conductor

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
