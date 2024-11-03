package Clases;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Asiento implements Serializable {
    private String numeroAsiento;
    private boolean disponible;

    // Constructor
    public Asiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
        this.disponible = true; // Por defecto, el asiento está disponible
    }

    // Getters y Setters
    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Métodos estáticos para manejar asientos

    // Método para generar asientos según el tipo de bus
    public static List<Asiento> generarAsientos(String tipoBus) {
        List<Asiento> listaAsientos = new ArrayList<>();
        int capacidadTotal = asignarCapacidadPorTipo(tipoBus);

        // Los primeros 9 asientos son del primer piso (A1-A9)
        int numAsientosPrimerPiso = Math.min(capacidadTotal, 9);
        int numAsientosSegundoPiso = capacidadTotal - numAsientosPrimerPiso;

        // Generar asientos del primer piso
        for (int i = 1; i <= numAsientosPrimerPiso; i++) {
            String numeroAsiento = "A" + i;
            listaAsientos.add(new Asiento(numeroAsiento));
        }

        // Generar asientos del segundo piso
        for (int i = 1; i <= numAsientosSegundoPiso; i++) {
            String numeroAsiento = "B" + i;
            listaAsientos.add(new Asiento(numeroAsiento));
        }

        return listaAsientos;
    }

    // Método para asignar capacidad según el tipo de bus
    public static int asignarCapacidadPorTipo(String tipo) {
        int capacidad;
        switch (tipo) {
            case "Premium":
                capacidad = 30;
                break;
            case "Ejecutivo":
                capacidad = 45;
                break;
            case "Estándar":
                capacidad = 60;
                break;
            default:
                capacidad = 60;
                break;
        }
        return capacidad;
    }

    // Método para mostrar asientos disponibles
    public static void mostrarAsientosDisponibles(List<Asiento> asientos) {
        System.out.println("Asientos disponibles:");
        for (Asiento asiento : asientos) {
            if (asiento.isDisponible()) {
                System.out.print(asiento.getNumeroAsiento() + " ");
            }
            else{
                System.out.print("X");
            }
        }
        System.out.println();
    }

    // Método para reservar un asiento
    public static boolean reservarAsiento(List<Asiento> asientos, String codigoAsiento) {
        for (Asiento asiento : asientos) {
            if (asiento.getNumeroAsiento().equalsIgnoreCase(codigoAsiento)) {
                if (asiento.isDisponible()) {
                    asiento.setDisponible(false);
                    return true;
                } else {
                    return false; // El asiento no está disponible
                }
            }
        }
        return false; // No se encontró el asiento
    }

    // Método para liberar un asiento
    public static void liberarAsiento(List<Asiento> asientos, String codigoAsiento) {
        for (Asiento asiento : asientos) {
            if (asiento.getNumeroAsiento().equalsIgnoreCase(codigoAsiento)) {
                asiento.setDisponible(true);
                break;
            }
        }
    }
}
