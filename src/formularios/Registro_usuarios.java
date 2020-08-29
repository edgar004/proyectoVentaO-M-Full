package formularios;

import conectar.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Registro_usuarios extends javax.swing.JFrame {

    public Registro_usuarios() {
        initComponents();
             crud_usuario = new clases.crud_usuario();
         carga_tabla_permisos_suarios= new clases.carga_tab_permisos_usuarios(this);
           addWindowListener(carga_tabla_permisos_suarios);
           txtfiltro_permiso.addKeyListener(new clases.filtra_tab_permiso_usuario(this));
           jtable_permiso.addMouseListener(new clases.evento_mouse_tab_permiso_usu(this));
     
        nombre.requestFocus();
        this.setLocationRelativeTo(null);
    }
 
    
    void validacion() {

        String[] registros = new String[8];
        String sql = "SELECT e.nombre,e.apellido,r.usuario FROM registro_usuario as r, empleado as e where r.idempleado='" + idempleado.getText() + "' and e.idempleado = '" + idempleado.getText() + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                nombre.setText("");
                password.setText("");
                password1.setText("");
                usuario.setText("");

                registros[0] = rs.getString("nombre");
                registros[1] = rs.getString("apellido");
                registros[2] = rs.getString("usuario");
                JOptionPane.showMessageDialog(null, "EL USUARIO " + registros[0] + " " + registros[1] + " " + "TIENE UN USUARIO COMO " + registros[2]);
                usuario.requestFocus();
                return;
            } else {
                guardar();
                limpiar();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void limpiar() {
       carga_tabla_permisos_suarios.carga_tab_permiso_usuario();

           
        idempleado.setText("");
        nombre.setText("");
        usuario.setText("");
        password.setText("");
        password1.setText("");
        idempleado.requestFocus();
        usuario.setEnabled(false);
        password.setEnabled(false);
        password1.setEnabled(false);
        guardar_boton.setEnabled(false);
        boton_modificar.setEnabled(false);
        
    }

   void guardar(){
   
   
   
       try {
           String sql125= "";
           sql125 = "INSERT INTO registro_usuario (idempleado,nombre,tipo,usuario,password) VALUES('"+idempleado.getText()+"','"+nombre.getText()+"','"+combo.getSelectedItem()+"','"+usuario.getText()+"',md5('"+password.getText()+"'))";
                  
                  
                  
           PreparedStatement psk22 = cn.prepareStatement(sql125);
           
             int n ;
             n = psk22.executeUpdate();
             if (n > 0){
                 JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
                 
                     boolean acceso = false, crear = false, modificar = false, borrar = false, anular = false, imprimir = false, exportar = false;
            int id_permiso_usuario = 0, id_usuario = 0;
            String permiso_usuario = "";
            id_usuario = Integer.parseInt(idempleado.getText());
            for (int i = 0; i < jtable_permiso.getRowCount(); i++) {
                permiso_usuario = jtable_permiso.getValueAt(i, 0).toString();
                id_permiso_usuario = crud_usuario.busca_id_permisos(permiso_usuario);
                acceso = Boolean.parseBoolean(jtable_permiso.getValueAt(i, 1).toString());
                crear = Boolean.parseBoolean(jtable_permiso.getValueAt(i, 2).toString());
                modificar = Boolean.parseBoolean(jtable_permiso.getValueAt(i, 3).toString());
                borrar = Boolean.parseBoolean(jtable_permiso.getValueAt(i, 4).toString());
               // anular = Boolean.parseBoolean(jtable_permiso.getValueAt(i, 4).toString());
               // imprimir = Boolean.parseBoolean(jtable_permiso.getValueAt(i, 5).toString());
               // exportar = Boolean.parseBoolean(jtable_permiso.getValueAt(i, 6).toString());
                crud_usuario.inserta_role_usuarios(id_permiso_usuario, id_usuario, acceso, crear, modificar, borrar, anular, imprimir, exportar);

            }
            carga_tabla_permisos_suarios.limpiar_permiso();
             }
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
           JOptionPane.showMessageDialog(null, "ERROR EN GUARDAR DATOS ","NO SE PUEDE REGISTRAR",JOptionPane.ERROR_MESSAGE);
       }
       
       
   
        
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idempleado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        usuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        combo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        guardar_boton = new javax.swing.JButton();
        boton_modificar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtfiltro_permiso = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtable_permiso = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRAR USUARIOS");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("CODIGO");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 14, -1, 40));

        idempleado.setEditable(false);
        idempleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        idempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idempleadoActionPerformed(evt);
            }
        });
        jPanel2.add(idempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 22, 260, 25));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("NOMBRE ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 54, -1, 30));

        nombre.setEditable(false);
        nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nombre.setEnabled(false);
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 54, 260, 25));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("USUARIO");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(434, 23, -1, 25));

        usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuario.setEnabled(false);
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });
        usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuarioKeyTyped(evt);
            }
        });
        jPanel2.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 22, 229, 25));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("CONTRASEÑA");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 54, -1, 23));

        password.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        password.setEnabled(false);
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel2.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 54, 229, 25));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("CONFIRMAR CONTRASEÑA");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 83, -1, 30));

        password1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        password1.setEnabled(false);
        password1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password1ActionPerformed(evt);
            }
        });
        jPanel2.add(password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 83, 230, 25));

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jButton4.setText("BUSCAR EMPLEADO");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 160, 40));

        combo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE...", "Administrador", "Empleado", "Supervisor" }));
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });
        jPanel2.add(combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 86, 260, 25));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("CARGO");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 83, -1, 30));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 230, 25));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("ESTADO");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 110, 50, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 33, 737, 180));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\mujer (2).png")); // NOI18N
        jLabel10.setText("REGISTRO DE USUARIO");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 8, -1, 23));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(968, 2, -1, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        guardar_boton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        guardar_boton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\salvar.png")); // NOI18N
        guardar_boton.setText("Guardar");
        guardar_boton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        guardar_boton.setEnabled(false);
        guardar_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_botonActionPerformed(evt);
            }
        });

        boton_modificar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        boton_modificar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\modifi.png")); // NOI18N
        boton_modificar.setText("Modificar");
        boton_modificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_modificar.setEnabled(false);
        boton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_modificarActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\anadir (1).png")); // NOI18N
        jButton6.setText("Nuevo");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\salida.png")); // NOI18N
        jButton3.setText("Salir");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guardar_boton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(boton_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardar_boton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 216, -1, 60));

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jButton5.setText("BUSCAR USUARIOS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(753, 215, 210, 60));

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\usuario.png")); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 20, -1, 170));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 980, 290));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtfiltro_permiso.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtfiltro_permiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfiltro_permisoActionPerformed(evt);
            }
        });
        jPanel4.add(txtfiltro_permiso, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 690, 30));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 25)); // NOI18N
        jLabel11.setText("Modulos/aplicaciones:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 260, -1));

        jtable_permiso = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex,int colIndex){
                if(colIndex == 0 ){
                    return false;

                }
                return true;
            }
        };
        jtable_permiso.setBackground(new java.awt.Color(153, 153, 153));
        jtable_permiso.setFont(new java.awt.Font("Arial", 0, 25)); // NOI18N
        jtable_permiso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtable_permiso.setRowHeight(35);
        jtable_permiso.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jtable_permiso.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jtable_permiso);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 960, 310));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 980, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        usuario.requestFocus();
    }//GEN-LAST:event_formWindowActivated

    private void txtfiltro_permisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfiltro_permisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfiltro_permisoActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         buscarUsuarios window = new buscarUsuarios();
        window.setVisible(true);
        activar_campos();
        guardar_boton.setEnabled(false);
         if(temporal.privilegiosUser[5].modificar==1)boton_modificar.setEnabled(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        limpiar();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void boton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_modificarActionPerformed

        if (idempleado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CODIGO ESTA EN BLANCO");
            idempleado.requestFocus();
            return;
        }
        if (nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL NOMBRE");
            nombre.requestFocus();
            return;
        }
        if (usuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN USUARIO");
            usuario.requestFocus();
            return;
        }

        if (password.getText().endsWith(password1.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            return;
        }

        try {
            String pass = new String(password.getPassword());

            PreparedStatement psU;

            if (pass.equals("")) {
                psU = cn.prepareStatement("UPDATE registro_usuario SET tipo='" + combo.getSelectedItem() + "',usuario='" + usuario.getText()
                    + "' where idempleado='" + idempleado.getText() + "'");

            } else {
                psU = cn.prepareStatement("UPDATE registro_usuario SET tipo='" + combo.getSelectedItem() + "',usuario='" + usuario.getText() + "', password=md5('" + pass + "')"
                    + " where idempleado='" + idempleado.getText() + "'");

            }

            psU.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(Clientes_mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        carga_tabla_permisos_suarios.limpiar_permiso();
        limpiar();
    }//GEN-LAST:event_boton_modificarActionPerformed

    private void guardar_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_botonActionPerformed
        if (idempleado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CODIGO ESTA EN BLANCO");
            idempleado.requestFocus();
            return;
        }
        if (nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL NOMBRE");
            nombre.requestFocus();
            return;
        }
        if (usuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UN USUARIO");
            usuario.requestFocus();
            return;
        }
        if (password.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE DE INGRESAR UNA CONTRASEÑA");
            password.requestFocus();
            return;
        }
        if (password1.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE DE CONFIRMAR SU CONTRASEÑA");
            password1.requestFocus();
            return;
        }
        if (combo.getSelectedItem().equals("SELECCIONE...")) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN CARGO");
            combo.requestFocus();
            return;
        }

        if (password.getText().endsWith(password1.getText())) {
            validacion();
        } else {
            JOptionPane.showMessageDialog(null, "LAS CONTRASEÑAS NO COINCIDEN");
            password.setText("");
            password1.setText("");
            password.requestFocus();
        }
    }//GEN-LAST:event_guardar_botonActionPerformed

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        buscar_empleados windows = new buscar_empleados("usuarios");
        usuario.setEnabled(true);
        password.setEnabled(true);
        password1.setEnabled(true);
        windows.setVisible(true);
        guardar_boton.setEnabled(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void password1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password1ActionPerformed

    }//GEN-LAST:event_password1ActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        password1.requestFocus();
    }//GEN-LAST:event_passwordActionPerformed

    private void usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyTyped

    }//GEN-LAST:event_usuarioKeyTyped

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
        password.requestFocus();
    }//GEN-LAST:event_usuarioActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        usuario.requestFocus();
    }//GEN-LAST:event_nombreActionPerformed

    private void idempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idempleadoActionPerformed
        nombre.requestFocus();
    }//GEN-LAST:event_idempleadoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed
    void activar_campos() {
        usuario.setEnabled(true);
        password.setEnabled(true);
        password1.setEnabled(true);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro_usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton boton_modificar;
    public static javax.swing.JComboBox<String> combo;
    private javax.swing.JButton guardar_boton;
    public static javax.swing.JTextField idempleado;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    public static javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private static javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTable jtable_permiso;
    public static javax.swing.JTextField nombre;
    public static javax.swing.JPasswordField password;
    public static javax.swing.JPasswordField password1;
    public static javax.swing.JTextField txtfiltro_permiso;
    public static javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
 conectar con = new conectar();
    Connection cn = con.connection();
private clases.carga_tab_permisos_usuarios carga_tabla_permisos_suarios;
private clases.crud_usuario crud_usuario;
}
