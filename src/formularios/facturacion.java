
package formularios;

import conectar.conectar;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class facturacion extends javax.swing.JFrame {
   String codfac = "";
    String codven = "";
   public facturacion() {
        initComponents();
        jLabel1.setText("Online: "  +temporal.getNombre());
         Timer tiempo = new Timer(100, new facturacion.horas());
        tiempo.start();
        Date sistema_fecha =new Date();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
       fecha.setText(formato.format(sistema_fecha));
       sacar_texto();
       numero_factura();
      cantidad.setVisible(false);
      itbis.setVisible(false);
       sub_total.setText("0");
       total_itbis.setText("0");
      // total_descuento.setText("0");
      // desc.setText("0");
      // rebaja99.setText("0");
       rb1.setSelected(true);
        filtrar("");
    }
     void imprimir01() {
        ArrayList lista = new ArrayList();
       // lista.add("");
        for (int i = 0; i < datos.getRowCount(); i++) {

            factura_credito mortizar = new factura_credito ( datos.getValueAt(i, 1).toString(),
                    datos.getValueAt(i, 2).toString(), datos.getValueAt(i, 3).toString(),datos.getValueAt(i, 4).toString() + "");

            lista.add(mortizar);

        }
        JasperReport jr = null;
        
          try {
            jr = (JasperReport) JRLoader.loadObject(getClass().getResource("/reportes/imprimir_credito.jasper"));
           // JOptionPane.showMessageDialog(null, "EL CODIGO ES " + sub_total01+sub_total02+sub_total03);
            HashMap parametro = new HashMap();
            
            
            parametro.put("company", "TIENDA LA MUÑECA HATO DEL YAQUE EL TAMARINDO");
             parametro.put("fecha", fecha.getText());
            parametro.put("hora", hora);
           
            parametro.put("telefono3", "809-585-7590");
            parametro.put("idfactura", txtcomp.getText());
             parametro.put("cliente", cliente.getText());
            parametro.put("direccion", "EL TAMARINDO C/ 10, SANTIAGO R.D");
           
            parametro.put("tipo", "CREDITO");
             parametro.put("total", sub_total.getText());
            parametro.put("jitbis", total_itbis.getText());
//            parametro.put("descuentos", total_descuento.getText());
            parametro.put("jgeneral", total.getText());
             parametro.put("usuario",temporal.getNombre());

            //parametro.put("devolucion", "NO ACEPTAMOS DEVOLUCIONES A 3 DIAS");
            parametro.put("saludo", "GRACIAS POR PREFERIRNOS");
            JasperPrint jp = JasperFillManager.fillReport(jr, parametro, new JRBeanCollectionDataSource(lista));
            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
      void AgregarVentaDia(){
    
       String datos[]=new String [3];
          try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT idfactura FROM contador"
                        + " WHERE idfactura= '"+ txtcomp.getText()+"'");
                rs.next();
                codfac = (String.valueOf(rs.getString("idfactura")));
                
        } catch (Exception ex) {
          //  JOptionPane.showMessageDialog(null, ex);
        } 
       
       try {
            PreparedStatement pts = cn.prepareStatement("INSERT INTO ventadia(id_fac,total) VALUES (?,?)");
            pts.setString(1, txtcomp.getText());
            pts.setString(2,total.getText());
             
            
            pts.executeUpdate();
            
          } catch (SQLException ex) {
          //JOptionPane.showMessageDialog(null, ex);
        }
       
         try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT id_venta FROM ventadia"
                        + " WHERE id_fac= '"+codfac+"'");
                rs.next();
                codven= (String.valueOf(rs.getString("id_venta")));
                
        } catch (Exception ex) {
        }
        
            
        }
     
    
        void grabar_detalle() {
        String idcodigo_art = "", cantidad17 = "", precio17 = "", importe17 = "";
       
        for (int j = 0; j < datos.getRowCount(); j++) {
            idcodigo_art = (datos.getValueAt(j, 0).toString());
           cantidad17 = (datos.getValueAt(j, 2).toString());
          
            precio17 = (datos.getValueAt(j, 3).toString());
            //itbis20 = (datos.getValueAt(j, 4).toString());
            importe17 = (datos.getValueAt(j, 4).toString());
            try {
                String sql125 = "";
                sql125 = "INSERT INTO detalle_fact(no_factura,id_art,cant,precio,costo,importe) VALUES ('"+txtcomp.getText()+"','" + idcodigo_art + "','" +cantidad17+ "','" + precio17 + "','"+precio_compra+"','" + importe17 + "')";
                PreparedStatement psk22 = cn.prepareStatement(sql125);

                int n;
                n = psk22.executeUpdate();
                if (n > 0) {
                    // JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
                }
            } catch (Exception ex) {
               JOptionPane.showMessageDialog(null, "error en detalle temporal");
            }
        }
    }
     void actualizar_factura() {
       
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idfactura=idfactura + 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
            }
        
        buscar_articulo.requestFocus();
    }
     void actualizar_articulo_cantidad() {
        String codigo_art = "", cantidad_art = "";
        for (int j = 0; j < datos.getRowCount(); j++) {
            codigo_art = (datos.getValueAt(j, 0).toString());
            cantidad_art = (datos.getValueAt(j, 2).toString());
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE articulo SET cant_art=cant_art - '"+cantidad_art+"' where cod_art='" + codigo_art + "' " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
            }
        }
        buscar_articulo.requestFocus();
    }
     
     void tabla_factura() {
     
        try {
            String sql125 = "";
            sql125 = "INSERT INTO factura_normal (no_factura,fecha,sub_total,itbis,monto,idusuario,tipo_factura,forma_pago) VALUES ('" + txtcomp.getText() + "',now(),'"+sub_total.getText()+"','"+total_itbis.getText()+"','" + total.getText() + "',"
                    + "'" + idusuario.getText() + "','CONTADO','EFECTIVO')";
            PreparedStatement psk22 = cn.prepareStatement(sql125);

            int n;
            n = psk22.executeUpdate();
            if (n > 0) {
                // JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA TABLA FACTURA CONTADO");
        }
        

    }
     void reiniciarJTable(javax.swing.JTable Tabla){
        DefaultTableModel modelo = (DefaultTableModel) datos.getModel();
        while(modelo.getRowCount()>0)modelo.removeRow(0);  
     }
     void tabla_factura001() {
     
        try {
            String sql125 = "";
            sql125 = "INSERT INTO factura (no_factura,fecha,sub_total,itbis,monto,total,idcliente,idusuario,tipo_factura) VALUES ('" + txtcomp.getText() + "',now(),'"+sub_total.getText()+"','"+total_itbis.getText()+"','" + total.getText() + "','"+total.getText()+"',"
                    + "'" + idcliente.getText() + "','" + idusuario.getText() + "','CREDITO')";
            PreparedStatement psk22 = cn.prepareStatement(sql125);

            int n;
            n = psk22.executeUpdate();
            if (n > 0) {
                 JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN LA TABLA FACTURA CONTADO");
        }
        

    }
     void numero_factura() {
        String[] registros = new String[6];
        String sql = "SELECT idfactura FROM contador ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idfactura");

            }
            int numero_factura = Integer.parseInt(registros[0]);
            numero_factura = numero_factura + 1;
            txtcomp.setText(String.valueOf( "0000000"+numero_factura));
             int numero_factura2 = Integer.parseInt(registros[0]);
             numero_factura = numero_factura2 +1 ;
            txtcomp2.setText(String.valueOf( "0000000"+numero_factura2));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
 void filtrar(String valor) {
DefaultTableModel modelo2 = (DefaultTableModel)tabla_art.getModel();
     modelo2.getDataVector().clear();
      

        String[] registros = new String[6];
        String sql = "SELECT cod_art,desc_art,cant_art,pre_venta,itbis FROM articulo  where estado='existe' and cant_art >0 and  CONCAT (cod_art,'',desc_art)LIKE '%"+valor+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                registros[0] = rs.getString("cod_art");
                
                registros[1] = rs.getString("desc_art");
                registros[2] = rs.getString("cant_art");
                registros[3]=rs.getString("pre_venta");
                registros[4]=rs.getString("itbis");
                 modelo2.addRow(registros);
               }
            
               
           tabla_art.setModel(modelo2);  
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }
 	 void ConfirmarAgregado() {

		String codprod = codigo.getText();

		for (int i = 0; i < datos.getRowCount(); i++) {
			if (codprod.equals(datos.getValueAt(i, 6).toString())) {
				JOptionPane.showMessageDialog(null, "ESTE PRODUCTO YA ESTA AGREGADO", "ERROR", JOptionPane.OK_OPTION);
                              
				
				buscar_articulo.requestFocus();
                                  return;
                              
			  }
		}
              
	}
  void filtrar2(String valor) {
DefaultTableModel modelo2 = (DefaultTableModel)tabla_art.getModel();
     modelo2.getDataVector().clear();
      

        String[] registros = new String[6];
        String sql = "SELECT cod_art,desc_art,cant_art,pre_mayor,itbis FROM articulo  where estado='existe' and CONCAT (cod_art,'',desc_art)LIKE '%"+valor+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                registros[0] = rs.getString("cod_art");
                
                registros[1] = rs.getString("desc_art");
                registros[2] = rs.getString("cant_art");
                registros[3]=rs.getString("pre_mayor");
                registros[4]=rs.getString("itbis");
                 modelo2.addRow(registros);
               }
            
               
           tabla_art.setModel(modelo2);  
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }
    void validacion(){
        
        String [] registros = new String [8];
        String sql ="SELECT cod_art,desc_art FROM articulo where estado='existe'cod_art='"+buscar_articulo.getText()+"' or desc_art='"+buscar_articulo.getText()+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
           if (rs.next()){
              cargar_art01();
           }
           else{
               JOptionPane.showMessageDialog(null, "ESTE ARTICULO NO EXISTE EN EL INVENTARIO");
               buscar_articulo.setText("");
               buscar_articulo.requestFocus();
               return;
           }
                      
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    void cargar_art01() {

      

        String[] registros = new String[6];
        String sql = "SELECT cod_art,desc_art,pre_compra,pre_venta,itbis FROM articulo where cod_art='"+buscar_articulo.getText()+"' or desc_art='"+buscar_articulo.getText()+"' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                registros[0] = rs.getString("desc_art");
                
                registros[1] = rs.getString("pre_venta");
                registros[2] = rs.getString("cod_art");
                registros[3]=rs.getString("pre_compra");
                registros[4]=rs.getString("itbis");
               }
                
                des_art.setText(registros[0]);
           
            precio.setText(registros[1]);
            id_articulo = registros[2];
            precio_compra = registros[3];
            cant.requestFocus();
          
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void sacar_texto(){
        temporal global = new temporal();
        idusuario.setText(global.getTexto());
       
    }
 class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date sistemahora = new Date();
            String pmma = "hh:mm:ss a";
            SimpleDateFormat format = new SimpleDateFormat(pmma);
            Calendar hoy = Calendar.getInstance();
            hora =(String.format(format.format(sistemahora), hoy));
            hora99.setText(hora);

        }
    }
   void limpiar(){
        buscar_articulo.setText("");
        des_art.setText("");
        precio.setText("");
        cant.setText("");
        buscar_articulo.requestFocus();
        consumidor.setSelected(false);
        mayor.setSelected(false);
        filtrar(buscar_articulo.getText());
    }
     void sumar() {
         String jtotal = "", jitbis = "";
         float cv1 = 0 , cv2 = 0 , cv3 = 0;
         float suma1 = 0, suma2 = 0;
         
          for (int j = 0; j < datos.getRowCount(); j++) {
            jtotal = (datos.getValueAt(j, 4).toString());
            jitbis = (datos.getValueAt(j, 5).toString());
             //jgeneral = (datos.getValueAt(j, 4).toString());
           
           
           cv1 = Float.parseFloat(jtotal);
           cv2 = Float.parseFloat(jitbis);
       //     cv3 = Float.parseFloat(jgeneral);
           
           suma1 = suma1 + cv1;
           suma2 = suma2 + cv2;
          // suma3 = suma3 + cv2;
            
            
          }
        
         
         sub_total.setText(String.valueOf(suma1));
         total_itbis.setText(String.valueOf(suma2));
         total.setText(String.valueOf(suma1+suma2));
              
    }
    void buscar_precio_compra() {

      

        String[] registros = new String[6];
        String sql = "SELECT cod_art,desc_art,pre_compra FROM articulo where cod_art='"+buscar_articulo.getText()+"' or desc_art='"+buscar_articulo.getText()+"' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               
                
                registros[3]=rs.getString("pre_compra");
               }
            
                
               
            precio_compra = registros[3];
         
          
             
            

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            // JOptionPane.showMessageDialog(null, "EL NUMERO DE FACTURA NO EXISTE","ADVERTENCIA",JOptionPane.ERROR_MESSAGE);
        }
    }
    void llenar() {
        DefaultTableModel modelo2 = (DefaultTableModel) datos.getModel();
        //modelo2.getDataVector().clear();
        float pre_vent1 = Float.parseFloat(precio.getText());
        float cantidad = Float.parseFloat(cant.getText());
        float total = pre_vent1 * cantidad;
        float art_itbis = Float.parseFloat(itbis.getText());
        float itbis = total * art_itbis / 100;
      
       

        Object[] registros = new Object[8];
        registros[0] = id_articulo;
        registros[6] = codigo.getText();
        registros[1] = des_art.getText();
        registros[2] = String.valueOf(cantidad);
        registros[3] = precio.getText();
        registros[4] = String.valueOf(total);
        registros[5] = String.valueOf(itbis);

        modelo2.addRow(registros);

        datos.setModel(modelo2);

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        datos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        buscar_articulo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cant = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        des_art = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lb_fecha1 = new javax.swing.JLabel();
        hora99 = new javax.swing.JLabel();
        txtcomp = new javax.swing.JTextField();
        idusuario = new javax.swing.JTextField();
        fecha = new javax.swing.JTextField();
        lb_fecha = new javax.swing.JLabel();
        lb_usuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        idcliente = new javax.swing.JTextField();
        cliente = new javax.swing.JTextField();
        rnc = new javax.swing.JTextField();
        lb_fecha2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        rb1 = new javax.swing.JRadioButton();
        rb3 = new javax.swing.JRadioButton();
        lb_fecha3 = new javax.swing.JLabel();
        consumidor = new javax.swing.JCheckBox();
        mayor = new javax.swing.JCheckBox();
        txtcomp2 = new javax.swing.JTextField();
        codigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_art = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        sub_total = new javax.swing.JTextField();
        total_itbis = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        efectivo = new javax.swing.JRadioButton();
        cantidad = new javax.swing.JLabel();
        itbis = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        precio = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FACTURACION");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(153, 153, 153));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        datos.setBackground(new java.awt.Color(102, 255, 102));
        datos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "IMPORTE", "ITBIS", "codigo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datosMouseClicked(evt);
            }
        });
        datos.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                datosInputMethodTextChanged(evt);
            }
        });
        datos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                datosKeyReleased(evt);
            }
        });
        datos.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                datosVetoableChange(evt);
            }
        });
        jScrollPane5.setViewportView(datos);
        if (datos.getColumnModel().getColumnCount() > 0) {
            datos.getColumnModel().getColumn(0).setResizable(false);
            datos.getColumnModel().getColumn(1).setResizable(false);
            datos.getColumnModel().getColumn(2).setResizable(false);
            datos.getColumnModel().getColumn(3).setResizable(false);
            datos.getColumnModel().getColumn(4).setResizable(false);
            datos.getColumnModel().getColumn(5).setResizable(false);
            datos.getColumnModel().getColumn(6).setMinWidth(0);
            datos.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("BUSCAR PRODUCTO");

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

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("PRECIO");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("CANTIDAD");

        cant.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cant.setCaretColor(new java.awt.Color(0, 0, 153));
        cant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantActionPerformed(evt);
            }
        });
        cant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("PRODUCTO");

        des_art.setEditable(false);
        des_art.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        des_art.setForeground(new java.awt.Color(0, 0, 153));
        des_art.setCaretColor(new java.awt.Color(0, 0, 153));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("Agregar Producto");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ITBIS :");

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("FACTURA");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 13, -1, -1));

        lb_fecha1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha1.setText("HORA ");
        jPanel2.add(lb_fecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 56, -1, 20));

        hora99.setBackground(new java.awt.Color(255, 255, 255));
        hora99.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        hora99.setForeground(new java.awt.Color(0, 0, 153));
        jPanel2.add(hora99, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 51, 211, 25));

        txtcomp.setEditable(false);
        txtcomp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txtcomp, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 111, 182, -1));

        idusuario.setEditable(false);
        idusuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(idusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 140, 182, -1));

        fecha.setEditable(false);
        fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });
        jPanel2.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 82, 213, -1));

        lb_fecha.setBackground(new java.awt.Color(0, 0, 0));
        lb_fecha.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha.setText("FECHA");
        jPanel2.add(lb_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 86, -1, -1));

        lb_usuario.setBackground(new java.awt.Color(0, 0, 0));
        lb_usuario.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_usuario.setText("IDUSUARIO");
        jPanel2.add(lb_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 144, -1, -1));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("NO.FACTURA");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 115, -1, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("CODIGO");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 53, 70, 20));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("CLIENTE");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 87, 70, 20));

        idcliente.setEditable(false);
        idcliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(idcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 51, 214, 25));

        cliente.setEditable(false);
        cliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 82, 214, 25));

        rnc.setEditable(false);
        rnc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(rnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 116, 214, 25));

        lb_fecha2.setBackground(new java.awt.Color(0, 0, 0));
        lb_fecha2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha2.setText("NO.RNC");
        jPanel2.add(lb_fecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 120, 60, 20));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("BUSCAR CLIENTE");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 150, 210, 35));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        rb1.setBackground(new java.awt.Color(204, 204, 204));
        rb1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rb1.setText("CONTADO");
        rb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb1ActionPerformed(evt);
            }
        });

        rb3.setBackground(new java.awt.Color(204, 204, 204));
        rb3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rb3.setText("CREDITO");
        rb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb3ActionPerformed(evt);
            }
        });

        lb_fecha3.setBackground(new java.awt.Color(153, 204, 255));
        lb_fecha3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lb_fecha3.setText("TIPO DE FACTURA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(rb1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rb3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(lb_fecha3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_fecha3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb1)
                    .addComponent(rb3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 171, 275, 70));

        consumidor.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(consumidor);
        consumidor.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        consumidor.setText("PRECIO CONSUMIDOR");
        consumidor.setEnabled(false);
        consumidor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                consumidorStateChanged(evt);
            }
        });
        consumidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consumidorActionPerformed(evt);
            }
        });
        consumidor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                consumidorPropertyChange(evt);
            }
        });
        jPanel2.add(consumidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 252, -1, -1));

        mayor.setBackground(new java.awt.Color(204, 204, 204));
        buttonGroup1.add(mayor);
        mayor.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        mayor.setText("PRECIO x MAYOR");
        mayor.setEnabled(false);
        mayor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mayorStateChanged(evt);
            }
        });
        mayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mayorActionPerformed(evt);
            }
        });
        jPanel2.add(mayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 252, -1, -1));
        jPanel2.add(txtcomp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(361, 252, 0, 0));
        jPanel2.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 269, 0, 0));
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 30, 40));

        tabla_art.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_art.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO", "ITBIS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        tabla_art.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tabla_artInputMethodTextChanged(evt);
            }
        });
        tabla_art.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabla_artKeyReleased(evt);
            }
        });
        tabla_art.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                tabla_artVetoableChange(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_art);
        if (tabla_art.getColumnModel().getColumnCount() > 0) {
            tabla_art.getColumnModel().getColumn(0).setResizable(false);
            tabla_art.getColumnModel().getColumn(1).setResizable(false);
            tabla_art.getColumnModel().getColumn(2).setResizable(false);
            tabla_art.getColumnModel().getColumn(3).setResizable(false);
            tabla_art.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setText("Cancelar");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/factur.png"))); // NOI18N
        jButton3.setText("Facturar");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton5.setText("Nuevo");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton7.setText("Salir");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("SUBTOTAL ");

        sub_total.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        sub_total.setForeground(new java.awt.Color(0, 51, 255));
        sub_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub_totalActionPerformed(evt);
            }
        });

        total_itbis.setFont(new java.awt.Font("Tahoma", 1, 25)); // NOI18N
        total_itbis.setForeground(new java.awt.Color(0, 51, 255));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("ITBIS");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("TOTAL");

        total.setBackground(new java.awt.Color(0, 0, 0));
        total.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        total.setForeground(new java.awt.Color(255, 0, 0));
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(jLabel13))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sub_total, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                    .addComponent(total_itbis, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(total, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sub_total, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(total_itbis, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        efectivo.setBackground(new java.awt.Color(153, 204, 255));
        efectivo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efectivoActionPerformed(evt);
            }
        });

        cantidad.setText("jLabel1");

        itbis.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Online");

        precio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.##"))));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(efectivo))
                .addGap(263, 263, 263))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(des_art, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addGap(10, 10, 10)
                        .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cant, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itbis, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 28, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscar_articulo))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buscar_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(des_art, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cant, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(itbis, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cantidad))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(82, 82, 82)
                        .addComponent(efectivo))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_artInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tabla_artInputMethodTextChanged
        //Mensajes.mostrar("Imput");
    }//GEN-LAST:event_tabla_artInputMethodTextChanged

    private void tabla_artKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_artKeyReleased
     
    }//GEN-LAST:event_tabla_artKeyReleased

    private void tabla_artVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_tabla_artVetoableChange

    }//GEN-LAST:event_tabla_artVetoableChange

    private void datosInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_datosInputMethodTextChanged
      
    }//GEN-LAST:event_datosInputMethodTextChanged

    private void datosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datosKeyReleased
       
    }//GEN-LAST:event_datosKeyReleased

    private void datosVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_datosVetoableChange
     
    }//GEN-LAST:event_datosVetoableChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                  if (total.getText().equals("")){
                       JOptionPane.showMessageDialog(null, "DEBE FACTURAR ALGUN ARTICULO");
                       buscar_articulo.requestFocus();
                      return;
                   }
                if (rb3.isSelected()){
                        if (idcliente.getText().equals("")){
                                JOptionPane.showMessageDialog(null, "DEBE BUSCAR UN CLIENTE");
                                buscar_articulo.requestFocus();
                                return;
                            }
                        tabla_factura001();
                        AgregarVentaDia();
                        grabar_detalle();
                        actualizar_articulo_cantidad();
                        actualizar_factura();
                         imprimir01();
                        this.dispose();
                       try {
                            facturacion windows = new facturacion();
                        windows.setVisible(true);
                        windows.setLocationRelativeTo(null);
                       }catch(Exception ex){
                       
                         }
                    }
         else{

                        if (rb1.isSelected()){
                             
                           
                               tabla_factura();
                                
                                grabar_detalle();
                                AgregarVentaDia();
                                idfactura_cobro global = new idfactura_cobro();
                                
                    
                               global.setTexto(txtcomp.getText().trim());
                                actualizar_articulo_cantidad();
                                this.dispose();
                                cobrar_factura windows = new cobrar_factura();
                                windows.setVisible(true);
                                windows.setLocationRelativeTo(null);
                
                            }
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                buscar_clientes windows = new buscar_clientes("factura");
                windows.setVisible(true);
                windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
 int sle = JOptionPane.showConfirmDialog(this, " ESTAS SEGURO QUE DESEA CERRAR ESTA VENTANA ?","ADVERTENCIA",JOptionPane.YES_NO_OPTION);
        if (sle ==JOptionPane.YES_OPTION){
        
        this.dispose();   
        Menu windows = new Menu();
               windows.setVisible(true);
              windows.setLocationRelativeTo(null);//        this.dispose();
        //        menu windows = new menu();
        //        windows.setVisible(true);
        //        windows.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
       
    }//GEN-LAST:event_fechaActionPerformed

    private void buscar_articuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar_articuloActionPerformed
if (buscar_articulo.getText().equals("")){
    JOptionPane.showMessageDialog(null, "EL CODIGO O LA DESCRIPCION DE ARTICULO ESTA EN BLANCO");
    buscar_articulo.requestFocus();
    return;
    
}
validacion();        
    }//GEN-LAST:event_buscar_articuloActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  if (cant.getText().equals("")){
    JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CANTIDAD");
    cant.requestFocus();
    return;
     //ConfirmarAgregado();
  }
  
  if(Double.parseDouble(precio.getText())<0){
      JOptionPane.showMessageDialog(null, "EL PRECIO NO PUEDE SER MENOR QUE CERO");
    precio.requestFocus();
    return; 
  }
  
    if(Double.parseDouble(cant.getText())<0){
      JOptionPane.showMessageDialog(null, "LA CANTIDAD NO PUEDE SER MENOR QUE CERO");
    cant.requestFocus();
    return; 
  }
  
  
  if(Double.parseDouble(cant.getText())>Double.parseDouble(cantidad.getText())){
                                    JOptionPane.showMessageDialog(null,"El producto no tiene la suficiente cantidad disponible para vender dicha cantidad.");
                                    buscar_articulo.requestFocus();
                                    limpiar();
                                    return;
                                }
  
		String codprod = codigo.getText();
                   double sumaPro=0;
		for (int i = 0; i < datos.getRowCount(); i++) {
			if (codprod.equals(datos.getValueAt(i, 6).toString())) {
                                sumaPro+=Double.parseDouble(datos.getValueAt(i, 6).toString());
                                if(sumaPro>Double.parseDouble(cantidad.getText())){
                                    JOptionPane.showMessageDialog(null,"El producto no tiene la suficiente cantidad disponible para vender dicha cantidad.");
                                    buscar_articulo.requestFocus();
                                    limpiar();
                                    return;
                                }
				
			  }
		}
	
                llenar();
                sumar();
                limpiar();        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buscar_articuloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar_articuloKeyReleased
