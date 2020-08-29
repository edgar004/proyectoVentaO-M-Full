
package formularios;



import conectar.conectar;
import static formularios.facturacion.cliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class cobrar_factura extends javax.swing.JFrame {

        String id_art;
        //String atributo;
    public cobrar_factura() {
        initComponents();
        sacar_texto();
        cajero();
        
        sacar_nofactura();
        datos_regiatros();
        cargar_detalle();
         Timer tiempo = new Timer(100, new cobrar_factura.horas());
        tiempo.start();
        Date sistema_fecha =new Date();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
          fecha = (formato.format(sistema_fecha));
        efectivo.requestFocus();
        this.setLocationRelativeTo(null);
        cargar_factura();
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
     void cargar_factura() {

        String[] registros = new String[6];
        String sql = "SELECT  sub_total, itbis,monto FROM factura_normal  where no_factura ='"+idfactura+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                registros[0] = rs.getString("sub_total");
                 registros[1]= rs.getString("itbis");
                  registros[2]= rs.getString("monto");
                  
               }
            
          sub_total01 =(registros[0]);
          sub_total02 = (registros[1]);
          
         /// total99 = (registros[2]);
          
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void cargar_detalle() {
       // JOptionPane.showMessageDialog(null, "HAHAHHAHAH" +idfactura);
DefaultTableModel modelo2 = (DefaultTableModel)t_detalle.getModel();
     modelo2.getDataVector().clear();
      
        String[] array = new String[6];
        String[] registros = new String[6];
        String sql = "SELECT detalle_fact.id_art,articulo.desc_art,detalle_fact.cant,detalle_fact.precio,detalle_fact.importe FROM articulo,detalle_fact  where articulo.cod_art=detalle_fact.id_art and detalle_fact.no_factura='"+idfactura+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                registros[0] = rs.getString("id_art");
                 registros[1]= rs.getString("desc_art");
                  registros[2]= rs.getString("cant");
                   registros[3]= rs.getString("precio");
                    registros[4]= "0.00";
                     registros[5]= rs.getString("importe");
                
                 modelo2.addRow(registros);
               }
            
               
           t_detalle.setModel(modelo2);  
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }
     void imprimir01() {
        ArrayList lista = new ArrayList();
       // lista.add("");
        for (int i = 0; i < t_detalle.getRowCount(); i++) {

            factura_credito mortizar = new factura_credito ( t_detalle.getValueAt(i, 1).toString(),
                    t_detalle.getValueAt(i, 2).toString(), t_detalle.getValueAt(i, 3).toString(),t_detalle.getValueAt(i, 5).toString() + "");

            lista.add(mortizar);

        }
        JasperReport jr = null;
        
          
        try {
            jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/imprimir_credito.jasper"));
           // JOptionPane.showMessageDialog(null, "EL CODIGO ES " + sub_total01+sub_total02+sub_total03);
            HashMap parametro = new HashMap();
            
            // ESCRIBELE UNA DIRECCION Y EL TELEFONO QUE QUIIERAS PONERLE
            
            parametro.put("company", "TIENDA LA MUÑECA ,DIRECCION HATO DEL YAQUE EL TAMARINDO");
             parametro.put("fecha", hora);
            parametro.put("hora", fecha);
                       parametro.put("idfactura", idfactura);
             parametro.put("cliente", cliente.getText());
            parametro.put("direccion", "CIENFUEGOS C/5,#45 SANTIAGO R.D");

            parametro.put("telefono3", "809-585-7590");
           
            parametro.put("tipo", "CONTADO");
             parametro.put("total", sub_total01);
            parametro.put("jitbis", sub_total02);
           // parametro.put("descuentos", total99);
            parametro.put("jgeneral", total.getText());
           parametro.put("usuario",temporal.getNombre());

            //parametro.put("devolucion", "");
            parametro.put("saludo", "!GRACIAS POR SU COMPRA!");
            JasperPrint jp = JasperFillManager.fillReport(jr, parametro, new JRBeanCollectionDataSource(lista));
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);
////             JasperPrint jp = JasperFillManager.fillReport(jr, parametro,new JRBeanCollectionDataSource(lista));
////             JasperViewer jv = new JasperViewer (jp, false);
////             
////             
////             JasperPrintManager jpm= JasperPrintManager.getInstance(DefaultJasperReportsContext.getInstance());
////             jpm.print(jp,false);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "ERROR\n" + ex.getMessage());
        }

    }
    
    
    void ELiminar2(){
   int fila= t_detalle.getSelectedRow();

        if(fila>=0){
             String valor= t_detalle.getValueAt(fila, 0).toString();
            try {
                PreparedStatement pst = cn.prepareStatement("delete from detalle_fact where id_art='"+valor+"'");
                int confirmar=JOptionPane.showConfirmDialog(null, "ESTA SEGURO QUE DESEA ELIMINAR ESTE CLIENTE?", "ELIMINACION DE CLIENTE", JOptionPane.YES_NO_OPTION);
                if(JOptionPane.OK_OPTION==confirmar){
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATOS ELIMINADOS CON EXITO","ELIMINACION DE DATOS", JOptionPane.INFORMATION_MESSAGE);
                 //reiniciarJTable(t_detalle);
                ;}
               // reiniciarJTable(tabla_cliente);
               // MostrarCliente("");
                
            } catch (SQLException ex) {
//                JOptionPane.showMessageDialog(null, ex);
            }
        }else{JOptionPane.showMessageDialog(null, "POR FAVOR SELECCIONE EL CLIENTE QUE DESEA ELIMINAR","SELECCIONE UNA FILA", JOptionPane.OK_OPTION);}
   }
      void cajero(){
        DefaultTableModel modelo2 = (DefaultTableModel)datos.getModel();
     modelo2.getDataVector().clear();
      
        String[] array = new String[6];
        String[] registros = new String[6];
       
        String sql ="SELECT nombre, apellido, cedula FROM empleado where idempleado='"+idempleado+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
          while (rs.next()){
              registros[0]=rs.getString("nombre");
              registros[1]=rs.getString("apellido");
              registros[2]=rs.getString("cedula");
               modelo2.addRow(registros);
          }
          
       
         datos.setModel(modelo2);
           
           
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     private void sacar_texto(){
        temporal global = new temporal();
        idempleado =(global.getTexto());
       
    }
      private void sacar_nofactura(){
        idfactura_cobro global = new idfactura_cobro();
        idfactura = (global.getTexto());
       
    }
      void datos_regiatros(){
        String [] registros = new String [8];
        String sql ="SELECT no_factura,monto FROM factura_normal where no_factura='"+idfactura+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
          while (rs.next()){
              registros[0]=rs.getString("no_factura");
              registros[1]=rs.getString("monto");
              
          }
          no_factura.setText(registros[0]);
          total.setText(registros[1]);
        
           
           
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        datos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_detalle = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        no_factura = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        efectivo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        recibido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cambio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        procesar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("COBRAR FACTURA");
        setBackground(new java.awt.Color(153, 153, 255));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        datos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDUSUARIO", "NOMBRE EMPLEADO", "CEDULA"
            }
        ));
        datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(datos);
        if (datos.getColumnModel().getColumnCount() > 0) {
            datos.getColumnModel().getColumn(0).setMinWidth(100);
            datos.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        t_detalle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t_detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "ITBIS", "IMPORTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        t_detalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_detalleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(t_detalle);
        if (t_detalle.getColumnModel().getColumnCount() > 0) {
            t_detalle.getColumnModel().getColumn(0).setResizable(false);
            t_detalle.getColumnModel().getColumn(1).setResizable(false);
            t_detalle.getColumnModel().getColumn(2).setResizable(false);
            t_detalle.getColumnModel().getColumn(3).setResizable(false);
            t_detalle.getColumnModel().getColumn(4).setResizable(false);
            t_detalle.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("NO. FACTURA");

        no_factura.setEditable(false);
        no_factura.setBackground(new java.awt.Color(255, 255, 204));
        no_factura.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        no_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_facturaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("BALANCE FACTURA");

        total.setEditable(false);
        total.setBackground(new java.awt.Color(51, 204, 255));
        total.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        total.setForeground(new java.awt.Color(255, 0, 0));
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("EFECTIVO");

        efectivo.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        efectivo.setForeground(new java.awt.Color(0, 0, 255));
        efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efectivoActionPerformed(evt);
            }
        });
        efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                efectivoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("TOTAL RECIBIDO");

        recibido.setEditable(false);
        recibido.setBackground(new java.awt.Color(255, 255, 204));
        recibido.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        recibido.setForeground(new java.awt.Color(0, 204, 0));
        recibido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibidoActionPerformed(evt);
            }
        });
        recibido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recibidoKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("CAMBIO");

        cambio.setEditable(false);
        cambio.setBackground(new java.awt.Color(255, 255, 204));
        cambio.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        cambio.setForeground(new java.awt.Color(0, 0, 153));
        cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambioActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\cruzar.png")); // NOI18N
        jButton1.setText("CANCELAR");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        procesar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        procesar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/imprimir.png"))); // NOI18N
        procesar.setText("IMPRIMIR");
        procesar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        procesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procesarActionPerformed(evt);
            }
        });
        procesar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                procesarKeyPressed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aceptar30.png"))); // NOI18N
        jButton2.setText("CONFIRMAR");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(procesar, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(no_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(88, 88, 88)
                                        .addComponent(jLabel6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel5)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cambio, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                    .addComponent(recibido)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(no_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(recibido, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(procesar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efectivoActionPerformed
float a = Float.parseFloat(total.getText());
float b = Float.parseFloat(efectivo.getText());
float resta = 0;

if (b<a){
    JOptionPane.showMessageDialog(null, "EL EFECTIVO ES MENOR AL MONTO");
    efectivo.setText("");
    efectivo.requestFocus();
    return;
}
resta = b-a;
recibido.setText(efectivo.getText());
cambio.setText(String.valueOf(resta));
procesar.requestFocus();
// TODO add your handling code here:
    }//GEN-LAST:event_efectivoActionPerformed

    private void procesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procesarActionPerformed
           {       if (cambio.getText().equals("")){
JOptionPane.showMessageDialog(null, "DEBE CONFIRMAR LA COMPARA");
cambio.requestFocus();
return;
}
        try {
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idfactura=idfactura + 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS");
            } catch (Exception ex) {
                Logger.getLogger(facturacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       imprimir01();
    
                this.dispose();
facturacion windows =new facturacion();
windows.setVisible(true);
windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_procesarActionPerformed
    }
    private void procesarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_procesarKeyPressed

        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
       
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idfactura=idfactura + 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
                Logger.getLogger(facturacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        
//       imprimir01();
    
                this.dispose();
facturacion windows =new facturacion();
windows.setVisible(true);
windows.setLocationRelativeTo(null);    
                }        
    }//GEN-LAST:event_procesarKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//ELiminar2();
this.dispose(); 
facturacion windows =new facturacion();
windows.setVisible(true);
windows.setLocationRelativeTo(null);   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void no_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_facturaActionPerformed
        
    }//GEN-LAST:event_no_facturaActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        
    }//GEN-LAST:event_totalActionPerformed

    private void recibidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibidoActionPerformed
       
    }//GEN-LAST:event_recibidoActionPerformed

    private void cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambioActionPerformed
        
    }//GEN-LAST:event_cambioActionPerformed

    private void recibidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recibidoKeyReleased
       
    }//GEN-LAST:event_recibidoKeyReleased

    private void datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datosMouseClicked
       
    }//GEN-LAST:event_datosMouseClicked

    private void t_detalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_detalleMouseClicked
 int fila = t_detalle.getSelectedRow();
        if (fila>=0){
           id_art = (t_detalle.getValueAt(fila, 0).toString());
            // des_art.setText(tabla_art.getValueAt(fila, 1).toString());
             // precio.setText(tabla_art.getValueAt(fila, 3).toString());
             

        }   
    }//GEN-LAST:event_t_detalleMouseClicked

    private void efectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_efectivoKeyTyped
