
package Clases;

import java.util.List;
import java.util.Scanner;

public class Operador extends Persona {
    private String usuario;
    private String contrasena;
    private String cargo;

    //Default

    public Operador() {
    }

    // Constructor
    public Operador(String usuario, String contrasena, String cargo) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.cargo = cargo;
    }
    

    // Métodos de Operador
    public void iniciarSesion() {
        String user,password;
        Scanner entrada = new Scanner(System.in);
        do{
            
        System.out.println("------------------------------------");
        System.out.println("\t INICIO DE SESION ");
        System.out.println("------------------------------------");
        System.out.println("USUARIO: ");
        user = entrada.next();
        System.out.println("CONTRASEÑA: ");
        password = entrada.next();
        if(!user.equals(this.usuario)){
            System.out.println("Usuario incorrecto");
            if(!password.equals(this.contrasena)){
                System.out.println("Contraseña incorrecta");
            }
        }   
        }while(!password.equals(this.contrasena)||!user.equals(this.usuario));
    }




    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void getDatos() {
        System.out.println("Cargo(A:Administrador,O:Operador): "+this.cargo+"| Usuario: "+this.usuario+"| Contraseña: "+this.contrasena);
    }   
    public  void menuOperadoresCRUD(){
        System.out.println("-------------------------------------------------------");
        System.out.println("\t===Gestion de operadores===");
        System.out.println("-------------------------------------------------------");
        System.out.println("1. Registro de operadores");
        System.out.println("2. Lista de operadores");
        System.out.println("3. Eliminar operadores");
        System.out.println("4. Actualizar operadores");
        System.out.println("5. SALIR");
        System.out.println("Ingrese su opcion: ");
    }
    public void opcionesCRUD(List<Operador> lista,int opcion, Scanner entrada){
        switch(opcion){

            case 1:
                agregarOperadorCRUD(lista,opcion,entrada);
                break;
            case 2:
                buscarOperadorCRUD(lista,opcion,entrada);
                break;
            case 3:
                eliminarOperadorCRUD(lista,entrada);
                break;
            case 4:
                actualizarOperadorCRUD(lista,entrada);
                break;
    }
    
    }
    public void agregarOperadorCRUD(List<Operador> lista,int opcion, Scanner entrada){
        String usuario,contraseña,rol;
                Operador ope = new Operador();
                System.out.println("\n\tRegistro de operadores");
                System.out.println("Ingrese el usuario del operador: ");
                usuario = entrada.next();
                ope.setUsuario(usuario);
                System.out.println("Ingrese la contraseña del operador: ");
                contraseña = entrada.next();
                ope.setContrasena(contraseña);
                do{
                System.out.println("Ingrese el rol del operador(Admin:A,Operador:O): ");
                rol = entrada.next();
                if(!rol.equals("A") && !rol.equals("O")){
                    System.out.println("Error, introduzca el rol adecuado");
                }
                }while(!rol.equals("A") && !rol.equals("O"));
                ope.setCargo(rol);
                lista.add(ope);
    }
    public void buscarOperadorCRUD(List<Operador> lista,int opcion, Scanner entrada){ 
        System.out.println("\n\tBuscar operadores");
                for(Operador operador:lista){
                operador.getDatos();
                } 
    }    
    public void eliminarOperadorCRUD(List<Operador> lista, Scanner entrada) {
        System.out.println("\n\tEliminar operadores");
        System.out.println("Ingrese el usuario del operador a borrar: ");
        String usuario = entrada.next();  // Asegúrate de declarar el usuario localmente.
    
        boolean encontrado = false;
    
        // Usar un ciclo con índice para permitir la eliminación correcta
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getUsuario().equals(usuario)) {
                System.out.println("\n\tUsuario encontrado");
                lista.get(i).getDatos();
                System.out.println("El operador será eliminado");
                lista.remove(i);  // Elimina el operador en la posición correcta
                encontrado = true;
                break;  // Salimos del ciclo una vez que eliminamos el operador
            }
        }
    
        if (!encontrado) {
            System.out.println("Operador no encontrado.");
        }
    }
    
    public void actualizarOperadorCRUD(List<Operador> lista, Scanner entrada) {
        System.out.println("\n\tModificar operadores");
        System.out.println("Ingrese el usuario actual del operador a modificar: ");
        String usuario = entrada.next();  // Asegúrate de declarar la variable usuario localmente.
    
        boolean encontrado = false;
    
        // Usar un ciclo con índice para actualizar el operador correcto
        for (int i = 0; i < lista.size(); i++) {
            Operador opi = lista.get(i);
            if (usuario.equals(opi.getUsuario())) {
                System.out.println("Usuario encontrado:");
                opi.getDatos();  // Muestra los datos actuales del operador
    
                // Pedimos nuevos datos del operador
                System.out.println("Ingrese el nuevo usuario del operador: ");
                String nuevoUsuario = entrada.next();
                opi.setUsuario(nuevoUsuario);
    
                System.out.println("Ingrese la nueva contraseña del operador: ");
                String nuevaContrasena = entrada.next();
                opi.setContrasena(nuevaContrasena);
    
                String rol;
                do {
                    System.out.println("Ingrese el nuevo rol del operador (Admin:A, Operador:O): ");
                    rol = entrada.next();
                    if (!rol.equals("A") && !rol.equals("O")) {
                        System.out.println("Error, introduzca el rol adecuado");
                    }
                } while (!rol.equals("A") && !rol.equals("O"));
                opi.setCargo(rol);
    
                System.out.println("Operador actualizado exitosamente.");
                encontrado = true;
                break;  // Salimos del ciclo después de actualizar el operador
            }
        }
    
        if (!encontrado) {
            System.out.println("Operador no encontrado.");
        }
    }
}