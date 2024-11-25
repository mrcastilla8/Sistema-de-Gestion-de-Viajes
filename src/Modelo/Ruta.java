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
            Object[] rutas = new Object[5];
            modelo = (DefaultTableModel) ventanaRuta.TablaRutas.getModel();
            while (rs.next()) {
                rutas[0] = rs.getInt("idRuta");
                rutas[1] = rs.getString("LugarInicio");
                rutas[2] = rs.getString("LugarDestino");
                rutas[3] = rs.getString("horaSalida");
                rutas[4] = rs.getString("horaLlegada");
                modelo.addRow(rutas);

            }
            ventanaRuta.TablaRutas.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean esRutaValida(String lugarInicio, String lugarDestino) {
    // Convertir ambos lugares a minúsculas
    String inicioMinuscula = lugarInicio.toLowerCase();
    String destinoMinuscula = lugarDestino.toLowerCase();

    // Comparar las palabras en minúsculas
    if (inicioMinuscula.equals(destinoMinuscula)) {
        JOptionPane.showMessageDialog(null, "¡Lugar de inicio y destino no pueden ser iguales!");
        return false;
    }
    return true;
}

    public void agregar(){
        String LugarInicio = ventanaRuta.txtfLugarInicio.getText();
        String LugarDestino = ventanaRuta.txtfLugarDestino.getText();
        String HoraSalida = ventanaRuta.txtfHoraSalida.getText();
        String HoraLlegada = ventanaRuta.txtfHoraLlegada.getText();
        
        try{
            //Revisamos si algun txtfield está vacío
            if(LugarInicio.equals("") || LugarDestino.equals("")|| HoraSalida.equals("")|| HoraLlegada.equals("")){
                JOptionPane.showMessageDialog(null, "Faltan ingresar datos!");
            }
            else if (esRutaValida(LugarInicio, LugarDestino)) {
                if(existeRuta(LugarInicio, LugarDestino, HoraSalida, HoraLlegada)){
                    JOptionPane.showMessageDialog(null, "Esta ruta y hora de salida ya existen!");
                    limpiarTabla();
                }else{
                    //Agregamos a la persona a la tabla persona
                    String sql1 =  "INSERT INTO Ruta(LugarInicio,LugarDestino,horaSalida,horaLlegada) VALUES('"+LugarInicio+"','"+LugarDestino+"','"+HoraSalida+"','"+HoraLlegada+"')";
                    conet = con.obtenerConexion();
                    st = conet.createStatement();
                    st.executeUpdate(sql1);
                    JOptionPane.showMessageDialog(null, "Nueva ruta agregada!");
                    nuevo(); 
                }
                
            }
            
            limpiarTabla();
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar ruta.");
            limpiarTabla();
        }
    }
    public void modificar(){
        // Recopilar los datos de los campos de texto
        String LugarInicio = ventanaRuta.txtfLugarInicio.getText();
        String LugarDestino = ventanaRuta.txtfLugarDestino.getText();
        String HoraSalida = ventanaRuta.txtfHoraSalida.getText();
        String HoraLlegada = ventanaRuta.txtfHoraLlegada.getText();
        try {
            // Revisar si algún campo está vacío
            if(LugarInicio.equals("") || LugarDestino.equals("")|| HoraSalida.equals("")|| HoraLlegada.equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan ingresar datos!");
                limpiarTabla();
            }
            else if (esRutaValida(LugarInicio, LugarDestino)){
                if(existeRuta(LugarInicio, LugarDestino, HoraSalida, HoraLlegada)){
                    JOptionPane.showMessageDialog(null, "Esta ruta y hora de salida y llegada ya existen!");
                    limpiarTabla();
                }else{
                    int idRuta = Integer.parseInt(ventanaRuta.txtfIdRuta.getText());

                    //Nos conectamos a la base de datos
                    conet = con.obtenerConexion();
                    st = conet.createStatement();

                    // Actualizar los datos en la tabla `rutas`
                    String sql2 = "UPDATE Ruta SET LugarInicio='"+LugarInicio+"', LugarDestino='"+LugarDestino+"', horaSalida='"+HoraSalida+"', horaLlegada='"+HoraLlegada+"' WHERE idRuta=" + idRuta;
                    st.executeUpdate(sql2);

                    JOptionPane.showMessageDialog(null, "Datos de la ruta actualizados!");
                    limpiarTabla();  // Refrescar la tabla
                }
                
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
        ventanaRuta.txtfHoraSalida.setText("");
        ventanaRuta.txtfHoraLlegada.setText("");
    }
    public void limpiarTabla() {
        // Usa un while para eliminar todas las filas de la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    private boolean existeRuta(String lugarInicio, String lugarDestino, String horaSalida, String horaLlegada) {
    boolean existe = false;
    try {
        // Construir la consulta SQL manualmente
        String sql = "SELECT COUNT(*) AS total FROM Ruta WHERE LugarInicio = '" 
                    + lugarInicio + "' AND LugarDestino = '" 
                    + lugarDestino + "' AND horaSalida = '" 
                    + horaSalida +"' AND horaLlegada = '" 
                    + horaLlegada + "'";
        
        conet = con.obtenerConexion(); // Obtener la conexión
        st = conet.createStatement();   // Crear el statement
        rs = st.executeQuery(sql);      // Ejecutar la consulta
        
        if (rs.next()) {
            int count = rs.getInt("total");
            if (count > 0) {
                existe = true;
            }
        }
        rs.close();
        st.close();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al verificar si la ruta ya existe.");
    }
    return existe;
    }
}
