
package formularios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class factura_clientes extends javax.swing.JFrame {

    
    public factura_clientes() {
        initComponents();
        idfactura.requestFocus();
        Date sistema_fecha =new Date();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
        fecha17.setText(formato.format(sistema_fecha));
//        sumar();
        this.setLocationRelativeTo(null);
   cargar_fechas();
//    } void fecha1(){
//       String fecha11;
//      java.util.Date fecha = fecha1.getDate();
//      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
//      fecha11 = formato.format(fecha);
//      fecha01 = fecha11;
  }
    void fecha_2(){
       String fecha22;
      java.util.Date fecha = fecha2.getDate();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      fecha22 = formato.format(fecha);
      fecha02 = fecha22;
   }
      void cargar_fechas(){
          //fecha1();
         // fecha_2();
         DefaultTableModel modelo2 = (DefaultTableModel)tblcuentacobrar.getModel();
         modelo2.getDataVector().clear();
        String [] registros = new String [9];
         String [] vector = new String [9];
        String sql ="SELECT cliente.nombre,cliente.apellido,factura.fecha,factura.tipo_factura,factura.no_factura,factura.itbis,factura.monto,factura.total FROM cliente,factura where cliente.idcliente=factura.idcliente AND total >=1 ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                vector[0]=rs.getString("nombre");
                vector[1]=rs.getString("apellido");
                registros[0] = vector[0]+" "+vector[1];
                registros[1]=rs.getString("fecha");
                registros[2]=rs.getString("tipo_factura");
                registros[3]=rs.getString("no_factura");
                registros[4]=rs.getString("itbis");
                registros[5]=rs.getString("monto");
                registros[6]=rs.getString("total");
               
                
               
                
                modelo2.addRow(registros);
               
            }
          tblcuentacobrar.setModel(modelo2); 
          total2.setText(registros[5]);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
        void limpiar(){
    buscar.setText("");
     fac.setText("");
      valor.setText("");
       btncance.setText("");
        fecha.setText("");
         abonar.setText("");
         
    
    }
     
      void Actualizar(){
    
        if(abonar.getText().equals("")){
        JOptionPane.showMessageDialog(null, "FAVOR DIGITE CANTIDAD A ABONAR","ATENCION", JOptionPane.INFORMATION_MESSAGE);
        }else{
            try {
                
                
                 if(Double.parseDouble(abonar.getText())<0){
               JOptionPane.showMessageDialog(null, "LA CANTIADAD ABONADA TIENE QUE SER MAYOR QUE 0","ATENCION", JOptionPane.INFORMATION_MESSAGE);
                return;
               } 
                 
                 
    float balance1 = 0;
    float abono1 = 0;
    float estado1 = 0;
    balance1 = Float.parseFloat(btncance.getText());
    abono1 = Float.parseFloat(abonar.getText());
    estado1 = balance1 - abono1;
    btncance.setText(String.valueOf(estado1));
                PreparedStatement pst = cn.prepareStatement("UPDATE factura SET total='"+btncance.getText()+"' WHERE no_factura='"+fac.getText()+"'");
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATOS ACTUALIZADOS !!");
                AgregarPago();
              limpiar();
                reiniciarJTable3(tblcuentacobrar);
                reiniciarJTable4(tabla_detalle);
                reiniciarJTable5(tabla_pagos);
                abonar.setText("");
                abonar.requestFocus();
                cargar_fechas();
            } catch (SQLException ex) {//JOptionPane.showMessageDialog(null, ex);
           
            }
        }
    }
                   void reiniciarJTable3(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) tblcuentacobrar.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
         
    }
                    void reiniciarJTable5(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) tabla_pagos.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
         
    }
                                void reiniciarJTable4(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) tabla_detalle.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
         
    }
                                
 
      void AgregarPago(){
        try {
            PreparedStatement pts = cn.prepareStatement("INSERT INTO pagos(fecha,num_compra,pago) VALUES (?,?,?)");
           // pts.setString(1, codprov);
            pts.setString(1, fecha17.getText());
            pts.setString(2, fac.getText());
            pts.setString(3, abonar.getText());
            pts.executeUpdate();
        } catch (SQLException ex) {
///       JOptionPane.showMessageDialog(null, ex);
        }
    }
    void MostrarPago(String valor){
             DefaultTableModel cliente = new DefaultTableModel();
            cliente.addColumn("FACTURA NO. ");
            cliente.addColumn("MONTO PAGADO");
            cliente.addColumn("FECHA DE PAGO ");
            tabla_pagos.setModel(cliente);
            
             String datos[]=new String [3];
            String sql="SELECT * FROM pagos WHERE num_compra='"+fac.getText()+"'";
  
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while(rs.next()){
                datos[2]=rs.getString("fecha");
                datos[0]=rs.getString("num_compra");
                datos[1]=rs.getString("pago");
                cliente.addRow(datos);
                }
                tabla_pagos.setModel(cliente);
                
                
                
            } catch (SQLException ex) {
JOptionPane.showMessageDialog(null, ex);
            
            }
            }
      void ModificarDatos(){  
         float total =0;
       DecimalFormat formateador = new DecimalFormat("###,###,###.##");
       int fila = tblcuentacobrar.getSelectedRow();
        if(fila>=0){
        fac.setText(tblcuentacobrar.getValueAt(fila, 3).toString());
        //lblcli.setText(tblcuentacobrar.getValueAt(fila, 1).toString());
        valor.setText(tblcuentacobrar.getValueAt(fila, 5).toString());
        btncance.setText(tblcuentacobrar.getValueAt(fila, 6).toString());
         total2.setText(tblcuentacobrar.getValueAt(fila, 5).toString());
        fecha.setText(tblcuentacobrar.getValueAt(fila, 1).toString());
          total= Float.parseFloat(valor.getText());
            valor.setText(formateador.format(total));
        
     try {
//               Statement st = cn.createStatement();
//                ResultSet rs = st.executeQuery("SELECT cod_prov FROM proveedor"
//                        + " WHERE nom_prov = '"+lblcli.getText()+"'");
//                rs.next();
//                codprov = (String.valueOf(rs.getString("cod_prov")));
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        
        
    }else{
        JOptionPane.showMessageDialog(null, "SELECCIONE UNA FILA");
    }
    }
     void sumar() {
        DecimalFormat formateador = new DecimalFormat("RD$###,###,###,###.##");
         String jtotal = "";
         float cv1 = 0 ;
         float suma1 = 0, suma2 = 0, suma3 = 0;
         
          for (int j = 0; j < tblcuentacobrar.getRowCount(); j++) {
            jtotal = (tblcuentacobrar.getValueAt(j, 5).toString());
            cv1 = Float.parseFloat(jtotal);
            suma1 = suma1 + cv1;
           }
          total2.setText(formateador.format(suma1));
         
              
    }
void cargar_detalle(){
         DefaultTableModel modelo2 = (DefaultTableModel)tabla_detalle.getModel();
     modelo2.getDataVector().clear();
        String [] registros = new String [9];
        String sql ="SELECT articulo.cod_art,articulo.desc_art,detalle_fact.cant,detalle_fact.precio,detalle_fact.importe FROM articulo,detalle_fact where articulo.cod_art=detalle_fact.id_art and detalle_fact.no_factura='"+idfacture+"' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registros[0]=rs.getString("cod_art");
                registros[1]=rs.getString("desc_art");
                
                registros[2]=rs.getString("cant");
               
                registros[3]=rs.getString("precio");
                
                registros[4]=rs.getString("importe");
               
                
                modelo2.addRow(registros);
               
            }
          tabla_detalle.setModel(modelo2);  
      
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        //cargar_factura();
    }
