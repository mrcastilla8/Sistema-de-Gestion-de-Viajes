package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Vista.CRUD_VIAJES;
import Controlador.Conexion;
import java.text.SimpleDateFormat;
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
   // Método para agregar un nuevo viaje
public void agregar() {
    String idBus = ventanaViajes.Entrada_Bus.getSelectedItem().toString();
    idBus = idBus.split(" - ")[0].trim();
    String rutaSeleccionada = ventanaViajes.Entrada_Ruta.getSelectedItem().toString();
    String idRuta = rutaSeleccionada.split(" - ")[0].trim(); // Extraer el idRuta
    String precio = ventanaViajes.Entrada_Precio.getText();
    String conductor1 = ventanaViajes.Entrada_Primer_Conductor.getSelectedItem().toString().split(" - ")[0].trim();
    String conductor2 = ventanaViajes.Entrada_Segundo_Conductor.getSelectedItem().toString().split(" - ")[0].trim();

    // Obtener las horas de salida y llegada de la tabla Ruta
    String horaSalida = "";
    String horaLlegada = "";

    String sqlRuta = "SELECT horaSalida, horaLlegada FROM Ruta WHERE idRuta = ?";
    try {
        conet = con.obtenerConexion();
        ps = conet.prepareStatement(sqlRuta);
        ps.setString(1, idRuta);
        rs = ps.executeQuery();
        if (rs.next()) {
            horaSalida = rs.getString("horaSalida");
            horaLlegada = rs.getString("horaLlegada");
        } else {
            JOptionPane.showMessageDialog(null, "Ruta no encontrada. Por favor verifica.");
            return; // Salir si no se encuentra la ruta
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener horas de la ruta: " + e.getMessage());
        return;
    }

    // Validar que los conductores no sean iguales
    if (conductor1.equals(conductor2)) {
        JOptionPane.showMessageDialog(null, "El conductor 1 y el conductor 2 no pueden ser la misma persona.");
        return;
    }

    // Obtener la fecha del JDateChooser
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fechaSalida = sdf.format(ventanaViajes.Entrada_Fecha.getDate());

    String sqlViaje = "INSERT INTO viajes (id_bus, id_ruta, precio, conductor_id_1, conductor_id_2, fecha_salida) VALUES (?, ?, ?, ?, ?, ?)";
    try {
        ps = conet.prepareStatement(sqlViaje);
        ps.setString(1, idBus);
        ps.setString(2, idRuta);
        ps.setString(3, precio);
        ps.setString(4, conductor1);
        ps.setString(5, conductor2);
        ps.setString(6, fechaSalida);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Viaje agregado con éxito");
        consultar(); // Actualizar la tabla
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
        consultar(); // Actualizar la tabla
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
    String idBus = ventanaViajes.Entrada_Bus.getSelectedItem().toString().split(" - ")[0].trim();
    String rutaSeleccionada = ventanaViajes.Entrada_Ruta.getSelectedItem().toString();
    String idRuta = rutaSeleccionada.split(" - ")[0].trim();
    String precio = ventanaViajes.Entrada_Precio.getText();
    String conductor1 = ventanaViajes.Entrada_Primer_Conductor.getSelectedItem().toString().split(" - ")[0].trim();
    String conductor2 = ventanaViajes.Entrada_Segundo_Conductor.getSelectedItem().toString().split(" - ")[0].trim();

    // Obtener las horas de salida y llegada de la tabla Ruta
    String horaSalida = "";
    String horaLlegada = "";

    String sqlRuta = "SELECT horaSalida, horaLlegada FROM Ruta WHERE idRuta = ?";
    try {
        conet = con.obtenerConexion();
        ps = conet.prepareStatement(sqlRuta);
        ps.setString(1, idRuta);
        rs = ps.executeQuery();
        if (rs.next()) {
            horaSalida = rs.getString("horaSalida");
            horaLlegada = rs.getString("horaLlegada");
        } else {
            JOptionPane.showMessageDialog(null, "Ruta no encontrada. Por favor verifica.");
            return;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener horas de la ruta: " + e.getMessage());
        return;
    }

    // Obtener la fecha del JDateChooser
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String fechaSalida = sdf.format(ventanaViajes.Entrada_Fecha.getDate());

    // Validar que los conductores no sean iguales
    if (conductor1.equals(conductor2)) {
        JOptionPane.showMessageDialog(null, "El conductor 1 y el conductor 2 no pueden ser la misma persona.");
        return;
    }

    String sqlViaje = "UPDATE viajes SET id_bus = ?, id_ruta = ?, precio = ?, conductor_id_1 = ?, conductor_id_2 = ?, fecha_salida = ? WHERE id_viaje = ?";
    try {
        ps = conet.prepareStatement(sqlViaje);
        ps.setString(1, idBus);
        ps.setString(2, idRuta);
        ps.setString(3, precio);
        ps.setString(4, conductor1);
        ps.setString(5, conductor2);
        ps.setString(6, fechaSalida);
        ps.setString(7, idViaje);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Viaje modificado con éxito");
        consultar(); // Actualizar la tabla
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al modificar viaje: " + e.getMessage());
    }
}


    // Método para consultar y mostrar los viajes en la tabla
public void consultar() {
    String sql = "SELECT v.id_viaje, v.id_bus, r.LugarInicio, r.LugarDestino, v.precio, "
                + "p1.nombre AS conductor1_nombre, p1.apellido AS conductor1_apellido, "
                + "p2.nombre AS conductor2_nombre, p2.apellido AS conductor2_apellido, "
                + "v.fecha_salida, r.horaSalida, r.horaLlegada "
                + "FROM viajes v "
                + "JOIN Ruta r ON v.id_ruta = r.idRuta "
                + "JOIN conductores c1 ON v.conductor_id_1 = c1.idConductor "
                + "JOIN persona p1 ON c1.idPersona = p1.idPersona "
                + "JOIN conductores c2 ON v.conductor_id_2 = c2.idConductor "
                + "JOIN persona p2 ON c2.idPersona = p2.idPersona";

    try {
        conet = con.obtenerConexion();
        ps = conet.prepareStatement(sql);
        rs = ps.executeQuery();
        DefaultTableModel modelo = (DefaultTableModel) ventanaViajes.Tabla_Viajes.getModel();
        modelo.setRowCount(0); // Limpiar la tabla

        while (rs.next()) {
            Object[] viaje = new Object[10];
            viaje[0] = rs.getString("id_viaje");
            viaje[1] = rs.getString("id_bus");
            viaje[2] = rs.getString("LugarInicio");
            viaje[3] = rs.getString("LugarDestino");
            viaje[4] = rs.getString("precio");
            viaje[5] = rs.getString("conductor1_nombre") + " " + rs.getString("conductor1_apellido");
            viaje[6] = rs.getString("conductor2_nombre") + " " + rs.getString("conductor2_apellido");
            viaje[7] = rs.getString("fecha_salida");
            viaje[8] = rs.getString("horaSalida");
            viaje[9] = rs.getString("horaLlegada");
            modelo.addRow(viaje);
        }

        ventanaViajes.Tabla_Viajes.setModel(modelo);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al consultar viajes: " + e.getMessage());
    }
}


    public void cargarIdsConductores() {
        String sql = "SELECT c.idConductor, p.nombre, p.apellido FROM conductores c JOIN persona p ON c.idPersona = p.idPersona";
        try {
            conet = con.obtenerConexion();
            ps = conet.prepareStatement(sql);
            rs = ps.executeQuery();

            // Limpiar los comboBox de conductores antes de cargar los datos
            ventanaViajes.Entrada_Primer_Conductor.removeAllItems();
            ventanaViajes.Entrada_Segundo_Conductor.removeAllItems();

            while (rs.next()) {
                int idConductor = rs.getInt("idConductor");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String conductorInfo = idConductor + " - " + nombre + " " + apellido;

                // Agregar el idConductor seguido del nombre y apellido
                ventanaViajes.Entrada_Primer_Conductor.addItem(conductorInfo);
                ventanaViajes.Entrada_Segundo_Conductor.addItem(conductorInfo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar conductores: " + e.getMessage());
        }
    }
    public void cargarIdsBuses() {
    String sql = "SELECT id_bus, tipo FROM buses";
    try {
        conet = con.obtenerConexion();
        ps = conet.prepareStatement(sql);
        rs = ps.executeQuery();
        ventanaViajes.Entrada_Bus.removeAllItems(); // Limpiar items actuales
        while (rs.next()) {
            ventanaViajes.Entrada_Bus.addItem(rs.getString("id_bus") + " - " + rs.getString("tipo"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar IDs de buses: " + e.getMessage());
    }
}

 public void cargarIdsRutas() {
    String sql = "SELECT idRuta, LugarInicio, LugarDestino, horaSalida, horaLlegada FROM Ruta";
    try {
        conet = con.obtenerConexion();
        ps = conet.prepareStatement(sql);
        rs = ps.executeQuery();
        ventanaViajes.Entrada_Ruta.removeAllItems(); // Limpiar el comboBox antes de cargar datos

        while (rs.next()) {
            int idRuta = rs.getInt("idRuta");
            String lugarInicio = rs.getString("LugarInicio");
            String lugarDestino = rs.getString("LugarDestino");
            String horaSalida = rs.getString("horaSalida");
            String horaLlegada = rs.getString("horaLlegada");

            // Agregar el idRuta, lugares, hora de salida y hora de llegada al comboBox
            ventanaViajes.Entrada_Ruta.addItem(
                idRuta + " - " + lugarInicio + " - " + lugarDestino + " - " + horaSalida + " - " + horaLlegada
            );
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar rutas: " + e.getMessage());
    }
}



    
}
