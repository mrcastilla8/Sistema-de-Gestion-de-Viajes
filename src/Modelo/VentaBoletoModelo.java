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
        String sql = "SELECT DISTINCT LugarDestino FROM Ruta;";
        try (PreparedStatement statm = conexionDB.obtenerConexion().prepareStatement(sql)) {
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

    public List<Viaje> obtenerViajesCoincidentes(String origen, String destino, String fecha) {
        String query = "SELECT * FROM viajes";
        List<String> condiciones = new ArrayList<>();
        List<Viaje> viajes = new ArrayList<>();

        if (!origen.equals("-")) {
            condiciones.add("origen = ?");
        }
        if (!destino.equals("-")) {
            condiciones.add("destino = ?");
        }
        if (!fecha.isEmpty()) {
            condiciones.add("fecha = ?");
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
                Viaje viaje = new Viaje(
                    rs.getInt("id_viaje"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getInt("id_ruta"),
                    rs.getInt("id_bus")
                );
                viajes.add(viaje);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar viajes: " + e.getMessage());
        }

    }
}