filtrar(buscar_articulo.getText());    
    }//GEN-LAST:event_buscar_articuloKeyReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
buscar_articulo.requestFocus();       
    }//GEN-LAST:event_formWindowActivated

    private void datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datosMouseClicked
  DefaultTableModel modelo2 = (DefaultTableModel) datos.getModel();
         // modelo2.getDataVector().clear();

        int fila = datos.getSelectedRow();
        if (fila >= 0) {
            int sle = JOptionPane.showConfirmDialog(this, " ESTAS SEGURO QUE DESEA ELIMINAR ESTE ARTICULO ?","ADVERTENCIA",JOptionPane.YES_NO_OPTION);
            if (sle ==JOptionPane.YES_OPTION){
                modelo2.removeRow(fila);
                sumar();
                buscar_articulo.requestFocus();
            }
            if (sle==JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(null, "CANCELADO","ARTICULO NO ANULADO",JOptionPane.CANCEL_OPTION);
            }
        }          
    }//GEN-LAST:event_datosMouseClicked

    private void tabla_artMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_artMouseClicked
 int fila = tabla_art.getSelectedRow();
        if (fila>=0){
            
            if(idcliente.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente");
                return;
            }
            
            
              buscar_articulo.setText(tabla_art.getValueAt(fila, 0).toString());
              codigo.setText(tabla_art.getValueAt(fila, 0).toString());
              des_art.setText(tabla_art.getValueAt(fila, 1).toString());
              cantidad.setText(tabla_art.getValueAt(fila, 2).toString());
              precio.setText(tabla_art.getValueAt(fila, 3).toString());
              itbis.setText(tabla_art.getValueAt(fila, 4).toString());
              
        }
        
       id_articulo = buscar_articulo.getText();
       buscar_precio_compra();
         
      
        cant.requestFocus();        
    }//GEN-LAST:event_tabla_artMouseClicked

    private void sub_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub_totalActionPerformed
        
    }//GEN-LAST:event_sub_totalActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
       
    }//GEN-LAST:event_totalActionPerformed

    private void rb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb3ActionPerformed
 rb3.setSelected(true);
  rb1.setSelected(false);        
    }//GEN-LAST:event_rb3ActionPerformed

    private void cantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantKeyReleased
                              
   
    }//GEN-LAST:event_cantKeyReleased

    private void cantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantActionPerformed
 if (cant.getText().equals("")){
    JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CANTIDAD");
    cant.requestFocus();
    return;
}
  {

		String codprod = codigo.getText();

		for (int i = 0; i < datos.getRowCount(); i++) {
			if (codprod.equals(datos.getValueAt(i, 6).toString())) {
				JOptionPane.showMessageDialog(null, "ESTE PRODUCTO YA ESTA AGREGADO", "ERROR", JOptionPane.OK_OPTION);
                              
				
				buscar_articulo.requestFocus();
                                  limpiar();
                                  return;
                                
                              
			  }
		}
              
	}
