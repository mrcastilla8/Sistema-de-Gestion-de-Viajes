/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import Modelo.Viaje;
import java.awt.Graphics;
import java.awt.Image;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class CRUD_VIAJES extends javax.swing.JFrame {
    MainMenu menu;
    Conexion con1= new Conexion();
    Connection conet;
    DefaultTableModel modelo;
    Statement st;
    ResultSet rs;
    int idc;
    Viaje viaje;
    FondoPanel fondo = new FondoPanel();
public CRUD_VIAJES(MainMenu menu) {
    
    this.menu = menu;  // Guardar la referencia del menú principal
    this.setContentPane(fondo);
    initComponents(); // Inicializar componentes de la GUI
    setLocationRelativeTo(null); // Centrar la ventana
    viaje = new Viaje(this);
    viaje.cargarIdsBuses();          // Cargar IDs de buses
    viaje.cargarIdsRutas();          // Cargar IDs de rutas
    viaje.cargarIdsConductores();     // Cargar IDs de conductores
    viaje.consultar();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new FondoPanel();
        jPanel2 = new javax.swing.JPanel();
        Label_Bus = new javax.swing.JLabel();
        Label_Conductor1 = new javax.swing.JLabel();
        Label_Conductor2 = new javax.swing.JLabel();
        Label_Ruta = new javax.swing.JLabel();
        Label_Ruta1 = new javax.swing.JLabel();
        Label_Ruta2 = new javax.swing.JLabel();
        Entrada_Precio = new javax.swing.JTextField();
        Entrada_Bus = new javax.swing.JComboBox<>();
        Entrada_Primer_Conductor = new javax.swing.JComboBox<>();
        Entrada_Segundo_Conductor = new javax.swing.JComboBox<>();
        Entrada_Ruta = new javax.swing.JComboBox<>();
        Entrada_Fecha = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Viajes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Crear = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Boton_Regresar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(46, 138, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(31, 110, 140));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        Label_Bus.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        Label_Bus.setForeground(new java.awt.Color(255, 255, 255));
        Label_Bus.setText("ID del bus");

        Label_Conductor1.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        Label_Conductor1.setForeground(new java.awt.Color(255, 255, 255));
        Label_Conductor1.setText("ID del primer conductor");

        Label_Conductor2.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        Label_Conductor2.setForeground(new java.awt.Color(255, 255, 255));
        Label_Conductor2.setText("ID del segundo conductor");

        Label_Ruta.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        Label_Ruta.setForeground(new java.awt.Color(255, 255, 255));
        Label_Ruta.setText("ID de la ruta");

        Label_Ruta1.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        Label_Ruta1.setForeground(new java.awt.Color(255, 255, 255));
        Label_Ruta1.setText("Precio");

        Label_Ruta2.setFont(new java.awt.Font("SF Pro Display", 1, 17)); // NOI18N
        Label_Ruta2.setForeground(new java.awt.Color(255, 255, 255));
        Label_Ruta2.setText("Fecha de salida");

        Entrada_Precio.setBackground(new java.awt.Color(132, 167, 161));
        Entrada_Precio.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        Entrada_Precio.setForeground(new java.awt.Color(255, 255, 255));
        Entrada_Precio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Entrada_Precio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        Entrada_Precio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Entrada_PrecioActionPerformed(evt);
            }
        });

        Entrada_Bus.setBackground(new java.awt.Color(132, 167, 161));
        Entrada_Bus.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        Entrada_Bus.setForeground(new java.awt.Color(255, 255, 255));
        Entrada_Bus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Entrada_Bus.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        Entrada_Bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Entrada_BusActionPerformed(evt);
            }
        });

        Entrada_Primer_Conductor.setBackground(new java.awt.Color(132, 167, 161));
        Entrada_Primer_Conductor.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        Entrada_Primer_Conductor.setForeground(new java.awt.Color(255, 255, 255));
        Entrada_Primer_Conductor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Entrada_Primer_Conductor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        Entrada_Primer_Conductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Entrada_Primer_ConductorActionPerformed(evt);
            }
        });

        Entrada_Segundo_Conductor.setBackground(new java.awt.Color(132, 167, 161));
        Entrada_Segundo_Conductor.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        Entrada_Segundo_Conductor.setForeground(new java.awt.Color(255, 255, 255));
        Entrada_Segundo_Conductor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Entrada_Segundo_Conductor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        Entrada_Segundo_Conductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Entrada_Segundo_ConductorActionPerformed(evt);
            }
        });

        Entrada_Ruta.setBackground(new java.awt.Color(132, 167, 161));
        Entrada_Ruta.setFont(new java.awt.Font("SF Pro Display", 0, 17)); // NOI18N
        Entrada_Ruta.setForeground(new java.awt.Color(255, 255, 255));
        Entrada_Ruta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Entrada_Ruta.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        Entrada_Ruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Entrada_RutaActionPerformed(evt);
            }
        });

        Entrada_Fecha.setBackground(new java.awt.Color(132, 167, 161));
        Entrada_Fecha.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Label_Bus)
                    .addComponent(Label_Conductor1)
                    .addComponent(Label_Conductor2)
                    .addComponent(Label_Ruta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Entrada_Primer_Conductor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Entrada_Ruta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Entrada_Segundo_Conductor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Entrada_Bus, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Label_Ruta1)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(Label_Ruta2)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Entrada_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Entrada_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Entrada_Bus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_Bus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Entrada_Primer_Conductor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Label_Conductor1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Label_Ruta1)
                            .addComponent(Entrada_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Label_Ruta2)
                            .addComponent(Entrada_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Entrada_Segundo_Conductor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_Conductor2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Entrada_Ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_Ruta))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 101, -1, -1));

        jPanel4.setBackground(new java.awt.Color(46, 138, 153));

        Tabla_Viajes.setBackground(new java.awt.Color(132, 167, 161));
        Tabla_Viajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Viaje", "Id Bus", "Origen", "Destino", "Precio", "Primer Conductor", "Segundo Conductor", "Fecha de Salida", "Hora de salida", "Hora de llegada"
            }
        ));
        Tabla_Viajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_ViajesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla_Viajes);

        jLabel6.setFont(new java.awt.Font("SF Pro Display", 1, 26)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(242, 242, 242));
        jLabel6.setText("Base de datos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1311, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 359, 1350, -1));

        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(867, 353, -1, -1));

        Crear.setBackground(new java.awt.Color(14, 41, 84));
        Crear.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        Crear.setForeground(new java.awt.Color(255, 255, 255));
        Crear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/crear.png"))); // NOI18N
        Crear.setText("Crear");
        Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearActionPerformed(evt);
            }
        });
        jPanel1.add(Crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 302, 110, 51));

        Eliminar.setBackground(new java.awt.Color(14, 41, 84));
        Eliminar.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar1.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 303, 145, 50));

        Modificar.setBackground(new java.awt.Color(14, 41, 84));
        Modificar.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        Modificar.setForeground(new java.awt.Color(255, 255, 255));
        Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        Modificar.setText("Modificar");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        jPanel1.add(Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 303, 163, 50));

        jButton2.setBackground(new java.awt.Color(14, 41, 84));
        jButton2.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(586, 303, 150, 50));

        Boton_Regresar.setBackground(new java.awt.Color(14, 41, 84));
        Boton_Regresar.setFont(new java.awt.Font("SF Pro Display", 1, 22)); // NOI18N
        Boton_Regresar.setForeground(new java.awt.Color(255, 255, 255));
        Boton_Regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar.png"))); // NOI18N
        Boton_Regresar.setText("Regresar");
        Boton_Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_RegresarActionPerformed(evt);
            }
        });
        jPanel1.add(Boton_Regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(779, 303, 163, 50));

        jLabel5.setFont(new java.awt.Font("SF Pro Display", 1, 26)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(242, 242, 242));
        jLabel5.setText("GESTIÓN DE VIAJES");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 17, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(242, 242, 242));
        jLabel7.setText("Base de datos");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 419, -1, -1));

        jLabel8.setFont(new java.awt.Font("SF Pro Display", 1, 26)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(242, 242, 242));
        jLabel8.setText("Datos");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 63, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_RegresarActionPerformed
        menu.setVisible(true);
        limpiarCampos();
        this.setVisible(false);
    }//GEN-LAST:event_Boton_RegresarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        if (validarCamposLlenos()){
            viaje.modificar();
        }

    }//GEN-LAST:event_ModificarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        viaje.eliminar();
    }//GEN-LAST:event_EliminarActionPerformed

    private void CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearActionPerformed
        if (validarCamposLlenos()){

            viaje.agregar();
        }
    }//GEN-LAST:event_CrearActionPerformed

    private void Tabla_ViajesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_ViajesMouseClicked
         int fila = Tabla_Viajes.getSelectedRow(); // Obtener la fila seleccionada
    if (fila == -1) {
        JOptionPane.showMessageDialog(null, "Seleccione un viaje para cargar los datos");
    } else {
        // Obtener los valores de la fila seleccionada
        String idBus = Tabla_Viajes.getValueAt(fila, 1).toString();
        String conductor1Nombre = Tabla_Viajes.getValueAt(fila, 5).toString();
        String conductor2Nombre = Tabla_Viajes.getValueAt(fila, 6).toString();
        String rutaOrigen = Tabla_Viajes.getValueAt(fila, 2).toString();
        String rutaDestino = Tabla_Viajes.getValueAt(fila, 3).toString();
        String precio = Tabla_Viajes.getValueAt(fila, 4).toString();
        String fechaSalida = Tabla_Viajes.getValueAt(fila, 7).toString();
        String horaSalida = Tabla_Viajes.getValueAt(fila, 8).toString();
        String horaLlegada = Tabla_Viajes.getValueAt(fila, 9).toString();

        // Buscar y seleccionar los IDs correspondientes en los comboBox
        seleccionarItemPorTexto(Entrada_Bus, idBus);
        seleccionarItemPorTexto(Entrada_Primer_Conductor, conductor1Nombre);
        seleccionarItemPorTexto(Entrada_Segundo_Conductor, conductor2Nombre);
        seleccionarItemPorTexto(Entrada_Ruta, rutaOrigen + " - " + rutaDestino);

        // Cargar los demás valores en los campos correspondientes
        Entrada_Precio.setText(precio);

        try {
            // Convertir la fecha a un objeto Date para el JDateChooser
            java.util.Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaSalida);
            Entrada_Fecha.setDate(fecha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la fecha: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_Tabla_ViajesMouseClicked

    private void Entrada_RutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Entrada_RutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Entrada_RutaActionPerformed

    private void Entrada_Segundo_ConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Entrada_Segundo_ConductorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Entrada_Segundo_ConductorActionPerformed

    private void Entrada_Primer_ConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Entrada_Primer_ConductorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Entrada_Primer_ConductorActionPerformed

    private void Entrada_BusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Entrada_BusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Entrada_BusActionPerformed

    private void Entrada_PrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Entrada_PrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Entrada_PrecioActionPerformed

    
private void seleccionarItemPorTexto(javax.swing.JComboBox<String> comboBox, String texto) {
    for (int i = 0; i < comboBox.getItemCount(); i++) {
        String item = comboBox.getItemAt(i);
        if (item.contains(texto)) { // Buscar coincidencias parciales o exactas
            comboBox.setSelectedIndex(i);
            break;
        }
    }
}

private boolean validarCamposLlenos() {
    if (Entrada_Bus.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un bus.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (Entrada_Primer_Conductor.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un primer conductor.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (Entrada_Segundo_Conductor.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un segundo conductor.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (Entrada_Ruta.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una ruta.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (Entrada_Precio.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe ingresar un precio.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (Entrada_Fecha.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de salida.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
   
    return true;
}

    private void limpiarCampos() {
        Entrada_Bus.setSelectedIndex(0);
        Entrada_Primer_Conductor.setSelectedIndex(0);
        Entrada_Segundo_Conductor.setSelectedIndex(0);
        Entrada_Ruta.setSelectedIndex(0);
        Entrada_Precio.setText("");
        Entrada_Fecha.setDate(null);
        }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_Regresar;
    private javax.swing.JButton Crear;
    private javax.swing.JButton Eliminar;
    public javax.swing.JComboBox<String> Entrada_Bus;
    public com.toedter.calendar.JDateChooser Entrada_Fecha;
    public javax.swing.JTextField Entrada_Precio;
    public javax.swing.JComboBox<String> Entrada_Primer_Conductor;
    public javax.swing.JComboBox<String> Entrada_Ruta;
    public javax.swing.JComboBox<String> Entrada_Segundo_Conductor;
    private javax.swing.JLabel Label_Bus;
    private javax.swing.JLabel Label_Conductor1;
    private javax.swing.JLabel Label_Conductor2;
    private javax.swing.JLabel Label_Ruta;
    private javax.swing.JLabel Label_Ruta1;
    private javax.swing.JLabel Label_Ruta2;
    private javax.swing.JButton Modificar;
    public javax.swing.JTable Tabla_Viajes;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    class FondoPanel extends JPanel{
        private Image imagen;
        @Override
        public void paint(Graphics g){
              imagen =  new ImageIcon(getClass().getResource("/imagenes/fondoOperadores.png ")).getImage();
              g.drawImage(imagen,0,0,getWidth(),getHeight( ),this);
              setOpaque(false);
              super.paint(g);
        }
    }
 }
