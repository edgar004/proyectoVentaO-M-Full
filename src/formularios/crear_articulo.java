

package formularios;


import conectar.conectar;
import static formularios.Clientes_mantenimiento.rnc;
import static formularios.Clientes_mantenimiento.txt_fecha;
import static formularios.proveedor.calle;
import static formularios.proveedor.celular;
import static formularios.proveedor.ciudad;
import static formularios.proveedor.razon_social;
import static formularios.proveedor.representante;
import static formularios.proveedor.residencial;
import static formularios.proveedor.rnc;
import static formularios.proveedor.sector;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class crear_articulo extends javax.swing.JFrame {

   
    public crear_articulo() {
        initComponents();
        Date fechaActual = new Date();
        txt_fecha.setDate(fechaActual);
         id_art.requestFocus();
         this.setLocationRelativeTo(null);
        
         
    }
    
    
    void capturarFecha(){
        Date fecha = new Date();
        
    }    
    
    
    void buscar02() {

        String[] registros = new String[20];
        String sql = "SELECT cod_art,des_art FROM articulo where cod_art='" + id + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
         if (rs.next()){
             JOptionPane.showMessageDialog(null, "NO SE PUEDE DUPLICAR UN ARTICULO");
             limpiar();
             
         }
         else{
             guardar();
limpiar();
actualizar_codigo();
this.dispose();
crear_articulo windows = new crear_articulo();
windows.setVisible(true);
windows.setLocationRelativeTo(null);
         }
        

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     void actualizar() {
       
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET id_art=id_art- 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
                Logger.getLogger(crear_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       des_art.requestFocus();
    }
     void actualizar_codigo() {
       
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET id_art=id_art + 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
                Logger.getLogger(crear_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        id_art.requestFocus();
    }
   
     void buscar() {

        String[] registros = new String[15];
        String sql = "SELECT * FROM articulo INNER JOIN proveedor on proveedor.idproveedor=articulo.id_proveedor INNER JOIN marca on marca.id_marca=articulo.id_marca where cod_art='" + id + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("cod_art");
                registros[1] = rs.getString("desc_art");
                registros[2] = rs.getString("cate_art");
                registros[3] = rs.getString("id_marca");
                registros[4] = rs.getString("no_marca");
                registros[5] = rs.getString("talla_art");
                registros[6]=rs.getString("cant_art");
                registros[7]=rs.getString("pre_compra");
                 registros[8] = rs.getString("pre_mayor");
                 registros[9] = rs.getString("pre_venta");
                 registros[10] = rs.getString("reorden");
                 registros[11] = rs.getString("id_proveedor");
                 registros[12]=rs.getString("representante");
                  registros[13]=rs.getString("itbis");
                 registros[14] = rs.getString("fecha_art");
                  
                  
                }
            
            des_art.setText(registros[0]);
            
           
            pre_com.setText(registros[3]);
            pre_mayor.setText(registros[4]);
            pre_consumidor.setText(registros[4]);
            
            cant.setText(registros[6]);
        

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void validacion(){
        
        String [] registros = new String [8];
        String sql ="SELECT cod_art,desc_art FROM articulo where cod_art='"+id_art.getText()+"'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
           if (rs.next()){
               registros[0]=rs.getString("desc_art");
               JOptionPane.showMessageDialog(null, "ESTE CODIGO ESTA COMO "+registros[0]);
               id_art.setText("");
               id_art.requestFocus();
               return;
           }
           
           
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     void limpiar02(){
        
        id_art.setText("");
        des_art.setText("");
        cant.setText("");
        pre_com.setText("");
       talla.setText("");
       categoria.setSelectedItem("SELECCIONAR");
       id_marca.setText("");
       id_proveedor.setText("");
        pre_mayor.setText("");
        reorden.setText("");
        pre_consumidor.setText("");
        cant.setText("");
        comboxItbis.setSelectedIndex(0);
         Date fechaActual = new Date();
        txt_fecha.setDate(fechaActual);
        id_art.requestFocus();
           jButton1.setEnabled(true);
    jButton3.setEnabled(false);
   activar_campos();
    }
     
     
     void activar_campos(){
           id_art.setEditable(true);
        des_art.setEditable(true);
        talla.setEditable(true);
        id_marca.setEditable(true);
        pre_com.setEditable(true);
        pre_mayor.setEditable(true);
        pre_consumidor.setEditable(true);
        reorden.setEditable(true);
        id_proveedor.setEditable(true);
        id_proveedor.setEditable(true);
        comboxItbis.setEditable(true);
        cant.setEditable(true);
        
        jButton1.setEnabled(true);
        jButton3.setEnabled(true);
        
    }
     
     void desactivar_campos(){
           id_art.setEditable(false);
        des_art.setEditable(false);
        talla.setEditable(false);
        id_marca.setEditable(false);
        pre_com.setEditable(false);
        pre_mayor.setEditable(false);
        pre_consumidor.setEditable(false);
        reorden.setEditable(false);
        id_proveedor.setEditable(false);
        id_proveedor.setEditable(false);
        comboxItbis.setEditable(false);
        cant.setEditable(false);
        
         guardar.setEnabled(false);
        jButton1.setEnabled(false);
        jButton3.setEnabled(false);
        
    }
     void limpiar(){
        id_art.setText("");
        reorden.setText("");
        des_art.setText("");
        cant.setText("");
        pre_com.setText("");
       talla.setText("");
       categoria.setSelectedItem("SELECCIONAR");
       id_marca.setText("");
       id_proveedor.setText("");
        pre_mayor.setText("");
        pre_consumidor.setText("");
        cant.setText("");
        comboxItbis.setSelectedIndex(0);
          Date fechaActual = new Date();
        txt_fecha.setDate(fechaActual);
        desactivar_campos();
        
    }
   
     
     String fecha(){
            String fecha11;
      java.util.Date fecha = txt_fecha.getDate();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      fecha11 = formato.format(fecha);
     return fecha11;
     }
   void guardar(){
      String fecha01 = fecha();  
       try {
           String sql125= "";
           sql125 = "INSERT INTO articulo (cod_art,id_marca,id_proveedor,desc_art,cate_art,talla_art,cant_art,pre_compra,pre_venta,pre_mayor,reorden,itbis,fecha_art) VALUES('"+id_art.getText()+"',"
                   + "'"+id_marca.getText()+"','"+id_proveedor.getText()+"',"
                   + "'"+des_art.getText()+"',"
                   + "'"+categoria.getSelectedItem()+"','"+talla.getText()+"','"+cant.getText()+"','"+pre_com.getText()+"','"+pre_consumidor.getText()+"','"+pre_mayor.getText()+"','"+reorden.getText()+"','"+comboxItbis.getSelectedItem()+"','"+fecha01+"')";
                  
                  
           PreparedStatement psk22 = cn.prepareStatement(sql125);
           
             int n ;
             n = psk22.executeUpdate();
             if (n > 0){
                 JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
             }
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
           JOptionPane.showMessageDialog(null, "ERROR EN GUARDAR DATOS ","NO SE PUEDE REGISTRAR",JOptionPane.ERROR_MESSAGE);
       }
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        id_art = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        des_art = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cant = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pre_consumidor = new javax.swing.JFormattedTextField();
        pre_mayor = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        id_proveedor = new javax.swing.JTextField();
        categoria = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txt_fecha = new com.toedter.calendar.JDateChooser();
        pre_com = new javax.swing.JFormattedTextField();
        jButton7 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        id_marca = new javax.swing.JTextField();
        reorden = new javax.swing.JFormattedTextField();
        talla = new javax.swing.JTextField();
        comboxItbis = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        guardar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton5.setText("jButton3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CREAR ARTICULO");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setEnabled(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("CODIGO ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 70, 30));

        id_art.setEditable(false);
        id_art.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        id_art.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_artActionPerformed(evt);
            }
        });
        jPanel2.add(id_art, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 184, 25));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("DESCRIPCION");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 100, 30));

        des_art.setEditable(false);
        des_art.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        des_art.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                des_artActionPerformed(evt);
            }
        });
        des_art.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                des_artKeyTyped(evt);
            }
        });
        jPanel2.add(des_art, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 184, 25));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("CATEGORIA");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("PRECIO COMPRA");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("REORDEN");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 70, 30));

        cant.setEditable(false);
        cant.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        cant.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
        jPanel2.add(cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 180, 25));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("CONSUMIDOR");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, 30));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("PRECIO X MAYOR");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, 30));

        pre_consumidor.setEditable(false);
        pre_consumidor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        pre_consumidor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pre_consumidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pre_consumidorActionPerformed(evt);
            }
        });
        pre_consumidor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pre_consumidorKeyTyped(evt);
            }
        });
        jPanel2.add(pre_consumidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 180, 25));

        pre_mayor.setEditable(false);
        pre_mayor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        pre_mayor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pre_mayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pre_mayorActionPerformed(evt);
            }
        });
        pre_mayor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pre_mayorKeyTyped(evt);
            }
        });
        jPanel2.add(pre_mayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 180, 25));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("CANTIDAD");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("MARCA");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 60, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("TALLA");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 97, 50, 40));

        id_proveedor.setEditable(false);
        id_proveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        id_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_proveedorActionPerformed(evt);
            }
        });
        jPanel2.add(id_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 180, 25));

        categoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "CABALLEROS", "DAMAS", "NIÑOS" }));
        jPanel2.add(categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 180, 25));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setText("FECHA");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, 30));

        txt_fecha.setEnabled(false);
        jPanel2.add(txt_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 180, 25));

        pre_com.setEditable(false);
        pre_com.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        pre_com.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pre_com.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pre_comActionPerformed(evt);
            }
        });
        pre_com.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pre_comKeyTyped(evt);
            }
        });
        jPanel2.add(pre_com, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 180, 25));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton7.setText("PROVEEDOR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 180, 25));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("PROVEEDOR");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 90, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("ITBIS");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 40, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("BUSCAR MARCA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 180, 25));

        id_marca.setEditable(false);
        id_marca.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        id_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_marcaActionPerformed(evt);
            }
        });
        jPanel2.add(id_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 180, 25));

        reorden.setEditable(false);
        reorden.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        reorden.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reorden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reordenActionPerformed(evt);
            }
        });
        reorden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                reordenKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                reordenKeyTyped(evt);
            }
        });
        jPanel2.add(reorden, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 180, 25));

        talla.setEditable(false);
        talla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        talla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tallaActionPerformed(evt);
            }
        });
        jPanel2.add(talla, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 180, 25));

        comboxItbis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "18", "16" }));
        jPanel2.add(comboxItbis, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 90, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 790, 240));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        guardar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        guardar.setText("Guardar");
        guardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        guardar.setEnabled(false);
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        guardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                guardarKeyPressed(evt);
            }
        });
        jPanel3.add(guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 12, 136, 40));

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton4.setText("Modificar");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 12, 148, 40));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(671, 12, 145, 40));

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton6.setText("Nuevo");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 12, 149, 40));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setText("Salir");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(873, 12, 146, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 1030, 70));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("REGISTRO DE ARTICULOS");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, -1, -1));

        jButton8.setBackground(new java.awt.Color(204, 204, 204));
        jButton8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton8.setText("BUSCAR ARTICULO");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, 220, 50));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 260, 210));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         limpiar02();
            if(temporal.privilegiosUser[6].crear==1){
                  guardar.setEnabled(true);
                 cant.setEnabled(true);
            }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (id_art.getText().equals("")){
            JOptionPane.showMessageDialog(null,"EL CODIGO ESTA EN BLANCO");
            id_art.requestFocus();
            return;
        }

        if (des_art.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA DESCRIPCION");
            des_art.requestFocus();
            return;
        }

        if (categoria.getSelectedItem().equals("SELECCIONAR")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CATEGORIA");
            categoria.requestFocus();
            return;
        }

        if (talla.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA TALLA");
            talla.requestFocus();
            return;
        }

        if (cant.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CANTIDAD");
            cant.requestFocus();
            return;
        }

        if (id_marca.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA MARCA");
            id_marca.requestFocus();
            return;
        }

        if (pre_com.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PRECIO DE COMPRA");
            pre_com.requestFocus();
            return;
        }

        if (pre_mayor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PRECIO DE POR MAYOR");
            pre_mayor.requestFocus();
            return;
        }

        if (pre_consumidor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PRECIO AL CONSUMIDOR");
            pre_consumidor.requestFocus();
            return;
        }

        if (reorden.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CANTIDAD DE REORDEN");
            reorden.requestFocus();
            return;
        }

        if (txt_fecha.getDate()==null){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA FECHA");
            txt_fecha.requestFocus();
            return;
        }

        if (id_proveedor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PROVEEDOR");
            id_proveedor.requestFocus();
            return;
        }

