
package formularios;


import conectar.conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class proveedor extends javax.swing.JFrame {

    
    public proveedor() {
        initComponents();
        numero_serie();
        Date fechaActual = new Date();
        txt_fecha.setDate(fechaActual);
        representante.requestFocus();
        this.setLocationRelativeTo(null);
       
    }
    
    void consulta() {

        String[] registros = new String[4];
        String sql = "SELECT idproveedor,representante,razon_social FROM proveedor where idproveedor='"+id+"'  ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
          if (rs.next()){
              registros[0]=rs.getString("representante");
              registros[1]=rs.getString("razon_social");
              JOptionPane.showMessageDialog(null, "EL PROVEEDOR ESTA COMO "+registros[0]+" "+registros[1]);
              limpiar();
              numero_serie();
          }
          else{
        guardar();
        guardar_telefono();
        guardar_celular();
        limpiar();
        actualizar_codigo();
        this.dispose();
        proveedor windows = new proveedor();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);  
          }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "error en telefonos");
        }
     }
       

    void actualizar() {
        
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idproveedor=idproveedor - 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
                Logger.getLogger(proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       representante.requestFocus();
    }
     void actualizar_codigo() {
       
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idproveedor=idproveedor + 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
                Logger.getLogger(proveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        idproveedor.requestFocus();
    }
      void numero_serie() {
        String[] registros = new String[6];
        String sql = "SELECT idproveedor FROM contador ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idproveedor");

            }
            int numero_serie = Integer.parseInt(registros[0]);
            numero_serie = numero_serie + 1;
            idproveedor.setText(String.valueOf( numero_serie));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
      
        void buscar_telefono() {

        String[] registros = new String[4];
        String sql = "SELECT tabla_proveedor.numero,tabla_proveedor.id_tipo_tel FROM tabla_proveedor where tabla_proveedor.idproveedor='"+id+"'  ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
             while (rs.next()) {
                registros[0] = rs.getString("id_tipo_tel");
                if (registros[0].equals("1")) {
                    registros[1] = rs.getString("numero");
                    celular.setText(registros[1]);
                }
                if (registros[0].equals("2")) {
                    registros[1] = rs.getString("numero");
                    residencial.setText(registros[1]);
                }

            }        
          

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "error en telefonos");
        }
    }
        void buscar() {

        String[] registros = new String[10];
        String sql = "SELECT * FROM proveedor where idproveedor='" + id + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
               registros[0]=rs.getString("idproveedor");
                registros[1]=rs.getString("representante");
                registros[2]=rs.getString("razon_social");
                registros[3]=rs.getString("rnc");
                
                  registros[4]=rs.getString("calle");
                  registros[5]=rs.getString("sector");
                  registros[6]=rs.getString("ciudad");


                 String sqlCelular ="SELECT * FROM tabla_proveedor where idproveedor='"+registros[0]+"' and id_tipo_tel=1";
                 Statement stCelular  = cn.createStatement();
                ResultSet rsCelular  = stCelular .executeQuery(sqlCelular);
                
                if(rsCelular.next()){
                    registros[7]=rsCelular.getString("numero");
                }
                
                 String sqlResidencial ="SELECT * FROM tabla_proveedor where idproveedor='"+registros[0]+"' and id_tipo_tel=2";
                 Statement sResidencial  = cn.createStatement();
                ResultSet rsResidencial  = sResidencial.executeQuery(sqlResidencial);
                
                if(rsResidencial.next()){
                    registros[8]=rsResidencial.getString("numero");
                }
                
                   String formato_fecha = rs.getString("fecha_prove").split("-")[2]+"/"+rs.getString("fecha_prove").split("-")[1]+"/"+rs.getString("fecha_prove").split("-")[0];
               registros[9]=formato_fecha;
                     
                             
                 
                  
                }
            
            representante.setText(registros[1]);
            razon_social.setText(registros[2]);
            rnc.setText(registros[3]);
            calle.setText(registros[4]);
            sector.setText(registros[5]);
            ciudad.setText(registros[6]);
            celular.setText(registros[7]);
            residencial.setText(registros[8]);
            
             Date fecha = new Date(registros[9].split("/")[1]+"/"+registros[9].split("/")[2]+"/"+registros[9].split("/")[0]);
               txt_fecha.setDate(fecha);

        

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
         void actualizar_celular(){
         try {
            PreparedStatement psU = cn.prepareStatement("UPDATE tabla_proveedor SET numero='"+celular.getText()+"' where id_tipo_tel=1 and idproveedor='"+idproveedor.getText()+"'");
            psU.executeUpdate();
          //  JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void actualizar_residencial(){
         try {
            PreparedStatement psU = cn.prepareStatement("UPDATE tabla_proveedor SET numero='"+residencial.getText()+"' where id_tipo_tel=2 and idproveedor='"+idproveedor.getText()+"'");
            psU.executeUpdate();
          //  JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 void limpiar02(){
   representante.setText("");
   razon_social.setText("");
   rnc.setText("");
   celular.setText("");
   sector.setText("");
   calle.setText("");
   ciudad.setText("");
   celular.setText("");
   residencial.setText("");
   numero_serie();
   representante.requestFocus();
   if(temporal.privilegiosUser[4].crear==1){
           jButton1.setEnabled(true);
       activar_campos();
   }
       jButton3.setEnabled(false);
}
        
 void limpiar (){
   representante.setText("");
   razon_social.setText("");
   rnc.setText("");
   celular.setText("");
   sector.setText("");
   calle.setText("");
   ciudad.setText("");
   residencial.setText("");
   idproveedor.requestFocus(); 
txt_fecha.setDate(null);

     desactivar_campos();

        }
   
    void guardar_celular(){
   
       try {
           String sql125= "";
           sql125 = "INSERT INTO tabla_proveedor (idproveedor,id_tipo_tel,numero) VALUES('"+idproveedor.getText()+"','1',"
                   + "'"+celular.getText()+"')";
                  
                  
           PreparedStatement psk22 = cn.prepareStatement(sql125);
           
             int n ;
             n = psk22.executeUpdate();
             if (n > 0){
                 //JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
             }
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
           JOptionPane.showMessageDialog(null, "ERROR EN GUARDAR DATOS ","NO SE PUEDE REGISTRAR",JOptionPane.ERROR_MESSAGE);
       }
   }
    void guardar_telefono(){
   
  
       try {
           String sql125= "";
           sql125 = "INSERT INTO tabla_proveedor (idproveedor,id_tipo_tel,numero) VALUES('"+idproveedor.getText()+"','2',"
                   + "'"+residencial.getText()+"')";
                  
                  
           PreparedStatement psk22 = cn.prepareStatement(sql125);
           
             int n ;
             n = psk22.executeUpdate();
             if (n > 0){
                 //JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
             }
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
           JOptionPane.showMessageDialog(null, "ERROR EN GUARDAR DATOS ","NO SE PUEDE REGISTRAR",JOptionPane.ERROR_MESSAGE);
       }
       
       
   }
    void guardar(){
   
       try {
           
             String fecha11;
      java.util.Date fecha = txt_fecha.getDate();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      fecha11 = formato.format(fecha);
     String fecha01 = fecha11;
           
           
           
           String sql125= "";
           sql125 = "INSERT INTO proveedor (representante,razon_social,rnc,sector,calle,fecha_prove,ciudad) VALUES('"+representante.getText()+"',"
                   + "'"+razon_social.getText()+"','"+rnc.getText()+"',"
                   + "'"+sector.getText()+"','"+calle.getText()+"','"+fecha01+"','" +ciudad.getText()+"')";
                  
           PreparedStatement psk22 = cn.prepareStatement(sql125);
           
             int n ;
             n = psk22.executeUpdate();
             if (n > 0){
                 JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
                 
                 numero_serie();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        idproveedor = new javax.swing.JTextField();
        representante = new javax.swing.JTextField();
        rnc = new javax.swing.JTextField();
        razon_social = new javax.swing.JTextField();
        ciudad = new javax.swing.JTextField();
        residencial = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        sector = new javax.swing.JTextField();
        calle = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        celular = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_fecha = new com.toedter.calendar.JDateChooser();
        jButton6 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRO DE PROVEEDORES");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\proyecto\\Proyecto duany\\src\\iconos\\profesor.png")); // NOI18N
        jLabel1.setText("REGISTRO DE PROVEEDOR");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, -1, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\proyecto\\Proyecto duany\\src\\iconos\\salvar.png")); // NOI18N
        jButton1.setText("Guardar");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\proyecto\\Proyecto duany\\src\\iconos\\actualizar.png")); // NOI18N
        jButton3.setText("Modificar");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\proyecto\\Proyecto duany\\src\\iconos\\anadir (1).png")); // NOI18N
        jButton4.setText("Nuevo");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\proyecto\\Proyecto duany\\src\\iconos\\salida.png")); // NOI18N
        jButton5.setText("Salir");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton7.setText("Salir");
        jButton7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(450, 450, 450)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 570, 70));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("CODIGO");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 30));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("REPRESENTANTE");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 54, 110, 20));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("RAZON SOCIAL");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 84, -1, 20));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("RNC");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 30, 30));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("RESIDENCIAL");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 80, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setText("SECTOR");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 60, 30));

        idproveedor.setBackground(new java.awt.Color(240, 240, 240));
        idproveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(idproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 200, 25));

        representante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        representante.setEnabled(false);
        representante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                representanteActionPerformed(evt);
            }
        });
        jPanel2.add(representante, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 200, 25));

        rnc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rncActionPerformed(evt);
            }
        });
        jPanel2.add(rnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 160, 25));

        razon_social.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        razon_social.setEnabled(false);
        razon_social.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                razon_socialActionPerformed(evt);
            }
        });
        jPanel2.add(razon_social, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 200, 25));

        ciudad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ciudad.setEnabled(false);
        ciudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciudadActionPerformed(evt);
            }
        });
        jPanel2.add(ciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 220, 25));

        try {
            residencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        residencial.setEnabled(false);
        residencial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        residencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                residencialActionPerformed(evt);
            }
        });
        jPanel2.add(residencial, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 220, 25));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setText("CALLE");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 50, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("CIUDAD");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 50, 30));

        sector.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sector.setEnabled(false);
        sector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectorActionPerformed(evt);
            }
        });
        jPanel2.add(sector, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 220, 25));

        calle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        calle.setEnabled(false);
        calle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calleActionPerformed(evt);
            }
        });
        jPanel2.add(calle, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 200, 25));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setText("CELULAR");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 60, 30));

        try {
            celular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        celular.setEnabled(false);
        celular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celularActionPerformed(evt);
            }
        });
        jPanel2.add(celular, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 220, 25));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("FECHA");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 40, 30));

        txt_fecha.setEnabled(false);
        jPanel2.add(txt_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 220, 25));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\proyecto\\Proyecto duany\\src\\iconos\\lupa.png")); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 40, 25));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 640, 180));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\proyecto\\Proyecto duany\\src\\iconos\\lupa.png")); // NOI18N
        jButton2.setText("BUSCAR PROVEEDOR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 190, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 812, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 this.dispose();         
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if (idproveedor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"EL CODIGO ESTA EN BLANCO");
            idproveedor.requestFocus();
            return;
        }
       if (representante.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL REPRESENTANTE");
            representante.requestFocus();
            return;
        }
        if (razon_social.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA RAZON SOCIAL");
            razon_social.requestFocus();
            return;
        }
     
        if (celular.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL CELULAR");
           celular.requestFocus();
            return;
        }
         if (residencial.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL TELEFONO");
           residencial.requestFocus();
            return;
        }
          if (sector.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL SECTOR");
           sector.requestFocus();
            return;
        }
           if (calle.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CALLE");
           calle.requestFocus();
            return;
        }
            if (ciudad.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CIUDAD");
           ciudad.requestFocus();
            return;
        }
            
              if (txt_fecha.getDate()==null){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA FECHA");
           txt_fecha.requestFocus();
            return;
        }
            
            
               if(rnc.getText().equals("")==false && validarRNC()==0){
              return;
          }

        guardar();
        guardar_telefono();
        guardar_celular();
        limpiar();
        actualizar_codigo();
        numero_serie();
        this.dispose();
        proveedor windows = new proveedor();
        windows.setVisible(true);
        windows.setLocationRelativeTo(null);  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
     if (idproveedor.getText().equals("")){
            JOptionPane.showMessageDialog(null,"EL CODIGO ESTA EN BLANCO");
            idproveedor.requestFocus();
            return;
        }
       if (representante.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL REPRESENTANTE");
            representante.requestFocus();
            return;
        }
        if (razon_social.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA RAZON SOCIAL");
            razon_social.requestFocus();
            return;
        }
        if (rnc.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL RNC");
           rnc.requestFocus();
            return;
        }
        if (celular.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL CELULAR");
           celular.requestFocus();
            return;
        }
         if (residencial.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL TELEFONO");
           residencial.requestFocus();
            return;
        }
          if (sector.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL SECTOR");
           sector.requestFocus();
            return;
        }
           if (calle.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CALLE");
           calle.requestFocus();
            return;
        }
            if (ciudad.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CIUDAD");
           ciudad.requestFocus();
            return;
        }

        try {
             String fecha11;
      java.util.Date fecha = txt_fecha.getDate();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      fecha11 = formato.format(fecha);
     String fecha01 = fecha11;
     
            PreparedStatement psU = cn.prepareStatement("UPDATE proveedor SET idproveedor='"+idproveedor.getText()+"', representante='"+representante.getText()+"'"
                + ",razon_social='"+razon_social.getText()+"',rnc ='"+rnc.getText()+"',"
                + " sector = '"+sector.getText()+"',calle='"+calle.getText()+"',ciudad='"+ciudad.getText()+"',fecha_prove='"+fecha01+"' where idproveedor='"+idproveedor.getText()+"'");
            psU.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizar_residencial();
        actualizar_celular();
        limpiar();        
                            
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       limpiar02();  
    }//GEN-LAST:event_jButton4ActionPerformed

    private void representanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_representanteActionPerformed
      razon_social.requestFocus();  
    }//GEN-LAST:event_representanteActionPerformed

    private void razon_socialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_razon_socialActionPerformed
       rnc.requestFocus();  
    }//GEN-LAST:event_razon_socialActionPerformed

    private void rncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rncActionPerformed
      celular.requestFocus();
    }//GEN-LAST:event_rncActionPerformed

    private void celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularActionPerformed
      residencial.requestFocus();
    }//GEN-LAST:event_celularActionPerformed

    private void residencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_residencialActionPerformed
   sector.requestFocus();
    }//GEN-LAST:event_residencialActionPerformed

    private void sectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectorActionPerformed
    calle.requestFocus();
    }//GEN-LAST:event_sectorActionPerformed

    private void calleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calleActionPerformed
     ciudad.requestFocus();
    }//GEN-LAST:event_calleActionPerformed