llenar();
sumar();
limpiar();        
    }//GEN-LAST:event_cantActionPerformed

    private void rb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb1ActionPerformed

 rb3.setSelected(false);
  rb1.setSelected(true);      
    }//GEN-LAST:event_rb1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
limpiar();
reiniciarJTable(datos);


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//limpiar(); 
 AgregarVentaDia();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void consumidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consumidorActionPerformed
 if(idcliente.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente");
                return;
            }
        filtrar(buscar_articulo.getText());      
    }//GEN-LAST:event_consumidorActionPerformed

    private void mayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mayorActionPerformed
         if(idcliente.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente");
                return;
            }
        filtrar2(buscar_articulo.getText()); 
    }//GEN-LAST:event_mayorActionPerformed

    private void efectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_efectivoActionPerformed

    private void cantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantKeyTyped
 char c = evt.getKeyChar();
        if (c<'0'  || c >'9') evt.consume();        // TODO add your handling code here:
    }//GEN-LAST:event_cantKeyTyped

    private void consumidorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_consumidorPropertyChange
     
    }//GEN-LAST:event_consumidorPropertyChange

    private void mayorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mayorStateChanged
       filtrar2(buscar_articulo.getText());
    }//GEN-LAST:event_mayorStateChanged

    private void consumidorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_consumidorStateChanged
        filtrar(buscar_articulo.getText());
    }//GEN-LAST:event_consumidorStateChanged

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new facturacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscar_articulo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cant;
    private javax.swing.JLabel cantidad;
    public static javax.swing.JTextField cliente;
    private javax.swing.JTextField codigo;
    public static javax.swing.JCheckBox consumidor;
    public static javax.swing.JTable datos;
    private javax.swing.JTextField des_art;
    public static javax.swing.JRadioButton efectivo;
    private javax.swing.JTextField fecha;
    private javax.swing.JLabel hora99;
    public static javax.swing.JTextField idcliente;
    private javax.swing.JTextField idusuario;
    private javax.swing.JLabel itbis;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lb_fecha;
    private javax.swing.JLabel lb_fecha1;
    private javax.swing.JLabel lb_fecha2;
    private javax.swing.JLabel lb_fecha3;
    private javax.swing.JLabel lb_usuario;
    public static javax.swing.JCheckBox mayor;
    private javax.swing.JFormattedTextField precio;
    public static javax.swing.JRadioButton rb1;
    public static javax.swing.JRadioButton rb3;
    public static javax.swing.JTextField rnc;
    private javax.swing.JTextField sub_total;
    public static javax.swing.JTable tabla_art;
    private javax.swing.JTextField total;
    private javax.swing.JTextField total_itbis;
    private javax.swing.JTextField txtcomp;
    private javax.swing.JTextField txtcomp2;
    // End of variables declaration//GEN-END:variables

String id_articulo = "", precio_compra = "",hora = "";
  conectar con = new conectar();
      Connection cn = con.connection();

}
