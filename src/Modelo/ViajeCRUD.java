package Modelo;

import Controlador.Main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ViajeCRUD {
/*
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
            Main.limpiarPantalla();

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
            Main.limpiarPantalla();
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
            System.out.println("ID Viaje: " + viaje.getIdViaje()
                    + ", Bus ID: " + viaje.getBusAsignado().getIdBus()
                    + ", Tipo de bus: " + viaje.getBusAsignado().getTipo()
                    + ", Conductores: " + obtenerNombresConductores(viaje.getConductoresAsignados())
                    + ", Ruta: " + viaje.getRutaAsignada().getLugarInicio() + " - " + viaje.getRutaAsignada().getLugarDestino()
                    + ", Precio: S/" + viaje.getPrecio());
        }
        pausar();
    }

    public List<Viaje> obtenerViajesCoincidentes(int idRuta) {
        List<Viaje> viajesCoincidentes = new ArrayList<>();
        for (Viaje viaje : viajes) {
            if (viaje.getRutaAsignada().getIdRuta() == idRuta) {
                viajesCoincidentes.add(viaje);
            }
        }
        return viajesCoincidentes;
    }

    public String obtenerNombresConductores(List<Conductor> conductores) {
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

    // NUEVO MÉTODO: Eliminar un viaje
    public void eliminarViaje() {
        System.out.println("Eliminar Viaje");
        if (viajes.isEmpty()) {
            System.out.println("No hay viajes registrados.");
            pausar();
            return;
        }

        listarViajes();
        System.out.print("Ingrese el ID del viaje que desea eliminar: ");
        int idViaje = entrada.nextInt();
        entrada.nextLine(); // Limpiar buffer

        Viaje viajeAEliminar = null;
        for (Viaje viaje : viajes) {
            if (viaje.getIdViaje() == idViaje) {
                viajeAEliminar = viaje;
                break;
            }
        }

        if (viajeAEliminar != null) {
            viajes.remove(viajeAEliminar);
            Archivos.guardarObjetos("Viajes.txt", viajes);
            System.out.println("Viaje eliminado con éxito.");
        } else {
            System.out.println("No se encontró un viaje con el ID proporcionado.");
        }
        pausar();
    }

    // NUEVO MÉTODO: Modificar un viaje
    public void modificarViaje() {
        System.out.println("Modificar Viaje");
        if (viajes.isEmpty()) {
            System.out.println("No hay viajes registrados.");
            pausar();
            return;
        }

        listarViajes();
        System.out.print("Ingrese el ID del viaje que desea modificar: ");
        int idViaje = entrada.nextInt();
        entrada.nextLine(); // Limpiar buffer

        Viaje viajeAModificar = null;
        for (Viaje viaje : viajes) {
            if (viaje.getIdViaje() == idViaje) {
                viajeAModificar = viaje;
                break;
            }
        }

        if (viajeAModificar != null) {
            System.out.println("Modificando el viaje con ID: " + idViaje);

            // Puedes reutilizar la lógica del método `agregarViaje()` para seleccionar nuevo bus, conductores, y ruta.
            System.out.println("Seleccione el nuevo bus:");
            List<Bus> buses = Archivos.leerObjetos("Buses.txt");
            Set<Integer> busesAsignados = new HashSet<>();
            for (Viaje v : viajes) {
                if (v.getIdViaje() != idViaje) { // Evita el bus asignado al viaje actual
                    busesAsignados.add(v.getBusAsignado().getIdBus());
                }
            }

            Bus nuevoBus = seleccionarBus(buses, busesAsignados);
            viajeAModificar.setBusAsignado(nuevoBus);

            System.out.println("Seleccione los nuevos conductores:");
            List<Conductor> conductores = Archivos.leerObjetos("Conductores.txt");
            Set<Integer> conductoresAsignados = new HashSet<>();
            for (Viaje v : viajes) {
                if (v.getIdViaje() != idViaje) { // Evita los conductores asignados al viaje actual
                    for (Conductor c : v.getConductoresAsignados()) {
                        conductoresAsignados.add(c.getIdConductor());
                    }
                }
            }

            List<Conductor> nuevosConductores = seleccionarConductores(conductores, conductoresAsignados);
            viajeAModificar.setConductoresAsignados(nuevosConductores);

            System.out.println("Seleccione la nueva ruta:");
            List<Ruta> rutas = Archivos.leerObjetos("rutas.txt");
            Ruta nuevaRuta = seleccionarRuta(rutas);
            viajeAModificar.setRutaAsignada(nuevaRuta);

            // Guardar cambios
            Archivos.guardarObjetos("Viajes.txt", viajes);
            System.out.println("Viaje modificado con éxito.");
        } else {
            System.out.println("No se encontró un viaje con el ID proporcionado.");
        }
        pausar();
    }

    // Funciones auxiliares reutilizadas para selección de buses, conductores y rutas
    private Bus seleccionarBus(List<Bus> buses, Set<Integer> busesAsignados) {
        Bus busAsignado = null;
        while (busAsignado == null) {
            for (Bus bus : buses) {
                if (!busesAsignados.contains(bus.getIdBus())) {
                    System.out.println("ID: " + bus.getIdBus() + ", Tipo: " + bus.getTipo());
                }
            }
            System.out.print("Ingrese el ID del bus a asignar: ");
            int idBus = entrada.nextInt();
            entrada.nextLine();
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
        return busAsignado;
    }

    private List<Conductor> seleccionarConductores(List<Conductor> conductores, Set<Integer> conductoresAsignados) {
        List<Conductor> conductoresAsignadosLista = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Conductor conductorAsignado = null;
            while (conductorAsignado == null) {
                for (Conductor conductor : conductores) {
                    if (!conductoresAsignados.contains(conductor.getIdConductor())) {
                        System.out.println("ID: " + conductor.getIdConductor() + ", Nombre: " + conductor.getNombre());
                    }
                }
                System.out.print("Ingrese el ID del conductor " + (i + 1) + " a asignar: ");
                int idConductor = entrada.nextInt();
                entrada.nextLine();
                for (Conductor conductor : conductores) {
                    if (conductor.getIdConductor() == idConductor) {
                        conductorAsignado = conductor;
                        conductoresAsignados.add(idConductor);
                        break;
                    }
                }
                if (conductorAsignado == null) {
                    System.out.println("Conductor no encontrado. Intente nuevamente.");
                }
            }
            conductoresAsignadosLista.add(conductorAsignado);
        }
        return conductoresAsignadosLista;
    }

    private Ruta seleccionarRuta(List<Ruta> rutas) {
        Ruta rutaAsignada = null;
        while (rutaAsignada == null) {
            for (Ruta ruta : rutas) {
                System.out.println("ID: " + ruta.getIdRuta() + ", Origen: " + ruta.getLugarInicio() + ", Destino: " + ruta.getLugarDestino());
            }
            System.out.print("Ingrese el ID de la ruta a asignar: ");
            int idRuta = entrada.nextInt();
            entrada.nextLine();
            for (Ruta ruta : rutas) {
                if (ruta.getIdRuta() == idRuta) {
                    rutaAsignada = ruta;
                    break;
                }
            }
            if (rutaAsignada == null) {
                System.out.println("Ruta no encontrada. Intente nuevamente.");
            }
        }
        return rutaAsignada;
    }

    // Menú actualizado con las nuevas opciones
    public void ejecutarMenu() {
        int opcion;
        do {
            Main.limpiarPantalla();
            System.out.println("-------------------------------------");
            System.out.println("\t=== Gestión de Viajes ===");
            System.out.println("-------------------------------------");
            System.out.println("1. Agregar Viaje");
            System.out.println("2. Lista de Viajes");
            System.out.println("3. Modificar Viaje");
            System.out.println("4. Eliminar Viaje");
            System.out.println("5. Salir");
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
                    modificarViaje();
                    break;
                case 4:
                    eliminarViaje();
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
*/

}
