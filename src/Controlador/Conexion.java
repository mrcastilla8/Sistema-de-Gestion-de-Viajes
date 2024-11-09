package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class Conexion {
    private Connection con;

    public Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://junction.proxy.rlwy.net:57902/railway", "root", "NtSdYuSEvepsgTzNShRNuCMYXeHlnaIm");
            System.out.println("Conexión exitosa.");
        } catch(Exception e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        }
    }
    
    public <T> List<T> obtenerRegistros(String nombreTabla, Class<T> claseEntidad) {
        List<T> listaObjetos = new ArrayList<>();
        String sql = "SELECT * FROM " + nombreTabla;

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                T objeto = claseEntidad.getDeclaredConstructor().newInstance();
                ResultSetMetaData metaData = resultSet.getMetaData();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String nombreColumna = metaData.getColumnName(i);
                    Object valor = resultSet.getObject(i);

                    try {
                        var campo = claseEntidad.getDeclaredField(nombreColumna);
                        campo.setAccessible(true);
                        campo.set(objeto, valor);
                    } catch (NoSuchFieldException e) {
                        System.out.println("La clase " + claseEntidad.getSimpleName() + 
                                           " no tiene un campo para la columna: " + nombreColumna);
                    }
                }
                listaObjetos.add(objeto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaObjetos;
    }
    
    public void obtenerRutaPorID(int idRuta) {
        String sql = "SELECT * FROM Ruta WHERE idRuta = ?"; 
        
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
