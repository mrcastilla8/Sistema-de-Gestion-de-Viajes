
package Clases;

public class Main {


    public static void main(String[] args) {
        Bus busesDisponibles[] = new Bus[4];
        busesDisponibles[0] = new Bus(1, "Premium", 40,"En Linea", "Aldana kbro");
        busesDisponibles[1] = new Bus(2, "Vip", 30,"En Linea", "Marco");
        busesDisponibles[2] = new Bus(3, "Premium", 40,"En Mantenimiento", "Axel");
        busesDisponibles[3] = new Bus(4, "Regular", 60,"En Linea", "Taco");
       
        Comprobante comprobante1 = new Comprobante();

        comprobante1.asignarBus(busesDisponibles);
        
        System.out.println("El ID del bus asignado en el comprobante es: " + comprobante1.getIdBus());
    }
    
}
