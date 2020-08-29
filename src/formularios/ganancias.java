
package formularios;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
public class ganancias extends javax.swing.JFrame {

    
    public ganancias() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("CONSULTAR VENTAS");
       BuscarFactura("");
     
        SumarProducto();
    }
              void reiniciarJTable3(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) tblfacturaimp.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
         
    }
 void SumarProducto(){
        DecimalFormat formateador = new DecimalFormat("######,###.##");
         DecimalFormat formateador2 = new DecimalFormat("#########.##");
        DefaultTableModel factura = (DefaultTableModel) tblfacturaimp.getModel();
        
        String total1 = "";
        float sub_total1 = 0;
        float sub_total2 = 0;
        String jitebis = "";
        float itbis_total2 = 0;
        double itbis_total3 = 0.18;
        float gran_total = 0;
        String total_venta = "";
        float total_precio1 = 0;
         
        float cantidad1 = 0;
        String cantidad2 = "";
        
        for (int i = 0; i < tblfacturaimp.getRowCount(); i++){
        total1 = (tblfacturaimp.getValueAt(i, 1).toString());
        sub_total2 = Float.parseFloat(total1);
        sub_total1 = sub_total1 + sub_total2;
        
        itbis_total2 = (float) (sub_total1 * itbis_total3);
        
        gran_total = (sub_total1 - itbis_total2);        
        }
        txttotal.setText(formateador.format(sub_total1));
       
       // txtvalor.setText(txttotal.getText());
        
    }
    void BuscarFactura(String valor){
    DefaultTableModel cliente =new DefaultTableModel();
             cliente.addColumn("FACTURA No.");
            cliente.addColumn("VENTA ");
            //cliente.addColumn("FECHA");
            tblfacturaimp.setModel(cliente);
            
         
    
    String [] datos  = new String[3];
    String sql = "SELECT ventadia.TOTAL,VENTADIA.ID_FAC FROM ventadia INNER JOIN factura ON VENTADIA.ID_FAC=factura.no_factura ";
    
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            datos[0]=rs.getString("ID_FAC");
            datos[1]=rs.getString("TOTAL");
            //datos[2]=rs.getString("(factura.fecha,'%d-%m-%Y')");
                        
            cliente.addRow(datos);
          //  VentaTotal();
             }
           tblfacturaimp.setModel(cliente);
            
            
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
        }
    
    
    
    }
     void BuscarFactura2(String valor){
    DefaultTableModel cliente =new DefaultTableModel();
             cliente.addColumn("FACTURA No.");
            cliente.addColumn("VENTA ");
            //cliente.addColumn("FECHA");
            tblfacturaimp.setModel(cliente);
            
         
    
    String [] datos  = new String[3];
    String sql = "SELECT ventadia.TOTAL,VENTADIA.ID_FAC FROM ventadia INNER JOIN factura_normal ON VENTADIA.ID_FAC=factura_normal.no_factura ";
    
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            datos[0]=rs.getString("ID_FAC");
            datos[1]=rs.getString("TOTAL");
            //datos[2]=rs.getString("(factura.fecha,'%d-%m-%Y')");
                        
            cliente.addRow(datos);
          //  VentaTotal();
             }
           tblfacturaimp.setModel(cliente);
            
            
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
        }
    
    
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblfacturaimp = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GANANCIAS");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setToolTipText("");

        tblfacturaimp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblfacturaimp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblfacturaimp.setGridColor(new java.awt.Color(153, 204, 255));
        tblfacturaimp.setInheritsPopupMenu(true);
        jScrollPane1.setViewportView(tblfacturaimp);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("TOTAL $");

        txttotal.setBackground(new java.awt.Color(0, 0, 0));
        txttotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txttotal.setForeground(new java.awt.Color(255, 0, 0));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\dolar.png")); // NOI18N
        jLabel13.setText("VENTAS REALIZADAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jRadioButton1.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jRadioButton1.setText("A Credito");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jRadioButton2.setText("Contado");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        reiniciarJTable3(tblfacturaimp);
        
        BuscarFactura2("");
SumarProducto();      
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        reiniciarJTable3(tblfacturaimp);
        BuscarFactura("");  
        SumarProducto();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ganancias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ganancias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ganancias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ganancias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ganancias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblfacturaimp;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
  String atributo;
     String fec1;
     String fec2;
     String txtbuscarimp;
   
    conectar.conectar con = new conectar.conectar();
      Connection cn = con.connection();
}
