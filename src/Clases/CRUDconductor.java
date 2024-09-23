package Clases;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class CRUDconductor {
    Scanner entrada = new Scanner(System.in);
    int ID=0; //Iniciamos un contador de IDs .
    
        public void addChofer(List<Conductor> Conductores){
        System.out.println("Agregar nuevo conductor:");
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Edad: ");
        int edad = entrada.nextInt();
        
        //Limpieza buffer
        entrada.nextLine();
        
        System.out.print("DNI: ");
        String DNI = entrada.nextLine();
        System.out.print("Numero de telefono: ");
        String numero = entrada.nextLine();
        System.out.print("Numero de licencia: ");
        String num_licencia = entrada.nextLine();
        
        ID++;

        System.out.println("ID asignada: " + ID);


        Conductores.add(new Conductor(ID, num_licencia, DNI, nombre, numero, edad));
        
        System.out.println("\nNuevo conductor registrado!");
        entrada.nextLine();
        }   
        
       public void verificarChofer(List<Conductor> Conductores){

        System.out.print("Ingrese la ID del chofer a buscar: ");
        int ID = entrada.nextInt();


        boolean flag = true;
        //Recorremos toda la lista buscando lo que se pide
        for(int i=0; i<Conductores.size(); i++){
            

            if(Conductores.get(i).getIdConductor() == ID){


                System.out.println("\nConductor encontrado!");
                System.out.println("Nombre: " + Conductores.get(i).getNombre());
                System.out.println("Edad: " + Conductores.get(i).getEdad());
                System.out.println("DNI: " + Conductores.get(i).getDni());
                System.out.println("Numero telefonico: " + Conductores.get(i).getNumero());
                System.out.println("Numero de licencia: " + Conductores.get(i).getNumLicencia());
                System.out.println("ID: " + Conductores.get(i).getIdConductor());
                entrada.nextLine();
                flag = false;
                break;
            }

        }
        if(flag){
            System.out.println("Conductor no encontrado...");
            entrada.nextLine();
        }
        }
        
        public void modificarInfoChofer(List<Conductor> Conductores) {
        System.out.println("\nModificar o actualizar datos del conductor:");
        //Mostremos una lista de los conductores para modificarlos:
        System.out.print("\n");
        for(int i=0; i<Conductores.size(); i++){
            System.out.println((i+1) + ". " + Conductores.get(i).getNombre());
        }
        System.out.print("Conductor a modificar (indice): ");
        int opc = entrada.nextInt();
        
            //Limpieza buffer
            entrada.nextLine();    
            
            System.out.print("Nombre: ");
            String nombre = entrada.nextLine();
            Conductores.get(opc-1).setNombre(nombre);
            System.out.print("Edad: ");
            int edad = entrada.nextInt();
            
            //Limpieza buffer
            entrada.nextLine();
            
            Conductores.get(opc-1).setEdad(edad);
            System.out.print("Numero de telefono: ");
            String numero = entrada.nextLine();
            Conductores.get(opc-1).setNumero(numero);
            System.out.print("DNI: ");
            String DNI = entrada.nextLine();
            Conductores.get(opc-1).setDni(DNI);
            System.out.print("Numero de licencia: ");
            String numLicencia = entrada.nextLine();
            Conductores.get(opc -1).setNumLicencia(numLicencia);   
            
            System.out.print("\nModificacion exitosa!");
            entrada.nextLine();
        }
            

        public void eliminarChofer(List<Conductor> Conductores) {
        System.out.print("ID del conductor a eliminar: ");
        int ID = entrada.nextInt();
        boolean flag = true;
        for (int i = 0; i < Conductores.size(); i++) {
            if (ID == Conductores.get(i).getIdConductor()) {
            System.out.print("Conductor encontrado, seguro que desea eliminarlo? Si(s) No(n): ");
            entrada.nextLine(); // Limpieza del buffer tras el uso de nextInt()
            String opc = entrada.nextLine(); // Lee la opción del usuario
            
            if (opc.equalsIgnoreCase("s")) {
                Conductores.remove(i);
                System.out.println("\nConductor eliminado con exito!");
                flag = false;
                break;
            } else {
                System.out.println("Eliminación cancelada.");
                flag = false;
                break;
            }
            }
        }
    
        if (flag) {
            System.out.println("Conductor no encontrado...");
        }

        }
        
        public void menuCrudConductor(List<Conductor> Conductores, CRUDconductor crudConductor){
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
           
           //Limpieza de buffer
           entrada.nextLine();
           
           switch(opc_chofer){
               case 1: {
                   crudConductor.addChofer(Conductores);
                   break;
               }
               case 2: {

                   crudConductor.verificarChofer(Conductores);   
                   entrada.nextLine();

                   break;
               }
               case 3:{
                   crudConductor.modificarInfoChofer(Conductores);

                   entrada.nextLine();
                   entrada.nextLine();

                   break;
               }
               case 4: {
                   crudConductor.eliminarChofer(Conductores);

                   entrada.nextLine();


               }
               case 0: break;
           }
        }while(opc_chofer != 0);
        
        
    }
        
        
        
}
