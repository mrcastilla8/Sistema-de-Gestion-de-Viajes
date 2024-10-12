package Clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Operador extends Persona {
    private String usuario;
    private String contrasena;
    private String cargo;
    private static final String ARCHIVO_OPERADORES = "operadores.txt";

    //Default
    public Operador() {
    }

    // Constructor
    public Operador(String usuario, String contrasena, String cargo) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.cargo = cargo;
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

    // Métodos de Operador
    public void iniciarSesion() {
        String user, password;
        Scanner entrada = new Scanner(System.in);
        List<Operador> operadores = leerOperadores();
        boolean inicioExitoso = false;
        do {
            System.out.println("------------------------------------");
            System.out.println("\t INICIO DE SESION ");
            System.out.println("------------------------------------");
            System.out.println("USUARIO: ");
            user = entrada.next();
            System.out.println("CONTRASEÑA: ");
            password = entrada.next();

            for (Operador operador : operadores) {
                if (user.equals(operador.usuario) && password.equals(operador.contrasena)) {
                    System.out.println("Inicio de sesión exitoso.");
                    inicioExitoso = true;
                    break;
                }
            }

            if (!inicioExitoso) {
                System.out.println("Usuario o contraseña incorrectos. Intente nuevamente.");
            }
        } while (!inicioExitoso);
    }

    // Guardar operadores en archivo
    public static void guardarOperadores(List<Operador> operadores) {
        Archivos.guardarObjetos(ARCHIVO_OPERADORES, operadores);
    }

    // Leer operadores desde archivo
    public static List<Operador> leerOperadores() {
        return Archivos.leerObjetos(ARCHIVO_OPERADORES);
    }

    // Crear un nuevo operador y agregarlo a la lista
    public static void crearOperador(String usuario, String contrasena, String cargo) {
        List<Operador> operadores = leerOperadores();
        operadores.add(new Operador(usuario, contrasena, cargo));
        guardarOperadores(operadores);
    }

    // Obtener todos los operadores
    public static void mostrarOperadores() {
        List<Operador> operadores = leerOperadores();
        for (Operador operador : operadores) {
            System.out.println("Usuario: " + operador.usuario + ", Cargo: " + operador.cargo);
        }
    }

    // Menú de CRUD de Operadores
    public static void menuOperadoresCRUD() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("------------------------------------");
            System.out.println("\t MENU DE CRUD DE OPERADORES ");
            System.out.println("------------------------------------");
            System.out.println("1. Crear Operador");
            System.out.println("2. Mostrar Operadores");
            System.out.println("3. Editar Operador");
            System.out.println("4. Eliminar Operador");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese usuario: ");
                    String usuario = entrada.next();
                    System.out.print("Ingrese contraseña: ");
                    String contrasena = entrada.next();
                    System.out.print("Ingrese cargo: ");
                    String cargo = entrada.next();
                    crearOperador(usuario, contrasena, cargo);
                    break;
                case 2:
                    mostrarOperadores();
                    break;
                case 3:
                    System.out.print("Ingrese el usuario del operador a editar: ");
                    String usuarioEditar = entrada.next();
                    editarOperador(usuarioEditar, entrada);
                    break;
                case 4:
                    System.out.print("Ingrese el usuario del operador a eliminar: ");
                    String usuarioEliminar = entrada.next();
                    eliminarOperador(usuarioEliminar);
                    break;
                case 5:
                    System.out.println("Saliendo del menú de CRUD de operadores...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 5);
    }

    // Editar un operador existente
    public static void editarOperador(String usuario, Scanner entrada) {
        List<Operador> operadores = leerOperadores();
        for (Operador operador : operadores) {
            if (operador.getUsuario().equals(usuario)) {
                System.out.print("Ingrese la nueva contraseña: ");
                operador.setContrasena(entrada.next());
                System.out.print("Ingrese el nuevo cargo: ");
                operador.setCargo(entrada.next());
                guardarOperadores(operadores);
                System.out.println("Operador editado correctamente.");
                return;
            }
        }
        System.out.println("Operador no encontrado.");
    }

    // Eliminar un operador existente
    public static void eliminarOperador(String usuario) {
        List<Operador> operadores = leerOperadores();
        Operador operadorAEliminar = null;
        for (Operador operador : operadores) {
            if (operador.getUsuario().equals(usuario)) {
                operadorAEliminar = operador;
                break;
            }
        }
        if (operadorAEliminar != null) {
            operadores.remove(operadorAEliminar);
            guardarOperadores(operadores);
            System.out.println("Operador eliminado correctamente.");
        } else {
            System.out.println("Operador no encontrado.");
        }
    }

    // Opciones CRUD para operadores
    public static void opcionesCRUD(List<Operador> lista, int opcion, Scanner entrada) {
        switch (opcion) {
            case 1:
                System.out.print("Ingrese usuario: ");
                String usuario = entrada.next();
                System.out.print("Ingrese contraseña: ");
                String contrasena = entrada.next();
                System.out.print("Ingrese cargo: ");
                String cargo = entrada.next();
                crearOperador(usuario, contrasena, cargo);
                break;
            case 2:
                mostrarOperadores();
                break;
            case 3:
                System.out.print("Ingrese el usuario del operador a editar: ");
                String usuarioEditar = entrada.next();
                editarOperador(usuarioEditar, entrada);
                break;
            case 4:
                System.out.print("Ingrese el usuario del operador a eliminar: ");
                String usuarioEliminar = entrada.next();
                eliminarOperador(usuarioEliminar);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    // Menú de Operador
    public static void menuOperador() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("------------------------------------");
            System.out.println("\t MENU DE OPERADOR ");
            System.out.println("------------------------------------");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Crear Operador");
            System.out.println("3. Mostrar Operadores");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    Operador operador = new Operador();
                    operador.iniciarSesion();
                    break;
                case 2:
                    System.out.print("Ingrese usuario: ");
                    String usuario = entrada.next();
                    System.out.print("Ingrese contraseña: ");
                    String contrasena = entrada.next();
                    System.out.print("Ingrese cargo: ");
                    String cargo = entrada.next();
                    crearOperador(usuario, contrasena, cargo);
                    break;
                case 3:
                    mostrarOperadores();
                    break;
                case 4:
                    System.out.println("Saliendo del menú de operador...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 4);
    }
}