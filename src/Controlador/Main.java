package Controlador;

import java.util.Scanner;
import java.util.List;
import Modelo.*;
import Vista.CRUD_OPERADORES;
import Vista.CRUD_Buses;
import Vista.IguConductor;
import Vista.IguRuta;
public class Main {

    public static void main(String[] args) {
        
        //CRUDD ventanacrud = new CRUDD();
        //ventanacrud.setVisible(true);
        
        CRUD_OPERADORES ventanaOperadores = new CRUD_OPERADORES();
        CRUD_Buses ventanaBuses = new CRUD_Buses();
        IguConductor ventanaConductores = new IguConductor();
        Conductor conductor = new Conductor(ventanaConductores);
        IguRuta ventanaRuta = new IguRuta();
        Scanner entrada = new Scanner(System.in);
        Operador op1 = new Operador(ventanaOperadores);
        Archivos archivo = new Archivos();
        
        int opcionMenu = 0;
        do {
            limpiarPantalla();
            // Menu de opciones
            System.out.println("---------------------------------------------------------");
            System.out.println("\t===BIENVENIDO AL SISTEMA DE TRANSPORTE===");
            System.out.println("---------------------------------------------------------");
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Gestionar Conductores");
            System.out.println("2. Gestionar Buses");
            System.out.println("3. Gestionar Rutas");
            System.out.println("4. Gestionar Operadores");
            System.out.println("5. Gestionar Viajes");
            System.out.println("6. Venta de boletos");
            System.out.println("7. Salir");
            System.out.print("Ingrese su opcion: ");
            opcionMenu = entrada.nextInt();
            switch(opcionMenu) {
                case 1:
                    ventanaConductores.setVisible(true);

                    break;
                case 2:
                    // Gestionar Buses
                    ventanaBuses.setVisible(true);
                    break;
                case 3:
                    // Gestionar Rutas
                    ventanaRuta.setVisible(true);
                    break;
                case 4:
                    // Gestionar Operadores
                    ventanaOperadores.setVisible(true);
                    break;
                case 5:
                    // Gestionar Viajes
                    ViajeCRUD viajeCRUD = new ViajeCRUD();
                  //viajeCRUD.ejecutarMenu();
                    break;
                case 6:
                    // Venta de boletos
                    VentaBoleto ventaBoleto = new VentaBoleto();
                  //ventaBoleto.menuVentaBoleto();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcionMenu != 7);
        
    }

    public static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al intentar limpiar la consola.");
        }
    }
}
