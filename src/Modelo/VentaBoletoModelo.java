package Modelo;

import Controlador.Conexion;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

import java.sql.SQLException;

public class VentaBoletoModelo {
    private Conexion conexionDB;
    
    public VentaBoletoModelo() {
        this.conexionDB = new Conexion();
    }

    public List<String> obtenerOrigenes() {
        String query = "SELECT DISTINCT LugarInicio FROM Ruta;";
        try (PreparedStatement statm = conexionDB.obtenerConexion().prepareStatement(query)) {
            try (ResultSet rs = statm.executeQuery()) {
                ArrayList<String> origenes = new ArrayList<>();
                origenes.add("-");
                while (rs.next()) {
                    origenes.add(rs.getString("LugarInicio"));
                }
                return origenes;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los origenes: " + e.getMessage());
            return null;
        }
    }

    public List<String> obtenerDestinos() {
        String query = "SELECT DISTINCT LugarDestino FROM Ruta;";
        try (PreparedStatement statm = conexionDB.obtenerConexion().prepareStatement(query)) {
            try (ResultSet rs = statm.executeQuery()) {
                ArrayList<String> destinos = new ArrayList<>();
                destinos.add("-");
                while (rs.next()) {
                    destinos.add(rs.getString("LugarDestino"));
                }
                return destinos;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los destinos: " + e.getMessage());
            return null;
        }
    }

    public List<Object[]> obtenerViajesCoincidentes(String origen, String destino, String fecha) {
        String query = "SELECT v.id_viaje, b.tipo, r.LugarInicio, r.LugarDestino, v.precio, " +
               "p1.nombre AS conductor1_nombre, p1.apellido AS conductor1_apellido, " +
               "p2.nombre AS conductor2_nombre, p2.apellido AS conductor2_apellido, " +
               "v.fecha_salida " +
               "FROM viajes v " +
               "JOIN buses b ON v.id_bus = b.id_bus " +
               "JOIN Ruta r ON v.id_ruta = r.idRuta " +
               "JOIN conductores c1 ON v.conductor_id_1 = c1.idConductor " +
               "JOIN persona p1 ON c1.idPersona = p1.idPersona " +
               "JOIN conductores c2 ON v.conductor_id_2 = c2.idConductor " +
               "JOIN persona p2 ON c2.idPersona = p2.idPersona";
        List<String> condiciones = new ArrayList<>();
        List<Object[]> viajes = new ArrayList<>();

        if (!origen.equals("-")) {
            condiciones.add("r.LugarInicio = ?");
        }
        if (!destino.equals("-")) {
            condiciones.add("r.LugarDestino = ?");
        }
        if (!fecha.isEmpty()) {
            condiciones.add("v.fecha_salida = ?");
        }
        if (!condiciones.isEmpty()) {
            query += " WHERE " + String.join(" AND ", condiciones);
        }

        try (PreparedStatement statm = conexionDB.obtenerConexion().prepareStatement(query)) {
            int index = 1;
            if (!origen.equals("-")) {
                statm.setString(index++, origen);
            }
            if (!destino.equals("-")) {
                statm.setString(index++, destino);
            }
            if (!fecha.isEmpty()) {
                statm.setString(index, fecha);
            }

            ResultSet rs = statm.executeQuery();
            while (rs.next()) {
                Object[] viaje = new Object[8];
                viaje[0] = rs.getString("id_viaje");
                viaje[1] = rs.getString("tipo");
                viaje[2] = rs.getString("LugarInicio");
                viaje[3] = rs.getString("LugarDestino");
                viaje[4] = rs.getString("precio");
                viaje[5] = rs.getString("conductor1_nombre") + " " + rs.getString("conductor1_apellido");
                viaje[6] = rs.getString("conductor2_nombre") + " " + rs.getString("conductor2_apellido");
                viaje[7] = rs.getString("fecha_salida");
                viajes.add(viaje);
            }
            return viajes;

        } catch (SQLException e) {
            System.err.println("Error al buscar viajes: " + e.getMessage());
            return null;
        }
    }
}