
package Clases;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Archivos {
    // Guarda una lista de objetos en un archivo
    public static <T> void guardarObjetos(String archivo, List<T> objetos) {
        try (FileOutputStream fileOut = new FileOutputStream(archivo);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(objetos);  // Guarda la lista completa en el archivo
            System.out.println("Objetos guardados exitosamente en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> leerObjetos(String archivo) {
        List<T> objetos = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(archivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            objetos = (List<T>) in.readObject();  // Lee la lista completa del archivo
            System.out.println("Objetos leídos exitosamente de " + archivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objetos;
    }
}

// Ejemplo de uso
// Supongamos que tienes una clase llamada "Persona" que implementa Serializable
/*class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad;
    }
}

class Main {
    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Juan", 25));
        personas.add(new Persona("María", 30));

        // Guardar la lista de personas en un archivo
        Archivos.guardarObjetos("personas.ser", personas);

        // Leer la lista de personas desde el archivo
        List<Persona> personasLeidas = Archivos.leerObjetos("personas.ser");
        for (Persona p : personasLeidas) {
            System.out.println(p);
        }
    }
}/*
