package Clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ViajeCRUD {
    private List<Viaje> viajes;
    private Scanner entrada = new Scanner(System.in, "UTF-8");

    public ViajeCRUD() {
        viajes = Archivos.leerObjetos("Viajes.txt");
        Viaje.actualizarContadorId(viajes);
    }

    public void agregarViaje() {
        System.out.println("Agregar nuevo viaje:");

        // Cargar la lista de buses y viajes existentes
        List<Bus> buses = Archivos.leerObjetos("Buses.txt");
        if (buses.isEmpty()) {
            System.out.println("No hay buses disponibles.");
            pausar();
            return;
        }

        // Obtener IDs de buses ya asignados
        Set<Integer> busesAsignados = new HashSet<>();
        for (Viaje v : viajes) {
            busesAsignados.add(v.getBusAsignado().getIdBus());
        }

        // Mostrar buses disponibles
        System.out.println("Lista de buses:");
        for (Bus bus : buses) {
            if (busesAsignados.contains(bus.getIdBus())) {
                System.out.println("ID: " + bus.getIdBus() + ", Tipo: " + bus.getTipo() + " (ya asignado)");
            } else {
                System.out.println("ID: " + bus.getIdBus() + ", Tipo: " + bus.getTipo());
            }
        }

        // Seleccionar bus
        Bus busAsignado = null;
        while (busAsignado == null) {
            System.out.print("Ingrese el ID del bus a asignar: ");
            int idBus = entrada.nextInt();
            entrada.nextLine();

            if (busesAsignados.contains(idBus)) {
                System.out.println("El bus ya está asignado a otro viaje. Por favor, seleccione otro.");
            } else {
                for (Bus bus : buses) {
                    if (bus.getIdBus() == idBus) {
                        busAsignado = bus;
                        break;
                    }
                }
                if (busAsignado == null) {
                    System.out.println("Bus no encontrado. Intente nuevamente.");
                }
            }
        }

        // Cargar la lista de conductores y viajes existentes
        List<Conductor> conductores = Archivos.leerObjetos("Conductores.txt");
        if (conductores.isEmpty()) {
            System.out.println("No hay conductores disponibles.");
            pausar();
            return;
        }

        // Obtener IDs de conductores ya asignados
        Set<Integer> conductoresAsignadosIds = new HashSet<>();
        for (Viaje v : viajes) {
            for (Conductor c : v.getConductoresAsignados()) {
                conductoresAsignadosIds.add(c.getIdConductor());
            }
        }

        // Seleccionar conductores
        List<Conductor> conductoresAsignados = new ArrayList<>();
        int numConductores = 2; // Número de conductores a asignar
        for (int i = 1; i <= numConductores; i++) {
            System.out.println("Lista de conductores:");
            for (Conductor conductor : conductores) {
                if (conductoresAsignadosIds.contains(conductor.getIdConductor())) {
                    System.out.println("ID: " + conductor.getIdConductor() + ", Nombre: " + conductor.getNombre() + " (ya asignado)");
                } else {
                    System.out.println("ID: " + conductor.getIdConductor() + ", Nombre: " + conductor.getNombre());
                }
            }

            Conductor conductorAsignado = null;
            while (conductorAsignado == null) {
                System.out.print("Ingrese el ID del conductor " + i + " a asignar: ");
                int idConductor = entrada.nextInt();
                entrada.nextLine();

                if (conductoresAsignadosIds.contains(idConductor)) {
                    System.out.println("El conductor ya está asignado a otro viaje. Por favor, seleccione otro.");
                } else {
                    for (Conductor conductor : conductores) {
                        if (conductor.getIdConductor() == idConductor) {
                            conductorAsignado = conductor;
                            conductoresAsignadosIds.add(idConductor); // Marcar conductor como asignado
                            break;
                        }
                    }
                    if (conductorAsignado == null) {
                        System.out.println("Conductor no encontrado. Intente nuevamente.");
                    }
                }
            }
            conductoresAsignados.add(conductorAsignado);
        }

        // Seleccionar ruta
        List<Ruta> rutas = Archivos.leerObjetos("rutas.txt");
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas disponibles.");
            pausar();
            return;
        }

        System.out.println("Lista de rutas disponibles:");
        for (Ruta ruta : rutas) {
            System.out.println("ID: " + ruta.getIdRuta() + ", Origen: " + ruta.getLugarInicio() + ", Destino: " + ruta.getLugarDestino());
        }
        System.out.print("Ingrese el ID de la ruta a asignar: ");
        int idRuta = entrada.nextInt();
        entrada.nextLine();

        Ruta rutaAsignada = null;
        for (Ruta ruta : rutas) {
            if (ruta.getIdRuta() == idRuta) {
                rutaAsignada = ruta;
                break;
            }
        }
        if (rutaAsignada == null) {
            System.out.println("Ruta no encontrada.");
            pausar();
            return;
        }

        // Crear nuevo viaje
        Viaje nuevoViaje = new Viaje(busAsignado, conductoresAsignados, rutaAsignada);

        // Elegir asientos
        nuevoViaje.elegirAsientos();

        // Agregar viaje a la lista
        viajes.add(nuevoViaje);

        // Guardar viajes
        Archivos.guardarObjetos("Viajes.txt", viajes);

        System.out.println("Viaje creado con éxito.");
        pausar();
    }

    public void listarViajes() {
        if (viajes.isEmpty()) {
            System.out.println("No hay viajes registrados.");
            pausar();
            return;
        }
        for (Viaje viaje : viajes) {
            System.out.println("ID Viaje: " + viaje.getIdViaje() +
                ", Bus ID: " + viaje.getBusAsignado().getIdBus() +
                ", Conductores: " + obtenerNombresConductores(viaje.getConductoresAsignados()) +
                ", Ruta: " + viaje.getRutaAsignada().getLugarInicio() + " - " + viaje.getRutaAsignada().getLugarDestino());
        }
        pausar();
    }

    private String obtenerNombresConductores(List<Conductor> conductores) {
        StringBuilder nombres = new StringBuilder();
        for (Conductor conductor : conductores) {
            nombres.append(conductor.getNombre()).append(" ");
        }
        return nombres.toString();
    }

    private void pausar() {
        System.out.println("\nPresiona Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public void ejecutarMenu() {
        int opcion;
        do {
            limpiarPantalla();
            System.out.println("-------------------------------------");
            System.out.println("\t=== Gestión de Viajes ===");
            System.out.println("-------------------------------------");
            System.out.println("1. Agregar Viaje");
            System.out.println("2. Lista de Viajes");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    agregarViaje();
                    break;
                case 2:
                    listarViajes();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    pausar();
                    break;
            }
        } while (opcion != 3);
    }

    public static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para sistemas Unix
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al intentar limpiar la consola.");
        }
    }
}