//        if (itbis.getText().equals("")){
//            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL ITBIS");
//            itbis.requestFocus();
//            return;
//        }

        try {
            PreparedStatement psU = cn.prepareStatement("update articulo set estado='no_existe' where cod_art='"+id_art.getText()+"'");
            psU.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS ELIMINADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(crear_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizar();
        limpiar();
        //this.dispose();
        //crear_articulo windows = new crear_articulo ();
        //windows.setVisible(true);
        //windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (id_art.getText().equals("")){
            JOptionPane.showMessageDialog(null,"EL CODIGO ESTA EN BLANCO");
            id_art.requestFocus();
            return;
        }

        if (des_art.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA DESCRIPCION");
            des_art.requestFocus();
            return;
        }

        if (categoria.getSelectedItem().equals("SELECCIONAR")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CATEGORIA");
            categoria.requestFocus();
            return;
        }

        if (talla.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA TALLA");
            talla.requestFocus();
            return;
        }

        if (cant.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CANTIDAD");
            cant.requestFocus();
            return;
        }

        if (id_marca.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA MARCA");
            id_marca.requestFocus();
            return;
        }

        if (pre_com.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PRECIO DE COMPRA");
            pre_com.requestFocus();
            return;
        }

        if (pre_mayor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PRECIO DE POR MAYOR");
            pre_mayor.requestFocus();
            return;
        }

        if (pre_consumidor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PRECIO AL CONSUMIDOR");
            pre_consumidor.requestFocus();
            return;
        }

        if (reorden.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CANTIDAD DE REORDEN");
            reorden.requestFocus();
            return;
        }

        if (txt_fecha.getDate()==null){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA FECHA");
            txt_fecha.requestFocus();
            return;
        }

        if (id_proveedor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PROVEEDOR");
            id_proveedor.requestFocus();
            return;
        }

//        if (itbis.getText().equals("")){
//            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL ITBIS");
//            itbis.requestFocus();
//            return;
//        }

        double cantidad=Integer.parseInt(cant.getText());

        if (cantidad < 0){
            JOptionPane.showMessageDialog(null,"EN CANTIDAD DEBE INGRESAR VALORES MAYORES QUE 0");
            cant.requestFocus();
            return;
        }

        double precio_compra=Double.parseDouble(pre_com.getText());

        if (precio_compra < 0){
            JOptionPane.showMessageDialog(null,"EN EL PRECIO DE COMPRA DEBE INGRESAR VALORES MAYORES QUE 0");
            pre_com.requestFocus();
            return;
        }

        double premayor=Double.parseDouble(pre_mayor.getText());

        if (premayor < 0){
            JOptionPane.showMessageDialog(null,"EN EL PRECIO POR MAYOR  DEBE INGRESAR VALORES MAYORES QUE 0");
            pre_mayor.requestFocus();
            return;
        }

        double cosumidor=Double.parseDouble(pre_consumidor.getText());

        if (cosumidor < 0){
            JOptionPane.showMessageDialog(null,"EN EL PRECIO CONSUMIDOR  DEBE INGRESAR VALORES MAYORES QUE 0");
            pre_consumidor.requestFocus();
            return;
        }

        double Reorden=Double.parseDouble(reorden.getText());

        if (Reorden < 0){
            JOptionPane.showMessageDialog(null,"EN EL PRECIO REORDEN  DEBE INGRESAR VALORES MAYORES QUE 0");
            reorden.requestFocus();
            return;
        }

//        if (Reorden < 0){
//            JOptionPane.showMessageDialog(null,"EN EL  ITBIS  DEBE INGRESAR VALORES MAYORES QUE 0");
//            itbis.requestFocus();
//            return;
//        }

        if(premayor<precio_compra){
            JOptionPane.showMessageDialog(null,"EL PRECIO POR MAYOR NO DEBE SER MENOR QUE EL PRECIO COMPRA");
            pre_mayor.requestFocus();
            return;
        }

        if(cosumidor<precio_compra){
            JOptionPane.showMessageDialog(null,"EL PRECIO CONSUMIDOR NO DEBE SER MENOR QUE EL PRECIO COMPRA");
            pre_consumidor.requestFocus();
            return;
        }

        if(cosumidor<premayor){
            JOptionPane.showMessageDialog(null,"EL PRECIO CONSUMIDOR NO DEBE SER MENOR QUE EL PRECIO POR MAYOR");
            pre_consumidor.requestFocus();
            return;
        }

        try {
            String fecha01 = fecha();
            PreparedStatement psU = cn.prepareStatement("UPDATE articulo SET cod_art='"+id_art.getText()+"', id_marca='"+id_marca.getText()+"'"
                + ",id_proveedor='"+id_proveedor.getText()+"',desc_art ='"+des_art.getText()+"',cate_art= '"+categoria.getSelectedItem()+"',talla_art= '"+talla.getText()+"',"
                + " cant_art = '"+cant.getText()+"',pre_compra='"+pre_com.getText()+"',pre_venta='"+pre_consumidor.getText()+"',pre_mayor='"+pre_mayor.getText()+"',reorden='"+reorden.getText()+"',itbis='"+comboxItbis.getSelectedItem()+"',fecha_art='"+fecha01+"' where cod_art='"+id_art.getText()+"'");

            psU.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(crear_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void guardarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_guardarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            guardar();
            limpiar();
            actualizar_codigo();
            this.dispose();
            crear_articulo windows = new crear_articulo();
            windows.setVisible(true);
            windows.setLocationRelativeTo(null);

        }
    }//GEN-LAST:event_guardarKeyPressed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        if (id_art.getText().equals("")){
            JOptionPane.showMessageDialog(null,"EL CODIGO ESTA EN BLANCO");
            id_art.requestFocus();
            return;
        }

        if (des_art.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA DESCRIPCION");
            des_art.requestFocus();
            return;
        }

        if (categoria.getSelectedItem().equals("SELECCIONAR")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CATEGORIA");
            categoria.requestFocus();
            return;
        }

        if (talla.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA TALLA");
            talla.requestFocus();
            return;
        }

        if (cant.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CANTIDAD");
            cant.requestFocus();
            return;
        }

        if (id_marca.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA MARCA");
            id_marca.requestFocus();
            return;
        }

        if (pre_com.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PRECIO DE COMPRA");
            pre_com.requestFocus();
            return;
        }

        if (pre_mayor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PRECIO DE POR MAYOR");
            pre_mayor.requestFocus();
            return;
        }

        if (pre_consumidor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PRECIO AL CONSUMIDOR");
            pre_consumidor.requestFocus();
            return;
        }

        if (reorden.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CANTIDAD DE REORDEN");
            reorden.requestFocus();
            return;
        }

        if (txt_fecha.getDate()==null){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA FECHA");
            txt_fecha.requestFocus();
            return;
        }

        if (id_proveedor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL PROVEEDOR");
            id_proveedor.requestFocus();
            return;
        }

