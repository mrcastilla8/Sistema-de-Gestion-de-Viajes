
package Clases;
import java.util.Scanner;

public class Comprobante {
    private int idComprobante;
    private int idRuta;
    private int idBus;
    private String fechaEmision;
    private double precio;
    private String agencia;
    //Constructor 

    Scanner entrada = new Scanner(System.in);

    public Comprobante() {
    }
    //Constructor con valores

    public Comprobante(int id_comprobante, int idRuta, int idBus, String fechaEmision, double precio, String agencia) {
        this.idComprobante = id_comprobante;
        this.idRuta = idRuta;
        this.idBus = idBus;
        this.fechaEmision = fechaEmision;
        this.precio = precio;
        this.agencia = agencia;
    }
    
    public void asignarBus(Bus busesDisponibles[]) {
        Scanner entrada = new Scanner(System.in);  // Inicializamos el Scanner para leer la entrada del usuario
        System.out.println("Lista de buses disponibles:");

        // Mostramos todos los buses
        for (int i = 0; i < busesDisponibles.length; i++) {
            System.out.println((i + 1) + ". ");
            busesDisponibles[i].mostrarBus();  // Mostrar información del bus
        }

        // Solicitamos al usuario seleccionar un bus
        System.out.println("Seleccione el número del bus que desea asignar:");
        int seleccion = entrada.nextInt();  // Leemos la opción seleccionada

        // Verificamos que la selección sea válida
        if (seleccion > 0 && seleccion <= busesDisponibles.length) {
            Bus busSeleccionado = busesDisponibles[seleccion - 1];  // Obtenemos el bus seleccionado
            this.idBus = busSeleccionado.getIdBus();  // Asignamos el idBus al comprobante
            System.out.println("El bus con ID " + this.idBus + " ha sido asignado correctamente.");
        } else {
            System.out.println("Selección no válida. Por favor, intente de nuevo.");
        }
    }

    
    //Getters and setters

    
    public int getId_comprobante() 
    {
        return idComprobante;
    }

    public void setId_comprobante(int id_comprobante) {
        this.idComprobante = id_comprobante;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    } 
    
}
