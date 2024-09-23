
package Clases;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
    
        Operador op1 = new Operador("david.aldana","1234","A");
        op1.iniciarSesion();
        //Lista de conductores (dinamico)
        List<Conductor> Conductores = new ArrayList<Conductor> ();
        //Creamos nuestro objeto de la clase CRUDconductor
        CRUDconductor crudConductor = new CRUDconductor();
        //llamamos a la funcion que muestra el menu del CRUD:

        //Gestionar Buses
        BusCRUD busCRUD = new BusCRUD();
        busCRUD.ejecutarMenu();
      
        crudConductor.menuCrudConductor(Conductores, crudConductor);
        
        //Gestionar Rutas
        CRUDruta rutaManager = new CRUDruta();
        rutaManager.mostrarMenu();
        
        List<Operador> lista = new ArrayList<Operador> ();
        lista.add(op1);
        int opcion;

        do{
            op1.menuOperadoresCRUD();
            opcion = entrada.nextInt();
            op1.opcionesCRUD(lista,opcion,entrada);
        }while(opcion!=0);
       
    }
    
}
