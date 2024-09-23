package Clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CRUDruta {
    private List<Ruta> rutas;
    private Scanner scanner;

    public CRUDruta() {
        rutas = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Crear una nueva ruta
    public void addRuta() {
        System.out.println("=== Agregar Nueva Ruta ===");
        System.out.print("Ingrese ID de la Ruta: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Verificar si el ID ya existe
        for (Ruta ruta : rutas) {
            if (ruta.getIdRuta() == id) {
                System.out.println("Error: Ya existe una ruta con este ID.");
                return;
            }
        }

        System.out.print("Ingrese Lugar de Inicio: ");
        String inicio = scanner.nextLine();
        System.out.print("Ingrese Lugar de Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Ingrese Duracion Estimada (en horas): ");
        int duracion = Integer.parseInt(scanner.nextLine());

        Ruta nuevaRuta = new Ruta(id, inicio, destino, duracion);
        rutas.add(nuevaRuta);
        System.out.println("Ruta agregada exitosamente.");
    }

    // Leer y mostrar todas las rutas
    public void verRutas() {
        System.out.println("=== Lista de Rutas ===");
        if (rutas.isEmpty()) {
            System.out.println("No hay rutas disponibles.");
            return;
        }
        System.out.println(" ID     Inicio      Destino     Duracion");
        for (Ruta ruta : rutas) {
            System.out.println( ruta.getIdRuta() + " - " + ruta.getLugarInicio() + " - " + ruta.getLugarDestino() + " - "+ ruta.getDuracionEstimada()+ " hora(s)." );
        }
    }

    // Actualizar una ruta existente
    public void modificarRuta() {
        System.out.println("=== Modificar Ruta ===");
        System.out.print("Ingrese el ID de la Ruta a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Ruta rutaEncontrada = null;
        for (Ruta ruta : rutas) {
            if (ruta.getIdRuta() == id) {
                rutaEncontrada = ruta;
                break;
            }
        }

        if (rutaEncontrada == null) {
            System.out.println("Ruta no encontrada.");
            return;
        }

        System.out.println("Ingrese los nuevos datos (dejar en blanco para mantener el valor actual):");

        System.out.print("Nuevo Lugar de Inicio (" + rutaEncontrada.getLugarInicio() + "): ");
        String inicio = scanner.nextLine();
        if (!inicio.trim().isEmpty()) {
            rutaEncontrada.setLugarInicio(inicio);
        }

        System.out.print("Nuevo Lugar de Destino (" + rutaEncontrada.getLugarDestino() + "): ");
        String destino = scanner.nextLine();
        if (!destino.trim().isEmpty()) {
            rutaEncontrada.setLugarDestino(destino);
        }

        System.out.print("Nueva Duracion Estimada (" + rutaEncontrada.getDuracionEstimada() + " horas): ");
        String duracionInput = scanner.nextLine();
        if (!duracionInput.trim().isEmpty()) {
            int duracion = Integer.parseInt(duracionInput);
            rutaEncontrada.setDuracionEstimada(duracion);
        }

        System.out.println("Ruta actualizada exitosamente.");
    }

    // Eliminar una ruta
    public void borrarRuta() {
        System.out.println("=== Borrar Ruta ===");
        System.out.print("Ingrese el ID de la Ruta a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Iterator<Ruta> iterator = rutas.iterator();
        boolean encontrado = false;
        while (iterator.hasNext()) {
            Ruta ruta = iterator.next();
            if (ruta.getIdRuta() == id) {
                iterator.remove();
                encontrado = true;
                System.out.println("Ruta eliminada exitosamente.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Ruta no encontrada.");
        }
    }

    // Menú de opciones
    public void mostrarMenu() {
        int opcion = 0;
        do {
            System.out.println("\n=== Menu de Gestion de Rutas ===");
            System.out.println("1. Agregar Ruta");
            System.out.println("2. Ver Rutas");
            System.out.println("3. Modificar Ruta");
            System.out.println("4. Borrar Ruta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

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
                    verRutas();
                    break;
                case 3:
                    modificarRuta();
                    break;
                case 4:
                    borrarRuta();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, intente de nuevo.");
            }
        } while (opcion != 5);
    }
}