void PagoTotal(){
  String sql = "";
  sql= "SELECT SUM(pago) FROM pagos WHERE num_compra='"+fac.getText()+"'";
  
  Statement st ;
  
       try {
           st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
          monto.setText(rs.getString(1));
           }
           
           
       } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
       }
  
  }    
   void cargar(){
         DefaultTableModel modelo2 = (DefaultTableModel)tblcuentacobrar.getModel();
     modelo2.getDataVector().clear();
        String [] registros = new String [9];
        String [] vector = new String [9];
        String sql ="SELECT cliente.nombre,cliente.apellido,factura.fecha,factura.tipo_factura,factura.no_factura,factura.itbis,factura.monto FROM cliente,factura where cliente.idcliente=factura.idcliente and factura.no_factura ='"+idfactura.getText()+"' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                vector[0]=rs.getString("nombre");
                vector[1]=rs.getString("apellido");
                registros[0] = vector[0]+" "+vector[1];
                registros[1]=rs.getString("fecha");
                registros[2]=rs.getString("tipo_factura");
                registros[3]=rs.getString("no_factura");
                registros[4]=rs.getString("itbis");
                registros[5]=rs.getString("monto");
               
                
                modelo2.addRow(registros);
               
            }
          tblcuentacobrar.setModel(modelo2); 
          total2.setText(registros[5]);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcuentacobrar = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        total2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        fecha2 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        idfactura = new javax.swing.JTextField();
        fecha1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        fecha17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fac = new javax.swing.JTextField();
        valor = new javax.swing.JTextField();
        btncance = new javax.swing.JTextField();
        fecha = new javax.swing.JTextField();
        abonar = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnrealizar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_detalle = new javax.swing.JTable();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_pagos = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CUENTAS POR COBRAR");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tblcuentacobrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblcuentacobrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOMBRE CLIENTE", "FECHA", "TIPO FACTURA", "NUMERO FACTURA", "ITBIS", "TOTAL", "BALANCE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblcuentacobrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcuentacobrarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcuentacobrar);
        if (tblcuentacobrar.getColumnModel().getColumnCount() > 0) {
            tblcuentacobrar.getColumnModel().getColumn(0).setResizable(false);
            tblcuentacobrar.getColumnModel().getColumn(1).setResizable(false);
            tblcuentacobrar.getColumnModel().getColumn(2).setResizable(false);
            tblcuentacobrar.getColumnModel().getColumn(3).setResizable(false);
            tblcuentacobrar.getColumnModel().getColumn(4).setMinWidth(0);
            tblcuentacobrar.getColumnModel().getColumn(4).setMaxWidth(0);
            tblcuentacobrar.getColumnModel().getColumn(5).setResizable(false);
            tblcuentacobrar.getColumnModel().getColumn(6).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("TOTAL");

        total2.setEditable(false);
        total2.setBackground(new java.awt.Color(0, 204, 255));
        total2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        fecha2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("FECHA");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("HASTA");

        idfactura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        idfactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idfacturaActionPerformed(evt);
            }
        });

        fecha1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setText("DESDE");

        fecha17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fecha17.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(idfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha17, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(idfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(fecha17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(fecha2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("BUSCAR");

        buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 255), new java.awt.Color(0, 0, 255), null));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("No.FACTURA");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("BALANCE");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("FECHA");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("VALOR");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("ABONAR");

        fac.setEditable(false);
        fac.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fac.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 255), new java.awt.Color(0, 0, 255), null));

        valor.setEditable(false);
        valor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        valor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 255), new java.awt.Color(0, 0, 255), null));

        btncance.setEditable(false);
        btncance.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btncance.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 255), new java.awt.Color(0, 0, 255), null));

        fecha.setEditable(false);
        fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fecha.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 255), new java.awt.Color(0, 0, 255), null));

        abonar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        abonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abonarActionPerformed(evt);
            }
        });
        abonar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                abonarKeyTyped(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnrealizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnrealizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/aceptar30.png"))); // NOI18N
        btnrealizar.setText("REALIZAR");
        btnrealizar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnrealizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrealizarActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/forceexit_103817.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-proceso-filled-30.png"))); // NOI18N
        jButton3.setText("ACTUALIZAR");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/salida.png"))); // NOI18N
        jButton4.setText("SALIR");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(btnrealizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnrealizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(abonar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(fecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(fac, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(valor)
                        .addComponent(btncance)
                        .addComponent(buscar, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(0, 48, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fac)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valor)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncance)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abonar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        jTabbedPane1.setBackground(new java.awt.Color(153, 204, 255));

        tabla_detalle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ARTICULO", "DESCRIPCION", "CANTIDAD", "PRECIO", "IMPORTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabla_detalle);
        if (tabla_detalle.getColumnModel().getColumnCount() > 0) {
            tabla_detalle.getColumnModel().getColumn(0).setResizable(false);
            tabla_detalle.getColumnModel().getColumn(1).setResizable(false);
            tabla_detalle.getColumnModel().getColumn(2).setResizable(false);
            tabla_detalle.getColumnModel().getColumn(3).setResizable(false);
            tabla_detalle.getColumnModel().getColumn(4).setResizable(false);
        }

        jTabbedPane2.addTab("", jScrollPane3);

        jTabbedPane1.addTab("DETALLE DE FACTURA", jTabbedPane2);

        jTabbedPane3.setBackground(new java.awt.Color(153, 204, 255));

        tabla_pagos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_pagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tabla_pagos);

        jTabbedPane3.addTab("", jScrollPane4);

        jTabbedPane1.addTab("PAGOS REALIZADOS", jTabbedPane3);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\credito.png")); // NOI18N
        jLabel12.setText("CUENTAS POR COBRAR");

        monto.setBackground(new java.awt.Color(0, 0, 0));
        monto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        monto.setForeground(new java.awt.Color(255, 0, 0));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("TOTAL DE PAGOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(6, 6, 6)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(total2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(monto, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblcuentacobrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcuentacobrarMouseClicked
         int fila = tblcuentacobrar.getSelectedRow();
        if (fila >= 0) {
            
            idfacture=(tblcuentacobrar.getValueAt(fila, 3).toString());
              
           

        }
      
       ModificarDatos();
      
       PagoTotal();
       reiniciarJTable4(tabla_detalle);
      cargar_detalle();
         MostrarPago("");
       abonar.requestFocus();
    }//GEN-LAST:event_tblcuentacobrarMouseClicked

    private void abonarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_abonarKeyTyped
        char caracter = evt.getKeyChar();
       if (((caracter < '0') || (caracter > '9')) 
        && (caracter != evt.VK_BACK_SPACE)
        && (caracter != '.' || abonar.getText().contains(".")) ) {
            evt.consume();
} 
    }//GEN-LAST:event_abonarKeyTyped

    private void btnrealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrealizarActionPerformed
        Actualizar();
      //  MostrarDatos("");
        abonar.requestFocus();
       
    }//GEN-LAST:event_btnrealizarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        limpiar();
          reiniciarJTable5(tabla_pagos);
        reiniciarJTable3(tabla_detalle);
        cargar_fechas();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 this.dispose();
