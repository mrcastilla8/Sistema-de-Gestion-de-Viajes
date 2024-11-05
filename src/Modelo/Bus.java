package Modelo;
import java.io.Serializable;
import java.util.List;

public class Bus implements Serializable {
    private static int contadorId = 1; 
    private final int idBus; 
    private String tipo;
    private int capacidad;
    private String estado;
    private Integer conductorId1; // Ahora almacena el ID del conductor
    private Integer conductorId2; // Ahora almacena el ID del conductor

    private List<Asiento> asientos; // Lista de asientos del bus

    // Constructor
    public Bus(String tipo, int capacidad, String estado, Integer conductorId1, Integer conductorId2) {
        this.idBus = contadorId++;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
        this.conductorId1 = conductorId1;
        this.conductorId2 = conductorId2;
    }

    // Método para actualizar el contador de IDs al cargar desde archivo
    public static void actualizarContadorId(List<Bus> buses) {
        if (buses.isEmpty()) {
            contadorId = 1;
        } else {
            int maxId = 0;
            for (Bus bus : buses) {
                if (bus.getIdBus() > maxId) {
                    maxId = bus.getIdBus();
                }
            }
            contadorId = maxId + 1;
        }
    }

    // Getters y Setters
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

    public Integer getConductorId1() {
        return conductorId1;
    }

    public void setConductorId1(Integer conductorId1) {
        this.conductorId1 = conductorId1;
    }

    public Integer getConductorId2() {
        return conductorId2;
    }

    public void setConductorId2(Integer conductorId2) {
        this.conductorId2 = conductorId2;
    }
}
