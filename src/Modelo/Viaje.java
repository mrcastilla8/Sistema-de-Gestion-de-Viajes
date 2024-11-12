package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Vista.CRUD_VIAJES;
import Controlador.Conexion;
import javax.swing.table.DefaultTableModel;

public class Viaje {

    Conexion con = new Conexion();
    Connection conet;
    CRUD_VIAJES ventanaViajes;
    PreparedStatement ps;
    ResultSet rs;

    public Viaje(CRUD_VIAJES ventanaViajes) {
        this.ventanaViajes = ventanaViajes;
    }

    // Método para agregar un nuevo viaje
    public void agregar() {
        String idBus = ventanaViajes.Entrada_Bus.getText();
        String idRuta = ventanaViajes.Entrada_Ruta.getText();
        String precio = ventanaViajes.Entrada_Precio.getText();
        String conductor1 = ventanaViajes.Entrada_Conductor1.getText();
        String conductor2 = ventanaViajes.Entrada_Conductor2.getText();
        String fechaSalida = ventanaViajes.Entrada_HoraSalida.getText();

        String sql = "INSERT INTO viajes (id_bus, id_ruta, precio, conductor_id_1, conductor_id_2, fecha_salida) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conet = con.obtenerConexion();
            ps = conet.prepareStatement(sql);
            ps.setString(1, idBus);
            ps.setString(2, idRuta);
            ps.setString(3, precio);
            ps.setString(4, conductor1);
            ps.setString(5, conductor2);
            ps.setString(6, fechaSalida);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Viaje agregado con éxito");
            consultar(); // Actualizar la tabla en la interfaz
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar viaje: " + e.getMessage());
        }
    }

    // Método para eliminar un viaje
    public void eliminar() {
        int fila = ventanaViajes.Tabla_Viajes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un viaje para eliminar");
            return;
        }
        
        String idViaje = ventanaViajes.Tabla_Viajes.getValueAt(fila, 0).toString();
        String sql = "DELETE FROM viajes WHERE id_viaje = ?";
        
        try {
            conet = con.obtenerConexion();
            ps = conet.prepareStatement(sql);
            ps.setString(1, idViaje);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Viaje eliminado con éxito");
            consultar(); // Actualizar la tabla en la interfaz
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar viaje: " + e.getMessage());
        }
    }

    // Método para modificar un viaje
    public void modificar() {
        int fila = ventanaViajes.Tabla_Viajes.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un viaje para modificar");
            return;
        }

        String idViaje = ventanaViajes.Tabla_Viajes.getValueAt(fila, 0).toString();
        String idBus = ventanaViajes.Entrada_Bus.getText();
        String idRuta = ventanaViajes.Entrada_Ruta.getText();
        String precio = ventanaViajes.Entrada_Precio.getText();
        String conductor1 = ventanaViajes.Entrada_Conductor1.getText();
        String conductor2 = ventanaViajes.Entrada_Conductor2.getText();
        String fechaSalida = ventanaViajes.Entrada_HoraSalida.getText();

        String sql = "UPDATE viajes SET id_bus = ?, id_ruta = ?, precio = ?, conductor_id_1 = ?, conductor_id_2 = ?, fecha_salida = ? WHERE id_viaje = ?";

        try {
            conet = con.obtenerConexion();
            ps = conet.prepareStatement(sql);
            ps.setString(1, idBus);
            ps.setString(2, idRuta);
            ps.setString(3, precio);
            ps.setString(4, conductor1);
            ps.setString(5, conductor2);
            ps.setString(6, fechaSalida);
            ps.setString(7, idViaje);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Viaje modificado con éxito");
            consultar(); // Actualizar la tabla en la interfaz
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar viaje: " + e.getMessage());
        }
    }

    // Método para consultar y mostrar los viajes en la tabla
    public void consultar() {
        String sql = "SELECT * FROM viajes";
        try {
            conet = con.obtenerConexion();
            ps = conet.prepareStatement(sql);
            rs = ps.executeQuery();
            DefaultTableModel modelo = (DefaultTableModel) ventanaViajes.Tabla_Viajes.getModel();
            modelo.setRowCount(0); // Limpiar la tabla

            while (rs.next()) {
                Object[] viaje = new Object[7];
                viaje[0] = rs.getString("id_viaje");
                viaje[1] = rs.getString("id_bus");
                viaje[2] = rs.getString("id_ruta");
                viaje[3] = rs.getString("precio");
                viaje[4] = rs.getString("conductor_id_1");
                viaje[5] = rs.getString("conductor_id_2");
                viaje[6] = rs.getString("fecha_salida");
                modelo.addRow(viaje);
            }

            ventanaViajes.Tabla_Viajes.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar viajes: " + e.getMessage());
        }
    }
}