AgregarPago(); 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void idfacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idfacturaActionPerformed

        cargar();
        sumar();
    }//GEN-LAST:event_idfacturaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       reiniciarJTable3(tblcuentacobrar);
        reiniciarJTable4(tabla_pagos);
        reiniciarJTable3(tabla_detalle);
        limpiar();
cargar_fechas();// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void abonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abonarActionPerformed
  Actualizar();
        abonar.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_abonarActionPerformed

    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new factura_clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField abonar;
    private javax.swing.JTextField btncance;
    private javax.swing.JButton btnrealizar;
    private javax.swing.JTextField buscar;
    private javax.swing.JTextField fac;
    private javax.swing.JTextField fecha;
    private com.toedter.calendar.JDateChooser fecha1;
    private javax.swing.JLabel fecha17;
    private com.toedter.calendar.JDateChooser fecha2;
    private javax.swing.JTextField idfactura;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField monto;
    private javax.swing.JTable tabla_detalle;
    private javax.swing.JTable tabla_pagos;
    private javax.swing.JTable tblcuentacobrar;
    private javax.swing.JTextField total2;
    private javax.swing.JTextField valor;
    // End of variables declaration//GEN-END:variables
  String fecha01 = "",fecha02 ="",idusuario="",iduser = "", nom = "",cod_cli="",idfacture = "";
    
     conectar.conectar con = new conectar.conectar();
      Connection cn = con.connection();

}