char c = evt.getKeyChar();

if (c<'0'  || c >'9') evt.consume();         
    }//GEN-LAST:event_efectivoKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 {       if (efectivo.getText().equals("")){
JOptionPane.showMessageDialog(null, "DEBE EFECTUAR UN MONTO");
efectivo.requestFocus();
return;
}
             if(Double.parseDouble(efectivo.getText())<0){
               JOptionPane.showMessageDialog(null, "LA CANTIADAD EN EFECTIVO TIENE QUE SER MAYOR QUE 0","ATENCION", JOptionPane.INFORMATION_MESSAGE);
                return;
               } 
    
         float a = Float.parseFloat(total.getText());
float b = Float.parseFloat(efectivo.getText());
float resta = 0;

if (b<a){
    JOptionPane.showMessageDialog(null, "EL EFECTIVO ES MENOR AL MONTO");
    efectivo.setText("");
    efectivo.requestFocus();
    return;

    }
            
resta = b-a;

 DecimalFormat df = new DecimalFormat("#.00");
 double result = new Double(df.format(Double.parseDouble(efectivo.getText())));
recibido.setText( result+"");


result = new Double(df.format(Double.parseDouble(String.valueOf(resta))));
cambio.setText(String.valueOf(result));
procesar.requestFocus();
 }
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cobrar_factura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cambio;
    private javax.swing.JTable datos;
    private javax.swing.JTextField efectivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField no_factura;
    private javax.swing.JButton procesar;
    private javax.swing.JTextField recibido;
    public static javax.swing.JTable t_detalle;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
String idfactura = "",idempleado = "", sub_total01 = "",sub_total02 ="",total99 = "", hora = "",fecha = "";
    
    
     conectar con = new conectar();
      Connection cn = con.connection();


}
