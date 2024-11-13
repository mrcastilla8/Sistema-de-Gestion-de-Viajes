package Modelo;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
        // Inserta el nuevo bus en la tabla `buses` (inserta los asientos del bus creado en la tabla 'asientos')
        String queryBus = "INSERT INTO buses (tipo, capacidad, estado) VALUES (?, ?, ?)";
        String queryAsiento = "INSERT INTO asientos (numero_asiento, disponible, id_bus) VALUES (?, ?, ?)";

        try (PreparedStatement stmtBus = conexionDB.obtenerConexion().prepareStatement(queryBus, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement stmtAsiento = conexionDB.obtenerConexion().prepareStatement(queryAsiento)) {

            // Configura y ejecuta el insert del bus
            stmtBus.setString(1, bus.getTipo());
            stmtBus.setInt(2, bus.getCapacidad());
            stmtBus.setString(3, bus.getEstado());
            int rowsAffected = stmtBus.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Error al crear el bus, no se insertó ningún registro.");
            }

            // Obtener el id generado para el bus recién insertado
            try (ResultSet generatedKeys = stmtBus.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idBus = generatedKeys.getInt(1);

                    // Determina la cantidad de asientos según el tipo de bus
                    int cantidadAsientos;
                    switch (bus.getTipo()) {
                        case "Estándar":
                            cantidadAsientos = 60;
                            break;
                        case "Premium":
                            cantidadAsientos = 50;
                            break;
                        case "Vip":
                            cantidadAsientos = 40;
                            break;
                        default:
                            throw new IllegalArgumentException("Tipo de bus no reconocido");
                    }

                    // Inserta los registros de asientos en la tabla `asientos`
                    for (int i = 1; i <= cantidadAsientos; i++) {
                        if(i<=12){
                            stmtAsiento.setString(1, "A" + i);
                        }
                        else{
                            stmtAsiento.setString(1, "B" + i);
                        }
                        stmtAsiento.setInt(2, 1); // 1 = disponible, 0 = no disponible
                        stmtAsiento.setInt(3, idBus);
                        stmtAsiento.addBatch(); // Añade a batch para ejecutar en lote
                    }
                    stmtAsiento.executeBatch(); // Ejecuta todos los inserts de asientos en lote
                    return true; // Operación completada exitosamente
                } else {
                    throw new SQLException("Error al obtener el ID del bus insertado.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el bus y sus asientos: " + e.getMessage());
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
