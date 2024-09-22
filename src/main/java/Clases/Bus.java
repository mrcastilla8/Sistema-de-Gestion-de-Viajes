
package Clases;

public class Bus {
    private int idBus;
    private String tipo;
    private int capacidad;
    private String estado;
    private String conductor;

//Default 
    public Bus() {    
    }

// Constructor
    public Bus(int idBus, String tipo, int capacidad, String estado, String conductor) {
        this.idBus = idBus;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
        this.conductor = conductor;
    }

    // Métodos
    public void mostrarBus(){
        System.out.println("id: "+this.idBus+", tipo: "+this.tipo+", capacidad: "+this.capacidad+" estado: "+this.estado+", conductor: "+this.conductor);
    }
    
    public void addBus() {
        // Implementación
    }

    public void alertaAveria() {
        // Implementación
    }

    public void revisarEstado() {
        // Implementación
    }

    public void borrarBus() {
        // Implementación
    }

    public void modificarInfo() {
        // Implementación
    }

    // Getters y Setters
    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
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

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }
}

