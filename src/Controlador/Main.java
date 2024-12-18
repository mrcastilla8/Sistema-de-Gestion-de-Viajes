package Controlador;

import java.util.Scanner;
import java.util.List;
import Modelo.*;
import Vista.CRUD_OPERADORES;
import Vista.CRUD_Buses;
import Vista.IguConductor;
import Vista.MainMenu;
import Vista.IguRuta;
import Vista.Inicio_Sesión;
public class Main {

    public static void main(String[] args) {
        
      
        MainMenu menu = new MainMenu();
        Inicio_Sesión login = new Inicio_Sesión(menu);
        CRUD_OPERADORES ventanaOperadores = new CRUD_OPERADORES(menu);
        CRUD_Buses ventanaBuses = new  CRUD_Buses(menu);
        IguConductor ventanaConductores = new IguConductor(menu);
        Conductor conductor = new Conductor(ventanaConductores);
        IguRuta ventanaRuta = new IguRuta(menu);
        Scanner entrada = new Scanner(System.in);
        Operador op1 = new Operador(ventanaOperadores);
        
        login.setVisible(true);
        

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


