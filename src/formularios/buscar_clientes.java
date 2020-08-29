
package formularios;



import conectar.conectar;
import formularios.Clientes_mantenimiento;
import formularios.facturacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class buscar_clientes extends javax.swing.JFrame {

    public String modelo="cliente";
    public buscar_clientes() {
        initComponents();
        cargar();
        //cargardatos();
        this.setLocationRelativeTo(null);
    }
    
    
     public buscar_clientes(String modelo) {
        initComponents();
        this.modelo=modelo;
        cargar();
        //cargardatos();
        this.setLocationRelativeTo(null);
    }
    
  void filtrar(String valor){
         DefaultTableModel modelo2 = (DefaultTableModel)tabla_cli.getModel();
     modelo2.setNumRows(0);
        String [] registros = new String [14];
        String sql ="SELECT * FROM cliente  where CONCAT (idcliente,' ',nombre,' ',apellido,'',rnc,'',cedula)LIKE '%"+valor+"%' ";
       
         if(modelo.equals("factura")){
         sql ="SELECT * FROM cliente  where estado='ACTIVO' and CONCAT (idcliente,' ',nombre,' ',apellido,'',rnc,'',cedula)LIKE '%"+valor+"%' ";

        }
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registros[0]=rs.getString("idcliente");
                registros[1]=rs.getString("cedula");
                registros[2]=rs.getString("nombre");
                registros[3]=rs.getString("apellido");
                registros[4]=rs.getString("rnc");
                registros[5]=rs.getString("estado");
                registros[6]=rs.getString("limite_credito");
                registros[7]=rs.getString("calle");
                registros[8]=rs.getString("sector");
                registros[9]=rs.getString("ciudad");
                
                String sqlCelular ="select * from tabla_cliente where id_tipo_tel = 1 and  idcliente= " + rs.getString("idcliente")+"";

                    Statement std = cn.createStatement();
                ResultSet rsC = std.executeQuery(sqlCelular);
                  if(rsC.next()){
                  registros[10]=rsC.getString("numero");
                  }
                  
                   String sqlResidencial ="select * from tabla_cliente where id_tipo_tel = 2 and  idcliente= " + rs.getString("idcliente")+"";

                    Statement stdr = cn.createStatement();
                ResultSet rsR = stdr.executeQuery(sqlResidencial);
                  if(rsR.next()){
                  registros[11]=rsR.getString("numero");
                  } 
                   registros[12]=rs.getString("razon_social");

                  String formato_fecha = rs.getString("fecha_cliente").split("-")[2]+"/"+rs.getString("fecha_cliente").split("-")[1]+"/"+rs.getString("fecha_cliente").split("-")[0];
               registros[13]=formato_fecha;

               
                modelo2.addRow(registros);
               
            }
          tabla_cli.setModel(modelo2);  
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
  
  
    void cargar(){
    int contador = 0;
         DefaultTableModel modelo2 = (DefaultTableModel)tabla_cli.getModel();
     modelo2.getDataVector().clear();
        String [] registros = new String [15];
        String sql ="SELECT * FROM cliente ";
        
         if(modelo.equals("factura")){
            sql="SELECT * FROM cliente where estado='ACTIVO'";
        }
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                contador++;
                registros[0]=rs.getString("idcliente");
                registros[1]=rs.getString("cedula");
                registros[2]=rs.getString("nombre");
                registros[3]=rs.getString("apellido");
                registros[4]=rs.getString("rnc");
                registros[5]=rs.getString("estado");
                registros[6]=rs.getString("limite_credito");
                registros[7]=rs.getString("calle");
                registros[8]=rs.getString("sector");
                registros[9]=rs.getString("ciudad");
                
                String sqlCelular ="select * from tabla_cliente where id_tipo_tel = 1 and  idcliente= " + rs.getString("idcliente")+"";

                    Statement std = cn.createStatement();
                ResultSet rsC = std.executeQuery(sqlCelular);
                  if(rsC.next()){
                  registros[10]=rsC.getString("numero");
                  }
                  
                   String sqlResidencial ="select * from tabla_cliente where id_tipo_tel = 2 and  idcliente= " + rs.getString("idcliente")+"";

                    Statement stdr = cn.createStatement();
                ResultSet rsR = stdr.executeQuery(sqlResidencial);
                  if(rsR.next()){
                  registros[11]=rsR.getString("numero");
                  } 
               registros[12]=rs.getString("razon_social");

                  String formato_fecha = rs.getString("fecha_cliente").split("-")[2]+"/"+rs.getString("fecha_cliente").split("-")[1]+"/"+rs.getString("fecha_cliente").split("-")[0];
               registros[13]=formato_fecha;
               registros[14]=rs.getString("precio_por_mayor");

               
                modelo2.addRow(registros);
               
            }
          tabla_cli.setModel(modelo2);  
            contador_txt.setText(String.valueOf(contador));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_cli = new javax.swing.JTable();
        buscar_cliente = new javax.swing.JTextField();
        contador_txt = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTA DE CLIENTES");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jLabel1.setText("Buscar ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, 30));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\equipo (3).png")); // NOI18N
        jLabel3.setText("Clientes Registrados");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 260, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Registro");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 70, -1, 30));

        tabla_cli.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_cli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cedula", "Nombres", "Apellidos", "RNC", "Estados", "Credito", "Calle", "Sector", "Ciudad", "Celular", "Residencial", "Razon", "Fecha", "Por mayor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_cli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_cliMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_cli);
        if (tabla_cli.getColumnModel().getColumnCount() > 0) {
            tabla_cli.getColumnModel().getColumn(0).setMinWidth(50);
            tabla_cli.getColumnModel().getColumn(0).setMaxWidth(50);
            tabla_cli.getColumnModel().getColumn(1).setMinWidth(130);
            tabla_cli.getColumnModel().getColumn(1).setMaxWidth(130);
            tabla_cli.getColumnModel().getColumn(2).setMinWidth(100);
            tabla_cli.getColumnModel().getColumn(2).setMaxWidth(100);
            tabla_cli.getColumnModel().getColumn(3).setMinWidth(100);
            tabla_cli.getColumnModel().getColumn(3).setMaxWidth(100);
            tabla_cli.getColumnModel().getColumn(4).setMinWidth(100);
            tabla_cli.getColumnModel().getColumn(4).setMaxWidth(100);
            tabla_cli.getColumnModel().getColumn(5).setMinWidth(95);
            tabla_cli.getColumnModel().getColumn(5).setMaxWidth(95);
            tabla_cli.getColumnModel().getColumn(6).setMinWidth(90);
            tabla_cli.getColumnModel().getColumn(6).setMaxWidth(90);
            tabla_cli.getColumnModel().getColumn(7).setMinWidth(95);
            tabla_cli.getColumnModel().getColumn(7).setMaxWidth(95);
            tabla_cli.getColumnModel().getColumn(8).setMinWidth(90);
            tabla_cli.getColumnModel().getColumn(8).setMaxWidth(90);
            tabla_cli.getColumnModel().getColumn(9).setMinWidth(90);
            tabla_cli.getColumnModel().getColumn(9).setMaxWidth(90);
            tabla_cli.getColumnModel().getColumn(10).setMinWidth(120);
            tabla_cli.getColumnModel().getColumn(10).setMaxWidth(120);
            tabla_cli.getColumnModel().getColumn(11).setMinWidth(120);
            tabla_cli.getColumnModel().getColumn(11).setMaxWidth(120);
            tabla_cli.getColumnModel().getColumn(12).setMinWidth(90);
            tabla_cli.getColumnModel().getColumn(12).setMaxWidth(90);
            tabla_cli.getColumnModel().getColumn(13).setMinWidth(100);
            tabla_cli.getColumnModel().getColumn(13).setMaxWidth(100);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1450, 250));

        buscar_cliente.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        buscar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_clienteActionPerformed(evt);
            }
        });
        buscar_cliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar_clienteKeyReleased(evt);
            }
        });
        jPanel1.add(buscar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 1190, 30));

        contador_txt.setEditable(false);
        contador_txt.setBackground(new java.awt.Color(0, 0, 0));
        contador_txt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        contador_txt.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(contador_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 70, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_cliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_cliMouseClicked
           int fila = tabla_cli.getSelectedRow();
        if (fila>=0){
             if(modelo.equals("factura")){
                   facturacion.idcliente.setText(tabla_cli.getValueAt(fila, 0).toString());
                   facturacion.cliente.setText(tabla_cli.getValueAt(fila, 2).toString());
                   facturacion.rnc.setText(tabla_cli.getValueAt(fila, 4).toString());
                   
                   if(tabla_cli.getValueAt(fila, 14).equals("0")){
                    facturacion.consumidor.setSelected(true);
                   facturacion.mayor.setSelected(false);
               }else{
                    facturacion.consumidor.setSelected(false);
                   facturacion.mayor.setSelected(true);
               }
                   
              }else{
                   Clientes_mantenimiento.idcliente.setText( tabla_cli.getValueAt(fila, 0).toString());
               Clientes_mantenimiento.cedula.setText( tabla_cli.getValueAt(fila, 1).toString());
             Clientes_mantenimiento.nombre.setText(tabla_cli.getValueAt(fila, 2).toString());
             Clientes_mantenimiento.apellido.setText(tabla_cli.getValueAt(fila, 3).toString());
             Clientes_mantenimiento.rnc.setText(tabla_cli.getValueAt(fila, 4).toString());
             Clientes_mantenimiento.estado.setSelectedItem(tabla_cli.getValueAt(fila, 5).toString());
             Clientes_mantenimiento.limite_credito.setText(tabla_cli.getValueAt(fila, 6).toString());
              Clientes_mantenimiento.calle.setText(tabla_cli.getValueAt(fila, 7).toString());
              Clientes_mantenimiento.sector.setText(tabla_cli.getValueAt(fila, 8).toString());
              Clientes_mantenimiento.ciudad.setText(tabla_cli.getValueAt(fila, 9).toString());
              Clientes_mantenimiento.celular.setText(tabla_cli.getValueAt(fila, 10).toString());
              Clientes_mantenimiento.residencial.setText(tabla_cli.getValueAt(fila, 11).toString());
               Clientes_mantenimiento.razon_social.setText(tabla_cli.getValueAt(fila, 12).toString());

              Date fecha = new Date(tabla_cli.getValueAt(fila, 13).toString().split("/")[1]+"/"+tabla_cli.getValueAt(fila, 13).toString().split("/")[2]+"/"+tabla_cli.getValueAt(fila, 13).toString().split("/")[0]);
               Clientes_mantenimiento.txt_fecha.setDate(fecha);
               if(tabla_cli.getValueAt(fila, 14).equals("0")){
                    Clientes_mantenimiento.consumidor.setSelected(true);
                   Clientes_mantenimiento.mayor.setSelected(false);
               }else{
                    Clientes_mantenimiento.consumidor.setSelected(false);
                   Clientes_mantenimiento.mayor.setSelected(true);
               }
              }
             
             this.dispose();
        }
                      
       
    }//GEN-LAST:event_tabla_cliMouseClicked

    
    
    private void buscar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar_clienteActionPerformed

    private void buscar_clienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar_clienteKeyReleased
        filtrar(buscar_cliente.getText());
    }//GEN-LAST:event_buscar_clienteKeyReleased

    
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
            java.util.logging.Logger.getLogger(buscar_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscar_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscar_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscar_clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new buscar_clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar_cliente;
    private javax.swing.JTextField contador_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_cli;
    // End of variables declaration//GEN-END:variables

    String cod = "";
    
    conectar con = new conectar();
      Connection cn = con.connection();


}
