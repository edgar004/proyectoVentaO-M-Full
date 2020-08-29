package formularios;


import conectar.conectar;
import facturacion_oym.inventario_art;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class inventario extends javax.swing.JFrame {

    public inventario() {
        initComponents();
         cargar();
         jButton2.setVisible(false);
          Timer tiempo = new Timer(100, new inventario.horas());
        tiempo.start();
          Date sistema_fecha =new Date();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
        fecha.setText(formato.format(sistema_fecha));
    }
class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date sistemahora = new Date();
            String pmma = "hh:mm:ss a";
            SimpleDateFormat format = new SimpleDateFormat(pmma);
            Calendar hoy = Calendar.getInstance();
            hora =(String.format(format.format(sistemahora), hoy));

        }
    }
void imprimir01() {
        ArrayList lista = new ArrayList();
       // lista.add("");
        for (int i = 0; i < inventario.getRowCount(); i++) {

            inventario_art mortizar = new inventario_art (inventario.getValueAt(i, 0).toString(),
                    inventario.getValueAt(i, 1).toString(), inventario.getValueAt(i, 2).toString()+"", inventario.getValueAt(i, 3).toString()+ "");

            lista.add(mortizar);

        }
        JasperReport jr = null;
        
          
        try {
            jr = (JasperReport) JRLoader.loadObjectFromFile("inventario.jasper");
           // JOptionPane.showMessageDialog(null, "EL CODIGO ES " + sub_total01+sub_total02+sub_total03);
            HashMap parametro = new HashMap();
              parametro.put("company", "PROVISIONES RAMOS AVE. 30 CABALLEROS, LOS REYES");
             parametro.put("hora", hora);
              parametro.put("fecha", fecha.getText());
               parametro.put("condicion", "INVENTARIO DE MERCANCIAS DE LA EMPRESA");
                parametro.put("saludo", "VENTAS AL POR MAYOR Y DETALLE");
          
           
            
            JasperPrint jp = JasperFillManager.fillReport(jr, parametro, new JRBeanCollectionDataSource(lista));
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
//             JasperPrint jp = JasperFillManager.fillReport(jr, parametro,new JRBeanCollectionDataSource(lista));
//             JasperViewer jv = new JasperViewer (jp, false);
//             
//             
//             JasperPrintManager jpm= JasperPrintManager.getInstance(DefaultJasperReportsContext.getInstance());
//             jpm.print(jp,false);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
        }

    }


 void filtrar(String valor) {
DefaultTableModel modelo2 = (DefaultTableModel)inventario.getModel();
     modelo2.getDataVector().clear();
      

        String[] registros = new String[6];
        String sql = "SELECT cod_art,desc_art,cant_art,pre_venta,valor_inventario FROM articulo  where CONCAT (cod_art,'',desc_art)LIKE '%"+valor+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                registros[0] = rs.getString("cod_art");
                
                registros[1] = rs.getString("desc_art");
                registros[2] = rs.getString("cant_art");
                 registros[2] = rs.getString("valor_inventario");
               
                 modelo2.addRow(registros);
               }
            
               
           inventario.setModel(modelo2);  
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }

 void cargar_fechas() {
DefaultTableModel modelo2 = (DefaultTableModel)inventario.getModel();
     modelo2.getDataVector().clear();
      

        String[] registros = new String[6];
        String sql = "SELECT cod_art,desc_art,cant_art,pre_venta FROM articulo where  ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                registros[0] = rs.getString("cod_art");
                
                registros[1] = rs.getString("desc_art");
                registros[2] = rs.getString("cant_art");
               
                 modelo2.addRow(registros);
               }
            
               
           inventario.setModel(modelo2);  
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }
     void cargar() {
DefaultTableModel modelo2 = (DefaultTableModel)inventario.getModel();
     modelo2.getDataVector().clear();
      

        String[] registros = new String[7];
        String sql = "SELECT cod_art,desc_art,cant_art,pre_venta,valor_inventario FROM articulo ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                registros[0] = rs.getString("cod_art");
                
                registros[1] = rs.getString("desc_art");
                registros[2] = rs.getString("cant_art");
                registros[3] = rs.getString("valor_inventario");
               
                 modelo2.addRow(registros);
               }
            
               
           inventario.setModel(modelo2);  
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        inventario = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        fecha = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INVENTARIO");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jLabel1.setText("PRODUCTOS");

        buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });

        inventario.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "EXISTENCIA", "VALOR INVENTARIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(inventario);
        if (inventario.getColumnModel().getColumnCount() > 0) {
            inventario.getColumnModel().getColumn(0).setResizable(false);
            inventario.getColumnModel().getColumn(1).setResizable(false);
            inventario.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/imprimir.png"))); // NOI18N
        jButton2.setText("IMPRIMIR");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        fecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\inventario.png")); // NOI18N
        jLabel2.setText("INVENTARIO");

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salida.png"))); // NOI18N
        jButton1.setText("SALIR");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(buscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
imprimir01();    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
filtrar(buscar.getText());      
    }//GEN-LAST:event_buscarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
this.dispose ();       
    }//GEN-LAST:event_jButton1ActionPerformed

   
    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar;
    private javax.swing.JLabel fecha;
    private javax.swing.JTable inventario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    String hora ="",fecha99 ="",fecha88 = "";
    
     conectar con = new conectar();
      Connection cn = con.connection();
}
