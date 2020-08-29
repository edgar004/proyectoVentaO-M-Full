package formularios;

import conectar.conectar;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
//import vista.panelesmenu.Inicio;
//import util.Controles;
//import util.Parametros;

public class Menu extends javax.swing.JFrame {

    public Menu() {
        String id_usu;
      
        initComponents();
        this.setExtendedState(this.MAXIMIZED_BOTH);
        sacar_texto();

    }

    private void sacar_texto() {
        temporal global = new temporal();
        idusuario = global.getTexto();
        System.out.println(idusuario);
        cajero();

    }

    void cajero() {

        String[] registros = new String[8];
        String sql = "SELECT nombre, apellido FROM empleado where idempleado='" + idusuario + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("nombre");
                registros[1] = rs.getString("apellido");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn_base = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        usuario_logeando = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuCliente = new javax.swing.JMenuItem();
        menuEmpleado = new javax.swing.JMenuItem();
        menuProveedor = new javax.swing.JMenuItem();
        menuUsuario = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        menuArticulo = new javax.swing.JMenuItem();
        menuCompra = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuFactura = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuConsultaVenta = new javax.swing.JMenuItem();
        menuConsultaCompras = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        menuInventario = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MENU PRINCIPAL");

        pn_base.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\Menu.jpg")); // NOI18N
        jLabel1.setText("jLabel1");

        usuario_logeando.setBackground(new java.awt.Color(255, 255, 255));
        usuario_logeando.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        usuario_logeando.setForeground(new java.awt.Color(0, 255, 0));
        usuario_logeando.setText("Oline");

        javax.swing.GroupLayout pn_baseLayout = new javax.swing.GroupLayout(pn_base);
        pn_base.setLayout(pn_baseLayout);
        pn_baseLayout.setHorizontalGroup(
            pn_baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pn_baseLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pn_baseLayout.createSequentialGroup()
                        .addGap(1083, 1083, 1083)
                        .addComponent(usuario_logeando, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pn_baseLayout.setVerticalGroup(
            pn_baseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pn_baseLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuario_logeando, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jMenuBar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jMenu1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\registro.png")); // NOI18N
        jMenu1.setText("REGISTROS");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        menuCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/business_application_addmale_useradd_insert_add_user_client_2312.png"))); // NOI18N
        menuCliente.setText("CLIENTES");
        menuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClienteActionPerformed(evt);
            }
        });
        jMenu1.add(menuCliente);

        menuEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new_add_user_16734.png"))); // NOI18N
        menuEmpleado.setText("EMPLEADOS");
        menuEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEmpleadoActionPerformed(evt);
            }
        });
        jMenu1.add(menuEmpleado);

        menuProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/shop_icon-icons.com_51010.png"))); // NOI18N
        menuProveedor.setText("PROVEEDOR");
        menuProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProveedorActionPerformed(evt);
            }
        });
        jMenu1.add(menuProveedor);

        menuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        menuUsuario.setText("USUARIOS");
        menuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(menuUsuario);

        jMenuBar1.add(jMenu1);

        jMenu6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jMenu6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\mantenimiento1.png")); // NOI18N
        jMenu6.setText("MANTENIMIENTO");
        jMenu6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jMenu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu6ActionPerformed(evt);
            }
        });

        menuArticulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/shopping-basket-add_40507.png"))); // NOI18N
        menuArticulo.setText(" REGISTRO ARTICULOS");
        menuArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuArticuloActionPerformed(evt);
            }
        });
        jMenu6.add(menuArticulo);

        menuCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/shoppingcart_compra_12833.png"))); // NOI18N
        menuCompra.setText("COMPRA");
        menuCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCompraActionPerformed(evt);
            }
        });
        jMenu6.add(menuCompra);

        jMenuBar1.add(jMenu6);

        jMenu2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\vender.png")); // NOI18N
        jMenu2.setText("VENTA");
        jMenu2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jMenu2.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenu2MenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        menuFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/factur.png"))); // NOI18N
        menuFactura.setText("FACTURA");
        menuFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFacturaActionPerformed(evt);
            }
        });
        jMenu2.add(menuFactura);

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/caja.png"))); // NOI18N
        jMenuItem12.setText("CUADRE DE CAJA");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuBar1.add(jMenu2);

        jMenu8.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\cuentas.png")); // NOI18N
        jMenu8.setText("CUENTAS");
        jMenu8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jMenu8.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenu8MenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenu8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu8ActionPerformed(evt);
            }
        });

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/credit_card_22167.png"))); // NOI18N
        jMenuItem13.setText("CUENTAS POR COBRAR");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem13);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Payment-card_25388.png"))); // NOI18N
        jMenuItem15.setText("CUENTAS POR PAGAR");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem15);

        jMenuBar1.add(jMenu8);

        jMenu3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jMenu3.setText("CONSULTA");
        jMenu3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        menuConsultaVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ganancias.png"))); // NOI18N
        menuConsultaVenta.setText("VENTAS");
        menuConsultaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultaVentaActionPerformed(evt);
            }
        });
        jMenu3.add(menuConsultaVenta);

        menuConsultaCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprasreal.png"))); // NOI18N
        menuConsultaCompras.setText("COMPRAS REALIZADAS");
        menuConsultaCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultaComprasActionPerformed(evt);
            }
        });
        jMenu3.add(menuConsultaCompras);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/sumar.png"))); // NOI18N
        jMenuItem5.setText("CUADRES");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseClicked(evt);
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        menuInventario.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\inventario (1).png")); // NOI18N
        menuInventario.setText("INVENTARIO");
        menuInventario.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/productos.png"))); // NOI18N
        jMenuItem8.setText("INVENTARIO");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuInventario.add(jMenuItem8);

        jMenuBar1.add(menuInventario);

        jMenu4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\salir.png")); // NOI18N
        jMenu4.setText("SALIR");
        jMenu4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pn_base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        this.dispose();

        facturacion windows = new facturacion();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu2MenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenu2MenuKeyPressed
        this.dispose();

        facturacion windows = new facturacion();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenu2MenuKeyPressed

    private void menuEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmpleadoActionPerformed
        empleado_1 windows = new empleado_1();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuEmpleadoActionPerformed

    private void menuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsuarioActionPerformed
        Registro_usuarios windows = new Registro_usuarios();
        windows.setVisible(true);
        //windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuUsuarioActionPerformed

    private void menuFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFacturaActionPerformed
//  this.dispose();
        facturacion windows = new facturacion();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuFacturaActionPerformed

    private void menuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClienteActionPerformed
        Clientes_mantenimiento windows = new Clientes_mantenimiento();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuClienteActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        inventario windows = new inventario();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void menuArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuArticuloActionPerformed
        crear_articulo windows = new crear_articulo();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuArticuloActionPerformed

    private void jMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu6ActionPerformed

    }//GEN-LAST:event_jMenu6ActionPerformed

    private void menuCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCompraActionPerformed
        compra windows = new compra();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuCompraActionPerformed

    private void menuProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProveedorActionPerformed
        proveedor windows = new proveedor();
        windows.setVisible(true);
        //windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuProveedorActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        factura_clientes windows = new factura_clientes();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        cuenta_pagar windows = new cuenta_pagar();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenu8MenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenu8MenuKeyPressed

    }//GEN-LAST:event_jMenu8MenuKeyPressed

    private void jMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu8ActionPerformed

    }//GEN-LAST:event_jMenu8ActionPerformed

    private void menuConsultaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultaVentaActionPerformed
        ganancias windows = new ganancias();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuConsultaVentaActionPerformed

    private void menuConsultaComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultaComprasActionPerformed
        compras_realizadas windows = new compras_realizadas();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_menuConsultaComprasActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        cuadre_caja windows = new cuadre_caja();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);    // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
       Cuadre obj;
     obj = new Cuadre(new javax.swing.JFrame(), true);
     obj.setVisible(true);          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseClicked
           // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
       int confirmar = JOptionPane.showConfirmDialog(null, "ESTA SEGURO QUE QUIERE SALIR ?!", "ATENCION !!!!", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == confirmar) {
         System.exit(0);
        login windows = new login();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
           //  System.exit(0);
            //return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MouseClicked

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem12;
    public javax.swing.JMenuItem jMenuItem13;
    public javax.swing.JMenuItem jMenuItem15;
    public javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem8;
    public javax.swing.JMenuItem menuArticulo;
    public javax.swing.JMenuItem menuCliente;
    public javax.swing.JMenuItem menuCompra;
    public javax.swing.JMenuItem menuConsultaCompras;
    public javax.swing.JMenuItem menuConsultaVenta;
    public javax.swing.JMenuItem menuEmpleado;
    public javax.swing.JMenuItem menuFactura;
    public javax.swing.JMenu menuInventario;
    public javax.swing.JMenuItem menuProveedor;
    public javax.swing.JMenuItem menuUsuario;
    private javax.swing.JPanel pn_base;
    public javax.swing.JLabel usuario_logeando;
    // End of variables declaration//GEN-END:variables
String idusuario = "";
    conectar con = new conectar();
    Connection cn = con.connection();
}
