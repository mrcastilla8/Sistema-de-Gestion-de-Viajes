package Modelo;

import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Controlador.Conexion.getConexion;
import java.util.Scanner;

public class Ruta {
    private int idRuta;
    private String lugarInicio;
    private String lugarDestino;
    private int duracionEstimada;

    // Constructor

    public Ruta() {
    }

    public Ruta(int idRuta) {
        this.idRuta = idRuta;
    }

    public Ruta(String lugarInicio, String lugarDestino, int duracionEstimada) {
        this.lugarInicio = lugarInicio;
        this.lugarDestino = lugarDestino;
        this.duracionEstimada = duracionEstimada;
    }

    public Ruta(int idRuta, String lugarInicio, String lugarDestino, int duracionEstimada) {
        this(lugarInicio, lugarDestino, duracionEstimada);
        this.idRuta = idRuta;
    }
    
    
    // Getters y Setters
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getLugarInicio() {
        return lugarInicio;
    }

    public void setLugarInicio(String lugarInicio) {
        this.lugarInicio = lugarInicio;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(int duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }
    //hashcode equals and toString

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idRuta;
        hash = 53 * hash + Objects.hashCode(this.lugarInicio);
        hash = 53 * hash + Objects.hashCode(this.lugarDestino);
        hash = 53 * hash + this.duracionEstimada;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ruta other = (Ruta) obj;
        if (this.idRuta != other.idRuta) {
            return false;
        }
        if (this.duracionEstimada != other.duracionEstimada) {
            return false;
        }
        if (!Objects.equals(this.lugarInicio, other.lugarInicio)) {
            return false;
        }
        return Objects.equals(this.lugarDestino, other.lugarDestino);
    }

    @Override
    public String toString() {
        return "Ruta{" + "idRuta=" + idRuta + ", lugarInicio=" + lugarInicio + ", lugarDestino=" + lugarDestino + ", duracionEstimada=" + duracionEstimada + '}';
    }
    
    //Operaciones CRUD
    public static List<Ruta> listarRutas() {
        List<Ruta> rutas = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM Ruta ORDER BY idRuta";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var ruta = new Ruta();
                ruta.setIdRuta(rs.getInt("idRuta"));
                ruta.setLugarInicio(rs.getString("LugarInicio"));
                ruta.setLugarDestino(rs.getString("LugarDestino"));
                ruta.setDuracionEstimada(rs.getInt("DuracionEstimada"));
                rutas.add(ruta);
            }
        }catch(SQLException e){
            System.out.println("Error al listar las rutas: "+ e.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar conexion: " + e.getMessage());
            }
        }    
        return rutas;
    }
    public static void menuRuta() {
        //Conexion
        Connection conexion = getConexion();
        if(conexion != null){
            System.out.println("Conexion exitosa: " + conexion);
        }else{
            System.out.println("Error al conectarse");
        }
        //codigo mochado DAO
        //consola
        var consola = new Scanner(System.in);
        var salir = false;
        while(!salir){
            try{
                var opcion = menu(consola);
                salir = ejecutarOpciones(opcion, consola);
            }catch(Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }finally{
                System.out.println("");
            }
        }
    }
    public static int menu(Scanner consola){
        System.out.println("\t Menu de opciones");
        System.out.println("1.- Agregar cliente");
        System.out.println("2.- Listar clientes");
        System.out.println("3.- Modificar cliente");
        System.out.println("4.- Eliminar cliente");
        System.out.println("5.- Safar");
        System.out.println("\nElige una opcion: ");
        return Integer.parseInt(consola.nextLine());
    }
    public static boolean ejecutarOpciones(int opcion, Scanner consola){
        boolean salir = false;
        switch(opcion){
            case 2->{
                var rutas = Ruta.listarRutas();
                rutas.forEach(System.out::println);
            }
            case 5->salir = true;
        }
        return salir;
    }
    
}
