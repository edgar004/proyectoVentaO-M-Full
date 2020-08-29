
package formularios;

import conectar.conectar;
import static formularios.Clientes_mantenimiento.idcliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class compra extends javax.swing.JFrame {
 String  precio ="";
   String txtfecha2;
    String txtfecha;
    
    public compra() {
        initComponents();
        
        datos.getColumnModel().getColumn(6).setMaxWidth(0);
        datos.getColumnModel().getColumn(6).setMinWidth(0);
        datos.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        datos.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        datos.getColumnModel().getColumn(6).setResizable(false);
        this.setLocationRelativeTo(null);
      // fecha.setText(fechaActual());
      cargar();
       NumeroCompra();
       PonerFecha();
       CalendarFecha.setEnabled(false);
    }
      void PonerFecha(){
        DateFormat fc = DateFormat.getDateInstance();
        Date FechaActual = new Date();
        CalendarFecha.setDate(FechaActual);
        txtfecha = (fc.format(FechaActual));
        fecha();
        fecha2();
    }
         void AgregarDetalleFactura(){
     if(datos.getRowCount()>0){
         for(int i = 0 ; i < datos.getRowCount(); i++){
             try {
                 con.connection();
                 con.sentencia.execute("INSERT INTO detalle_comp VALUES('"+datos.getValueAt(i, 5)+"',"
                                                                     +"'"+datos.getValueAt(i, 0)+"',"
                                                                     +"'"+datos.getValueAt(i, 2)+"',"
                                                                     +"'"+datos.getValueAt(i, 3)+"',"
                                                                     +"'"+datos.getValueAt(i, 4)+"')");
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
             }
         
         }
     }
    }
     void fecha(){
 
    try {
        //jDateChooser el nombre la variable  del componente jdatecgooser
     Date  fecha = CalendarFecha.getDate();
        DateFormat f=new SimpleDateFormat("dd-MM-yyyy");
        String fecha2=f.format(fecha);
 
        //textFecha nombre de la variable del componenten jtextfiel
        txtfecha2 =(fecha2);
    } catch (Exception e) {
    }
 
} void fecha2(){
 
    try {
        //jDateChooser el nombre la variable  del componente jdatecgooser
     Date  fecha = CalendarFecha.getDate();
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String fecha2=f.format(fecha);
 
        //textFecha nombre de la variable del componenten jtextfiel
        txtfecha=(fecha2);
    } catch (Exception e) {
    }
 
}
   
    void filtrar(String valor) {
DefaultTableModel modelo2 = (DefaultTableModel)tabla_art.getModel();
     modelo2.getDataVector().clear();
      

        String[] registros = new String[4];
        String sql = "SELECT  * FROM articulo  where CONCAT (cod_art,'',desc_art)LIKE '%"+valor+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                 registros[0]=rs.getString("cod_art");
                registros[1]=rs.getString("desc_art");
                registros[2]=rs.getString("cant_art");
                registros[3]=rs.getString("pre_com");
                //registros[4]=rs.getString("pre_mayor");
               
                 modelo2.addRow(registros);
               }
            
               
           tabla_art.setModel(modelo2);  
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }

 void limpiar (){
  buscar_articulo.setText("");
 id.setText("");
 des_art.setText("");
 precio3.setText("");
 cant.setText("");
 
 if(temporal.privilegiosUser[7].crear==1){
      jButton2.setEnabled(true);
 jButton3.setEnabled(true);
 
 }
 
 
 
 
         
 }
      public void actualizar_inventario() {
        String codigo_art = "", cantidad_art = "";
        for (int j = 0; j < datos.getRowCount(); j++) {
            codigo_art = (datos.getValueAt(j, 0).toString());
            cantidad_art = (datos.getValueAt(j, 2).toString());
            float precio_compra = calculo_poderado(codigo_art);
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE articulo SET cant_art=cant_art + '"+cantidad_art+"' ,pre_compra='"+precio_compra+"' where cod_art='" + codigo_art + "' " );
                psU.executeUpdate();
            } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null,ex);
            }
        }
    }
      
      float calculo_poderado(String codigo_art){
        try {
            String sql ="SELECT * FROM detalle_comp where codigo='" + codigo_art + "'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            float importe=0;
            int cantidad=0;
            while(rs.next()){
                importe+=Float.parseFloat(rs.getString("importe"));
                cantidad+=Float.parseFloat(rs.getString("cantidad"));
                }
            return importe/cantidad;
        } catch (Exception ex) {
            return 0;
        }
      }
 void sumar() {
         String jtotal = "";
         float cv1 = 0 , cv2 = 0;
         float suma1 = 0, suma2 = 0;
         
          for (int j = 0; j < datos.getRowCount(); j++) {
            jtotal = (datos.getValueAt(j, 4).toString());
          //  jitbis = (datos.getValueAt(j, 5).toString());
             //jgeneral = (datos.getValueAt(j, 4).toString());
           
           
           cv1 = Float.parseFloat(jtotal);
          // cv2 = Float.parseFloat(jitbis);
       //     cv3 = Float.parseFloat(jgeneral);
           
           suma1 = suma1 + cv1;
           suma2 = suma2 + cv2;
          // suma3 = suma3 + cv2;
            
            
          }
        
         
        // sub_total.setText(String.valueOf(suma1));
         //total_itbis.setText(String.valueOf(suma2));
         total.setText(String.valueOf(suma1+suma2));
              
    }
    public void reiniciarJTable(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) datos.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
         
    }
       public void reiniciarJTable2(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) tabla_art.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
         
    }
  void llenar() {
        DefaultTableModel modelo2 = (DefaultTableModel) datos.getModel();
        //modelo2.getDataVector().clear();
        float pre_vent1 = Float.parseFloat(precio3.getText());
        float cantidad = Float.parseFloat(cant.getText());
        float total = pre_vent1 * cantidad;
        float art_itbis = 0;
       // float itbis = total * art_itbis / 100;
      
       

        Object[] registros = new Object[7];
        registros[0] = id.getText();
        
        registros[1] = des_art.getText();
        registros[2] = String.valueOf(cantidad);
        registros[3] = precio3.getText();
        registros[4] = String.format("%.2f", total).replace(",", ".");
       // registros[5] = String.valueOf(itbis);
        registros[5] = txtcomp.getText();
        registros[6] = precio;

        modelo2.addRow(registros);

        datos.setModel(modelo2);

    }
   void NumeroCompra(){
   String sql = "";
   sql="SELECT MAX(compra)+1 FROM contador_compra";
   
         try {
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sql);
             while(rs.next()){
             txtcomp.setText(rs.getString(1));
             if(txtcomp.getText().equals("")){
             txtcomp.setText("1");
             }
             }
         } catch (SQLException ex) {
//             Logger.getLogger(Compra_Contado.class.getName()).log(Level.SEVERE, null, ex);
         }
   
   }
   
   
   void actualizar_codigo() {
       
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idcompra=idcompra + 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
                Logger.getLogger(Clientes_mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        id.requestFocus();
    }
  void Agregardato(){
      
       try {
            PreparedStatement pts = cn.prepareStatement("INSERT INTO contador_compra(compra,total_compra,fecha,tipo_compra,balance) VALUES (?,?,?,?,?)");
            pts.setString(1, txtcomp.getText());
            pts.setString(2, total.getText());
            pts.setString(3, txtfecha);
            pts.setString(4, combo_tipo.getSelectedItem().toString());
            pts.setString(5, total.getText());
            //pts.setString(7, txtrnc.getText());
            //pts.setString(8, txtncf.getText());
              
             pts.executeUpdate();
             actualizar_codigo();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "");
        }
        }
    
 void cargar(){
         DefaultTableModel modelo2 = (DefaultTableModel)tabla_art.getModel();
     modelo2.getDataVector().clear();
        String [] registros = new String [9];
        String sql ="SELECT * FROM articulo where estado='existe'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registros[0]=rs.getString("cod_art");
                registros[1]=rs.getString("desc_art");
                registros[2]=rs.getString("cant_art");
                registros[3]=rs.getString("pre_compra");
                
                modelo2.addRow(registros);
               
            }
          tabla_art.setModel(modelo2);  
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lb_fecha = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcomp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        proveedor = new javax.swing.JTextField();
        lb_usuario1 = new javax.swing.JLabel();
        combo_tipo = new javax.swing.JComboBox<>();
        CalendarFecha = new com.toedter.calendar.JDateChooser();
        codigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_art = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        datos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        total = new javax.swing.JTextField();
        lb_fecha6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        buscar_articulo = new javax.swing.JTextField();
        lb_fecha2 = new javax.swing.JLabel();
        lb_fecha3 = new javax.swing.JLabel();
        lb_fecha4 = new javax.swing.JLabel();
        des_art = new javax.swing.JTextField();
        cant = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        precio3 = new javax.swing.JTextField();
        lb_fecha5 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("COMPRA");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\comprar.png")); // NOI18N
        jLabel5.setText("COMPRA");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 150, -1));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("PROVEERDOR");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, 170, 25));

        lb_fecha.setBackground(new java.awt.Color(0, 0, 0));
        lb_fecha.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha.setText("FECHA");
        jPanel2.add(lb_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 50, 30));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("NO.COMPRA");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));

        txtcomp.setEditable(false);
        txtcomp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtcomp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcompActionPerformed(evt);
            }
        });
        jPanel2.add(txtcomp, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 166, 25));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("CODIGO");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(293, 70, 70, 30));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("PROVEEDOR");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, -1, 30));

        proveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        proveedor.setEnabled(false);
        proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedorActionPerformed(evt);
            }
        });
        jPanel2.add(proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 170, 25));

        lb_usuario1.setBackground(new java.awt.Color(0, 0, 0));
        lb_usuario1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_usuario1.setText("TIPO COMPRA");
        jPanel2.add(lb_usuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 100, 30));

        combo_tipo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE...", "CONTADO", "CREDITO" }));
        jPanel2.add(combo_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 170, 25));

        CalendarFecha.setEnabled(false);
        jPanel2.add(CalendarFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 166, 25));

        codigo.setEnabled(false);
        codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoActionPerformed(evt);
            }
        });
        jPanel2.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 170, 25));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jLabel1.setText("BUSCAR");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 90, 25));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 600, 210));

        tabla_art.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_art.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "EXISTENCIA", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_art.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_artMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_art);
        if (tabla_art.getColumnModel().getColumnCount() > 0) {
            tabla_art.getColumnModel().getColumn(0).setResizable(false);
            tabla_art.getColumnModel().getColumn(1).setResizable(false);
            tabla_art.getColumnModel().getColumn(2).setResizable(false);
            tabla_art.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(611, 2, 370, 210));

        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "IMPORTE", "No.COMPRA", "Precio compra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(datos);
        if (datos.getColumnModel().getColumnCount() > 0) {
            datos.getColumnModel().getColumn(0).setResizable(false);
            datos.getColumnModel().getColumn(1).setResizable(false);
            datos.getColumnModel().getColumn(2).setResizable(false);
            datos.getColumnModel().getColumn(3).setResizable(false);
            datos.getColumnModel().getColumn(4).setResizable(false);
            datos.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 297, 979, 220));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/factur.png"))); // NOI18N
        jButton2.setText("Registrar");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\cruzar.png")); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\anadir (1).png")); // NOI18N
        jButton4.setText("Nuevo");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\salida.png")); // NOI18N
        jButton5.setText("Salir");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 523, -1, 85));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        total.setBackground(new java.awt.Color(0, 0, 0));
        total.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        total.setForeground(new java.awt.Color(255, 0, 0));

        lb_fecha6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha6.setText("TOTAL $");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(lb_fecha6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_fecha6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(701, 523, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jLabel9.setText("BUSCAR PRODUCTO");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 220, 210, 40));

        buscar_articulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        buscar_articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar_articuloActionPerformed(evt);
            }
        });
        buscar_articulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar_articuloKeyReleased(evt);
            }
        });
        jPanel1.add(buscar_articulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(231, 221, 750, 33));

        lb_fecha2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha2.setText("PRODUCTO");
        jPanel1.add(lb_fecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 262, -1, 34));

        lb_fecha3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha3.setText("PRECIO");
        jPanel1.add(lb_fecha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 263, 60, 33));

        lb_fecha4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha4.setText("CANTIDAD");
        jPanel1.add(lb_fecha4, new org.netbeans.lib.awtextra.AbsoluteConstraints(644, 263, -1, 33));

        des_art.setBackground(new java.awt.Color(240, 240, 240));
        des_art.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(des_art, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 260, 150, 34));

        cant.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantKeyTyped(evt);
            }
        });
        jPanel1.add(cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 260, 141, 32));

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\centro-comercial.png")); // NOI18N
        jButton6.setText("Agregar ");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, 110, 33));

        precio3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(precio3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 141, 32));

        lb_fecha5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha5.setText("CODIGO");
        jPanel1.add(lb_fecha5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 266, -1, 30));

        id.setBackground(new java.awt.Color(240, 240, 240));
        id.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel1.add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 260, 86, 34));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 991, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 public static String fechaActual(){
  
  Date fecha = new Date();
  SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
  
  
  
  return formatoFecha.format(fecha);
  }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 this.dispose();   
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tabla_artMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_artMouseClicked
int fila = tabla_art.getSelectedRow();
        if (fila>=0){
            buscar_articulo.setText(tabla_art.getValueAt(fila, 0).toString());
             id.setText(tabla_art.getValueAt(fila, 0).toString());
             des_art.setText(tabla_art.getValueAt(fila, 1).toString());
              precio3.setText(tabla_art.getValueAt(fila, 3).toString());
        }
        precio=precio3.getText();
        cant.setText("1");  
        cant.requestFocus();
    }//GEN-LAST:event_tabla_artMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
   if(combo_tipo.getSelectedItem().equals("SELECCIONE...")){
 JOptionPane.showMessageDialog(null, "POR FAVOR ELIJA UN TIPO DE COMPRA");
 return;
 } 
   if(id.getText().equals("")){
      JOptionPane.showMessageDialog(null, "SELECCIONAR EL CODIGO DEL PRODUCTO");
 return; 
   }
   
    
       if(precio3.getText().equals("")){
      JOptionPane.showMessageDialog(null, "ESCRIBA EL PRECIO DEL PRODUCTO");
 return; 
   }
  if(cant.getText().equals("")){
      JOptionPane.showMessageDialog(null, "ESCRIBA LA CANTIDAD");
 return; 
   }



   
 llenar();
