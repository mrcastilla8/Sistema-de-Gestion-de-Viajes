package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Viaje implements Serializable {

    private static int contadorId = 1; // Contador para IDs de viajes
    private final int idViaje;
    private Bus busAsignado;
    private List<Conductor> conductoresAsignados; // Lista de conductores asignados
    private Ruta rutaAsignada;
    private List<String> asientosReservados;
    private double precio;

    // Constructor
    public Viaje(Bus busAsignado, List<Conductor> conductoresAsignados, Ruta rutaAsignada) {
        this.idViaje = contadorId++;
        this.busAsignado = busAsignado;
        this.conductoresAsignados = conductoresAsignados;
        this.rutaAsignada = rutaAsignada;
        this.asientosReservados = new ArrayList<>();
        this.precio = calcularPrecio(); // Calcular y asignar el precio
    }

    public double calcularPrecio() {
        double basePrecioPorHora = 10.0; // Precio base por hora
        double multiplicador = 1.0; // Multiplicador por defecto

        // Verificar si el bus es de tipo premium
        if (busAsignado != null && busAsignado.getTipo() != null) {
            if (busAsignado.getTipo().equalsIgnoreCase("premium")) {
                multiplicador = 1.5; // Aumentar el precio para buses premium
            }
        } else {
            System.out.println("Error: Bus asignado es nulo o no tiene tipo definido.");
        }

        // Calcular el precio basado en la duración estimada de la ruta
        if (rutaAsignada != null) {
            int duracion = rutaAsignada.getDuracionEstimada(); // Duración en horas
            if (duracion < 0) {
                System.out.println("Error: La duración estimada de la ruta es negativa.");
                duracion = 0;
            }
            return duracion * basePrecioPorHora * multiplicador;
        } else {
            System.out.println("Error: Ruta asignada es nula.");
            return 0.0;
        }
    }

    public double getPrecio() {
        return precio;
    }

    // Método para actualizar el contador de IDs al cargar desde archivo
    public static void actualizarContadorId(List<Viaje> viajes) {
        if (viajes.isEmpty()) {
            contadorId = 1;
        } else {
            int maxId = 0;
            for (Viaje viaje : viajes) {
                if (viaje.getIdViaje() > maxId) {
                    maxId = viaje.getIdViaje();
                }
            }
            contadorId = maxId + 1;
        }
    }

    // Métodos
    public void elegirAsientos() {
        // Implementación como antes, usando la clase Asiento
    }

    public void verDetalles() {
        // Implementación
    }

    public void borrarViaje() {
        // Implementación
    }

    // Getters y Setters
    public int getIdViaje() {
        return idViaje;
    }

    public Bus getBusAsignado() {
        return busAsignado;
    }

    public void setBusAsignado(Bus busAsignado) {
        this.busAsignado = busAsignado;
    }

    public List<Conductor> getConductoresAsignados() {
        return conductoresAsignados;
    }

    public void setConductoresAsignados(List<Conductor> conductoresAsignados) {
        this.conductoresAsignados = conductoresAsignados;
    }

    public Ruta getRutaAsignada() {
        return rutaAsignada;
    }

    public void setRutaAsignada(Ruta rutaAsignada) {
        this.rutaAsignada = rutaAsignada;
    }

    public List<String> getAsientosReservados() {
        return asientosReservados;
    }

    public void setAsientosReservados(List<String> asientosReservados) {
        this.asientosReservados = asientosReservados;
    }
}
