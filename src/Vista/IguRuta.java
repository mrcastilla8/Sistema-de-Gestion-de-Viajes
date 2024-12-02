package Vista;

import Controlador.Conexion;
import Modelo.Ruta;
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
    Ruta ruta;
    public IguRuta(MainMenu menu) {
        this.menu=menu;
        initComponents();
        setLocationRelativeTo(null);
        ruta = new Ruta(this);
        ruta.consultar();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtfLugarInicio = new javax.swing.JTextField();
        txtfLugarDestino = new javax.swing.JTextField();
        txtfIdRuta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtfHoraLlegada = new javax.swing.JTextField();
        txtfHoraSalida = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaRutas = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(46, 138, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1370, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ruta.png"))); // NOI18N
        jLabel1.setText("RUTAS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(585, 23, -1, -1));

        jPanel1.setBackground(new java.awt.Color(31, 110, 140));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        jLabel2.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Lugar de inicio");

        jLabel3.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Lugar de destino");

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("IdRuta");

        txtfLugarInicio.setBackground(new java.awt.Color(137, 167, 161));
        txtfLugarInicio.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        txtfLugarInicio.setForeground(new java.awt.Color(255, 255, 255));
        txtfLugarInicio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        txtfLugarInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfLugarInicioActionPerformed(evt);
            }
        });

        txtfLugarDestino.setBackground(new java.awt.Color(137, 167, 161));
        txtfLugarDestino.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        txtfLugarDestino.setForeground(new java.awt.Color(255, 255, 255));
        txtfLugarDestino.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        txtfIdRuta.setEditable(false);
        txtfIdRuta.setBackground(new java.awt.Color(137, 167, 161));
        txtfIdRuta.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        txtfIdRuta.setForeground(new java.awt.Color(255, 255, 255));
        txtfIdRuta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        jLabel7.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Hora de salida");

        jLabel8.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Hora de llegada");

        txtfHoraLlegada.setBackground(new java.awt.Color(137, 167, 161));
        txtfHoraLlegada.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        txtfHoraLlegada.setForeground(new java.awt.Color(255, 255, 255));
        txtfHoraLlegada.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        txtfHoraSalida.setBackground(new java.awt.Color(137, 167, 161));
        txtfHoraSalida.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        txtfHoraSalida.setForeground(new java.awt.Color(255, 255, 255));
        txtfHoraSalida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfLugarDestino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfLugarInicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfIdRuta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfHoraSalida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfHoraLlegada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtfLugarInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfLugarDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfIdRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtfHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtfHoraLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        jPanel3.setBackground(new java.awt.Color(46, 138, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-380, 450, 990, -1));

        jLabel4.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DATOS");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(14, 41, 84));
        btnAgregar.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/crear.png"))); // NOI18N
        btnAgregar.setText("Crear");
        btnAgregar.setPreferredSize(new java.awt.Dimension(76, 27));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 130, 40));

        btnModificar.setBackground(new java.awt.Color(14, 41, 84));
        btnModificar.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 150, -1));

        btnEliminar.setBackground(new java.awt.Color(14, 41, 84));
        btnEliminar.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar1.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(130, 40));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 140, -1));

        btnNuevo.setBackground(new java.awt.Color(14, 41, 84));
        btnNuevo.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnNuevo.setText("Limpiar");
        btnNuevo.setPreferredSize(new java.awt.Dimension(130, 40));
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 370, 130, -1));

        jButton1.setBackground(new java.awt.Color(14, 41, 84));
        jButton1.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar.png"))); // NOI18N
        jButton1.setText("Regresar");
        jButton1.setPreferredSize(new java.awt.Dimension(130, 40));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 370, 150, -1));

        jLabel6.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("BASE DE DATOS");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, -1, -1));

        TablaRutas.setBackground(new java.awt.Color(137, 167, 161));
        TablaRutas.setFont(new java.awt.Font("SF Pro Display", 1, 13)); // NOI18N
        TablaRutas.setForeground(new java.awt.Color(255, 255, 255));
        TablaRutas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Lugar de Inicio", "Lugar de Destino", "Hora de Salida", "Hora de Llegada"
            }
        ));
        TablaRutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaRutasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaRutas);
        if (TablaRutas.getColumnModel().getColumnCount() > 0) {
            TablaRutas.getColumnModel().getColumn(0).setResizable(false);
            TablaRutas.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, 967, 230));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoOperadores.png"))); // NOI18N
        jLabel13.setText("jLabel13");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfLugarInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfLugarInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfLugarInicioActionPerformed

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
            String horaSalida = (String) TablaRutas.getValueAt(fila, 3);
            String horallegada = (String) TablaRutas.getValueAt(fila, 4);
            
            //Mostramos en los txtFields
            txtfLugarInicio.setText(lugarInicio);
            txtfLugarDestino.setText(lugarDestino);
            txtfHoraSalida.setText(horaSalida);
            txtfHoraLlegada.setText(horallegada);
            txtfIdRuta.setText(""+idRuta);
            
        }
    }//GEN-LAST:event_TablaRutasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        menu.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        ruta.nuevo();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ruta.eliminar();
        ruta.consultar();
        ruta.nuevo();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ruta.modificar();
        ruta.consultar();
        ruta.nuevo();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        ruta.agregar();
        ruta.consultar();
    }//GEN-LAST:event_btnAgregarActionPerformed
                         
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable TablaRutas;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField txtfHoraLlegada;
    public javax.swing.JTextField txtfHoraSalida;
    public javax.swing.JTextField txtfIdRuta;
    public javax.swing.JTextField txtfLugarDestino;
    public javax.swing.JTextField txtfLugarInicio;
    // End of variables declaration//GEN-END:variables
}
