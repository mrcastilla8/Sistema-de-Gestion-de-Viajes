package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusCRUD {
    private final Conexion conexionDB;

    // Constructor
    public BusCRUD() {
        this.conexionDB = new Conexion(); // No cierra la conexión automáticamente
    }

    // Método para crear un nuevo bus en la base de datos
    public boolean crearBus(Bus bus) {
        String query = "INSERT INTO buses (tipo, capacidad, estado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexionDB.obtenerConexion().prepareStatement(query)) {
            stmt.setString(1, bus.getTipo());
            stmt.setInt(2, bus.getCapacidad());
            stmt.setString(3, bus.getEstado());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear el bus: " + e.getMessage());
            return false;
        }
    }

    // Método para eliminar un bus de la base de datos por ID
    public boolean eliminarBus(int id) {
        String query = "DELETE FROM buses WHERE id_bus = ?";
        try (PreparedStatement stmt = conexionDB.obtenerConexion().prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el bus: " + e.getMessage());
            return false;
        }
    }

    // Método para buscar buses por tipo y estado
    public List<Bus> buscarBus(String tipo, String estado) {
        String query = "SELECT * FROM buses";
        List<String> condiciones = new ArrayList<>();
        List<Bus> buses = new ArrayList<>();

    // Construir la consulta dinámicamente según los filtros disponibles
        if (!tipo.isEmpty()) {
            condiciones.add("tipo = ?");
        }
        if (!estado.isEmpty()) {
            condiciones.add("estado = ?");
        }
        if (!condiciones.isEmpty()) {
            query += " WHERE " + String.join(" AND ", condiciones);
        }

        try (PreparedStatement stmt = conexionDB.obtenerConexion().prepareStatement(query)) {
            int index = 1;
            if (!tipo.isEmpty()) {
                stmt.setString(index++, tipo);
            }
            if (!estado.isEmpty()) {
                stmt.setString(index, estado);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bus bus = new Bus(
                    rs.getInt("id_bus"),
                    rs.getString("tipo"),
                    rs.getInt("capacidad"),
                    rs.getString("estado")
                );
                buses.add(bus);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar buses: " + e.getMessage());
        }
        return buses;
    }

    // Método para modificar un bus en la base de datos
    public boolean modificarBus(Bus bus) {
        String query = "UPDATE buses SET tipo = ?, capacidad = ?, estado = ? WHERE id_bus = ?";
        try (PreparedStatement stmt = conexionDB.obtenerConexion().prepareStatement(query)) {
            stmt.setString(1, bus.getTipo());
            stmt.setInt(2, bus.getCapacidad());
            stmt.setString(3, bus.getEstado());
            stmt.setInt(4, bus.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al modificar el bus: " + e.getMessage());
            return false;
        }
    }
}
