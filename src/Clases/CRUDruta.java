package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CRUDruta {
    private List<Ruta> rutas;
    private Scanner scanner;
    int ID=1;
    private final String archivoRutas = "rutas.txt"; // Archivo donde se guardarán las rutas

    public CRUDruta() {
        // Inicializamos la lista de rutas cargando desde el archivo
        rutas = Archivos.leerObjetos(archivoRutas);
        scanner = new Scanner(System.in);
    }

    // Crear una nueva ruta
    public void addRuta() {
        
        System.out.println("=== Agregar Nueva Ruta ===");
        
        if(rutas.size() != 0){
            ID = rutas.get(rutas.size()-1).getIdRuta() + 1;
        }
        
        System.out.print("Ingrese Lugar de Inicio: ");
        String inicio = scanner.nextLine();
        System.out.print("Ingrese Lugar de Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Ingrese Duración Estimada (en horas): ");
        int duracion = Integer.parseInt(scanner.nextLine());

        Ruta nuevaRuta = new Ruta(ID, inicio, destino, duracion);
        rutas.add(nuevaRuta);

        // Guardar la lista actualizada de rutas en el archivo
        Archivos.guardarObjetos(archivoRutas, rutas);

        System.out.println("Ruta agregada exitosamente.");
        scanner.nextLine();
    }

    // Leer y mostrar todas las rutas
    public void verRutas() {
        System.out.println("\n=== Lista de Rutas ===");
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas disponibles.");
            return;
        }
        System.out.println(String.format("%-5s %-10s %-10s %-5s", "ID", "Inicio", "Destino", "Horas"));
        for (Ruta ruta : rutas) {
            System.out.println(String.format("%-5d %-10s %-10s %-5d",
            ruta.getIdRuta(),
            ruta.getLugarInicio(),
            ruta.getLugarDestino(),
            ruta.getDuracionEstimada()));
        }
    }

    // Modificar una ruta existente
    public void modificarRuta() {
        System.out.println("=== Modificar Ruta ===");
        System.out.print("Ingrese ID de la Ruta a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Ruta ruta : rutas) {
            if (ruta.getIdRuta() == id) {
                System.out.print("Ingrese nuevo Lugar de Inicio: ");
                String inicio = scanner.nextLine();
                System.out.print("Ingrese nuevo Lugar de Destino: ");
                String destino = scanner.nextLine();
                System.out.print("Ingrese nueva Duración Estimada (en horas): ");
                int duracion = Integer.parseInt(scanner.nextLine());

                ruta.setLugarInicio(inicio);
                ruta.setLugarDestino(destino);
                ruta.setDuracionEstimada(duracion);

                // Guardar la lista actualizada de rutas en el archivo
                Archivos.guardarObjetos(archivoRutas, rutas);

                System.out.println("Ruta modificada exitosamente.");
                scanner.nextLine();
                return;
            }
        }
        System.out.println("Error: No se encontró ninguna ruta con ese ID.");
        scanner.nextLine();
    }

    // Eliminar una ruta
    public void eliminarRuta() {
        System.out.println("=== Eliminar Ruta ===");
        System.out.print("Ingrese ID de la Ruta a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Ruta ruta : rutas) {
            if (ruta.getIdRuta() == id) {
                rutas.remove(ruta);

                // Guardar la lista actualizada de rutas en el archivo
                Archivos.guardarObjetos(archivoRutas, rutas);

                System.out.println("Ruta eliminada exitosamente.");
                scanner.nextLine();
                return;
            }
        }

        System.out.println("Error: No se encontró ninguna ruta con ese ID.");
        scanner.nextLine();
    }

    public int obtenerIdRutaPorSalidayDestino(String salida, String destino){
        for (Ruta ruta : rutas) {
            if (ruta.getLugarInicio().equalsIgnoreCase(salida) && ruta.getLugarDestino().equalsIgnoreCase(destino)) {
                return ruta.getIdRuta();
            }
        }
        return -1;
    }
    
    public void mostrarMenu() {
        int opcion = 0;
        do {
            limpiarPantalla();
            System.out.println("---------------------------------------------------");
            System.out.println("\t=== Menu de Gestion de Rutas ===");
            System.out.println("---------------------------------------------------");
            verRutas();
            System.out.println("\n=== Opciones ===");
            System.out.println("1. Agregar Ruta");
            System.out.println("2. Modificar Ruta");
            System.out.println("3. Borrar Ruta");
            System.out.println("4. Salir");

            System.out.print("\nSeleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    addRuta();
                    break;
                case 2:
                    modificarRuta();
                    break;
                case 3:
                    eliminarRuta();
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, intente de nuevo.");
            }
        } while (opcion != 4);
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
