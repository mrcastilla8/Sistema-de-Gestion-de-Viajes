
package Clases;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Archivos {
    // Guarda una lista de objetos en un archivo
    public static <T> void guardarObjetos(String archivo, List<T> objetos) {
        try (FileOutputStream fileOut = new FileOutputStream(archivo);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(objetos);  // Guarda la LISTA completa en el archivo
            System.out.println("Objetos guardados exitosamente en " + archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Lee tu txt para recuperar los objetos, esta funcion te RETORNA la lista con los objetos leidos, la idea es que la iguales a tu lista.
    public static <T> List<T> leerObjetos(String archivo) {
        List<T> objetos = new ArrayList<>();
        try (FileInputStream fileIn = new FileInputStream(archivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            objetos = (List<T>) in.readObject();  // Lee la LISTA completa del archivo
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objetos;
    }
}

