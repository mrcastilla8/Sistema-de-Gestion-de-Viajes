
package com.mycompany.holamundo;

public class Operador extends Persona {
    private String usuario;
    private String contrasena;
    private String cargo;

    //Default

    public Operador() {
    }

    // Constructor
    public Operador(String usuario, String contrasena, String cargo, String dni, String nombre, String numero, int edad) {
        super(dni, nombre, numero, edad);
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.cargo = cargo;
    }
    

    // Métodos de Operador
    public void iniciarSesion() {
        // Implementación del método
    }

    public void verificarCredenciales() {
        // Implementación del método
    }

    public void crearCuenta() {
        // Implementación del método
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}