void desactivar_campos(){
        representante.setEnabled(false);
        razon_social.setEnabled(false);
        calle.setEnabled(false);
        sector.setEnabled(false);
        ciudad.setEnabled(false);
        celular.setEnabled(false);
        residencial.setEnabled(false);
        txt_fecha.setEnabled(false);
        jButton1.setEnabled(false);
        jButton3.setEnabled(false);
        
    }


void activar_campos(){
        representante.setEnabled(true);
        rnc.setEnabled(true);
        razon_social.setEnabled(true);
        calle.setEnabled(true);
        sector.setEnabled(true);
        ciudad.setEnabled(true);
        celular.setEnabled(true);
        residencial.setEnabled(true);
        jButton1.setEnabled(true);
        jButton3.setEnabled(true);
        
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       if(rnc.getText().equals("")){
           JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL RNC");
          return;
       }
       
         String sql = "SELECT * FROM tabla_rnc where rnc='" + rnc.getText() + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
              JOptionPane.showMessageDialog(null,"RNC VALIDO: " +rs.getString("nombre"));
            }else{
               JOptionPane.showMessageDialog(null,"RNC NO VALIDO");

            }
                            validarRNC();
            }catch(Exception e){
                      JOptionPane.showMessageDialog(null,"Error al validar el RNC");
            }
       
    }//GEN-LAST:event_jButton6ActionPerformed
