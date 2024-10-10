package Clases;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
    
        Operador op1 = new Operador("david.aldana","1234","A");
        op1.iniciarSesion();
        
        int opcionMenu = 0;
        do {
            limpiarPantalla();
            //Menu de opciones:
            System.out.println("---------------------------------------------------------");
            System.out.println("\t===BIENVENIDO AL SISTEMA DE TRANSPORTE===");
            System.out.println("---------------------------------------------------------");
            System.out.println("Seleccione una opcion:");
            System.out.println("1. Gestionar Conductores");
            System.out.println("2. Gestionar Buses");
            System.out.println("3. Gestionar Rutas");
            System.out.println("4. Gestionar Operadores");
            System.out.println("5. Salir");
            System.out.println("Ingrese su opcion: ");
            opcionMenu = entrada.nextInt();
            switch(opcionMenu){
                case 1:
                    //Lista de conductores (dinamico)
                    List<Conductor> Conductores = new ArrayList<Conductor> ();
                    //Creamos nuestro objeto de la clase CRUDconductor
                    CRUDconductor crudConductor = new CRUDconductor();
                    //llamamos a la funcion que muestra el menu del CRUD:
                    crudConductor.menuCrudConductor(Conductores, crudConductor);
                    break;
                case 2:
                    //Gestionar Buses
                    BusCRUD busCRUD = new BusCRUD();
                    busCRUD.ejecutarMenu();
                    break;
                case 3:
                    //Gestionar Rutas
                    CRUDruta rutaManager = new CRUDruta();
                    rutaManager.mostrarMenu();
                    break;
                case 4:
                    List<Operador> lista = new ArrayList<Operador> ();
                    lista.add(op1);
                    int opcion;
                    do{
                        op1.menuOperadoresCRUD();
                        opcion = entrada.nextInt();
                        op1.opcionesCRUD(lista,opcion,entrada);
                    }while(opcion!=5);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (opcionMenu != 5);  
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
