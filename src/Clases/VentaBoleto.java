package Clases;

import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

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
        System.out.println("Ingrese la fecha de viaje (dd/mm/aaaa): ");
        String fechaViaje = entrada.nextLine();  
        int idRuta = crudRuta.obtenerIdRutaPorSalidayDestino(lugarInicio, lugarDestino);

        limpiarPantalla();
        System.out.println("Viajes coincidentes: ");
        viajesCoincidentes = crudViaje.obtenerViajesCoincidentes(idRuta);
        if (viajesCoincidentes.size() == 0) {
            System.out.println("No hay viajes coincidentes");
        } else {
            int opcionViaje, tamañoListaViajes = viajesCoincidentes.size();
            do {
                limpiarPantalla();
                System.out.println("Lista de viajes coincidentes: ");
                for (int i = 0; i < tamañoListaViajes; i++) {
                    Viaje viaje = viajesCoincidentes.get(i);
                    System.out.println(i + 1 + 
                            ". ID Viaje: " + viaje.getIdViaje() +
                            ", Bus ID: " + viaje.getBusAsignado().getIdBus() +
                            ", Tipo de bus: " + viaje.getBusAsignado().getTipo() +
                            ", Conductores: " + crudViaje.obtenerNombresConductores(viaje.getConductoresAsignados()) +
                            ", Ruta: " + viaje.getRutaAsignada().getLugarInicio() + " - " + viaje.getRutaAsignada().getLugarDestino());                   
                }
                System.out.println(tamañoListaViajes + 1 + ". Salir");
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
                
                int opcionAsiento, tamaañoListaAsientos = asientos.size();
                if (opcionReserva == 2) {
                    break;
                } else {
                    limpiarPantalla();
                    System.out.println("Lista de asientos disponibles: ");
                    do {
                        for (int i = 0; i < tamaañoListaAsientos; i++) {
                            System.out.println(i + 1 + ". " + asientos.get(i).getNumeroAsiento());
                        }
                        System.out.println(tamaañoListaAsientos + 1 + ". Salir"); 
                        System.out.println("Seleccione un asiento o salir: ");
                        opcionAsiento = entrada.nextInt();
                        entrada.nextLine();
                    } while (opcionAsiento < 1 || opcionAsiento > tamaañoListaAsientos + 1);

                    if (opcionAsiento == tamaañoListaAsientos + 1) {
                        break;
                    } else {
                        Asiento asientoSeleccionado = asientos.get(opcionAsiento - 1);
                        System.out.println("Asiento disponible.");
                        System.out.println("Ingrese su nombre completo: ");
                        String nombrePasajero = entrada.nextLine();
                        if (Asiento.reservarAsiento(asientos, asientoSeleccionado.getNumeroAsiento())) {
                            System.out.println("Asiento reservado exitosamente");
                            imprimirBoleto(fechaViaje, viajeSeleccionado.getRutaAsignada().getLugarInicio(), viajeSeleccionado.getRutaAsignada().getLugarDestino(), 
                                            viajeSeleccionado.getConductoresAsignados(), viajeSeleccionado.getBusAsignado().getTipo(), asientoSeleccionado.getNumeroAsiento(),
                                             nombrePasajero);
                        } else {
                            System.out.println("El asiento no está disponible o no existe");
                        }
                    }              
                }   
            } while (opcionViaje < 1 || opcionViaje > tamañoListaViajes + 1);       
        }  
        pausar(); 
    }

    public void imprimirBoleto(String fecha, String lugarSalida, String lugarDestino, List<Conductor> conductores, String tipoBus, 
                               String numeroAsiento, String nombreCliente) {
        String archivoNombre = "boleto_" + nombreCliente.replace(" ", "_") + ".txt"; // Crear nombre de archivo basado en el cliente
        String rutaArchivo = System.getProperty("user.dir") + "/" + archivoNombre;
        
        try (FileWriter writer = new FileWriter(archivoNombre)) {
            writer.write("----------------------------------------\n");
            writer.write("            BOLETO DE BUS\n");
            writer.write("----------------------------------------\n");
            writer.write("Fecha:          " + fecha + "\n");
            writer.write("----------------------------------------\n");
            writer.write("Lugar de salida:   " + lugarSalida + "\n");
            writer.write("Lugar de destino:  " + lugarDestino + "\n");
            writer.write("----------------------------------------\n");
            writer.write("Conductores asignados:\n");
            for (Conductor conductor : conductores) {
                writer.write("  - " + conductor.getNombre() + "\n");
            }
            writer.write("----------------------------------------\n");
            writer.write("Tipo de Bus:    " + tipoBus + "\n");
            writer.write("Número de asiento:  " + numeroAsiento + "\n");
            writer.write("----------------------------------------\n");
            writer.write("Nombre del cliente: " + nombreCliente + "\n");
            writer.write("----------------------------------------\n");
            writer.write("     ¡Gracias por elegir nuestra empresa!\n");
            writer.write("----------------------------------------\n");

            System.out.println("Boleto generado correctamente: " + archivoNombre);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al generar el boleto: " + e.getMessage());
        }
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