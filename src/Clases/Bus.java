
package Clases;

public class Bus {
    private static int contadorId = 1; 
    private final int idBus; 
    private String tipo;
    private int capacidad;
    private String estado;
    private String conductor1;
     private String conductor2;


    // Default
    public Bus() {
        this.idBus = contadorId++; 
    }

    // Constructor con parámetros
    public Bus(String tipo, int capacidad, String estado, String conductor1, String conductor2) {
        this.idBus = contadorId++;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
        this.conductor1 = conductor1;
    }

    // Muestra los atributos del bus
    public void mostrarBus() {
        System.out.println("ID: " + this.idBus + ", Tipo: " + this.tipo + ", Capacidad: " + this.capacidad + 
                           ", Estado: " + this.estado + ", Conductor 1: " + this.conductor1+", Conductor 2: " + this.conductor2);
    }

    // Getters y Setters 
    //No hay setter para getIdBus, porque segun los atributos, tiene que ser inmutable, unico y no nulo
    public int getIdBus() {
        return idBus;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getConductor1() {
        return conductor1;
    }

    public void setConductor1(String conductor) {
        this.conductor1 = conductor;
    }

    public String getConductor2() {
        return conductor2;
    }

    public void setConductor2(String conductor2) {
        this.conductor2 = conductor2;
    }
    
}