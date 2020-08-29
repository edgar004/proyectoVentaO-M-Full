
package formularios;



import conectar.conectar;
import formularios.empleado_1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class buscar_empleados extends javax.swing.JFrame {

   public  String modulo="empleado";
    public buscar_empleados() {
        initComponents();
        cargar();
      
        this.setLocationRelativeTo(null);
    }
    
       public buscar_empleados(String modulo) {
          this.modulo=modulo;
        initComponents();
        cargar();
      
        this.setLocationRelativeTo(null);
    }
    
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        buscar_empleado = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        contadorEmpleado = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_emp = new javax.swing.JTable();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONSULTA DE EMPLEADOS");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\equipo (3).png")); // NOI18N
        jLabel3.setText("EMPLEADOS");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jLabel1.setText("BUSCAR");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 100, 30));

        buscar_empleado.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        buscar_empleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar_empleadoKeyReleased(evt);
            }
        });
        jPanel1.add(buscar_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 890, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("REGISTROS");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 60, 80, 30));

        contadorEmpleado.setEditable(false);
        contadorEmpleado.setBackground(new java.awt.Color(0, 0, 0));
        contadorEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        contadorEmpleado.setForeground(new java.awt.Color(255, 51, 51));
        contadorEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contadorEmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(contadorEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 60, 90, 30));

        tabla_emp.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tabla_emp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido", "Cedula", "Credito", "Calle", "Sector", "Ciudad", "Feche nac.", "Ingreso", "Celular", "Residencial", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_emp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_empMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_emp);
        if (tabla_emp.getColumnModel().getColumnCount() > 0) {
            tabla_emp.getColumnModel().getColumn(1).setMinWidth(100);
            tabla_emp.getColumnModel().getColumn(1).setMaxWidth(100);
            tabla_emp.getColumnModel().getColumn(2).setMinWidth(100);
            tabla_emp.getColumnModel().getColumn(2).setMaxWidth(100);
            tabla_emp.getColumnModel().getColumn(8).setMinWidth(100);
            tabla_emp.getColumnModel().getColumn(8).setMaxWidth(100);
            tabla_emp.getColumnModel().getColumn(9).setMinWidth(100);
            tabla_emp.getColumnModel().getColumn(9).setMaxWidth(100);
            tabla_emp.getColumnModel().getColumn(10).setMinWidth(130);
            tabla_emp.getColumnModel().getColumn(10).setMaxWidth(130);
            tabla_emp.getColumnModel().getColumn(11).setMinWidth(130);
            tabla_emp.getColumnModel().getColumn(11).setMaxWidth(130);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 90, 1200, 250));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
