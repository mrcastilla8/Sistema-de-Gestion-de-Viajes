package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import Vista.CRUD_OPERADORES;
import Controlador.Conexion;

import javax.swing.table.DefaultTableModel;
public class Operador {
    
    Conexion con1= new Conexion();
    Connection conet;
    DefaultTableModel modelo;
    Statement st;
    ResultSet rs;
    int idc;
    private CRUD_OPERADORES ventanaOperadores;

    public Operador(CRUD_OPERADORES ventanaOperadores) {
        this.ventanaOperadores = ventanaOperadores;
    }
    
     public void consultar(){
        String sq = "SELECT c.idOperadores, p.nombre, p.apellido, p.edad, p.DNI, p.telefono, c.username, c.password, c.Rol " +
             "FROM persona p " +
             "JOIN operadores c ON p.idPersona = c.idPersona";
        try {
            conet = con1.obtenerConexion();
            st = conet.createStatement();
            rs = st.executeQuery(sq);
            Object[] operadores = new Object[9];
            modelo = (DefaultTableModel) ventanaOperadores.TablaOperador.getModel();
            while (rs.next()) {
                operadores[0] = rs.getInt("idOperadores");
                operadores[1] = rs.getString("nombre");
                operadores[2] = rs.getString("apellido");
                operadores[3] = rs.getString("username");
                operadores[4] = rs.getString("password");
                operadores[5] = rs.getString("Rol");
                operadores[6] = rs.getString("DNI");
                operadores[7] = rs.getString("telefono");
                operadores[8] = rs.getInt("edad");
                modelo.addRow(operadores);
                
            }
            ventanaOperadores.TablaOperador.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
    public void Agregar(){
        

        String user = ventanaOperadores.txtUsuario.getText();
        String contra = ventanaOperadores.txtContraseña.getText();
        String rol = ventanaOperadores.txtRol.getText();
        String nombre = ventanaOperadores.txtNombre.getText();
        String apellido = ventanaOperadores.txtApellido.getText();
        String DNI = ventanaOperadores.txtDNI.getText();
        String telefono = ventanaOperadores.txtTelefono.getText();
        

        try {
            if (ventanaOperadores.txtEdad.getText().equals("") || user.equals("") || contra.equals("") || rol.equals("") || nombre.equals("") || apellido.equals("") || DNI.equals("") || telefono.equals("")) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                limpiarTabla();
            }else{
                int edad = Integer.parseInt(ventanaOperadores.txtEdad.getText());
                String sq1 = "INSERT INTO persona (nombre, apellido, edad, DNI, telefono) VALUES ('"+nombre+"', '"+apellido+"', '"+edad+"', '"+DNI+"', '"+telefono+"')";
                conet = con1.obtenerConexion();
                st = conet.createStatement();
                st.executeUpdate(sq1,Statement.RETURN_GENERATED_KEYS);
                rs = st.getGeneratedKeys();
                int idPersona = -1;
                if (rs.next()) {
                    idPersona = rs.getInt(1);
                }
                String sq2 = "INSERT INTO operadores (idPersona,username, password, Rol) VALUES ('"+idPersona+"', '"+user+"', '"+contra+"', '"+rol+"')";
                st.executeUpdate(sq2);
                JOptionPane.showMessageDialog(null, "Operador agregado");
                limpiarTabla();
            }
            
        } catch (Exception e) {
        }
    }
    public void Modificar(){
        String nombre = ventanaOperadores.txtNombre.getText();
        String apellido = ventanaOperadores.txtApellido.getText();
        String edadStr = ventanaOperadores.txtEdad.getText();
        String contra = ventanaOperadores.txtContraseña.getText();
        String rol = ventanaOperadores.txtRol.getText();
        String usuario = ventanaOperadores.txtUsuario.getText();
        String telefono = ventanaOperadores.txtTelefono.getText();
        String DNI = ventanaOperadores.txtDNI.getText();

        try {
            if(nombre.equals("") || apellido.equals("") || edadStr.equals("") || contra.equals("") || rol.equals("") || usuario.equals("") || telefono.equals("") || ventanaOperadores.txtDNI.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Faltan ingresar datos!");
                limpiarTabla();
            }
            else {
                
                // Convertir edad a int después de verificar que no está vacío
                int edad = Integer.parseInt(edadStr);
                
                // Obtener el idConductor desde el txtField 
                int idOperador = Integer.parseInt(ventanaOperadores.txtID.getText());

                // Usar el idConductor para obtener el idPersona correspondiente
                int idPersona = obtenerIdPersonaDesdeidOperador(idOperador);

                // Verificar que se haya encontrado el idPersona
                if (idPersona == -1) {
                    JOptionPane.showMessageDialog(null, "No se encontró el ID de la persona asociado.");
                    return; // Salir si no se encontró
                }

                //Nos conectamos a la base de datos
                conet = con1.obtenerConexion();
                st = conet.createStatement();

                // Actualizar los datos en la tabla `persona`
                String sql5 = "UPDATE persona SET nombre = '" + nombre + "', apellido = '" + apellido + "', edad = '" + edad + "', telefono = '" + telefono + "', DNI = '" + DNI + "' WHERE idPersona = " + idPersona;
                st.executeUpdate(sql5);

                // Actualizar los datos en la tabla `conductores`
                String sql6 = "UPDATE operadores SET username = '" + usuario + "', password = '" + contra + "', Rol = '" + rol + "' WHERE idOperadores = " + idOperador;
                st.executeUpdate(sql6);

                JOptionPane.showMessageDialog(null, "Datos del operador actualizados!");
                limpiarTabla();  // Refrescar la tabla
            }
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos del operador.");
            limpiarTabla();
        }        
    }
    
    public void eliminar() {
        int fila = ventanaOperadores.TablaOperador.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Operador no seleccionado");
            return; // Salimos del método si no hay fila seleccionada
        }

        try {
            // Obtén el idOperador directamente de la tabla seleccionada
            int idOperador = Integer.parseInt(ventanaOperadores.TablaOperador.getValueAt(fila, 0).toString());

            // Usar el idOperador para obtener el idPersona correspondiente
            int idPersona = obtenerIdPersonaDesdeidOperador(idOperador);

            // Verificar que se haya encontrado el idPersona
            if (idPersona == -1) {
                JOptionPane.showMessageDialog(null, "No se encontró el ID de la persona asociado.");
                return; // Salir si no se encontró
            }

            // Nos conectamos a la base de datos
            conet = con1.obtenerConexion();
            st = conet.createStatement();

            // Primero eliminamos el registro de la tabla `operadores`
            String sqlOperador = "DELETE FROM operadores WHERE idOperadores = " + idOperador;
            st.executeUpdate(sqlOperador);

            // Luego eliminamos el registro de la tabla `persona`
            String sqlPersona = "DELETE FROM persona WHERE idPersona = " + idPersona;
            st.executeUpdate(sqlPersona);

            JOptionPane.showMessageDialog(null, "Operador eliminado!");
            limpiarTabla();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar el operador.");
        }
    }//fin Eliminar()

    public void limpiarTabla() {
    if (modelo == null) {
        modelo = (DefaultTableModel) ventanaOperadores.TablaOperador.getModel();
    }
    
    while (modelo.getRowCount() > 0) {
        modelo.removeRow(0);
    }
}

    public void nuevo(){
        ventanaOperadores.txtNombre.setText("");
        ventanaOperadores.txtApellido.setText("");
        ventanaOperadores.txtEdad.setText("");
        ventanaOperadores.txtDNI.setText("");
        ventanaOperadores.txtTelefono.setText("");
        ventanaOperadores.txtUsuario.setText("");
        ventanaOperadores.txtContraseña.setText("");
        ventanaOperadores.txtRol.setText("");
        ventanaOperadores.txtID.setText("");
    }
    
    public int obtenerIdPersonaDesdeidOperador(int idOperador) {
     int idPersona = -1; // Valor por defecto si no se encuentra
     try {
         String sq4 = "SELECT idPersona FROM operadores WHERE idOperadores = " + idOperador;

         conet = con1.obtenerConexion();
         st = conet.createStatement();
         rs = st.executeQuery(sq4);

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