//        if (itbis.getText().equals("")){
//            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL ITBIS");
//            itbis.requestFocus();
//            return;
//        }

        double cantidad=Integer.parseInt(cant.getText());

        if (cantidad < 0){
            JOptionPane.showMessageDialog(null,"EN CANTIDAD DEBE INGRESAR VALORES MAYORES QUE 0");
            cant.requestFocus();
            return;
        }

        double precio_compra=Double.parseDouble(pre_com.getText());

        if (precio_compra < 0){
            JOptionPane.showMessageDialog(null,"EN EL PRECIO DE COMPRA DEBE INGRESAR VALORES MAYORES QUE 0");
            pre_com.requestFocus();
            return;
        }

        double premayor=Double.parseDouble(pre_mayor.getText());

        if (premayor < 0){
            JOptionPane.showMessageDialog(null,"EN EL PRECIO POR MAYOR  DEBE INGRESAR VALORES MAYORES QUE 0");
            pre_mayor.requestFocus();
            return;
        }

        double cosumidor=Double.parseDouble(pre_consumidor.getText());

        if (cosumidor < 0){
            JOptionPane.showMessageDialog(null,"EN EL PRECIO CONSUMIDOR  DEBE INGRESAR VALORES MAYORES QUE 0");
            pre_consumidor.requestFocus();
            return;
        }

        double Reorden=Double.parseDouble(reorden.getText());

        if (Reorden < 0){
            JOptionPane.showMessageDialog(null,"EN EL PRECIO REORDEN  DEBE INGRESAR VALORES MAYORES QUE 0");
            reorden.requestFocus();
            return;
        }

