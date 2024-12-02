package Modelo;

import Controlador.Conexion;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

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
               "v.fecha_salida, r.horaSalida " +
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
                Object[] viaje = new Object[9];
                viaje[0] = rs.getString("id_viaje");
                viaje[1] = rs.getString("tipo");
                viaje[2] = rs.getString("LugarInicio");
                viaje[3] = rs.getString("LugarDestino");
                viaje[4] = rs.getString("precio");
                viaje[5] = rs.getString("conductor1_nombre") + " " + rs.getString("conductor1_apellido");
                viaje[6] = rs.getString("conductor2_nombre") + " " + rs.getString("conductor2_apellido");
                viaje[7] = rs.getString("fecha_salida");
                viaje[8] = rs.getString("horaSalida");
                viajes.add(viaje);
            }
            return viajes;

        } catch (SQLException e) {
            System.err.println("Error al buscar viajes: " + e.getMessage());
            return null;
        }
    }

    public Map<String, Boolean> obtenerEstadosAsientos(int idViaje) {
        String query = "SELECT numero_asiento, disponible FROM asientos " +
                       "WHERE id_bus = (SELECT id_bus FROM viajes WHERE id_viaje = ?);";
        Map<String, Boolean> estadosAsientos = new HashMap<>();

        try (PreparedStatement statm = conexionDB.obtenerConexion().prepareStatement(query)) {
            statm.setInt(1, idViaje);
            try (ResultSet rs = statm.executeQuery()) {
                while (rs.next()) {
                    String numeroAsiento = rs.getString("numero_asiento");
                    boolean disponible = rs.getBoolean("disponible");
                    estadosAsientos.put(numeroAsiento, disponible);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los estados de los asientos: " + e.getMessage());
        }

        return estadosAsientos;
    }

    public String obtenerTipoDeBus(int idViaje) {
        String query = "SELECT tipo FROM buses WHERE id_bus = (SELECT id_bus FROM viajes WHERE id_viaje = ?);";
        try (PreparedStatement statm = conexionDB.obtenerConexion().prepareStatement(query)) {
            statm.setInt(1, idViaje);
            try (ResultSet rs = statm.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tipo");
                }
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el tipo de bus: " + e.getMessage());
            return null;
        }
    }

    public void actualizarAsiento(int idViaje, String numAsiento) {
        String query = "UPDATE asientos SET disponible = 0 WHERE id_bus = (SELECT id_bus FROM viajes WHERE id_viaje = ?) AND numero_asiento = ?;";
        try (PreparedStatement statm = conexionDB.obtenerConexion().prepareStatement(query)) {
            statm.setInt(1, idViaje);
            statm.setString(2, numAsiento);
            statm.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el asiento: " + e.getMessage());
        }
    }

    public void imprimirBoleto(int idViaje, String numAsiento, String nombres, String apellidos, String dni) {
        String numeroAsiento = numAsiento;
        String nombrePasajero = nombres;
        String apellidoPasajero = apellidos;
        String dniPasajero = dni;
        String tipoBus = obtenerTipoDeBus(idViaje);
        String fechaSalida = "";
        String horaSalida = "";
        String lugarInicio = "";
        String lugarDestino = "";
        String precio = "";
        String conductor1 = "";
        String conductor2 = "";

        String query = "SELECT v.id_viaje, v.fecha_salida, r.horaSalida, r.LugarInicio, r.LugarDestino, " +
                "p1.nombre AS conductor1_nombre, p1.apellido AS conductor1_apellido, " +
                "p2.nombre AS conductor2_nombre, p2.apellido AS conductor2_apellido, " +
                "a.numero_asiento, v.precio " +
                "FROM viajes v " +
                "JOIN Ruta r ON v.id_ruta = r.idRuta " +
                "JOIN conductores c1 ON v.conductor_id_1 = c1.idConductor " +
                "JOIN persona p1 ON c1.idPersona = p1.idPersona " +
                "JOIN conductores c2 ON v.conductor_id_2 = c2.idConductor " +
                "JOIN persona p2 ON c2.idPersona = p2.idPersona " +
                "JOIN asientos a ON v.id_bus = a.id_bus " +
                "WHERE v.id_viaje = ? AND a.numero_asiento = ?;";
    
        try (PreparedStatement statm = conexionDB.obtenerConexion().prepareStatement(query)) {
            int index = 1;
            statm.setInt(index++, idViaje);
            statm.setString(index, numAsiento);

            ResultSet rs = statm.executeQuery();
            while (rs.next()) {
                fechaSalida = rs.getString("fecha_salida");
                horaSalida = rs.getString("horaSalida");
                lugarInicio = rs.getString("LugarInicio");
                lugarDestino = rs.getString("LugarDestino");
                precio = rs.getString("precio");
                conductor1 = rs.getString("conductor1_nombre") + " " + rs.getString("conductor1_apellido");
                conductor2 = rs.getString("conductor2_nombre") + " " + rs.getString("conductor2_apellido");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los datos del boleto: " + e.getMessage());
        }

        try {
            String ruta = "./boletos/";
            File directorio = new File(ruta);
            if (!directorio.exists()) {
                directorio.mkdirs();
            }
            String nombreArchivo = ruta + "boleto_" + dniPasajero + "_" + fechaSalida + "_" + horaSalida.replace(":", "") + "_asiento" + numeroAsiento + ".pdf";
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            // Agregar contenido al PDF
            documento.add(new Paragraph("--------------------------------------------------"));
            documento.add(new Paragraph("            BOLETO DE BUS              ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            documento.add(new Paragraph("--------------------------------------------------"));
            documento.add(new Paragraph("Fecha:          " + fechaSalida));
            documento.add(new Paragraph("Hora:           " + horaSalida));
            documento.add(new Paragraph("--------------------------------------------------"));
            documento.add(new Paragraph("Lugar de salida:   " + lugarInicio));
            documento.add(new Paragraph("Lugar de destino:  " + lugarDestino));
            documento.add(new Paragraph("--------------------------------------------------"));
            documento.add(new Paragraph("Conductores asignados:"));
            documento.add(new Paragraph("  - " + conductor1));
            documento.add(new Paragraph("  - " + conductor2));
            documento.add(new Paragraph("--------------------------------------------------"));
            documento.add(new Paragraph("Tipo de Bus:    " + tipoBus));
            documento.add(new Paragraph("Número de asiento:  " + numeroAsiento));
            documento.add(new Paragraph("--------------------------------------------------"));
            documento.add(new Paragraph("Nombres del cliente: " + nombrePasajero));
            documento.add(new Paragraph("Apellidos del cliente: " + apellidoPasajero));
            documento.add(new Paragraph("DNI del cliente: " + dniPasajero));
            documento.add(new Paragraph("--------------------------------------------------"));
            documento.add(new Paragraph("Precio: " + precio));
            documento.add(new Paragraph("----------------------------------------"));
            documento.add(new Paragraph("     ¡Gracias por elegir nuestra empresa!", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
            documento.add(new Paragraph("--------------------------------------------------"));

            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    public void crearPasajero(int idViaje, String numAsiento, String nombres, String apellidos, String dni) {

    }
}