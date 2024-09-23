
package Clases;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        //Lista de conductores (dinamico)
        List<Conductor> Conductores = new ArrayList<Conductor> ();
        CRUDconductor crudConductor = new CRUDconductor();
        
        //menu basico para el CRUD de conductores:
        Scanner entrada = new Scanner(System.in);
        int opc_chofer = 0;
        do{
           System.out.println("\nCRUD de choferes");
           System.out.println("1. Agregar chofer");
           System.out.println("2. Verificar");
           System.out.println("3. Eliminar chofer");
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
                   crudConductor.eliminarChofer(Conductores);
                   break;
               }
               case 0: break;
           }
        }while(opc_chofer != 0);
        
        
    }
    
}