//        if (Reorden < 0){
//            JOptionPane.showMessageDialog(null,"EN EL  ITBIS  DEBE INGRESAR VALORES MAYORES QUE 0");
//            itbis.requestFocus();
//            return;
//        }

        if(premayor<precio_compra){
            JOptionPane.showMessageDialog(null,"EL PRECIO POR MAYOR NO DEBE SER MENOR QUE EL PRECIO COMPRA");
            pre_mayor.requestFocus();
            return;
        }

        if(cosumidor<precio_compra){
            JOptionPane.showMessageDialog(null,"EL PRECIO CONSUMIDOR NO DEBE SER MENOR QUE EL PRECIO COMPRA");
            pre_consumidor.requestFocus();
            return;
        }

        if(cosumidor<premayor){
            JOptionPane.showMessageDialog(null,"EL PRECIO CONSUMIDOR NO DEBE SER MENOR QUE EL PRECIO POR MAYOR");
            pre_consumidor.requestFocus();
            return;
        }
        if(validarCODIGO()==0){
            return;
        }

        guardar();
        limpiar();
    }//GEN-LAST:event_guardarActionPerformed

    private void tallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tallaActionPerformed
        cant.requestFocus();
    }//GEN-LAST:event_tallaActionPerformed

    private void reordenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reordenKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_reordenKeyTyped

    private void reordenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_reordenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_reordenKeyReleased

    private void reordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reordenActionPerformed
        id_proveedor.requestFocus();
    }//GEN-LAST:event_reordenActionPerformed

    private void id_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_marcaActionPerformed
        pre_com.requestFocus();
    }//GEN-LAST:event_id_marcaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        marca windows = new marca();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        buscador_proveedor windows = new buscador_proveedor("articulo");
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void pre_comKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pre_comKeyTyped
        char c = evt.getKeyChar();
        if ((c<'0'  || c >'9')&& (c!= KeyEvent.VK_ENTER)&& (c!= KeyEvent.VK_DELETE) ) {

            evt.consume();
        }
    }//GEN-LAST:event_pre_comKeyTyped

    private void pre_comActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pre_comActionPerformed
        if(pre_com.getText().equals("")){
            compra ="0";
        }else{
            compra = pre_com.getText();
        }
        pre_mayor.requestFocus();
    }//GEN-LAST:event_pre_comActionPerformed

    private void id_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_proveedorActionPerformed
        //itbis.requestFocus();
    }//GEN-LAST:event_id_proveedorActionPerformed

    private void pre_mayorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pre_mayorKeyTyped
        char c = evt.getKeyChar();
        if ((c<'0'  || c >'9')&& (c!= KeyEvent.VK_ENTER)&& (c!= KeyEvent.VK_DELETE) ) {

            evt.consume();
        }
    }//GEN-LAST:event_pre_mayorKeyTyped

    private void pre_mayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pre_mayorActionPerformed
        pre_consumidor.requestFocus();
    }//GEN-LAST:event_pre_mayorActionPerformed

    private void pre_consumidorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pre_consumidorKeyTyped
        char c = evt.getKeyChar();
        if ((c<'0'  || c >'9')&& (c!= KeyEvent.VK_ENTER)&& (c!= KeyEvent.VK_DELETE) ) {

            evt.consume();
        }
    }//GEN-LAST:event_pre_consumidorKeyTyped

    private void pre_consumidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pre_consumidorActionPerformed
        reorden.requestFocus();
    }//GEN-LAST:event_pre_consumidorActionPerformed

    private void cantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantKeyTyped
        char c = evt.getKeyChar();
        if ((c<'0'  || c >'9')&& (c!= KeyEvent.VK_ENTER)&& (c!= KeyEvent.VK_DELETE) ) {

            evt.consume();
            JOptionPane.showMessageDialog(null, "Debe ingresar solo numeros","Acceso denegado",JOptionPane.WARNING_MESSAGE);  }
    }//GEN-LAST:event_cantKeyTyped

    private void cantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantKeyReleased

    }//GEN-LAST:event_cantKeyReleased

    private void cantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantActionPerformed
        id_marca.requestFocus();
    }//GEN-LAST:event_cantActionPerformed

    private void des_artKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_des_artKeyTyped

    }//GEN-LAST:event_des_artKeyTyped

    private void des_artActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_des_artActionPerformed
        talla.requestFocus();
    }//GEN-LAST:event_des_artActionPerformed

    private void id_artActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_artActionPerformed
        des_art.requestFocus();
    }//GEN-LAST:event_id_artActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        buscar_articulos window = new buscar_articulos();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
          if(temporal.privilegiosUser[6].modificar==1) jButton4.setEnabled(true);
        guardar.setEnabled(false);
          
    }//GEN-LAST:event_jButton8ActionPerformed
int validarCODIGO(){
     String sql = "SELECT * FROM articulo where cod_art='" + id_art.getText() + "'";
        try {
            String [] registros = new String [13];
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
               JOptionPane.showMessageDialog(null,"YA EXISTE UN PRODUCTO CON ESE CODIGO ARTICULO");
               return 0;
            }else{
            return 1;
            }
            }catch(Exception e){
          return 0;

            }
}
    
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crear_articulo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JFormattedTextField cant;
    public static javax.swing.JComboBox<String> categoria;
    public static javax.swing.JComboBox<String> comboxItbis;
    public static javax.swing.JTextField des_art;
    private javax.swing.JButton guardar;
    public static javax.swing.JTextField id_art;
    public static javax.swing.JTextField id_marca;
    public static javax.swing.JTextField id_proveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JFormattedTextField pre_com;
    public static javax.swing.JFormattedTextField pre_consumidor;
    public static javax.swing.JFormattedTextField pre_mayor;
    public static javax.swing.JFormattedTextField reorden;
    public static javax.swing.JTextField talla;
    public static com.toedter.calendar.JDateChooser txt_fecha;
    // End of variables declaration//GEN-END:variables
public static String id = "", compra="0";
   conectar con = new conectar();
      Connection cn = con.connection();


}
