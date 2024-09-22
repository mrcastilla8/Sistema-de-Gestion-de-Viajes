
package Clases;

public class Main {

    public static void main(String[] args) {
        Bus busesDisponibles[] = new Bus[20];
        busesDisponibles[0] = new Bus(1, "Premium", 40,"En Linea", "Aldana kbro");
        busesDisponibles[0] = new Bus(2, "Vip", 30,"En Linea", "Marco");
        busesDisponibles[0] = new Bus(3, "Premium", 40,"En Mantenimiento", "Axel");
        busesDisponibles[0] = new Bus(4, "Regular", 60,"En Linea", "Taco");
        
        System.out.println(busesDisponibles[0]);
        
    }
}
