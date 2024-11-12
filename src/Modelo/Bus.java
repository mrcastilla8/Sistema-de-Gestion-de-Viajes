package modelo;

public class Bus {
    private int id;
    private String tipo;
    private int capacidad;
    private String estado;

    // Constructor
    public Bus(int id, String tipo, int capacidad, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
