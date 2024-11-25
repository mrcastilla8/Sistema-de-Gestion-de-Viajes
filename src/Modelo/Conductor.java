package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Controlador.Conexion;
import Vista.IguConductor;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

public class Conductor{
     
    Conexion con = new Conexion();
    Connection conet;
    DefaultTableModel modelo;
    Statement st;
    ResultSet rs;
    private IguConductor ventanaConductores;
    
    public Conductor(IguConductor ventanaConductores){
        this.ventanaConductores = ventanaConductores;
    }
    
    public void consultar(){
        String sql = "SELECT c.idConductor, p.nombre, p.apellido, p.edad, p.DNI, p.telefono, c.numLicencia " +
             "FROM persona p " +
             "JOIN conductores c ON p.idPersona = c.idPersona";

        try {
            conet = con.obtenerConexion();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            Object[] conductores = new Object[7];
            modelo = (DefaultTableModel) ventanaConductores.TablaConductoresRegulares.getModel();
            while (rs.next()) {
                conductores[0] = rs.getInt("idConductor");
                conductores[1] = rs.getString("nombre");
                conductores[2] = rs.getString("apellido");
                conductores[3] = rs.getInt("edad");
                conductores[4] = rs.getString("telefono");
                conductores[5] = rs.getString("DNI");
                conductores[6] = rs.getString("numLicencia");
                modelo.addRow(conductores);

            }
            ventanaConductores.TablaConductoresRegulares.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void consultarCesados() {
        String sql = "SELECT idConductor, numLicencia, DNI, fecha_baja, razon_baja FROM conductores_cesados";

        try {
            conet = con.obtenerConexion();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            Object[] cesados = new Object[5];
            DefaultTableModel modeloCesados = (DefaultTableModel) ventanaConductores.TablaConductoresCesados.getModel();

            // Limpia la tabla antes de cargar nuevos datos
            modeloCesados.setRowCount(0);

            while (rs.next()) {
                cesados[0] = rs.getInt("idConductor");
                cesados[1] = rs.getString("numLicencia");
                cesados[2] = rs.getString("DNI");
                cesados[3] = rs.getDate("fecha_baja");
                cesados[4] = rs.getString("razon_baja");
                modeloCesados.addRow(cesados);
            }
            ventanaConductores.TablaConductoresCesados.setModel(modeloCesados);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregar() {
        String nombre = ventanaConductores.txtfNombre.getText();
        String apellido = ventanaConductores.txtfApellido.getText();
        String edadStr = ventanaConductores.txtfEdad.getText();
        String telefono = ventanaConductores.txtfTelefono.getText();
        String DNI = ventanaConductores.txtfDNI.getText();
        String licencia = ventanaConductores.txtfLicencia.getText();

        try {
            // Revisamos si algún txtfield está vacío
            if (nombre.equals("") || apellido.equals("") || edadStr.equals("") 
                    || telefono.equals("") || DNI.equals("") || licencia.equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan ingresar datos!");
            } else if (DNI.length() != 8) { 
                // Verificamos si el DNI tiene 8 caracteres
                JOptionPane.showMessageDialog(null, "DNI incorrecto, ingresar 8 dígitos");
            } else {
                // Primero, verificar si existen duplicados
                if (determinarDuplicados()) {
                    limpiarTabla();
                    return;
                }

                // Convertir edad a int después de verificar que no está vacío
                int edad = Integer.parseInt(edadStr);

                // Agregamos a la persona a la tabla persona
                String sql1 = "INSERT INTO persona(nombre, apellido, edad, DNI, telefono) " +
                              "VALUES ('" + nombre + "', '" + apellido + "', '" + edad + "', '" + DNI + "', '" + telefono + "')";
                conet = con.obtenerConexion();
                st = conet.createStatement();
                st.executeUpdate(sql1, Statement.RETURN_GENERATED_KEYS);

                // Luego obtenemos el idPersona y ahora agregamos al conductor a la tabla conductores
                rs = st.getGeneratedKeys();
                int idPersona = -1;
                if (rs.next()) {
                    idPersona = rs.getInt(1); // Aquí obtienes el idPersona generado
                }
                String sql2 = "INSERT INTO conductores(idPersona, numLicencia) " +
                              "VALUES ('" + idPersona + "', '" + licencia + "')";
                st.executeUpdate(sql2);
                JOptionPane.showMessageDialog(null, "Nuevo conductor agregado!");
                nuevo();
            }

            limpiarTabla();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar conductor.");    
        }
    }
    
    public void modificar(){
        // Recopilar los datos de los campos de texto
        String nombre = ventanaConductores.txtfNombre.getText();
        String apellido = ventanaConductores.txtfApellido.getText();
        String edadStr = ventanaConductores.txtfEdad.getText();
        String telefono = ventanaConductores.txtfTelefono.getText();
        String DNI = ventanaConductores.txtfDNI.getText();
        String licencia = ventanaConductores.txtfLicencia.getText();

        try {
            // Revisar si algún campo está vacío
            if(nombre.equals("") || apellido.equals("") || edadStr.equals("") 
                    || telefono.equals("") || DNI.equals("") || licencia.equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan ingresar datos!");
                limpiarTabla();
            }
            else {
                
                // Convertir edad a int después de verificar que no está vacío
                int edad = Integer.parseInt(edadStr);
                
                // Obtener el idConductor desde el txtField 
                int idConductor = Integer.parseInt(ventanaConductores.txtfIdConductor.getText());

                // Usar el idConductor para obtener el idPersona correspondiente
                int idPersona = obtenerIdPersonaDesdeIdConductor(idConductor);

                // Verificar que se haya encontrado el idPersona
                if (idPersona == -1) {
                    JOptionPane.showMessageDialog(null, "No se encontró el ID de la persona asociado.");
                    return; // Salir si no se encontró
                }

                //Nos conectamos a la base de datos
                conet = con.obtenerConexion();
                st = conet.createStatement();

                // Actualizar los datos en la tabla `persona`
                String sql1 = "UPDATE persona SET nombre = '" + nombre + "', apellido = '" + apellido + "', edad = '" + edad + "', telefono = '" + telefono + "', DNI = '" + DNI + "' WHERE idPersona = " + idPersona;
                st.executeUpdate(sql1);

                // Actualizar los datos en la tabla `conductores`
                String sql2 = "UPDATE conductores SET numLicencia = '" + licencia + "' WHERE idPersona = " + idPersona;
                st.executeUpdate(sql2);

                JOptionPane.showMessageDialog(null, "Datos del conductor actualizados!");
                limpiarTabla();  // Refrescar la tabla
            }
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos del conductor.");
            limpiarTabla();
        }        
    }
    
    public void eliminar() {
        int fila = ventanaConductores.TablaConductoresRegulares.getSelectedRow();
        try {
            if (fila < 0) {
                JOptionPane.showMessageDialog(null, "Conductor no seleccionado");
                limpiarTabla();
            } else {
                // Obtén el ID del conductor seleccionado
                int idConductor = Integer.parseInt(ventanaConductores.TablaConductoresRegulares.getValueAt(fila, 0).toString());

                // Consulta para obtener información del conductor a través de la clave foránea
                String sqlDatos = "SELECT c.numLicencia, p.DNI " +
                                  "FROM conductores c " +
                                  "JOIN persona p ON c.idPersona = p.idPersona " +
                                  "WHERE c.idConductor = " + idConductor;

                conet = con.obtenerConexion();
                st = conet.createStatement();
                rs = st.executeQuery(sqlDatos);

                String numLicencia = null;
                String dni = null;

                if (rs.next()) {
                    numLicencia = rs.getString("numLicencia");
                    dni = rs.getString("DNI");
                }

                // Solicitar razón de baja al usuario
                String razonBaja = JOptionPane.showInputDialog("Ingrese la razón de baja:");
                if (razonBaja == null || razonBaja.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Razón de baja requerida");
                    
                    return;
                }

                // Insertar datos en la tabla conductores_cesados
                String sqlInsert = "INSERT INTO conductores_cesados (idConductor, numLicencia, DNI, fecha_baja, razon_baja) " +
                                   "VALUES (" + idConductor + ", '" + numLicencia + "', '" + dni + "', CURDATE(), '" + razonBaja + "')";
                st.executeUpdate(sqlInsert);

                // Eliminar de la tabla conductores
                String sqlDelete = "DELETE FROM conductores WHERE idConductor = " + idConductor;
                st.executeUpdate(sqlDelete);

                JOptionPane.showMessageDialog(null, "Conductor dado de baja exitosamente!");

                limpiarTabla();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al dar de baja al conductor: " + e.getMessage());
            limpiarTabla();
        }
    }

    public void nuevo(){
        ventanaConductores.txtfNombre.setText("");
        ventanaConductores.txtfApellido.setText("");
        ventanaConductores.txtfEdad.setText("");
        ventanaConductores.txtfDNI.setText("");
        ventanaConductores.txtfIdConductor.setText("");
        ventanaConductores.txtfLicencia.setText("");
        ventanaConductores.txtfTelefono.setText("");
    }
    
    public boolean determinarDuplicados() {
        // Obtener los datos ingresados desde la interfaz
        String dni = ventanaConductores.txtfDNI.getText();
        String licencia = ventanaConductores.txtfLicencia.getText();

        // Validar que los campos que queremos verificar no estén vacíos
        if (dni.isEmpty() || licencia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos requeridos para verificar duplicados.");
            return true; // Retornamos true para que no continúe con la operación de agregar
        }

        try {
            // Establecer la conexión con la base de datos
            conet = con.obtenerConexion();
            String query = "SELECT * FROM conductores c " +
                           "JOIN persona p ON c.idPersona = p.idPersona " +
                           "WHERE p.DNI = ? OR c.numLicencia = ?";

            PreparedStatement pst = conet.prepareStatement(query);
            pst.setString(1, dni);
            pst.setString(2, licencia);

            rs = pst.executeQuery();

            // Si hay al menos un registro en el resultado, significa que hay duplicados
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "El DNI o la licencia ya están registrados en la base de datos.");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al verificar duplicados.");
        }
        return false;
    }
    
    public void limpiarTabla() {
        // Usa un while para eliminar todas las filas de la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    
    public int obtenerIdPersonaDesdeIdConductor(int idConductor) {
     int idPersona = -1; // Valor por defecto si no se encuentra
     try {
         String sql = "SELECT idPersona FROM conductores WHERE idConductor = " + idConductor;

         conet = con.obtenerConexion();
         st = conet.createStatement();
         rs = st.executeQuery(sql);

         if (rs.next()) {
             idPersona = rs.getInt("idPersona");
         }
     } catch (Exception e) {
         e.printStackTrace();
         JOptionPane.showMessageDialog(null, "Error al obtener el ID de la persona.");
     }
     return idPersona;
 } 
    
    
}
