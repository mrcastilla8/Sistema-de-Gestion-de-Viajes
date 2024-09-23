package Clases;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class CRUDconductor {
    Scanner entrada = new Scanner(System.in);
    int ID=0; //Iniciamos un contador de IDs
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
        Conductores.add(new Conductor(ID, num_licencia, DNI, nombre, numero, edad));
        
        System.out.println("\nNuevo conductor registrado!");
        entrada.nextLine();
        }   
        
       public void verificarChofer(List<Conductor> Conductores){
        System.out.print("Ingrese el nombre del chofer a buscar: ");
        String nombre = entrada.nextLine();
        boolean flag = true;
        //Recorremos toda la lista buscando lo que se pide
        for(int i=0; i<Conductores.size(); i++){
            
            if(Conductores.get(i).getNombre().equals(nombre)){
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
            
        public void eliminarChofer(List<Conductor> Conductores){
        System.out.print("Nombre del conductor a eliminar: ");
        String nombre = entrada.nextLine();
        boolean flag = true;
        for(int i=0; i<Conductores.size(); i++){
            if(nombre.equals(Conductores.get(i).getNombre())){
                System.out.print("Conductor encontrado, seguro que desea eliminarlo? Si(s) No(n): ");
                String opc = entrada.nextLine();
                if(opc.equals("s")){
                    Conductores.remove(i);
                    System.out.println("\nConductor eliminado con exito!");
                    flag = false;
                    break;
                }                         
    
            }
        }
        if(flag){
             System.out.println("Conductor no encontrado..."); 
        }
        
        }
        
        
}
