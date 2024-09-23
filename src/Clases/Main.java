
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
        
        //menu basico para el CRUD de conductores:
        Scanner entrada = new Scanner(System.in);
        int opc_chofer = 0;
        do{
           System.out.println("\nCRUD de choferes");
           System.out.println("1. Agregar chofer");
           System.out.println("2. Verificar chofer");
           System.out.println("3. Modificar chofer");        
           System.out.println("4. Eliminar chofer");
           System.out.println("0. Salir");
           System.out.print("\nOpcion ---> ");
           opc_chofer = entrada.nextInt();
           
           switch(opc_chofer){
               case 1: {
                   crudConductor.addChofer(Conductores);
                   break;
               }
               case 2: {
                   crudConductor.verificarChofer(Conductores);          
                   break;
               }
               case 3:{
                   crudConductor.modificarInfoChofer(Conductores);
                   break;
               }
               case 4: {
                   crudConductor.eliminarChofer(Conductores);
               }
               case 0: break;
           }
        }while(opc_chofer != 0);
        
        
    }
    
}