void cargar(){
    int contador=0;
       DefaultTableModel modelo2 = (DefaultTableModel)tabla_emp.getModel();
     modelo2.getDataVector().clear();
        String [] registros = new String [13];
        String sql ="";
        if(modulo.equals("usuarios")){
            sql="SELECT * FROM empleado where estado='ACTIVO'";
        }else{
            sql="SELECT * FROM empleado ";
        }
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                contador++;
                registros[0]=rs.getString("idempleado");
                registros[1]=rs.getString("nombre");
                registros[2]=rs.getString("apellido");
                registros[3]=rs.getString("cedula");
                registros[4]=rs.getString("limite_credito");
                registros[5]=rs.getString("calle");
                registros[6]=rs.getString("sector");
                registros[7]=rs.getString("ciudad");
                
                 String formato_fecha = rs.getString("fecha_nacimiento").split("-")[2]+"/"+rs.getString("fecha_nacimiento").split("-")[1]+"/"+rs.getString("fecha_nacimiento").split("-")[0];
                registros[8]=formato_fecha;
                
                 String formato_fechaIngreos = rs.getString("fecha_ingreso").split("-")[2]+"/"+rs.getString("fecha_ingreso").split("-")[1]+"/"+rs.getString("fecha_ingreso").split("-")[0];
                registros[9]=formato_fechaIngreos;
                
                 String sqlCelular ="SELECT * FROM tabla_empleado where idempleado='"+registros[0]+"' and id_tipo_tel=1";
                 Statement stCelular  = cn.createStatement();
                ResultSet rsCelular  = stCelular .executeQuery(sqlCelular);
                
                if(rsCelular.next()){
                    registros[10]=rsCelular.getString("numero");
                }
                
                 String sqlResidencial ="SELECT * FROM tabla_empleado where idempleado='"+registros[0]+"' and id_tipo_tel=2";
                 Statement sResidencial  = cn.createStatement();
                ResultSet rsResidencial  = sResidencial.executeQuery(sqlResidencial);
                
                if(rsResidencial.next()){
                    registros[11]=rsResidencial.getString("numero");
                }
                
                registros[12]=rs.getString("estado");
               
                
                
                modelo2.addRow(registros);
               
            }
          tabla_emp.setModel(modelo2); 
          contadorEmpleado.setText(String.valueOf(contador));
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void tabla_empMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_empMouseClicked
        int fila = tabla_emp.getSelectedRow();
        if (fila>=0){
            
            if(modulo.equals("usuarios")){
                 Registro_usuarios.idempleado.setText(tabla_emp.getValueAt(fila, 0).toString());
                 Registro_usuarios.nombre.setText(tabla_emp.getValueAt(fila, 1).toString());
            }else{
                empleado_1.idempleado.setText(tabla_emp.getValueAt(fila, 0).toString());
              empleado_1.nombre.setText(tabla_emp.getValueAt(fila, 1).toString());
             empleado_1.apellido.setText(tabla_emp.getValueAt(fila, 2).toString());
             empleado_1.cedula.setText(tabla_emp.getValueAt(fila, 3).toString());
             empleado_1.limite_credito.setText(tabla_emp.getValueAt(fila, 4).toString());
            empleado_1.calle.setText(tabla_emp.getValueAt(fila, 5).toString());
            empleado_1.sector.setText(tabla_emp.getValueAt(fila, 6).toString());
            empleado_1.ciudad.setText(tabla_emp.getValueAt(fila, 7).toString());
           
            Date fechaNac = new Date(tabla_emp.getValueAt(fila, 8).toString().split("/")[1]+"/"+tabla_emp.getValueAt(fila, 8).toString().split("/")[2]+"/"+tabla_emp.getValueAt(fila, 8).toString().split("/")[0]); 
            empleado_1.fecha_nac.setDate(fechaNac);
           
           Date fechaIngreso = new Date(tabla_emp.getValueAt(fila, 9).toString().split("/")[1]+"/"+tabla_emp.getValueAt(fila, 9).toString().split("/")[2]+"/"+tabla_emp.getValueAt(fila, 9).toString().split("/")[0]); 
            empleado_1.fecha_ingreso.setDate(fechaIngreso);
           
            empleado_1.celular.setText(tabla_emp.getValueAt(fila, 10).toString());
            empleado_1.residencial.setText(tabla_emp.getValueAt(fila, 11).toString());
            empleado_1.estado.setSelectedItem(tabla_emp.getValueAt(fila,12).toString());
              if(temporal.privilegiosUser[3].modificar==1){
              empleado_1.jButton4.setEnabled(true);
         }
            }
             
             this.dispose();
        }

         

    }//GEN-LAST:event_tabla_empMouseClicked

    private void buscar_empleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar_empleadoKeyReleased
        filtrar(buscar_empleado.getText());
    }//GEN-LAST:event_buscar_empleadoKeyReleased
void filtrar(String valor){
         DefaultTableModel modelo2 = (DefaultTableModel)tabla_emp.getModel();
         modelo2.setNumRows(0);
        String [] registros = new String [13];
        String sql ="SELECT * FROM empleado  where CONCAT (idempleado,'',nombre,'',apellido,'',cedula)LIKE '%"+valor+"%' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
               registros[0]=rs.getString("idempleado");
                registros[1]=rs.getString("nombre");
                registros[2]=rs.getString("apellido");
                registros[3]=rs.getString("cedula");
                registros[4]=rs.getString("limite_credito");
                registros[5]=rs.getString("sector");
                registros[6]=rs.getString("calle");
                registros[7]=rs.getString("ciudad");
                
                
               String formato_fecha = rs.getString("fecha_nacimiento").split("-")[2]+"/"+rs.getString("fecha_nacimiento").split("-")[1]+"/"+rs.getString("fecha_nacimiento").split("-")[0];
                registros[8]=formato_fecha;
                
                 String formato_fechaIngreos = rs.getString("fecha_ingreso").split("-")[2]+"/"+rs.getString("fecha_ingreso").split("-")[1]+"/"+rs.getString("fecha_ingreso").split("-")[0];
                registros[9]=formato_fechaIngreos;
                
                 String sqlCelular ="SELECT * FROM tabla_empleado where idempleado='"+registros[0]+"' and id_tipo_tel=1";
                 Statement stCelular  = cn.createStatement();
                ResultSet rsCelular  = stCelular .executeQuery(sqlCelular);
                
                if(rsCelular.next()){
                    registros[10]=rsCelular.getString("numero");
                }
                
                 String sqlResidencial ="SELECT * FROM tabla_empleado where idempleado='"+registros[0]+"' and id_tipo_tel=2";
                 Statement sResidencial  = cn.createStatement();
                ResultSet rsResidencial  = sResidencial.executeQuery(sqlResidencial);
                
                if(rsResidencial.next()){
                    registros[11]=rsResidencial.getString("numero");
                }
                
                registros[12]=rs.getString("estado");
               
                
                
                modelo2.addRow(registros);
               
            }
          tabla_emp.setModel(modelo2);  
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void contadorEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contadorEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contadorEmpleadoActionPerformed

    
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
            java.util.logging.Logger.getLogger(buscar_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscar_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscar_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscar_empleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new buscar_empleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar_empleado;
    private javax.swing.JTextField contadorEmpleado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla_emp;
    // End of variables declaration//GEN-END:variables

    String cod = "";
    
      conectar con = new conectar();
      Connection cn = con.connection();

}
