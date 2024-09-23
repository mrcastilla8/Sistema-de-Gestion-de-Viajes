
package Clases;

public class Persona {
    private String dni;
    private String nombre;
    private String numero;
    private int edad;

    public Persona() {
    }

    public Persona(String dni, String nombre, String numero, int edad) {
        this.dni = dni;
        this.nombre = nombre;
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
