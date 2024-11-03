package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class BusCRUD {
    private List<Bus> buses;  // Lista para almacenar los buses
    private Scanner entrada = new Scanner(System.in, "UTF-8");

    // Constructor que carga los buses desde archivo
    public BusCRUD() {
        buses = Archivos.leerObjetos("Buses.txt");
        Bus.actualizarContadorId(buses);
    }

    // Métodos principales
    // Crear
    public void agregarBus() {
        Main.limpiarPantalla();
        System.out.println("Ingrese los datos del nuevo bus:");
        // Selección de Tipo
        String tipo = seleccionarTipoBus();

        // Asignar capacidad según el tipo seleccionado
        int capacidad = asignarCapacidadPorTipo(tipo);

        // Selección de Estado
        String estado = seleccionarEstadoBus();

        // Cargar lista de conductores
        List<Conductor> conductores = Archivos.leerObjetos("Conductores.txt");
        if (conductores.isEmpty()) {
            System.out.println("No hay conductores registrados, no puede agregar un bus sin conductores.");
            pausar();
            return;
        }

        // Crear conjunto de IDs de conductores asignados
        Set<Integer> assignedConductorIds = obtenerConductorAsignados();

        // Mostrar conductores y su estado (asignado/no asignado)
        mostrarConductores(conductores, assignedConductorIds);

        // Seleccionar conductores
        Integer conductorId1 = seleccionarConductor(conductores, assignedConductorIds, "primer");
        Integer conductorId2 = seleccionarConductor(conductores, assignedConductorIds, "segundo");

        // Crear el nuevo bus y agregarlo a la lista
        Bus nuevoBus = new Bus(tipo, capacidad, estado, conductorId1, conductorId2);
        buses.add(nuevoBus);

        // Guardar la lista de buses
        Archivos.guardarObjetos("Buses.txt", buses);

        System.out.println("Bus agregado con éxito.");
        pausar();
    }

    // Método para seleccionar el tipo de bus
    private String seleccionarTipoBus() {
        System.out.println("Seleccione el tipo de bus:");
        System.out.println("1. Premium");
        System.out.println("2. Ejecutivo");
        System.out.println("3. Estándar");
        System.out.print("Opción: ");
        int opcionTipo = entrada.nextInt();
        entrada.nextLine(); // Limpiar buffer

        String tipo = "";
        switch (opcionTipo) {
            case 1:
                tipo = "Premium";
                break;
            case 2:
                tipo = "Ejecutivo";
                break;
            case 3:
                tipo = "Estándar";
                break;
            default:
                System.out.println("Opción no válida. Se asignará 'Estándar' por defecto.");
                tipo = "Estándar";
                break;
        }
        return tipo;
    }

    // Método para asignar capacidad según el tipo
    private int asignarCapacidadPorTipo(String tipo) {
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

    // Método para seleccionar el estado del bus
    private String seleccionarEstadoBus() {
        System.out.println("Seleccione el estado del bus:");
        System.out.println("1. En línea");
        System.out.println("2. En viaje");
        System.out.println("3. En mantenimiento");
        System.out.print("Opción: ");
        int opcionEstado = entrada.nextInt();
        entrada.nextLine(); // Limpiar buffer

        String estado = "";
        switch (opcionEstado) {
            case 1:
                estado = "En línea";
                break;
            case 2:
                estado = "En viaje";
                break;
            case 3:
                estado = "En mantenimiento";
                break;
            default:
                System.out.println("Opción no válida. Se asignará 'En línea' por defecto.");
                estado = "En línea";
                break;
        }
        return estado;
    }

    // Método para obtener IDs de conductores asignados
    private Set<Integer> obtenerConductorAsignados() {
        Set<Integer> assignedConductorIds = new HashSet<>();
        for (Bus bus : buses) {
            if (bus.getConductorId1() != null) {
                assignedConductorIds.add(bus.getConductorId1());
            }
            if (bus.getConductorId2() != null) {
                assignedConductorIds.add(bus.getConductorId2());
            }
        }
        return assignedConductorIds;
    }

    // Método para mostrar conductores
    private void mostrarConductores(List<Conductor> conductores, Set<Integer> assignedConductorIds) {
        System.out.println("Lista de conductores:");
        for (Conductor conductor : conductores) {
            System.out.print("ID: " + conductor.getIdConductor() + ", Nombre: " + conductor.getNombre());
            if (assignedConductorIds.contains(conductor.getIdConductor())) {
                System.out.println(" (ya asignado)");
            } else {
                System.out.println();
            }
        }
    }

    // Método para seleccionar conductor
    private Integer seleccionarConductor(List<Conductor> conductores, Set<Integer> assignedConductorIds, String orden) {
        Integer conductorId = null;
        while (conductorId == null) {
            System.out.print("Seleccione el ID del " + orden + " conductor: ");
            int id = entrada.nextInt();
            entrada.nextLine(); // Limpiar buffer

            if (assignedConductorIds.contains(id)) {
                System.out.println("El conductor ya está asignado, seleccione otro.");
            } else {
                boolean exists = conductores.stream().anyMatch(c -> c.getIdConductor() == id);
                if (exists) {
                    conductorId = id;
                    assignedConductorIds.add(id); // Marcar como asignado
                } else {
                    System.out.println("ID de conductor no válido, intente de nuevo.");
                }
            }
        }
        return conductorId;
    }

    // Leer
    public void listarBuses() {
        if (buses.isEmpty()) {
            System.out.println("No hay buses registrados.");
        } else {
            // Cargar la lista de conductores para mostrar nombres
            List<Conductor> conductores = Archivos.leerObjetos("Conductores.txt");

            System.out.println("Lista de buses:");
            for (Bus bus : buses) {
                // Obtener nombres de conductores
                String nombreConductor1 = "N/A";
                String nombreConductor2 = "N/A";

                for (Conductor conductor : conductores) {
                    if (bus.getConductorId1() != null && bus.getConductorId1().equals(conductor.getIdConductor())) {
                        nombreConductor1 = conductor.getNombre();
                    }
                    if (bus.getConductorId2() != null && bus.getConductorId2().equals(conductor.getIdConductor())) {
                        nombreConductor2 = conductor.getNombre();
                    }
                }

                // Mostrar información del bus
                System.out.println("ID: " + bus.getIdBus() +
                        ", Tipo: " + bus.getTipo() +
                        ", Capacidad: " + bus.getCapacidad() +
                        ", Estado: " + bus.getEstado() +
                        ", Conductor 1: " + nombreConductor1 +
                        ", Conductor 2: " + nombreConductor2);
            }
        }
    }

    // Modificar
    public void actualizarBus() {
        Main.limpiarPantalla();
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
            // Cargar lista de conductores
            List<Conductor> conductores = Archivos.leerObjetos("Conductores.txt");
            if (conductores.isEmpty()) {
                System.out.println("No hay conductores registrados, no puede actualizar un bus sin conductores.");
                pausar();
                return;
            }

            // Crear conjunto de IDs de conductores asignados, excluyendo los que están en este bus
            Set<Integer> assignedConductorIds = new HashSet<>();
            for (Bus bus : buses) {
                if (bus.getIdBus() != idBus) {
                    if (bus.getConductorId1() != null) {
                        assignedConductorIds.add(bus.getConductorId1());
                    }
                    if (bus.getConductorId2() != null) {
                        assignedConductorIds.add(bus.getConductorId2());
                    }
                }
            }

            System.out.println("Ingrese los nuevos datos del bus:");

            // Selección de Tipo
            String nuevoTipo = seleccionarTipoBus();

            // Asignar capacidad según el tipo seleccionado
            int nuevaCapacidad = asignarCapacidadPorTipo(nuevoTipo);

            // Selección de Estado
            String nuevoEstado = seleccionarEstadoBus();

            // Mostrar conductores y su estado (asignado/no asignado)
            mostrarConductores(conductores, assignedConductorIds);

            // Seleccionar conductores
            Integer nuevoConductorId1 = seleccionarConductor(conductores, assignedConductorIds, "primer");
            Integer nuevoConductorId2 = seleccionarConductor(conductores, assignedConductorIds, "segundo");

            busActualizar.setTipo(nuevoTipo);
            busActualizar.setCapacidad(nuevaCapacidad);
            busActualizar.setEstado(nuevoEstado);
            busActualizar.setConductorId1(nuevoConductorId1);
            busActualizar.setConductorId2(nuevoConductorId2);

            Archivos.guardarObjetos("Buses.txt", buses);

            System.out.println("Bus actualizado con éxito.");
        } else {
            System.out.println("No se encontró un bus con el ID proporcionado.");
        }
        pausar();
    }

    // Eliminar
    public void eliminarBus() {
        Main.limpiarPantalla();
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
            Archivos.guardarObjetos("Buses.txt", buses);
            System.out.println("Bus eliminado con éxito.");
        } else {
            System.out.println("No se encontró un bus con el ID proporcionado.");
        }
        pausar();
    }

    // Método para ejecutar el menú CRUD
    public void ejecutarMenu() {
        int opcion;
        do {
            Main.limpiarPantalla();
            System.out.println("-------------------------------------");
            System.out.println("\t=== Gestión de Buses ===");
            System.out.println("-------------------------------------");
            System.out.println("1. Agregar Bus");
            System.out.println("2. Lista de Buses");
            System.out.println("3. Actualizar Bus");
            System.out.println("4. Eliminar Bus");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    agregarBus();
                    break;
                case 2:
                    listarBuses();
                    pausar();
                    break;
                case 3:
                    actualizarBus();
                    break;
                case 4:
                    eliminarBus();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    pausar();
                    break;
            }
        } while (opcion != 5);
    }
    

    public static void pausar() {
        System.out.println("\nPresiona Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}