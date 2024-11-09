package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    Connection conexion;
    public Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conexion = DriverManager.getConnection("jdbc:mysql://junction.proxy.rlwy.net:57902/railway", "root", "NtSdYuSEvepsgTzNShRNuCMYXeHlnaIm");
        } catch(Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }
    
    public Connection obtenerConexion(){
        return conexion;
    }

    public void obtenerRutaPorID(int idRuta) {
        String sql = "SELECT * FROM Ruta WHERE idRuta = ?"; 
        Connection con = obtenerConexion();
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, idRuta); 
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
            
                    int id = resultSet.getInt("idRuta");
                    String lugarInicio = resultSet.getString("LugarInicio");
                    String lugarDestino = resultSet.getString("LugarDestino");
                    int duracionEstimada = resultSet.getInt("DuracionEstimada");


                    System.out.println("ID: " + id + ", Lugar de Inicio: " + lugarInicio + 
                                       ", Lugar de Destino: " + lugarDestino + 
                                       ", Duración Estimada: " + duracionEstimada + " horas");
                } else {
                    System.out.println("Ruta con idRuta " + idRuta + " no encontrada.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        Connection con = obtenerConexion();
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
