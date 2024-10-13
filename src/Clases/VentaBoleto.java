package Clases;

import java.util.List;
import java.util.Scanner;

public class VentaBoleto {
    private Scanner entrada = new Scanner(System.in);

    public void menuVentaBoleto() {
        int opcion;
        do {
            limpiarPantalla();
            System.out.println("-------------------------------------");
            System.out.println("\t=== Venta de boletos ===");
            System.out.println("-------------------------------------");
            System.out.println("1. Solicitud de preferencias");
            System.out.println("2. Salir");
            System.out.println("Seleccione una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    solicitudPreferencias();
                    break;
                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    pausar();
                    break;
            }
        } while (opcion != 2);       
    }   
    
    public void solicitudPreferencias() {
        Clases.CRUDruta crudRuta = new CRUDruta();
        Clases.ViajeCRUD crudViaje = new ViajeCRUD();
        List<Viaje> viajesCoincidentes;
        List<Asiento> asientos;

        limpiarPantalla();
        System.out.println("-------------------------------------");
        System.out.println("\t=== Solicitud de preferencias ===");
        System.out.println("-------------------------------------");
        System.out.println("Ingrese el lugar de inicio: ");
        String lugarInicio = entrada.nextLine();
        System.out.println("Ingrese el lugar de destino: ");
        String lugarDestino = entrada.nextLine();     
        int idRuta = crudRuta.obtenerIdRutaPorSalidayDestino(lugarInicio, lugarDestino);

        limpiarPantalla();
        System.out.println("Viajes coincidentes: ");
        viajesCoincidentes = crudViaje.obtenerViajesCoincidentes(idRuta);
        if (viajesCoincidentes.size() == 0) {
            System.out.println("No hay viajes coincidentes");
        } else {
            int opcionViaje, tamañoLista = viajesCoincidentes.size();
            do {
                limpiarPantalla();
                System.out.println("Lista de viajes coincidentes: ");
                for (int i = 0; i < tamañoLista; i++) {
                    Viaje viaje = viajesCoincidentes.get(i);
                    System.out.println(i + 1 + 
                            ". ID Viaje: " + viaje.getIdViaje() +
                            ", Bus ID: " + viaje.getBusAsignado().getIdBus() +
                            ", Tipo de bus: " + viaje.getBusAsignado().getTipo() +
                            ", Conductores: " + crudViaje.obtenerNombresConductores(viaje.getConductoresAsignados()) +
                            ", Ruta: " + viaje.getRutaAsignada().getLugarInicio() + " - " + viaje.getRutaAsignada().getLugarDestino());                   
                }
                System.out.println(tamañoLista + 1 + ". Salir");
                System.out.println("Seleccione un viaje o salir: ");
                opcionViaje = entrada.nextInt();
                entrada.nextLine(); 

                limpiarPantalla();
                Viaje viajeSeleccionado = viajesCoincidentes.get(opcionViaje - 1);
                System.out.println("Viaje seleccionado: ");
                System.out.println("ID Viaje: " + viajeSeleccionado.getIdViaje() +
                                ", Bus ID: " + viajeSeleccionado.getBusAsignado().getIdBus() +
                                ", Tipo de bus: " + viajeSeleccionado.getBusAsignado().getTipo() +
                                ", Conductores: " + crudViaje.obtenerNombresConductores(viajeSeleccionado.getConductoresAsignados()) +
                                ", Ruta: " + viajeSeleccionado.getRutaAsignada().getLugarInicio() + " - " + viajeSeleccionado.getRutaAsignada().getLugarDestino());
                
                asientos = Asiento.generarAsientos(viajeSeleccionado.getBusAsignado().getTipo());
                Asiento.mostrarAsientosDisponibles(asientos);
                System.out.println("Desea reservar un asiento?: ");
                System.out.println("1. Sí");
                System.out.println("2. No");
                int opcionReserva = entrada.nextInt();
                entrada.nextLine();
                
                if (opcionReserva == 2) {
                    break;
                } else {
                    System.out.println("Ingrese el número de asiento a reservar: ");
                    String numeroAsiento = entrada.nextLine();
                    if (Asiento.reservarAsiento(asientos, numeroAsiento)) {
                        System.out.println("Asiento reservado con éxito.");
                    } else {
                        System.out.println("El asiento no está disponible o no existe.");
                    }   
                }   
            } while (opcionViaje < 1 || opcionViaje > tamañoLista + 1);       
        }  
        pausar(); 
    }

    private void pausar() {
        System.out.println("\nPresiona Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
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