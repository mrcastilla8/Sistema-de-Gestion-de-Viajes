package Clases;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;

public class Frame_menu_chofer extends javax.swing.JFrame {
    
    //Cargamos nuestra lista de conductores
    Archivos archivo = new Archivos();
    List<Conductor> Conductores = archivo.leerObjetos("Conductores.txt");
    
    /**
     * Creates new form Frame_menu_chofer
     */
    public Frame_menu_chofer() {
        initComponents();
        cargarConductoresEnTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    
    private void cargarConductoresEnTabla() {
        // Obtenemos el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tablaChofer.getModel();

        // Limpiamos las filas actuales de la tabla
        modelo.setRowCount(0);

        // Recorremos la lista de conductores y añadimos cada uno como una fila en la tabla
        for (int i = 0; i < Conductores.size(); i++) {
            Conductor conductor = Conductores.get(i);
            Object[] fila = { conductor.getIdConductor(), conductor.getNombre() };
            modelo.addRow(fila);
        }
        
        centrarContenidoTabla(tablaChofer);
    }
    
    private void centrarContenidoTabla(JTable tabla) {
        DefaultTableCellRenderer modeloCentrado = new DefaultTableCellRenderer();
        modeloCentrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // Aplicamos el renderer a cada columna
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(modeloCentrado);
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaChofer = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaChofer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tablaChofer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1), "MIGUEL TACO"}
            },
            new String [] {
                "ID", "NOMBRE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaChofer.setRowHeight(30);
        tablaChofer.setRowSelectionAllowed(false);
        tablaChofer.setSelectionBackground(new java.awt.Color(153, 255, 153));
        tablaChofer.setSelectionForeground(new java.awt.Color(153, 204, 0));
        tablaChofer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaChofer.setShowGrid(true);
        jScrollPane2.setViewportView(tablaChofer);
        if (tablaChofer.getColumnModel().getColumnCount() > 0) {
            tablaChofer.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

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

    /**
     * @param args the command line arguments
     */
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tablaChofer;
    // End of variables declaration//GEN-END:variables
}
