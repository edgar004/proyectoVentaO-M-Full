
package formularios;

import static formularios.compra.cn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class cuadre_caja extends javax.swing.JFrame {
 String atributo;
     String txt_buscarfecha;
  
    
    public cuadre_caja() {
        initComponents();
       // BuscarFactura2("");
       // BuscarFactura("");
        //SumarProducto2();
         MostrarVenta2();
        SumarProducto();
         SumarProducto2();
         cargar_detalle();
         Fecha6();
         fecha3();
        total_caja.requestFocus();
       //Fecha2();
        CuentaCobrar();
        MostrarCuadre();
        this.setLocationRelativeTo(null);
    }
        void Fecha2(){
        DateFormat fc = DateFormat.getDateInstance();
        Date FechaActual = new Date();
       CalendarFecha.setDate(FechaActual);
        lblfecha2.setText(fc.format(FechaActual));
    }
        void Fecha5(){
        DateFormat fc = DateFormat.getDateInstance();
        Date FechaActual = new Date();
        CalendarFecha.setDate(FechaActual);
          lblfecha.setText(fc.format(FechaActual));
          lblfecha3.setText(fc.format(FechaActual));
      fecha2();
    }
         void imprimir01() {
       ArrayList lista = new ArrayList();
     lista.add("");
//       for (int i = 0; i < tabla_orden.getRowCount(); i++) {
//
//           Factura_rep mortizar = new Factura_rep (tabla_orden.getValueAt(i, 0).toString()+"");
//            lista.add(mortizar);
//
//        }
        JasperReport jr = null;
        
          
        try {
           jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/Reporte_cuadre2.jasper"));
           
           
                      Calendar c= new GregorianCalendar();
       String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));
               String fechaActual = Integer.valueOf(mes)+1+"/"+(dia)+"/"+(annio);


           // JOptionPane.showMessageDialog(null, "EL CODIGO ES " + sub_total01+sub_total02+sub_total03);
            HashMap parametro = new HashMap();
            //  parametro.put("company", "INVERSIONES SABATIAN");
                  //parametro.put("servicios",total.getText()); 
                parametro.put("facturas",txttotal3.getText());
                parametro.put("credito_cobrado",monto.getText());
                parametro.put("total_ventas",txtventatotal.getText());
                 parametro.put("monto_caja",total_caja.getText());
                    parametro.put("diferencia",diferencia.getText());
                              parametro.put("fecha",fechaActual);
                               parametro.put("usuario",temporal.getNombre());
                               // parametro.put("fecha_soli",fecha_entrega.toString());
          JasperPrint jp = JasperFillManager.fillReport(jr, parametro,new JRBeanCollectionDataSource(lista));
                JasperViewer jv = new JasperViewer(jp,false);
                jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                jv.setVisible(true);
                
                JasperPrintManager jpm = JasperPrintManager.getInstance(DefaultJasperReportsContext.getInstance());
                jpm.print(jp, true); //si le pongo false me lo manda al printer directo
                
                
                      } catch (JRException ex) {
                          ex.printStackTrace();
                          JOptionPane.showInternalMessageDialog(null, "ERROR\n"+ex.getMessage());
                          
            
        }
            
    }
    
          void limpiar (){
    txtventatotal.setText("");
      total_caja.setText("");
        diferencia.setText("");
          txtventatotal.setText("");
    
    
    }
     void cargar_detalle(){
         DefaultTableModel modelo2 = (DefaultTableModel)tblfacturaimp2.getModel();
     modelo2.getDataVector().clear();
        String [] registros = new String [4];
        String sql ="SELECT factura.no_factura,factura.monto,factura.total,factura.fecha FROM factura WHERE factura.FECHA=DATE_FORMAT(NOW(),'%Y-%m-%d') ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registros[0]=rs.getString("no_factura");
                registros[1]=rs.getString("monto");
                
                registros[2]=rs.getString("total");
               
                registros[3]=rs.getString("fecha");
                
                //registros[4]=rs.getString("importe");
               
                
                modelo2.addRow(registros);
               
            }
          tblfacturaimp2.setModel(modelo2);  
      
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        //cargar_factura();
    }
    void fecha2(){
 
    try {
        //jDateChooser el nombre la variable  del componente jdatecgooser
     Date  fecha = CalendarFecha.getDate();
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String fecha2=f.format(fecha);
         
        //textFecha nombre de la variable del componenten jtextfiel
        lblfecha2.setText(fecha2);
    } catch (Exception e) {
    }
 
}
     void MostrarCuadre(){
     String sql = "";
     sql = "SELECT MAX(num_cu)+1 FROM cuadre_caja";
     
     Statement st ;
     
       try {
           st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
           txtcuadre.setText(rs.getString(1));
           if(txtcuadre.getText().equals("")){
           txtcuadre.setText("1");
           }
           }
       } catch (SQLException ex) {
//           Logger.getLogger(CuadreCaja.class.getName()).log(Level.SEVERE, null, ex);
       }
     }
 public  void CuentaCobrar(){
  String sql = "";
  sql= "SELECT SUM(pago),pagos.fecha,pagos.pago "
          + "FROM pagos LEFT JOIN factura"
          + " ON pagos.num_compra=factura.no_factura "
          + " where  pagos.FECHA=DATE_FORMAT(NOW(),'%Y-%m-%d') ";
  
  Statement st ;
  
   float cobro = 0, cobro2 = 0;
       try {
            DecimalFormat formateador2 = new DecimalFormat("###,###,###.##");
           st = cn.createStatement();
           ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
           monto.setText(rs.getString(1));
           if(monto.getText().equals("")){
           monto.setText("0.0");
          
           
          
           }
            txtcuentacobrar2.setText(monto.getText());
            cobro = Float.parseFloat(monto.getText());
            monto.setText(formateador2.format(cobro));
           
            
           }
           
           
       } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, ex);
       }
  
  }
   void BuscarFactura(String valor){
    DefaultTableModel cliente =new DefaultTableModel();
             cliente.addColumn("FACTURA No.");
            cliente.addColumn("VENTA ");
            //cliente.addColumn("FECHA");
            tblfacturaimp.setModel(cliente);
            
         
    
    String [] datos  = new String[3];
    String sql = "SELECT ventadia.TOTAL,VENTADIA.ID_FAC FROM ventadia INNER JOIN factura ON VENTADIA.ID_FAC=factura.no_factura ";
    
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            datos[0]=rs.getString("ID_FAC");
            datos[1]=rs.getString("TOTAL");
            //datos[2]=rs.getString("(factura.fecha,'%d-%m-%Y')");
                        
            cliente.addRow(datos);
          //  VentaTotal();
             }
           tblfacturaimp2.setModel(cliente);
            
            
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
        }
    
    
    
    }
   void SumarProducto(){
        DecimalFormat formateador = new DecimalFormat("######,###.##");
         DecimalFormat formateador2 = new DecimalFormat("#########.##");
        DefaultTableModel factura = (DefaultTableModel) tblfacturaimp.getModel();
        
        String total1 = "";
        float sub_total1 = 0;
        float sub_total2 = 0;
        String jitebis = "";
        float itbis_total2 = 0;
        double itbis_total3 = 0.18;
        float gran_total = 0;
        String total_venta = "";
        float total_precio1 = 0;
         
        float cantidad1 = 0;
        String cantidad2 = "";
        
        for (int i = 0; i < tblfacturaimp.getRowCount(); i++){
        total1 = (tblfacturaimp.getValueAt(i, 1).toString());
        sub_total2 = Float.parseFloat(total1);
        sub_total1 = sub_total1 + sub_total2;
        
        itbis_total2 = (float) (sub_total1 * itbis_total3);
        
        gran_total = (sub_total1 - itbis_total2);        
        }
        txttotal3.setText(formateador.format(sub_total1));
       
       // txtvalor.setText(txttotal.getText());
        
    }
   void SumarProducto2(){
        DecimalFormat formateador = new DecimalFormat("######,###.##");
         DecimalFormat formateador2 = new DecimalFormat("#########.##");
        DefaultTableModel factura = (DefaultTableModel) tblfacturaimp.getModel();
        
        String total1 = "";
        float sub_total1 = 0;
        float sub_total2 = 0;
        String jitebis = "";
        float itbis_total2 = 0;
        double itbis_total3 = 0.18;
        float gran_total = 0;
        String total_venta = "";
        float total_precio1 = 0;
         
        float cantidad1 = 0;
        String cantidad2 = "";
        
        for (int i = 0; i < tblfacturaimp.getRowCount(); i++){
        total1 = (tblfacturaimp.getValueAt(i, 1).toString());
        sub_total2 = Float.parseFloat(total1);
        sub_total1 = sub_total1 + sub_total2;
        
        itbis_total2 = (float) (sub_total1 * itbis_total3);
        
        gran_total = (sub_total1 - itbis_total2);        
        }
        txttotal3.setText(formateador.format(sub_total1));
          total6.setText(String.valueOf(sub_total1));
       
       // txttotal3.setText(txttotal.getText());
        
    }
   void BuscarFactura2(String valor){
    DefaultTableModel cliente =new DefaultTableModel();
             cliente.addColumn("FACTURA No.");
            cliente.addColumn("VENTA ");
            cliente.addColumn("FECHA");
            tblfacturaimp.setModel(cliente);
            
         
    
    String [] datos  = new String[3];
    String sql = "SELECT ventadia.TOTAL,VENTADIA.ID_FAC FROM ventadia INNER JOIN factura_normal ON VENTADIA.ID_FAC=factura_normal.no_factura ";
    
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            datos[0]=rs.getString("ID_FAC");
            datos[1]=rs.getString("TOTAL");
            //datos[2]=rs.getString("(factura.fecha,'%d-%m-%Y')");
                        
            cliente.addRow(datos);
          //  VentaTotal();
             }
           tblfacturaimp.setModel(cliente);
            
            
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
        }
    
    
    
    }
    void MostrarVenta2(){
       DefaultTableModel venta = new DefaultTableModel();
       
      venta.addColumn("FACTURA No.");
      venta.addColumn("VENTA");
      venta.addColumn("FECHA");
    
    String [] datos  = new String[3];
    String sql = "SELECT VENTADIA.ID_FAC,TOTAL,DATE_FORMAT(fecha,'%d-%m-%Y')"
            + " AS FECHA FROM VENTADIA LEFT JOIN factura_normal ON"
            + " VENTADIA.ID_FAC=factura_normal.no_factura WHERE FECHA=DATE_FORMAT(NOW(),'%Y-%m-%d'); ";
    
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            datos[0]=rs.getString("ID_FAC");
            datos[1]=rs.getString("total");
            datos[2]=rs.getString("FECHA");
                    
            venta.addRow(datos);
             }
           tblfacturaimp.setModel(venta);
           
           
            
            
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, ex);
        }
        }
     void cuadrar(){
         // VentaTotal();
         DecimalFormat formateador2 = new DecimalFormat("######,###.##");
            DecimalFormat formateador3 = new DecimalFormat("#########.##");
       float servicio =0, venta =0 , inicio , monto,cuentacobrar,gasto1,rebaja = 0,total2;
      servicio = Float.parseFloat(total6.getText());
     venta = Float.parseFloat(total7.getText());
    
        cuentacobrar = Float.parseFloat(txtcuentacobrar2.getText()); 
       total2 = Float.parseFloat(total_caja.getText()); 
      
          
      monto  = servicio + venta + cuentacobrar;
      
     rebaja = total2 - monto ;
    
     txtventatotal.setText(formateador2.format(monto));
        txtventatotal2.setText(formateador3.format(monto));
   
      diferencia.setText(formateador2.format(rebaja));
        diferencia2.setText(formateador3.format(rebaja));
    
   //txtventatotal.setText(String.valueOf(monto));
    // diferencia.setText(String.valueOf(rebaja));
    
  }
        void Fecha6(){
        DateFormat fc = DateFormat.getDateInstance();
        Date FechaActual = new Date();
        txtbuscar2.setDate(FechaActual);
//        txt_fecha.setText(fc.format(FechaActual));
    }
     void fecha3(){
 
    try {
        //jDateChooser el nombre la variable  del componente jdatecgooser
     Date  fecha = txtbuscar2.getDate();
        DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String fecha2=f.format(fecha);
 
        //textFecha nombre de la variable del componenten jtextfiel
        txt_buscarfecha = (fecha2);
    } catch (Exception e) {
    }
 
}
     void AgregarCuadre(){
     try {
            PreparedStatement pts = cn.prepareStatement("INSERT INTO Cuadre_caja(total_venta,total_cobro,diferencia,fecha,total_caja) VALUES (?,?,?,?,?)");
            pts.setString(1, txtventatotal2.getText());
            pts.setString(2, txtcuentacobrar2.getText());
            pts.setString(3, diferencia2.getText());
            pts.setString(4, txt_buscarfecha);
               pts.setString(5, total_caja.getText());
            
            
             JOptionPane.showMessageDialog(null, "DATOS GUARDADOS CORRECTAMENTE","AGREGAR CUADRE", JOptionPane.INFORMATION_MESSAGE);
            pts.executeUpdate();
            
        } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, ex);
        }
     
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        total_caja = new javax.swing.JTextField();
        monto = new javax.swing.JTextField();
        diferencia = new javax.swing.JTextField();
        txtventatotal = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtcuadre = new javax.swing.JTextField();
        txtcuentacobrar2 = new javax.swing.JTextField();
        lblfecha = new javax.swing.JTextField();
        CalendarFecha = new com.toedter.calendar.JDateChooser();
        lblfecha2 = new javax.swing.JTextField();
        txtbuscar = new javax.swing.JTextField();
        lblfecha3 = new javax.swing.JTextField();
        total7 = new javax.swing.JTextField();
        total6 = new javax.swing.JTextField();
        txtventatotal2 = new javax.swing.JTextField();
        diferencia2 = new javax.swing.JTextField();
        txtbuscar2 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblfacturaimp2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblfacturaimp = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        txttotal3 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("MONTO TOTAL DEL DIA  $");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("MONTO TOTAL EN CAJA $");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setText("CREDITO COBRADO $");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setText("DIFERENCIA $");

        total_caja.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        total_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_cajaActionPerformed(evt);
            }
        });
        total_caja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                total_cajaKeyTyped(evt);
            }
        });

        monto.setEditable(false);
        monto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        monto.setText("0");

        diferencia.setEditable(false);
        diferencia.setBackground(new java.awt.Color(0, 0, 0));
        diferencia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        diferencia.setForeground(new java.awt.Color(255, 0, 0));

        txtventatotal.setEditable(false);
        txtventatotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\factura.png")); // NOI18N
        jButton1.setText("CUADRAR");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnguardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnguardar.setEnabled(false);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\cruzar.png")); // NOI18N
        jButton3.setText("CANCELAR");
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

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel20.setText("No.CUADRE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtcuadre, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcuadre, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        lblfecha2.setText(" ");

        total7.setText("0");

        total6.setText("0");

        txtventatotal2.setText("0");

        diferencia2.setText("0");

        txtbuscar2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(CalendarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblfecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(total_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtventatotal, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblfecha3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtcuentacobrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(diferencia2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(total6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtventatotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(total7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtventatotal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(total_caja, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(monto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblfecha3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcuentacobrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CalendarFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblfecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(total7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtventatotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(total6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addComponent(diferencia2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblfacturaimp2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.FACTURA", "MONTO", "BALANCE", "FECHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblfacturaimp2);
        if (tblfacturaimp2.getColumnModel().getColumnCount() > 0) {
            tblfacturaimp2.getColumnModel().getColumn(0).setResizable(false);
            tblfacturaimp2.getColumnModel().getColumn(1).setResizable(false);
            tblfacturaimp2.getColumnModel().getColumn(2).setResizable(false);
            tblfacturaimp2.getColumnModel().getColumn(3).setResizable(false);
        }

        tblfacturaimp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.FACTURA", "VENTA", "FECHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblfacturaimp);

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("CUENTAS POR COBRAR");

        txttotal3.setEditable(false);
        txttotal3.setBackground(new java.awt.Color(0, 0, 0));
        txttotal3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txttotal3.setForeground(new java.awt.Color(255, 0, 0));
        txttotal3.setText("0");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("TOTAL $");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("FACTURAS DEL DIA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txttotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel24))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txttotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 this.dispose();        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(tblfacturaimp.getRowCount()==0 && tblfacturaimp2.getRowCount()==0){
            JOptionPane.showMessageDialog(null, "Hoy no se ha realizado factura para poder realizar el cuadre de caja");
             return;
          }
      
        
        if(total_caja.getText().equals("")){
        JOptionPane.showMessageDialog(null, "DIGITE EL MONTO EN CAJA ");
        total_caja.requestFocus();
        }else{
            
              if(Double.parseDouble(total_caja.getText())<0){
            JOptionPane.showMessageDialog(null, "El total de caja no puede ser menor que cero");
            total_caja.setText("");
            total_caja.requestFocus();
            return;
        }
              
        cuadrar();
        btnguardar.setEnabled(true);
        }         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
  float monto;
         int confirmar2;
       monto = Float.parseFloat(diferencia2.getText());
        if(monto < 0){
         int confirmar = JOptionPane.showConfirmDialog(null, "LA DIFERENCIA DEL CUADRE ES NEGATIVA , DESEA GUARDARLA ?", "GUARDAR CUADRE", JOptionPane.YES_NO_OPTION);
        if(JOptionPane.YES_OPTION == confirmar){
   
            fecha3();
        AgregarCuadre();
       imprimir01();
        MostrarCuadre();
      
        this.dispose();
        }        
        }else{
            fecha3();

  AgregarCuadre();
  MostrarCuadre();
         confirmar2 = JOptionPane.showConfirmDialog(null, "DESEA IMPRIMIR EL REPORTE DEL DIA ?","IMPRIMIR REPORTE", JOptionPane.YES_NO_OPTION);
      if(confirmar2 == JOptionPane.YES_OPTION){
   imprimir01();
       }else{
       
        this.dispose();
       }
        this.dispose();
       }
                                         
    }//GEN-LAST:event_btnguardarActionPerformed

    private void total_cajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_cajaKeyTyped
  char caracter = evt.getKeyChar();
       if (((caracter < '0') || (caracter > '9')) 
        && (caracter != evt.VK_BACK_SPACE)
        && (caracter != '.' || total_caja.getText().contains(".")) ) {
            evt.consume();
       }// TODO add your handling code here:
    }//GEN-LAST:event_total_cajaKeyTyped

    private void total_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_cajaActionPerformed
 if(total_caja.getText().equals("")){
        JOptionPane.showMessageDialog(null, "DIGITE EL MONTO EN CAJA ");
        total_caja.requestFocus();
        }else{
        cuadrar();
        btnguardar.setEnabled(true);
        }          
    }//GEN-LAST:event_total_cajaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 limpiar();
 total_caja.requestFocus();

    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(cuadre_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cuadre_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cuadre_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cuadre_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cuadre_caja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser CalendarFecha;
    private javax.swing.JButton btnguardar;
    private javax.swing.JTextField diferencia;
    private javax.swing.JTextField diferencia2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lblfecha;
    private javax.swing.JTextField lblfecha2;
    private javax.swing.JTextField lblfecha3;
    private javax.swing.JTextField monto;
    private javax.swing.JTable tblfacturaimp;
    private javax.swing.JTable tblfacturaimp2;
    private javax.swing.JTextField total6;
    private javax.swing.JTextField total7;
    private javax.swing.JTextField total_caja;
    private javax.swing.JTextField txtbuscar;
    private com.toedter.calendar.JDateChooser txtbuscar2;
    private javax.swing.JTextField txtcuadre;
    private javax.swing.JTextField txtcuentacobrar2;
    private javax.swing.JTextField txttotal3;
    private javax.swing.JTextField txtventatotal;
    private javax.swing.JTextField txtventatotal2;
    // End of variables declaration//GEN-END:variables
}