sumar();
limpiar();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void buscar_articuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_articuloActionPerformed
       
    }//GEN-LAST:event_buscar_articuloActionPerformed

    private void buscar_articuloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar_articuloKeyReleased
        filtrar(buscar_articulo.getText());      
    }//GEN-LAST:event_buscar_articuloKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 if(combo_tipo.getSelectedItem().equals("SELECCIONE...")){
 JOptionPane.showMessageDialog(null, "POR FAVOR ELIJA UN TIPO DE COMPRA");
 return;
 }
        Agregardato();  
 AgregarDetalleFactura(); 
  actualizar_inventario();
        reiniciarJTable2(tabla_art);
        cargar();
        
         
         total.setText("");
        
   codigo.setText("");
       proveedor.setText("");
          reiniciarJTable(datos);
        limpiar();
buscar_articulo.requestFocus();
total.setText("");
 NumeroCompra();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reiniciarJTable(datos);
        limpiar();
buscar_articulo.requestFocus();
total.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantKeyTyped
    char c = evt.getKeyChar();
if (c<'0'  || c >'9') evt.consume();          
    }//GEN-LAST:event_cantKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscador_proveedor windows = new buscador_proveedor("compras");
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void des_artActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_des_artActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_des_artActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        codigo.setText("");
        proveedor.setText("");
        reiniciarJTable(datos);
        limpiar();
        buscar_articulo.requestFocus();
        total.setText("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoActionPerformed

    private void proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedorActionPerformed

    private void txtcompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcompActionPerformed

   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new compra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser CalendarFecha;
    public static javax.swing.JTextField buscar_articulo;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField cant;
    public static javax.swing.JTextField codigo;
    private javax.swing.JComboBox<String> combo_tipo;
    private javax.swing.JTable datos;
    private javax.swing.JTextField des_art;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_fecha;
    private javax.swing.JLabel lb_fecha2;
    private javax.swing.JLabel lb_fecha3;
    private javax.swing.JLabel lb_fecha4;
    private javax.swing.JLabel lb_fecha5;
    private javax.swing.JLabel lb_fecha6;
    private javax.swing.JLabel lb_usuario1;
    private javax.swing.JTextField precio3;
    public static javax.swing.JTextField proveedor;
    private javax.swing.JTable tabla_art;
    private javax.swing.JTextField total;
    private javax.swing.JTextField txtcomp;
    // End of variables declaration//GEN-END:variables

    public static conectar con = new conectar();
     public static Connection cn = con.connection();
}
