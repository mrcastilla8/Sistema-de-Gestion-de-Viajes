
package Clases;

import java.util.ArrayList;
import java.util.Scanner;

public class BusCRUD {
    private ArrayList<Bus> buses = new ArrayList<>();  // Lista para almacenar los buses
    private Scanner entrada = new Scanner(System.in);

    // Metodos principales
    //Crear
    public void agregarBus() {
        System.out.println("Ingrese los datos del nuevo bus:");
        
        entrada.nextLine();
        System.out.print("Tipo: ");
        String tipo = entrada.nextLine();
        
        
        int capacidad = 0;
        if (null != tipo.toLowerCase())switch (tipo.toLowerCase()) {
            case "vip" -> capacidad = 30;
            case "premium" -> capacidad = 50;
            case "estandar" -> capacidad = 60;
            default -> {
            }
        }
        
        System.out.print("Estado: ");
        String estado = entrada.nextLine();//Lo mismo
        
        System.out.print("Conductor 1: ");
        String conductor1 = entrada.nextLine();
        
        System.out.print("Conductor 2: ");
        String conductor2 = entrada.nextLine();

        Bus nuevoBus = new Bus(tipo, capacidad, estado, conductor1,conductor2);
        buses.add(nuevoBus);

        System.out.println("Bus agregado con éxito.");
    }

    // Leer
    public void listarBuses() {
        if (buses.isEmpty()) {
            System.out.println("No hay buses registrados.");
        } else {
            System.out.println("Lista de buses:");
            for (Bus bus : buses) {
                bus.mostrarBus();
            }
        }
    }

    //Modificar
    public void actualizarBus() {
        System.out.print("Ingrese el ID del bus que desea actualizar: ");
        int idBus = entrada.nextInt();
        entrada.nextLine(); 

        Bus busActualizar = null;
        for (Bus bus : buses) {
            if (bus.getIdBus() == idBus) {
                busActualizar = bus;
                break;
            }
        }

        if (busActualizar != null) {
            System.out.println("Ingrese los nuevos datos del bus:");
            System.out.print("Nuevo Tipo: ");
            String nuevoTipo = entrada.nextLine();
            System.out.print("Nueva Capacidad: ");
            int nuevaCapacidad = entrada.nextInt();
            entrada.nextLine();  // Limpiar el buffer
            System.out.print("Nuevo Estado: ");
            String nuevoEstado = entrada.nextLine();
            System.out.print("Nuevo Conductor 1: ");
            String nuevoConductor1 = entrada.nextLine();
            System.out.print("Nuevo Conductor 2: ");
            String nuevoConductor2 = entrada.nextLine();

            busActualizar.setTipo(nuevoTipo);
            busActualizar.setCapacidad(nuevaCapacidad);
            busActualizar.setEstado(nuevoEstado);
            busActualizar.setConductor1(nuevoConductor1);
            busActualizar.setConductor2(nuevoConductor2);

            System.out.println("Bus actualizado con exito.");
        } else {
            System.out.println("No se encontro un bus con el ID proporcionado.");
        }
    }

    // Eliminar
    public void eliminarBus() {
        System.out.print("Ingrese el ID del bus que desea eliminar: ");
        int idBus = entrada.nextInt();
        entrada.nextLine();  // Limpiar el buffer

        Bus busAEliminar = null;
        for (Bus bus : buses) {
            if (bus.getIdBus() == idBus) {
                busAEliminar = bus;
                break;
            }
        }

        if (busAEliminar != null) {
            buses.remove(busAEliminar);
            System.out.println("Bus eliminado con exito.");
        } else {
            System.out.println("No se encontró un bus con el ID proporcionado.");
        }
    }

    // Método para ejecutar el menú CRUD
    public void ejecutarMenu() {
        int opcion;
        do {
            System.out.println("-------------------------------------");
            System.out.println("\t=== Gestion de Buses ===");
            System.out.println("-------------------------------------");
            System.out.println("1. Agregar Bus");
            System.out.println("2. Lista de Buses");
            System.out.println("3. Actualizar Bus");
            System.out.println("4. Eliminar Bus");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1 -> agregarBus();
                case 2 -> listarBuses();
                case 3 -> actualizarBus();
                case 4 -> eliminarBus();
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}