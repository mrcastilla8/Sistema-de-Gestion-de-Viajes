
package Clases;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {

        //Gestionar Buses
        BusCRUD busCRUD = new BusCRUD();
        busCRUD.ejecutarMenu();
      
        //Gestionar Conductores
        List<Conductor> Conductores = new ArrayList<Conductor> ();
        CRUDconductor crudConductor = new CRUDconductor();
        crudConductor.menuCrudConductor(Conductores, crudConductor);
        
        //Gestionar Rutas
        CRUDruta rutaManager = new CRUDruta();
        rutaManager.mostrarMenu();
       
    }
    
}
