package Vista;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import Vista.MainMenu;
import javax.swing.table.DefaultTableModel;

public class IguRuta extends javax.swing.JFrame {
    MainMenu menu;
    Conexion con = new Conexion();
    Connection conet;
    DefaultTableModel modelo;
    Statement st;
    ResultSet rs;
    int idc;
    public IguRuta(MainMenu menu) {
        this.menu=menu;
        initComponents();
        setLocationRelativeTo(null);
        consultar();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtfLugarInicio = new javax.swing.JTextField();
        txtfLugarDestino = new javax.swing.JTextField();
        txtfDuracionEstimada = new javax.swing.JTextField();
        txtfIdRuta = new javax.swing.JTextField();
        buttonAtras3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaRutas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("GESTIÓN DE RUTAS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel2.setText("Lugar de inicio:");

        jLabel3.setText("Lugar de destino:");

        jLabel4.setText("Duración estimada:");

        jLabel5.setText("IdRuta:");

        txtfLugarInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfLugarInicioActionPerformed(evt);
            }
        });

        txtfIdRuta.setEditable(false);

        buttonAtras3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonAtras3.setText("ATRÁS");
        buttonAtras3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        buttonAtras3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAtras3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtfLugarDestino)
                    .addComponent(txtfDuracionEstimada)
                    .addComponent(txtfIdRuta, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(txtfLugarInicio))
                .addGap(68, 68, 68)
                .addComponent(buttonAtras3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtfLugarInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtfLugarDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAtras3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtfDuracionEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtfIdRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Operaciones"));

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnAgregar)
                .addGap(62, 62, 62)
                .addComponent(btnModificar)
                .addGap(64, 64, 64)
                .addComponent(btnEliminar)
                .addGap(62, 62, 62)
                .addComponent(btnNuevo)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnNuevo))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Base de datos"));

        TablaRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idRuta", "LugarInicio", "LugarDestino", "DuracionEstimada"
            }
        ));
        TablaRutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaRutasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaRutas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(85, 85, 85))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(63, 63, 63)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfLugarInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfLugarInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfLugarInicioActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
        consultar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        modificar();
        consultar();
        nuevo();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
        consultar();
        nuevo();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void TablaRutasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaRutasMouseClicked
        int fila = TablaRutas.getSelectedRow();
        if(fila == -1){
            //si no se selecciona ninguna fila
            JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada");
        }
        else{
            
            //Guardamos en variables locales el registro (fila seleccionada)
            int idRuta = Integer.parseInt((String) TablaRutas.getValueAt(fila,0) .toString() );
            String lugarInicio = (String) TablaRutas.getValueAt(fila, 1);
            String lugarDestino = (String) TablaRutas.getValueAt(fila, 2);
            int duracionEstimada = Integer.parseInt((String) TablaRutas.getValueAt(fila,3) .toString() );
            
            //Mostramos en los txtFields
            txtfLugarInicio.setText(lugarInicio);
            txtfLugarDestino.setText(lugarDestino);
            txtfDuracionEstimada.setText(""+duracionEstimada);
            txtfIdRuta.setText(""+idRuta);
            
        }
    }//GEN-LAST:event_TablaRutasMouseClicked

    private void buttonAtras3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAtras3ActionPerformed
        menu.setVisible(true);
        this.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAtras3ActionPerformed
                         
    
    public void consultar(){
        String sql = "SELECT * FROM Ruta ORDER BY idRuta";

        try {
            conet = con.obtenerConexion();
            st = conet.createStatement();
            rs = st.executeQuery(sql);
            Object[] rutas = new Object[7];
            modelo = (DefaultTableModel) TablaRutas.getModel();
            while (rs.next()) {
                rutas[0] = rs.getInt("idRuta");
                rutas[1] = rs.getString("LugarInicio");
                rutas[2] = rs.getString("LugarDestino");
                rutas[3] = rs.getInt("DuracionEstimada");
                modelo.addRow(rutas);

            }
            TablaRutas.setModel(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void agregar(){
        String LugarInicio = txtfLugarInicio.getText();
        String LugarDestino = txtfLugarDestino.getText();
        String DuracionEstimadaStr = txtfDuracionEstimada.getText();
        
        try{
            //Revisamos si algun txtfield está vacío
            if(LugarInicio.equals("") || LugarDestino.equals("") || DuracionEstimadaStr.equals("")){
                JOptionPane.showMessageDialog(null, "Faltan ingresar datos!");
            }
            else{
                
                // Convertir edad a int después de verificar que no está vacío
                int DuracionEstimada = Integer.parseInt(DuracionEstimadaStr);
                
                //Agregamos a la persona a la tabla persona
                String sql1 =  "INSERT INTO Ruta(LugarInicio,LugarDestino,duracionEstimada) VALUES('"+LugarInicio+"','"+LugarDestino+"','"+DuracionEstimada+"')";
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
        String LugarInicio = txtfLugarInicio.getText();
        String LugarDestino = txtfLugarDestino.getText();
        String DuracionEstimadaStr = txtfDuracionEstimada.getText();

        try {
            // Revisar si algún campo está vacío
            if(LugarInicio.equals("") || LugarDestino.equals("") || DuracionEstimadaStr.equals("")) {
                JOptionPane.showMessageDialog(null, "Faltan ingresar datos!");
                limpiarTabla();
            }
            else {
                
                int DuracionEstimada = Integer.parseInt(DuracionEstimadaStr);
                
                int idRuta = Integer.parseInt(txtfIdRuta.getText());

                //Nos conectamos a la base de datos
                conet = con.obtenerConexion();
                st = conet.createStatement();

                // Actualizar los datos en la tabla `rutas`
                String sql2 = "UPDATE Ruta SET LugarInicio='"+LugarInicio+"', LugarDestino='"+LugarDestino+"', DuracionEstimada='"+DuracionEstimada+"' WHERE idRuta=" + idRuta;
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
        int fila = TablaRutas.getSelectedRow();
        try{
            if(fila<0){
                JOptionPane.showMessageDialog(null, "Ruta no seleccionada");
                limpiarTabla();
            }
            else{
                int idRuta = Integer.parseInt(txtfIdRuta.getText());
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
        txtfIdRuta.setText("");
        txtfLugarInicio.setText("");
        txtfLugarDestino.setText("");
        txtfDuracionEstimada.setText("");
    }
    public void limpiarTabla() {
        // Usa un while para eliminar todas las filas de la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaRutas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton buttonAtras3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtfDuracionEstimada;
    private javax.swing.JTextField txtfIdRuta;
    private javax.swing.JTextField txtfLugarDestino;
    private javax.swing.JTextField txtfLugarInicio;
    // End of variables declaration//GEN-END:variables
}
