package Modelo;

import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Controlador.Conexion;
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
        Conexion conexionCrudRutas = new Conexion();
        Connection con = conexionCrudRutas.obtenerConexion();
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
    public static boolean agregarRuta(Ruta ruta) {
        PreparedStatement ps;
        Conexion conexionCrudRutas = new Conexion();
        Connection con = conexionCrudRutas.obtenerConexion();
        var sql = "INSERT INTO Ruta(LugarInicio,LugarDestino,duracionEstimada) VALUES(?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, ruta.getLugarInicio());
            ps.setString(2, ruta.getLugarDestino());
            ps.setInt(3, ruta.getDuracionEstimada());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al agregar ruta : " + e.getMessage());
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion: "+ e.getMessage());
            }
        }
        return false;
    }
    public static boolean modificarRuta(Ruta ruta) {
        PreparedStatement ps;
        Conexion conexionCrudRutas = new Conexion();
        Connection con = conexionCrudRutas.obtenerConexion();
        var sql = "UPDATE Ruta SET LugarInicio=?, LugarDestino=?, DuracionEstimada=? WHERE idRuta=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, ruta.getLugarInicio());
            ps.setString(2, ruta.getLugarDestino());
            ps.setInt(3, ruta.getDuracionEstimada());
            ps.setInt(4, ruta.getIdRuta());
            ps.execute();
            return true;
        }catch(Exception e){
            System.out.println("Error al modificar ruta: " + e.getMessage());
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion: "+ e.getMessage());
            }
        }
        return false;
    }
    public static boolean eliminarRuta(Ruta ruta) {
        PreparedStatement ps;
        Conexion conexionCrudRutas = new Conexion();
        Connection con = conexionCrudRutas.obtenerConexion();
        var sql = "DELETE FROM Ruta WHERE idRuta=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, ruta.getIdRuta());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println("Error al eliminar ruta: " + e.getMessage());
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println("Error al cerrar conexion: "+ e.getMessage());
            }
        }
        return false;  
    }
    public static boolean buscarRutaPorId(Ruta ruta) {
        PreparedStatement ps;
        ResultSet rs;
       Conexion conexionCrudRutas = new Conexion();
        Connection con = conexionCrudRutas.obtenerConexion();
        var sql = "SELECT * FROM Ruta WHERE idRuta = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, ruta.getIdRuta());
            rs = ps.executeQuery();
            if(rs.next()){
                ruta.setLugarInicio(rs.getString("LugarInicio"));
                ruta.setLugarDestino(rs.getString("LugarDestino"));
                ruta.setDuracionEstimada(rs.getInt("DuracionEstimada"));
                return true;
            }
        }catch(Exception e){
            System.out.println("Error al recuperar ruta por id: " + e.getMessage());
        }finally{
            try{
                con.close();
            }catch(Exception e){
                System.out.println("Error al cerrar conexion: "+ e.getMessage());
            }
        }
        return false;
    }
    
    //MENU RUTA
    public static void menuRuta() {
        //Conexion
        Conexion conexionCrudRutas = new Conexion();
        Connection conexion = conexionCrudRutas.obtenerConexion();
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
        System.out.println("1.- Agregar ruta");
        System.out.println("2.- Listar rutas");
        System.out.println("3.- Modificar ruta");
        System.out.println("4.- Eliminar ruta");
        System.out.println("5.- Salir");
        System.out.println("\nElige una opcion: ");
        return Integer.parseInt(consola.nextLine());
    }
    public static boolean ejecutarOpciones(int opcion, Scanner consola){
        boolean salir = false;
        switch(opcion){
            case 1->{
                System.out.println("\nLugar de inicio: ");
                var LugarInicio = consola.nextLine();
                System.out.println("\nLugar de destino: ");
                var LugarDestino = consola.nextLine();
                System.out.println("\nDuracion estimada: ");
                var duracionEstimada = Integer.parseInt(consola.nextLine());
                var newruta = new Ruta(LugarInicio, LugarDestino, duracionEstimada);
                var agregao = Ruta.agregarRuta(newruta);
                if(agregao){
                    System.out.println("Ruta nuevo agregado: " + newruta);
                }else{
                    System.out.println("No se puedo agregar la ruta: " + newruta);
                }
            }
            case 2->{
                var rutas = Ruta.listarRutas();
                rutas.forEach(System.out::println);
            }
            case 3->{
                System.out.println("ID de ruta a modificar: ");
                int id = Integer.parseInt(consola.nextLine());
                Ruta ruta = new Ruta(id);
                if(Ruta.buscarRutaPorId(ruta)){
                    System.out.println("Nuevo lugar de inicio ("+ ruta.getLugarInicio()+ "): ");
                    var lugarInicio = consola.nextLine();
                    System.out.println("Nuevo lugar de destino ("+ ruta.getLugarDestino()+ "): ");
                    var lugarDestino = consola.nextLine();
                    System.out.println("Nueva duracion estimada ("+ ruta.getDuracionEstimada()+ "): ");
                    var duracionEstimada = Integer.parseInt(consola.nextLine());
                    var rutamodificada = new Ruta(id, lugarInicio, lugarDestino, duracionEstimada);
                    if(Ruta.modificarRuta(rutamodificada)){
                        System.out.println("Ruta modificada: " + rutamodificada);
                    }else{
                        System.out.println("Error al modificar ruta: " + rutamodificada);
                    }
                }else{
                    System.out.println("Ruta inexistente.");
                }
            }
            case 4->{
                System.out.println("ID de ruta a eliminar: ");
                int id = Integer.parseInt(consola.nextLine());
                Ruta ruta = new Ruta(id);
                if(Ruta.buscarRutaPorId(ruta)){
                    System.out.println("Lugar de inicio: "+ ruta.getLugarInicio());
                    System.out.println("Lugar de destino: "+ ruta.getLugarDestino());
                    System.out.println("Duracion Estimada: "+ ruta.getDuracionEstimada());
                    System.out.println("Confirmar eliminacion?:(S/N)");
                    if( "S".equals(consola.nextLine()) || "s".equals(consola.nextLine()) ){
                        var rutaeliminada = new Ruta(id);
                        if(Ruta.eliminarRuta(rutaeliminada)){
                            System.out.println("Ruta Eliminada: " + rutaeliminada);
                        }else{
                            System.out.println("Error al eliminar ruta: " + rutaeliminada);
                        }
                    }
                }else{
                    System.out.println("ID de ruta inexistente.");
                }
            }
            case 5->salir = true;
        }
        return salir;
    }
    
}
