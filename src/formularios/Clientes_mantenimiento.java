package formularios;


import Clases.ColorearFilas;
import conectar.conectar;
import static formularios.Cuadre.tblcuadre;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Clientes_mantenimiento extends javax.swing.JFrame {

    public Clientes_mantenimiento() {
        
        
        
        
        initComponents();
        
        Date fechaActual = new Date();
        txt_fecha.setDate(fechaActual);
        numero_serie();
        nombre.requestFocus();
        this.setLocationRelativeTo(null);
    }
     void consulta() {

        String[] registros = new String[4];
        String sql = "SELECT idcliente,nombre,apellido FROM cliente where idcliente='"+id+"'  ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
          if (rs.next()){
              registros[0]=rs.getString("nombre");
              registros[1]=rs.getString("apellido");
              JOptionPane.showMessageDialog(null, "EL CLIENTE ESTA COMO "+registros[0]+" "+registros[1]);
              limpiar();
              numero_serie();
          }
          else{
            guardar();
        guardar_residencial();
        guardar_celular();
        limpiar();
        actualizar_codigo();
        this.dispose();
        Clientes_mantenimiento windows = new Clientes_mantenimiento();
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
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idcliente=idcliente - 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
                Logger.getLogger(Clientes_mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       nombre.requestFocus();
    }
     void actualizar_codigo() {
       
            try {
                PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idcliente=idcliente + 1  " );
                psU.executeUpdate();
                // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
            } catch (Exception ex) {
                Logger.getLogger(Clientes_mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        idcliente.requestFocus();
    }
    void numero_serie() {
        String[] registros = new String[6];
        String sql = "SELECT idcliente FROM contador ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idcliente");

            }
            int numero_serie = Integer.parseInt(registros[0]);
            numero_serie = numero_serie + 1;
            idcliente.setText(String.valueOf( numero_serie));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
  
    void buscar_telefono() {

        String[] registros = new String[4];
        String sql = "SELECT tabla_cliente.numero,tabla_cliente.id_tipo_tel FROM tabla_cliente where tabla_cliente.idcliente='"+id+"'  ";
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

        String[] registros = new String[13];
        String sql = "SELECT * FROM cliente where idcliente='" + id + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0]=rs.getString("idcliente");
                registros[1]=rs.getString("nombre");
                registros[2]=rs.getString("apellido");
                registros[3]=rs.getString("rnc");
                registros[4]=rs.getString("estado");
                registros[5]=rs.getString("limite_credito");
                registros[6]=rs.getString("calle");
                registros[7]=rs.getString("sector");
                registros[8]=rs.getString("ciudad");
                
                String sqlCelular ="select * from tabla_cliente where id_tipo_tel = 1 and  idcliente= " + rs.getString("idcliente")+"";

                    Statement std = cn.createStatement();
                ResultSet rsC = std.executeQuery(sqlCelular);
                  if(rsC.next()){
                  registros[9]=rsC.getString("numero");
                  }
                  
                   String sqlResidencial ="select * from tabla_cliente where id_tipo_tel = 2 and  idcliente= " + rs.getString("idcliente")+"";

                    Statement stdr = cn.createStatement();
                ResultSet rsR = stdr.executeQuery(sqlResidencial);
                  if(rsR.next()){
                  registros[10]=rsR.getString("numero");
                  }              
                  registros[11]=rs.getString("razon_social");

                
                  String formato_fecha = rs.getString("fecha_cliente").split("-")[2]+"/"+rs.getString("fecha_cliente").split("-")[1]+"/"+rs.getString("fecha_cliente").split("-")[0];
               registros[12]=formato_fecha;

                  
                  
                }
            
            nombre.setText(registros[1]);
             apellido.setText(registros[2]);
             rnc.setText(registros[3]);
             estado.setSelectedItem(registros[4]);
             limite_credito.setText(registros[5]);
              calle.setText(registros[6]);
              sector.setText(registros[7]);
              ciudad.setText(registros[8]);
              celular.setText(registros[9]);
              residencial.setText(registros[10]);
                            razon_social.setText(registros[11]);

              Date fecha = new Date(registros[12].split("/")[1]+"/"+registros[12].split("/")[2]+"/"+registros[12].split("/")[0]);
               txt_fecha.setDate(fecha);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     void actualizar_celular(){
         try {
            PreparedStatement psU = cn.prepareStatement("UPDATE tabla_cliente SET numero='"+celular.getText()+"' where id_tipo_tel=1 and idcliente='"+idcliente.getText()+"'");
            psU.executeUpdate();
          //  JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(Clientes_mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void actualizar_residencial(){
         try {
            PreparedStatement psU = cn.prepareStatement("UPDATE tabla_cliente SET numero='"+residencial.getText()+"' where id_tipo_tel=2 and idcliente='"+idcliente.getText()+"'");
            psU.executeUpdate();
          //  JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(Clientes_mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
void limpiar02(){
  
   nombre.setText("");
   apellido.setText("");
   rnc.setText("");
   limite_credito.setText("");
   sector.setText("");
   calle.setText("");
   ciudad.setText("");
   celular.setText("");
   residencial.setText("");
   razon_social.setText("");
  cedula.setText("");
   numero_serie();
   nombre.requestFocus();
   consumidor.setSelected(false);
   mayor.setSelected(false);
   if(temporal.privilegiosUser[2].crear==1)activar_campos();

}
void limpiar(){
   nombre.setText("");
   apellido.setText("");
   rnc.setText("");
   limite_credito.setText("");
   sector.setText("");
   calle.setText("");
   ciudad.setText("");
   celular.setText("");
   residencial.setText("");
   idcliente.requestFocus();
   razon_social.setText("");
   cedula.setText("");
   consumidor.setSelected(false);
     mayor.setSelected(false);
     desactivar_campos();

}

 void guardar_celular(){
   
       try {
           String sql125= "";
           sql125 = "INSERT INTO tabla_cliente (idcliente,id_tipo_tel,numero) VALUES('"+idcliente.getText()+"','1',"
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
void guardar_residencial(){
   
  
       try {
           String sql125= "";
           sql125 = "INSERT INTO tabla_cliente (idcliente,id_tipo_tel,numero) VALUES('"+idcliente.getText()+"','2',"
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
      fecha01 = fecha11;
           
           
           String sql125= "";
           sql125 = "INSERT INTO cliente (nombre,apellido,rnc,limite_credito,sector,calle,ciudad,estado,razon_social,cedula,fecha_cliente,precio_por_mayor) VALUES('"+nombre.getText()+"',"
                   + "'"+apellido.getText()+"','"+rnc.getText()+"',"
                   + "'"+limite_credito.getText()+"','"+sector.getText()+"','"+calle.getText()+"','"+ciudad.getText()+"','"+estado.getSelectedItem()+"','"+razon_social.getText()+"','"+cedula.getText()+"','"+fecha01+"',"+mayor.isSelected()+")";
                  
           PreparedStatement psk22 = cn.prepareStatement(sql125);
           
             int n ;
             n = psk22.executeUpdate();
             if (n > 0){
                 
                 JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
                         actualizar_codigo();
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        idcliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sector = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        calle = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ciudad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        celular = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        limite_credito = new javax.swing.JFormattedTextField();
        residencial = new javax.swing.JFormattedTextField();
        estado = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_fecha = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        razon_social = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cedula = new javax.swing.JFormattedTextField();
        valida_cedula = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        rnc = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        consumidor = new javax.swing.JCheckBox();
        mayor = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        guardar_boton = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        boton_modificar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRO DE CLIENTES");
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("CODIGO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 40, 70, 30));

        idcliente.setEditable(false);
        idcliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        idcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                idclienteMouseReleased(evt);
            }
        });
        idcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idclienteActionPerformed(evt);
            }
        });
        jPanel1.add(idcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 180, 25));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText(" NOMBRES");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 80, 30));

        nombre.setEditable(false);
        nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        jPanel1.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 180, 25));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("APELLIDO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 80, 30));

        apellido.setEditable(false);
        apellido.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });
        apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoKeyTyped(evt);
            }
        });
        jPanel1.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 180, 25));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText(" CREDITO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("SECTOR");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 60, 30));

        sector.setEditable(false);
        sector.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sectorActionPerformed(evt);
            }
        });
        sector.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sectorKeyTyped(evt);
            }
        });
        jPanel1.add(sector, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 190, 25));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("CALLE");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 50, 30));

        calle.setEditable(false);
        calle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        calle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calleActionPerformed(evt);
            }
        });
        calle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                calleKeyTyped(evt);
            }
        });
        jPanel1.add(calle, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 180, 25));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("CIUDAD");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 60, 30));

        ciudad.setEditable(false);
        ciudad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ciudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciudadActionPerformed(evt);
            }
        });
        ciudad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ciudadKeyTyped(evt);
            }
        });
        jPanel1.add(ciudad, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 190, 25));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("CELULAR");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 80, 30));

        celular.setEditable(false);
        try {
            celular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        celular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celularActionPerformed(evt);
            }
        });
        celular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                celularKeyReleased(evt);
            }
        });
        jPanel1.add(celular, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 190, 25));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("RESIDENCIAL");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 100, 50));

        limite_credito.setEditable(false);
        limite_credito.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0.###"))));
        limite_credito.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        limite_credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limite_creditoActionPerformed(evt);
            }
        });
        limite_credito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                limite_creditoKeyTyped(evt);
            }
        });
        jPanel1.add(limite_credito, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 180, 25));

        residencial.setEditable(false);
        try {
            residencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        residencial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        residencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                residencialActionPerformed(evt);
            }
        });
        jPanel1.add(residencial, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 190, 25));

        estado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE...", "ACTIVO", "INACTIVO" }));
        jPanel1.add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 190, 25));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("ESTADO");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 60, 30));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("FECHA");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 50, 30));

        txt_fecha.setBackground(new java.awt.Color(255, 255, 255));
        txt_fecha.setForeground(new java.awt.Color(153, 153, 153));
        txt_fecha.setEnabled(false);
        txt_fecha.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jPanel1.add(txt_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 190, 25));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("RAZON SOCIAL");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 110, 30));

        razon_social.setEditable(false);
        razon_social.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        razon_social.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                razon_socialActionPerformed(evt);
            }
        });
        jPanel1.add(razon_social, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 190, 25));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("CEDULA");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 70, 30));

        try {
            cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-#######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaActionPerformed(evt);
            }
        });
        jPanel1.add(cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 180, 25));

        valida_cedula.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        valida_cedula.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        valida_cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valida_cedulaActionPerformed(evt);
            }
        });
        jPanel1.add(valida_cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 40, 28));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText(" RNC");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, 30));

        rnc.setEditable(false);
        rnc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rncActionPerformed(evt);
            }
        });
        jPanel1.add(rnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 180, 25));

        jToggleButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jToggleButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 40, 28));

        consumidor.setBackground(new java.awt.Color(204, 204, 204));
        consumidor.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        consumidor.setText("PRECIO CONSUMIDOR");
        consumidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consumidorActionPerformed(evt);
            }
        });
        jPanel1.add(consumidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        mayor.setBackground(new java.awt.Color(204, 204, 204));
        mayor.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        mayor.setText("PRECIO x MAYOR");
        mayor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mayorActionPerformed(evt);
            }
        });
        jPanel1.add(mayor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 610, 260));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        guardar_boton.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        guardar_boton.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\salvar.png")); // NOI18N
        guardar_boton.setText("Guardar");
        guardar_boton.setToolTipText("Guardar");
        guardar_boton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        guardar_boton.setEnabled(false);
        guardar_boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardar_botonActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\anadir (1).png")); // NOI18N
        jButton6.setText("Nuevo");
        jButton6.setToolTipText("Nuevo");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\salida.png")); // NOI18N
        jButton3.setText("Salir");
        jButton3.setToolTipText("Salir");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        boton_modificar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        boton_modificar.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\modifi.png")); // NOI18N
        boton_modificar.setText("Modificar");
        boton_modificar.setToolTipText("Modificar");
        boton_modificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_modificar.setEnabled(false);
        boton_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_modificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(guardar_boton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(boton_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(boton_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardar_boton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 620, 60));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\cliente.png")); // NOI18N
        jLabel10.setText("REGISTRO DE CLIENTE");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, -1, 30));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jButton2.setText("BUSCAR CLIENTE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 240, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\mujer (1).png")); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 260, 240));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardar_botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardar_botonActionPerformed
         
        
        
        if (idcliente.getText().equals("")){
            JOptionPane.showMessageDialog(null,"EL CODIGO ESTA EN BLANCO");
            idcliente.requestFocus();
            return;
        }
            
        if (nombre.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL NOMBRE");
            nombre.requestFocus();
            return;             
        }
        if (apellido.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL APELLIDO");
            apellido.requestFocus();
            return;             
        }
    
        if (limite_credito.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL LIMITE DE CREDITO");
            limite_credito.requestFocus();
            return;            
        }
        if (calle.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA DIRECCION");
            calle.requestFocus();
            return;             
        }
        if (ciudad.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CIUDAD");
            ciudad.requestFocus();
            return;             
        }
        if (sector.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL SECTOR");
            sector.requestFocus();
            return;             
        }   

        if (celular.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL TELEFONO CELULAR");
            celular.requestFocus();
            return;             
        }
        if (limite_credito.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL LIMITE DE CREDITO");
            limite_credito.requestFocus();
            return;             
        }
          if (estado.getSelectedItem().equals("SELECCIONE...")){
            JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR EL ESTADO DEL CLIENTE");
            estado.requestFocus();
            return;             
        }
           if (txt_fecha.getCalendar()==null){
            JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR LA FECHA");
            txt_fecha.requestFocus();
            return;             
         }
        if (residencial.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL TELEFONO RESIDENCIAL");
            residencial.requestFocus();
            return; 
           
           }
         if (razon_social.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA RAZON SOCIAL");
            razon_social.requestFocus();
            return; 
           
           }
         
            if (cedula.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CEDULA");
            cedula.requestFocus();
            return; 
           
           }
            
            if(consumidor.isSelected()==false && mayor.isSelected()==false){
                 JOptionPane.showMessageDialog(null,"Debe SELECCIONAR PRECIO CONSUMIDOR O POR MAYOR");
                 return; 
            }
          
           
               if(rnc.getText().equals("")==false && validarRNC()==0){
              return;
          }

        guardar();
        guardar_residencial();
        guardar_celular();
        limpiar();
      
       
    }//GEN-LAST:event_guardar_botonActionPerformed

    private void boton_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_modificarActionPerformed
            
        
        
        
        if (idcliente.getText().equals("")){
            JOptionPane.showMessageDialog(null,"EL CODIGO ESTA EN BLANCO");
            idcliente.requestFocus();
            return;
        }
            
        if (nombre.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL NOMBRE");
            nombre.requestFocus();
            return;             
        }
        if (apellido.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL APELLIDO");
            apellido.requestFocus();
            return;             
        }
        if (rnc.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA RNC");
            rnc.requestFocus();
            return;             
        }
        if (limite_credito.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL LIMITE DE CREDITO");
            limite_credito.requestFocus();
            return;            
        }
        if (calle.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA DIRECCION");
            calle.requestFocus();
            return;             
        }
        if (ciudad.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CIUDAD");
            ciudad.requestFocus();
            return;             
        }
        if (sector.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL SECTOR");
            sector.requestFocus();
            return;             
        }   

        if (celular.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL TELEFONO CELULAR");
            celular.requestFocus();
            return;             
        }
        if (limite_credito.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL LIMITE DE CREDITO");
            limite_credito.requestFocus();
            return;             
        }
          if (estado.getSelectedItem().equals("SELECCIONE...")){
            JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR EL ESTADO DEL CLIENTE");
            estado.requestFocus();
            return;             
        }
           if (txt_fecha.getCalendar()==null){
            JOptionPane.showMessageDialog(null,"DEBE SELECCIONAR LA FECHA");
            txt_fecha.requestFocus();
            return;             
         }
        if (residencial.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR EL TELEFONO RESIDENCIAL");
            residencial.requestFocus();
            return; 
           
           }
         if (razon_social.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA RAZON SOCIAL");
            razon_social.requestFocus();
            return; 
           
           }
            if (cedula.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CEDULA");
            cedula.requestFocus();
            return; 
           
           }

        try {
             String fecha11;
      java.util.Date fecha = txt_fecha.getDate();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      fecha11 = formato.format(fecha);
      fecha01 = fecha11;
      
            PreparedStatement psU = cn.prepareStatement("UPDATE cliente SET idcliente='"+idcliente.getText()+"',fecha_cliente='"+fecha01+"', nombre='"+nombre.getText()+"'"
                + ",apellido='"+apellido.getText()+"',precio_por_mayor="+mayor.isSelected()+",rnc ='"+rnc.getText()+"',"
                + " sector = '"+sector.getText()+"',calle='"+calle.getText()+"',ciudad='"+ciudad.getText()+"',estado='"+estado.getSelectedItem()+"',limite_credito='"+limite_credito.getText()+"',cedula='"+cedula.getText()+"',razon_social='"+razon_social.getText()+"' where idcliente='"+idcliente.getText()+"'");
            

            psU.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(Clientes_mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizar_residencial();
        actualizar_celular();
        limpiar();   
           numero_serie();
        
    }//GEN-LAST:event_boton_modificarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        limpiar02();       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void limite_creditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_limite_creditoKeyTyped
        char c = evt.getKeyChar();
        if (c<'0'  || c >'9') evt.consume();       
    }//GEN-LAST:event_limite_creditoKeyTyped

    private void limite_creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limite_creditoActionPerformed
        calle.requestFocus();       
    }//GEN-LAST:event_limite_creditoActionPerformed

    private void celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularActionPerformed
        residencial.requestFocus();        
    }//GEN-LAST:event_celularActionPerformed

    private void ciudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ciudadKeyTyped

      char a = evt.getKeyChar();   

        if (!Character.isLetter(a) && a!=KeyEvent.VK_SPACE) evt.consume();          
    }//GEN-LAST:event_ciudadKeyTyped

    private void ciudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciudadActionPerformed
        celular.requestFocus();        
    }//GEN-LAST:event_ciudadActionPerformed

    private void calleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_calleKeyTyped

    }//GEN-LAST:event_calleKeyTyped

    private void calleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calleActionPerformed
        rnc.requestFocus();        
    }//GEN-LAST:event_calleActionPerformed

    private void sectorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sectorKeyTyped

 char a = evt.getKeyChar();   

        if (!Character.isLetter(a) && a!=KeyEvent.VK_SPACE) evt.consume();          
    }//GEN-LAST:event_sectorKeyTyped

    private void sectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectorActionPerformed
        ciudad.requestFocus();        
    }//GEN-LAST:event_sectorActionPerformed

    private void apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoKeyTyped
 char a = evt.getKeyChar();   

        if (!Character.isLetter(a) && a!=KeyEvent.VK_SPACE) evt.consume();            
    }//GEN-LAST:event_apellidoKeyTyped

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        rnc.requestFocus();        
    }//GEN-LAST:event_apellidoActionPerformed

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped

        char a = evt.getKeyChar();   

        if (!Character.isLetter(a) && a!=KeyEvent.VK_SPACE) evt.consume();             
    }//GEN-LAST:event_nombreKeyTyped

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        limite_credito.requestFocus();       
    }//GEN-LAST:event_nombreActionPerformed

    private void idclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idclienteActionPerformed
        //validacion();
    }//GEN-LAST:event_idclienteActionPerformed

    private void idclienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idclienteMouseReleased
        
    }//GEN-LAST:event_idclienteMouseReleased

    private void residencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_residencialActionPerformed
