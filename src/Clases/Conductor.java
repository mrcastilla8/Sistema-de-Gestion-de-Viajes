
package Clases;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Conductor extends Persona {
    private int idConductor;
    private String numLicencia;
    //lista de conductores:
    Scanner entrada = new Scanner(System.in);
    
    // Constructor

    public Conductor(int idConductor, String numLicencia, String dni, String nombre, String numero, int edad) {
        super(dni, nombre, numero, edad);
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
