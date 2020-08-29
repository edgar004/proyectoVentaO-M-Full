
package formularios;


import conectar.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

 class login extends javax.swing.JFrame {

    
    public login() {
        initComponents();
           this.setLocationRelativeTo(null);
    }

    void inicio_sesion(){
        
        String user, pass;
        user = name.getText();
        pass = new String(password.getPassword());
        
        Statement stmt;
        ResultSet rs;
        String[] registros = new String[6];
        try {
            stmt = cn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM registro_usuario WHERE (usuario='"+user+"' and password =md5('"+pass+"'))");
           
            if (rs!=null){
            if (rs.next()){
                registros[1] = rs.getString("idempleado");
                
                    Statement stmtEmpleado=cn.createStatement();
                 ResultSet rsEmpleado=stmtEmpleado.executeQuery("SELECT estado, nombre, apellido FROM empleado WHERE (idempleado='"+rs.getString("idempleado")+"')");
                  
                   if (rsEmpleado.next()){
                 if(rsEmpleado.getString("estado").equals("INACTIVO")){
                          JOptionPane.showMessageDialog(null, "ACTUALMENTE NO SE ENCUENRA ACTIVO DEBE COMUNICARSE CON EL ADMINISTRADOR");
                               name.setText("");
                          password.setText("");
                           name.requestFocus();
                          return;
                    }
                 
                Statement stmtRoles=cn.createStatement();

                 ResultSet rsRoles=stmtRoles.executeQuery("SELECT acceso,crear,modificar,borrar,permisos_usuarios.aplicaciones_modulos FROM roles_usuario inner join permisos_usuarios on permisos_usuarios.id_permiso_usuario =roles_usuario.id_permiso_usuario  WHERE (id_usuario='"+rs.getString("idempleado")+"')");
                     
                 
                 
                   Menu menu=new Menu();
                     temporal global = new temporal();
                     
                    try{
                        int contador=0;
                        while(rsRoles.next()){
                            temporal.privilegiosUser[contador].crear=rsRoles.getInt("crear");
                            temporal.privilegiosUser[contador].modificar=rsRoles.getInt("modificar");
                            temporal.privilegiosUser[contador].borrar=rsRoles.getInt("borrar");

                            switch (rsRoles.getString("aplicaciones_modulos")) {
                                case "Clientes":
                                    if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuCliente.setEnabled(false);
                                    }   break;
                                case "Empleados":
                                    if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuEmpleado.setEnabled(false);
                                    }   break;
                                case "Proveedor":
                                    if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuProveedor.setEnabled(false);
                                    }   break;
                                case "Usuarios":
                                    if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuUsuario.setEnabled(false);
                                    }   break;
                                case "Articulos":
                                    if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuArticulo.setEnabled(false);
                                    }   break;
                                case "Factura":
                                    if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuFactura.setEnabled(false);
                                    }   break;
                                     case "Consulta venta":
                                    if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuConsultaVenta.setEnabled(false);
                                    } 
                                     break;
                                     case "Consulta compra":
                                    if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuConsultaCompras.setEnabled(false);
                                    }
                                     break;
                                     case "Compra":
                                      if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuCompra.setEnabled(false);
                                    }
                                     break;
                                      case "Inventario":
                                      if(rsRoles.getString("acceso").equals("0")){
                                        menu.menuInventario.setEnabled(false);
                                    }
                                     break;
                                      case "Cuadre caja":
                                      if(rsRoles.getString("acceso").equals("0")){
                                        menu.jMenuItem12.setEnabled(false);
                                    }
                                     break;
                                      case "Cuenta Cobrar":
                                      if(rsRoles.getString("acceso").equals("0")){
                                        menu.jMenuItem13.setEnabled(false);
                                    }
                                     break;
                                       case "Cuanta Pagar":
                                      if(rsRoles.getString("acceso").equals("0")){
                                        menu.jMenuItem15.setEnabled(false);
                                    }
                                     break;
                                       case "Cuenta Cuadre":
                                      if(rsRoles.getString("acceso").equals("0")){
                                        menu.jMenuItem5.setEnabled(false);
                                    }
                                     break;
                                default:
                                    break;
                            }
                            contador++;
                        }
                    }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e.toString());
                    }
                               
                            idusuario = registros[1];
                  name.setText("");
                password.setText("");
                name.requestFocus();
                           
                    global.setTexto(idusuario.trim());
                     global.setNombre(rsEmpleado.getString("nombre")+" " + rsEmpleado.getString("apellido"));// el trim para eliminar los espacios en blanco sobrantes
                     menu.usuario_logeando.setText("Online: " + rsEmpleado.getString("nombre")+" " + rsEmpleado.getString("apellido"));
                      menu.setVisible(true);
                      menu.setLocationRelativeTo(null); 
              
            }else{
                JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTA"+"\n" + "Intente de Nuevo");
                name.setText("");
                password.setText("");
                name.requestFocus();
            }
            }   
            }
            
        } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e.toString());
        }
        
    
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        name = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INICIAR SESION");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        jPanel2.add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 244, -1));

        password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel2.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 244, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1486503785-authorisation-lock-padlock-password-privacy-safe-security_81267.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, -1));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\garrapata.png")); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 100, 36));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\cruzar.png")); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 110, 36));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bootloader_users_person_people_6080.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 23, -1, -1));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\usuario.png")); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 150, 140));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (name.getText().equals("")){
            JOptionPane.showMessageDialog(null, "INGRESE EL USUARIO");
            name.requestFocus();
            return;
        }
        
         if (password.getText().equals("")){
            JOptionPane.showMessageDialog(null, "INGRESE LA CONTRASEÑA");
            password.requestFocus();
            return;
        }
        
        inicio_sesion();        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
password.requestFocus();     
    }//GEN-LAST:event_nameActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
if (name.getText().equals("")){
            JOptionPane.showMessageDialog(null, "INGRESE EL USUARIO");
            name.requestFocus();
            return;
        }
        
         if (password.getText().equals("")){
            JOptionPane.showMessageDialog(null, "INGRESE LA CONTRASEÑA");
            password.requestFocus();
            return;
        }
        
        inicio_sesion();        
    }//GEN-LAST:event_passwordActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.dispose();        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField name;
    private javax.swing.JPasswordField password;
    // End of variables declaration//GEN-END:variables
String idusuario = "";
  conectar con = new conectar();
      Connection cn = con.connection();

}
