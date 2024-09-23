
package Clases;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        //Lista de conductores (dinamico)
        List<Conductor> Conductores = new ArrayList<Conductor> ();
        //Creamos nuestro objeto de la clase CRUDconductor
        CRUDconductor crudConductor = new CRUDconductor();
        //llamamos a la funcion que muestra el menu del CRUD:
        crudConductor.menuCrudConductor(Conductores, crudConductor);
        
    }
    
}
