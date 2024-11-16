package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import Controlador.Conexion;
import Vista.IguRuta;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ruta {
    Conexion con= new Conexion();
    Connection conet;
    DefaultTableModel modelo;
    Statement st;
    ResultSet rs;
    private IguRuta ventanaRuta;

    public Ruta(IguRuta ventanaRuta) {
        this.ventanaRuta = ventanaRuta;
    }
    
    public void consultar(){
        String sql = "SELECT * FROM Ruta ORDER BY idRuta";

        try {
            conet = con.obtenerConexion();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            Object[] rutas = new Object[3];
            modelo = (DefaultTableModel) ventanaRuta.TablaRutas.getModel();
            while (rs.next()) {
                rutas[0] = rs.getInt("idRuta");
                rutas[1] = rs.getString("LugarInicio");
                rutas[2] = rs.getString("LugarDestino");
                modelo.addRow(rutas);

            }
            ventanaRuta.TablaRutas.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void agregar(){
        String LugarInicio = ventanaRuta.txtfLugarInicio.getText();
        String LugarDestino = ventanaRuta.txtfLugarDestino.getText();
        
        try{
            //Revisamos si algun txtfield está vacío
            if(LugarInicio.equals("") || LugarDestino.equals("")){
                JOptionPane.showMessageDialog(null, "Faltan ingresar datos!");
            }
            else{
                //Agregamos a la persona a la tabla persona
                String sql1 =  "INSERT INTO Ruta(LugarInicio,LugarDestino) VALUES('"+LugarInicio+"','"+LugarDestino+"')";
                conet = con.obtenerConexion();
                st = conet.createStatement();
                st.executeUpdate(sql1, Statement.RETURN_GENERATED_KEYS);
                JOptionPane.showMessageDialog(null, "Nueva ruta agregada!");
                nuevo();
            }
            
            limpiarTabla();
        }catch(Exception e){
            
        }
    }
    public void modificar(){
        // Recopilar los datos de los campos de texto
        String LugarInicio = ventanaRuta.txtfLugarInicio.getText();
        String LugarDestino = ventanaRuta.txtfLugarDestino.getText();
        try {
            // Revisar si algún campo está vacío
            if(LugarInicio.equals("") || LugarDestino.equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan ingresar datos!");
                limpiarTabla();
            }
            else {
                
                int idRuta = Integer.parseInt(ventanaRuta.txtfIdRuta.getText());

                //Nos conectamos a la base de datos
                conet = con.obtenerConexion();
                st = conet.createStatement();

                // Actualizar los datos en la tabla `rutas`
                String sql2 = "UPDATE Ruta SET LugarInicio='"+LugarInicio+"', LugarDestino='"+LugarDestino+"' WHERE idRuta=" + idRuta;
                st.executeUpdate(sql2);

                JOptionPane.showMessageDialog(null, "Datos de la ruta actualizados!");
                limpiarTabla();  // Refrescar la tabla
            }
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos de la ruta.");
            limpiarTabla();
        }        
    }
    
    public void eliminar(){
        int fila = ventanaRuta.TablaRutas.getSelectedRow();
        try{
            if(fila<0){
                JOptionPane.showMessageDialog(null, "Ruta no seleccionada");
                limpiarTabla();
            }
            else{
                int idRuta = Integer.parseInt(ventanaRuta.txtfIdRuta.getText());
                String sql = "DELETE FROM Ruta WHERE idRuta="+idRuta;
                conet = con.obtenerConexion();
                st = conet.createStatement();
                st.executeUpdate(sql);
                JOptionPane.showMessageDialog(null, "Ruta eliminada!"); 
                limpiarTabla();
            }
            
        }catch(Exception e){
            limpiarTabla();
        }
    }
    
    public void nuevo(){
        ventanaRuta.txtfIdRuta.setText("");
        ventanaRuta.txtfLugarInicio.setText("");
        ventanaRuta.txtfLugarDestino.setText("");
    }
    public void limpiarTabla() {
        // Usa un while para eliminar todas las filas de la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
}
