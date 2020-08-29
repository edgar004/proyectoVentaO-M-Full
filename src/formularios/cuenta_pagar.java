
package formularios;

import conectar.conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class cuenta_pagar extends javax.swing.JFrame {
 conectar con = new conectar();
      Connection cn = con.connection();
       public static String atributo;
    public cuenta_pagar() {
        initComponents();
          MostrarDatos("");
        // Desactivar();
        Fecha();
        setResizable(false);
     
                 setResizable(false);
        //lblcli.setVisible(false);
        fecha2.setVisible(false);
        CalendarFecha.setVisible(false);
        setLocationRelativeTo(null);
        setTitle("CUENTA POR PAGAR");
        
    }
    void MostrarPago(String valor){
             DefaultTableModel cliente = new DefaultTableModel();
            cliente.addColumn("FACTURA NO. ");
            cliente.addColumn("MONTO PAGADO");
            cliente.addColumn("FECHA DE PAGO ");
            tblpago.setModel(cliente);
            
             String datos[]=new String [3];
            String sql="SELECT * FROM pago_cuentacredito WHERE num_compra='"+fac.getText()+"'";
  
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while(rs.next()){
                datos[2]=rs.getString("fecha");
                datos[0]=rs.getString("num_compra");
                datos[1]=rs.getString("pago");
                cliente.addRow(datos);
                }
                tblpago.setModel(cliente);
                
                
                
            } catch (SQLException ex) {//JOptionPane.showMessageDialog(null, ex);
            
            }
            }
    void Fecha(){
    DateFormat fc = DateFormat.getDateInstance();
        java.util.Date FechaActual = new java.util.Date();
        CalendarFecha.setDate(FechaActual);
        fecha2.setText(fc.format(FechaActual));
        
    }
    void limpiar(){
    buscar.setText("");
     fac.setText("");
      valor.setText("");
       btncance.setText("");
        fecha.setText("");
         abonar.setText("");
         
    
    }
     
    void Desactivar(){
      btnrealizar.setEnabled(false);
    btncance.setEnabled(false);
    abonar.setEnabled(false);
     }
    void PagoTotal(){
  String sql = "";
  sql= "SELECT SUM(pago) FROM pago_cuentacredito WHERE num_compra='"+fac.getText()+"'";
  
  Statement st ;
  
       try {
           st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
          valor4.setText(rs.getString(1));
           }
           
           
       } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
       }
  
  }    
     
 void MostrarDatos(String valor){
             DefaultTableModel cliente = new DefaultTableModel() ;
            cliente.addColumn("NO. FACTURA ");
            cliente.addColumn("VALOR FACTURA ");
            cliente.addColumn("BALANCE ");
            cliente.addColumn("FECHA ");
            tblcuentacobrar.setModel(cliente);
            
            String sql= "";
            if(valor.equals("")){
            sql= "SELECT COMPRA,TOTAL_COMPRA,BALANCE,DATE_FORMAT(FECHA,'%d-%m-%Y') AS FECHA FROM CONTADOR_COMPRA  WHERE TIPO_COMPRA='CREDITO' AND CONTADOR_COMPRA.BALANCE >1";
            }else{sql="SELECT COMPRA,TOTAL_COMPRA,BALANCE,DATE_FORMAT(FECHA,'%d-%m-%Y') AS FECHA FROM CONTADOR_COMPRA  WHERE "+atributo+"='"+valor+"' AND TIPO_COMPRA='CREDITO' AND CONTADOR_COMPRA.BALANCE >1";}
            
            String datos[]=new String [4];
            Statement st;     
            try {
                st= cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                //datos[4]=rs.getString(5);
                cliente.addRow(datos);
                }
                tblcuentacobrar.setModel(cliente);
                
                
                
            } catch (SQLException ex) {//JOptionPane.showMessageDialog(null, ex);
            
            }
    }
 void Actualizar(){
    
        if(abonar.getText().equals("")){
        JOptionPane.showMessageDialog(null, "FAVOR DIGITE CANTIDAD A ABONAR","ATENCION", JOptionPane.INFORMATION_MESSAGE);
        }else{
           if(Double.parseDouble(abonar.getText())<0){
               JOptionPane.showMessageDialog(null, "LA CANTIADAD ABONADA TIENE QUE SER MAYOR QUE 0","ATENCION", JOptionPane.INFORMATION_MESSAGE);
                return;
               } 
            
            
            try {
    float balance1 = 0;
    float abono1 = 0;
    float estado1 = 0;
    balance1 = Float.parseFloat(btncance.getText());
    abono1 = Float.parseFloat(abonar.getText());
    estado1 = balance1 - abono1;
    btncance.setText(String.valueOf(estado1));
                PreparedStatement pst = cn.prepareStatement("UPDATE CONTADOR_COMPRA SET BALANCE='"+btncance.getText()+"' WHERE compra='"+fac.getText()+"'");
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "DATOS ACTUALIZADOS !!");
                AgregarPago();
                MostrarDatos("");
                limpiar();
                reiniciarJTable3(tblcuentacobrar);
                reiniciarJTable2(tabla2);
                abonar.setText("");
                abonar.requestFocus();
            } catch (SQLException ex) {//JOptionPane.showMessageDialog(null, ex);
           
            }
        }
    }
      void AgregarPago(){
        try {
            PreparedStatement pts = cn.prepareStatement("INSERT INTO pago_cuentacredito(fecha,num_compra,pago) VALUES (?,?,?)");
           // pts.setString(1, codprov);
            pts.setString(1, fecha2.getText());
            pts.setString(2, fac.getText());
            pts.setString(3, abonar.getText());
            pts.executeUpdate();
        } catch (SQLException ex) {
///       JOptionPane.showMessageDialog(null, ex);
        }
    }
    
        void BuscarCompra(String valor){
    DefaultTableModel cliente = (DefaultTableModel) tabla2.getModel() ;
             
    String [] datos  = new String[10];
    String sql = "SELECT detalle_comp.num_compra,articulo.cod_art,articulo.desc_art,detalle_comp.cantidad,detalle_comp.precio,detalle_comp.importe"
            + " from contador_compra INNER JOIN detalle_comp ON detalle_comp.num_compra=contador_compra.compra INNER JOIN articulo ON detalle_comp.codigo=articulo.cod_art WHERE num_COMPRA= '"+fac.getText()+"'";
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
             datos[0]=rs.getString("num_COMPRA");  
                datos[1]=rs.getString("cod_art");
            datos[2]=rs.getString("desc_art");
            datos[3]=rs.getString("cantidad");
            datos[4]=rs.getString("precio");
           
            datos[5]=rs.getString("importe");
            cliente.addRow(datos);
              //txtfecha.setText(rs.getString("FECHA"));
              //txtprov.setText(datos[7]=rs.getString("NOM_PROV"));
              //txtrnc.setText(datos[8]=rs.getString("RNC"));
             // txtncf.setText(datos[9]=rs.getString("NCF"));
            //  VentaTotal();
             }
           tabla2.setModel(cliente);
            
            
        } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
        }
    
    
    
    }
          void reiniciarJTable2(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) tabla2.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
         
    }
                 void reiniciarJTable3(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) tblcuentacobrar.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);
 
         
    }
        void ModificarDatos(){  
         float total =0;
       DecimalFormat formateador = new DecimalFormat("###,###,###.##");
       int fila = tblcuentacobrar.getSelectedRow();
        if(fila>=0){
        fac.setText(tblcuentacobrar.getValueAt(fila, 0).toString());
        //lblcli.setText(tblcuentacobrar.getValueAt(fila, 1).toString());
        valor.setText(tblcuentacobrar.getValueAt(fila, 1).toString());
        btncance.setText(tblcuentacobrar.getValueAt(fila, 2).toString());
        fecha.setText(tblcuentacobrar.getValueAt(fila, 3).toString());
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        buscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcuentacobrar = new javax.swing.JTable();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        valor2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblpago = new javax.swing.JTable();
        valor4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        CalendarFecha = new com.toedter.calendar.JDateChooser();
        fecha2 = new javax.swing.JTextField();
        valor3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jTabbedPane5.setBackground(new java.awt.Color(153, 204, 255));
        jTabbedPane5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jTabbedPane6.setBackground(new java.awt.Color(153, 204, 255));
        jTabbedPane6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("BUSCAR");

        buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 255), new java.awt.Color(0, 0, 255), null));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("No.FACTURA");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("BALANCE");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("FECHA");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("VALOR");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("ABONAR");

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
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\cruzar.png")); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnrealizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(abonar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(fecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(fac, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(valor)
                        .addComponent(btncance)
                        .addComponent(buscar, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fac, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valor, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncance, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abonar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblcuentacobrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.FACTURA", "PROVEEDOR", "VALOR FACTURA", "BALANCE", "FECHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            tblcuentacobrar.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 19, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("", jPanel1);

        jTabbedPane5.addTab("CUENTA POR PAGAR", jTabbedPane6);

        jTabbedPane7.setBackground(new java.awt.Color(153, 204, 255));

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("TOTAL");

        valor2.setEditable(false);
        valor2.setBackground(new java.awt.Color(0, 0, 0));
        valor2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        valor2.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(valor2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valor2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.FACTURA", "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla2);
        if (tabla2.getColumnModel().getColumnCount() > 0) {
            tabla2.getColumnModel().getColumn(0).setResizable(false);
            tabla2.getColumnModel().getColumn(1).setResizable(false);
            tabla2.getColumnModel().getColumn(2).setResizable(false);
            tabla2.getColumnModel().getColumn(3).setResizable(false);
            tabla2.getColumnModel().getColumn(4).setResizable(false);
            tabla2.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 28, Short.MAX_VALUE))
        );

        jTabbedPane7.addTab("", jPanel6);

        jTabbedPane5.addTab("DETALLE DE LA COMPRA", jTabbedPane7);

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));

        jPanel9.setBackground(new java.awt.Color(153, 204, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("TOTAL");

        jTextField8.setBackground(new java.awt.Color(0, 204, 255));
        jTextField8.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(323, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        tblpago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.FACTURA", "MONTO PAGADO", "FECHA DE PAGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblpago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpagoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblpagoMouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(tblpago);
        if (tblpago.getColumnModel().getColumnCount() > 0) {
            tblpago.getColumnModel().getColumn(0).setResizable(false);
            tblpago.getColumnModel().getColumn(1).setResizable(false);
            tblpago.getColumnModel().getColumn(2).setResizable(false);
        }

        valor4.setEditable(false);
        valor4.setBackground(new java.awt.Color(0, 0, 0));
        valor4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        valor4.setForeground(new java.awt.Color(255, 51, 51));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setText("Total RD$");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(valor4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(valor4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane8.addTab("", jPanel8);

        jTabbedPane5.addTab("PAGOS REALIZADOS", jTabbedPane8);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\credito.png")); // NOI18N
        jLabel11.setText("CUENTAS POR PAGAR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(621, 621, 621)
                        .addComponent(valor3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CalendarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)))
                .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CalendarFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valor3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();  
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabla2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla2MouseClicked
       
    }//GEN-LAST:event_tabla2MouseClicked

    private void tblcuentacobrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcuentacobrarMouseClicked
       ModificarDatos();
        btnrealizar.setEnabled(true);
        btncance.setEnabled(true);
        abonar.setEnabled(true);
        abonar.requestFocus();
        reiniciarJTable2(tabla2);
       BuscarCompra(""); 
        PagoTotal();
        MostrarPago("");
        valor2.setText(valor.getText());
    }//GEN-LAST:event_tblcuentacobrarMouseClicked

    private void btnrealizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrealizarActionPerformed
 Actualizar(); 
        MostrarDatos("");
        abonar.requestFocus();
 // TODO add your handling code here:
    }//GEN-LAST:event_btnrealizarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void abonarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_abonarKeyTyped
     char caracter = evt.getKeyChar();
       if (((caracter < '0') || (caracter > '9')) 
        && (caracter != evt.VK_BACK_SPACE)
        && (caracter != '.' || abonar.getText().contains(".")) ) {
            evt.consume();
       }// TODO add your handling code here:
    }//GEN-LAST:event_abonarKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblpagoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpagoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblpagoMouseEntered

    private void tblpagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpagoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblpagoMouseClicked

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
            java.util.logging.Logger.getLogger(cuenta_pagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cuenta_pagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cuenta_pagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cuenta_pagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cuenta_pagar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser CalendarFecha;
    private javax.swing.JTextField abonar;
    private javax.swing.JTextField btncance;
    private javax.swing.JButton btnrealizar;
    private javax.swing.JTextField buscar;
    private javax.swing.JTextField fac;
    private javax.swing.JTextField fecha;
    private javax.swing.JTextField fecha2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTable tabla2;
    private javax.swing.JTable tblcuentacobrar;
    private javax.swing.JTable tblpago;
    private javax.swing.JTextField valor;
    private javax.swing.JTextField valor2;
    private javax.swing.JTextField valor3;
    private javax.swing.JTextField valor4;
    // End of variables declaration//GEN-END:variables
}
