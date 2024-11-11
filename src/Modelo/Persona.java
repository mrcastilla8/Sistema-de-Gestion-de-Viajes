
package Modelo;
import java.io.Serializable;
public class Persona implements Serializable{
    private String dni;
    private String nombre;
    private String apellido;
    private String numero;
    private int edad;

    public Persona() {
    }

    public Persona(String dni, String nombre, String apellido, String numero, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.edad = edad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }   
    
    public String getApellido() {
        return apellido;
    }    

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
}