int validarRNC(){
     String sql = "SELECT * FROM proveedor where rnc='" + rnc.getText() + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String [] registros = new String [10];
            if(rs.next()) {
               JOptionPane.showMessageDialog(null,"RNC EXISTENTE");
               
               
               idproveedor.setText(rs.getString("idproveedor"));
            representante.setText(rs.getString("representante"));
            razon_social.setText(rs.getString("razon_social"));
           rnc.setText(rs.getString("rnc"));
            calle.setText(rs.getString("calle"));
            sector.setText(rs.getString("sector"));
           ciudad.setText(rs.getString("ciudad"));
           
           
             String sqlCelular ="SELECT * FROM tabla_proveedor where idproveedor='"+rs.getString("idproveedor")+"' and id_tipo_tel=1";
                 Statement stCelular  = cn.createStatement();
                ResultSet rsCelular  = stCelular .executeQuery(sqlCelular);
                
                if(rsCelular.next()){
                    celular.setText(rsCelular.getString("numero").toString().replaceAll("\\-", "").replaceAll("\\ ", ""));
                }
                   String sqlResidencial ="SELECT * FROM tabla_proveedor where idproveedor='"+rs.getString("idproveedor")+"' and id_tipo_tel=2";
                 Statement sResidencial  = cn.createStatement();
                ResultSet rsResidencial  = sResidencial.executeQuery(sqlResidencial);
                
                if(rsResidencial.next()){
                       residencial.setText(rsResidencial.getString("numero").toString().replaceAll("\\-", "").replaceAll("\\ ", ""));
                }
           
             Date fecha = new Date(rs.getString("fecha_prove").split("-")[2]+"/"+rs.getString("fecha_prove").split("-")[1]+"/"+rs.getString("fecha_prove").split("-")[0]);
             proveedor.txt_fecha.setDate(fecha);
               activar_campos();
             jButton1.setEnabled(false);
               jButton3.setEnabled(true);
               return 0;
            }else{
                 jButton1.setEnabled(true);
                 activar_campos();
                 return 1;

            }
            
            }catch(Exception e){
          return 0;

            }
}
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       buscador_proveedor window = new buscador_proveedor();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
         if(temporal.privilegiosUser[4].modificar==1){
             activar_campos();
            jButton3.setEnabled(true);
         }
         jButton1.setEnabled(false);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void ciudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ciudadActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new proveedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField calle;
    public static javax.swing.JFormattedTextField celular;
    public static javax.swing.JTextField ciudad;
    public static javax.swing.JTextField idproveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JTextField razon_social;
    public static javax.swing.JTextField representante;
    public static javax.swing.JFormattedTextField residencial;
    public static javax.swing.JTextField rnc;
    public static javax.swing.JTextField sector;
    public static com.toedter.calendar.JDateChooser txt_fecha;
    // End of variables declaration//GEN-END:variables
String id = "";
      conectar con = new conectar();
      Connection cn = con.connection();
       
}