razon_social.requestFocus();        
    }//GEN-LAST:event_residencialActionPerformed

    private void celularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_celularKeyReleased
       
    }//GEN-LAST:event_celularKeyReleased

    private void rncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rncActionPerformed
       sector.requestFocus(); 
    }//GEN-LAST:event_rncActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
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
       
       
    }//GEN-LAST:event_jToggleButton1ActionPerformed
int validarRNC(){
     String sql = "SELECT * FROM cliente where rnc='" + rnc.getText() + "'";
        try {
            String [] registros = new String [13];
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
               JOptionPane.showMessageDialog(null,"RNC EXISTENTE");
               guardar_boton.setEnabled(false);
               return 0;
            }else{
                 guardar_boton.setEnabled(true);
                                return 1;

            }
            }catch(Exception e){
          return 0;

            }
}
    private void valida_cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valida_cedulaActionPerformed
       if (cedula.getText().equals("")){
            JOptionPane.showMessageDialog(null,"DEBE INGRESAR LA CEDULA");
            cedula.requestFocus();
            return; 
           
           }
       
        String sql = "SELECT * FROM cliente where cedula='" + cedula.getText() + "'";
        try {
            String [] registros = new String [13];
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                 registros[0]=rs.getString("idcliente");
                registros[1]=rs.getString("nombre");
                registros[2]=rs.getString("apellido");
                registros[3]=rs.getString("rnc");
                registros[4]=rs.getString("estado");
                registros[5]=rs.getString("limite_credito");
                registros[6]=rs.getString("calle");
                registros[7]=rs.getString("sector");
                registros[8]=rs.getString("ciudad");
                
                String sqlCelular ="select * from tabla_cliente where id_tipo_tel = 1 and  idcliente= " + rs.getString("idcliente")+"";

                    Statement std = cn.createStatement();
                ResultSet rsC = std.executeQuery(sqlCelular);
                  if(rsC.next()){
                  registros[9]=rsC.getString("numero");
                  }
                  
                   String sqlResidencial ="select * from tabla_cliente where id_tipo_tel = 2 and  idcliente= " + rs.getString("idcliente")+"";

                    Statement stdr = cn.createStatement();
                ResultSet rsR = stdr.executeQuery(sqlResidencial);
                  if(rsR.next()){
                  registros[10]=rsR.getString("numero");
                  }              
                  registros[11]=rs.getString("razon_social");

                
                  String formato_fecha = rs.getString("fecha_cliente").split("-")[2]+"/"+rs.getString("fecha_cliente").split("-")[1]+"/"+rs.getString("fecha_cliente").split("-")[0];
               registros[12]=formato_fecha;

               nombre.setText(registros[1]);
             apellido.setText(registros[2]);
             rnc.setText(registros[3]);
             estado.setSelectedItem(registros[4]);
             limite_credito.setText(registros[5]);
              calle.setText(registros[6]);
              sector.setText(registros[7]);
              ciudad.setText(registros[8]);
              celular.setText(registros[9]);
              residencial.setText(registros[10]);
               razon_social.setText(registros[11]);

              Date fecha = new Date(registros[12].split("/")[1]+"/"+registros[12].split("/")[2]+"/"+registros[12].split("/")[0]);
               txt_fecha.setDate(fecha);
               guardar_boton.setEnabled(false);
               boton_modificar.setEnabled(true);
            }else{
                guardar_boton.setEnabled(true);
               boton_modificar.setEnabled(false);
            }
            activar_campos();

            }catch(Exception e){
                      JOptionPane.showMessageDialog(null,"Error al validar el RNC");
            }
       
       
       
    }//GEN-LAST:event_valida_cedulaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          buscar_clientes window = new buscar_clientes();
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        guardar_boton.setEnabled(false);
         if(temporal.privilegiosUser[2].modificar==1){
               activar_campos();
         boton_modificar.setEnabled(true);
         }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cedulaActionPerformed

    private void razon_socialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_razon_socialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_razon_socialActionPerformed

    private void consumidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consumidorActionPerformed
        mayor.setSelected(false);
    }//GEN-LAST:event_consumidorActionPerformed

    private void mayorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mayorActionPerformed
        consumidor.setSelected(false);
    }//GEN-LAST:event_mayorActionPerformed
 
    void activar_campos(){
        nombre.setEditable(true);
        apellido.setEditable(true);
        rnc.setEditable(true);
        limite_credito.setEditable(true);
        calle.setEditable(true);
        sector.setEditable(true);
        ciudad.setEditable(true);
        celular.setEditable(true);
        residencial.setEditable(true);
        razon_social.setEditable(true);
        estado.setEditable(true);
        
    }
    
    void desactivar_campos(){

        nombre.setEditable(false);
        apellido.setEditable(false);
        rnc.setEditable(false);
        limite_credito.setEditable(false);
        calle.setEditable(false);
        sector.setEditable(false);
        ciudad.setEditable(false);
        celular.setEditable(false);
        residencial.setEditable(false);
        razon_social.setEditable(false);
        estado.setEditable(false);
            guardar_boton.setEnabled(false);
               boton_modificar.setEnabled(false);
        
    }
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Clientes_mantenimiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField apellido;
    private javax.swing.JButton boton_modificar;
    public static javax.swing.JTextField calle;
    public static javax.swing.JFormattedTextField cedula;
    public static javax.swing.JFormattedTextField celular;
    public static javax.swing.JTextField ciudad;
    public static javax.swing.JCheckBox consumidor;
    public static javax.swing.JComboBox<String> estado;
    private javax.swing.JButton guardar_boton;
    public static javax.swing.JTextField idcliente;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    public static javax.swing.JToggleButton jToggleButton1;
    public static javax.swing.JFormattedTextField limite_credito;
    public static javax.swing.JCheckBox mayor;
    public static javax.swing.JTextField nombre;
    public static javax.swing.JTextField razon_social;
    public static javax.swing.JFormattedTextField residencial;
    public static javax.swing.JTextField rnc;
    public static javax.swing.JTextField sector;
    public static com.toedter.calendar.JDateChooser txt_fecha;
    private javax.swing.JButton valida_cedula;
    // End of variables declaration//GEN-END:variables

String fecha01 = "", id = "";
  conectar con = new conectar();
      Connection cn = con.connection();
      
      
      
      


}
