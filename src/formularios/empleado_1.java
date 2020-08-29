package formularios;

import Clases.ColorearFilas;
import clases.ColorearFilas1;
import conectar.conectar;
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

public class empleado_1 extends javax.swing.JFrame {
    
    public empleado_1() {
        initComponents();
        numero_serie();
        nombre.requestFocus();
        this.setLocationRelativeTo(null);
        pintarColumna();
        
    }

    void actualizar() {
        
        try {
            PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idempleado=idempleado - 1  ");
            psU.executeUpdate();
            // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(empleado_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        nombre.requestFocus();
    }
    
    void actualizar_codigo() {
        
        try {
            PreparedStatement psU = cn.prepareStatement("UPDATE contador SET idempleado=idempleado + 1  ");
            psU.executeUpdate();
            // JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(empleado_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        idempleado.requestFocus();
    }

    void numero_serie() {
        String[] registros = new String[6];
        String sql = "SELECT idempleado FROM contador ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("idempleado");
                
            }
            int numero_serie = Integer.parseInt(registros[0]);
            numero_serie = numero_serie + 1;
            idempleado.setText(String.valueOf(numero_serie));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void actualizar_celular() {
        try {
            PreparedStatement psU = cn.prepareStatement("UPDATE tabla_empleado SET numero='" + celular.getText() + "' where id_tipo_tel=1 and idempleado='" + idempleado.getText() + "'");
            psU.executeUpdate();
            //  JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(crear_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void actualizar_residencial() {
        try {
            PreparedStatement psU = cn.prepareStatement("UPDATE tabla_empleado SET numero='" + residencial.getText() + "' where id_tipo_tel=2 and idempleado='" + idempleado.getText() + "'");
            psU.executeUpdate();
            //  JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(crear_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void buscar_telefono() {
        
        String[] registros = new String[4];
        String sql = "SELECT tabla_empleado.numero,tabla_empleado.id_tipo_tel FROM tabla_empleado where tabla_empleado.idempleado='" + id + "'  ";
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

    void actualizar_empleado() {
        try {
            PreparedStatement psU = cn.prepareStatement("UPDATE empleado SET idempleado='" + idempleado.getText() + "', nombre='" + nombre.getText() + "'"
                    + ",apellido='" + apellido.getText() + "',cedula ='" + cedula.getText() + "',limite_credito= '" + limite_credito.getText() + "',"
                    + " sector = '" + sector.getText() + "',calle='" + calle.getText() + "',ciudad='" + ciudad.getText() + "' where idempleado='" + idempleado.getText() + "'");
            psU.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(crear_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void pintarColumna() {
        
        ColorearFilas1 color = new ColorearFilas1(5);
    }

    void fecha1() {
        String fecha11;
        java.util.Date fecha = fecha_nac.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        fecha11 = formato.format(fecha);
        fecha01 = fecha11;
    }

    void consulta() {
        
        String[] registros = new String[4];
        String sql = "SELECT idempleado,nombre,apellido FROM empleado where idempleado='" + id + "'  ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if (rs.next()) {
                registros[0] = rs.getString("nombre");
                registros[1] = rs.getString("apellido");
                JOptionPane.showMessageDialog(null, "EL EMPLEADO ESTA COMO " + registros[0] + " " + registros[1]);
                limpiar();
                numero_serie();
            } else {
                
                guardar();
                guardar_residencial();
                guardar_celular();
                limpiar();
                actualizar_codigo();
                this.dispose();
                empleado_1 windows = new empleado_1();
                windows.setVisible(true);
                windows.setLocationRelativeTo(null);
                
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "error en telefonos");
        }
    }
    
    void limpiar() {
        
        nombre.setText("");
        apellido.setText("");
        cedula.setText("");
        limite_credito.setText("");
        sector.setText("");
        calle.setText("");
        ciudad.setText("");
        celular.setText("");
        residencial.setText("");
        nombre.requestFocus();
        numero_serie();
        fecha_nac.setDate(null);
        fecha_ingreso.setDate(null);
        jButton4.setEnabled(false);
    }

    void fecha_2() {
        String fecha22;
        java.util.Date fecha = fecha_ingreso.getDate();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        fecha22 = formato.format(fecha);
        fecha02 = fecha22;
    }

    void guardar_celular() {
        
        try {
            String sql125 = "";
            sql125 = "INSERT INTO tabla_empleado (idempleado,id_tipo_tel,numero) VALUES('" + idempleado.getText() + "','1',"
                    + "'" + celular.getText() + "')";
            
            PreparedStatement psk22 = cn.prepareStatement(sql125);
            
            int n;
            n = psk22.executeUpdate();
            if (n > 0) {
                //JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN GUARDAR DATOS ", "NO SE PUEDE REGISTRAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    void guardar_residencial() {
        
        try {
            String sql125 = "";
            sql125 = "INSERT INTO tabla_empleado (idempleado,id_tipo_tel,numero) VALUES('" + idempleado.getText() + "','2',"
                    + "'" + residencial.getText() + "')";
            
            PreparedStatement psk22 = cn.prepareStatement(sql125);
            
            int n;
            n = psk22.executeUpdate();
            if (n > 0) {
                //JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN GUARDAR DATOS ", "NO SE PUEDE REGISTRAR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void guardar() {
        
        fecha1();
        fecha_2();
        try {
            String sql125 = "";
            sql125 = "INSERT INTO empleado (nombre,apellido,limite_credito,sector,calle,ciudad,fecha_nacimiento,fecha_ingreso,cedula,estado) VALUES('" + nombre.getText() + "',"
                    + "'" + apellido.getText() + "',"
                    + "'" + limite_credito.getText() + "','" + sector.getText() + "','" + calle.getText() + "','" + ciudad.getText() + "','" + fecha01 + "','" + fecha02 + "','" + cedula.getText() + "','" + estado.getSelectedItem() + "')";
            
            PreparedStatement psk22 = cn.prepareStatement(sql125);
            
            int n;
            n = psk22.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
               
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            JOptionPane.showMessageDialog(null, "ERROR EN GUARDAR DATOS ", "NO SE PUEDE REGISTRAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        idempleado = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sector = new javax.swing.JTextField();
        calle = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ciudad = new javax.swing.JTextField();
        fecha_nac = new com.toedter.calendar.JDateChooser();
        fecha_ingreso = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cedula = new javax.swing.JFormattedTextField();
        celular = new javax.swing.JFormattedTextField();
        residencial = new javax.swing.JFormattedTextField();
        limite_credito = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        estado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRAR EMPLEADOS");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("CODIGO");

        idempleado.setEditable(false);
        idempleado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        idempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idempleadoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("NOMBRES");

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

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("APELLIDO");

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

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("CEDULA");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("LIMITE DE CREDITO");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setText("SECTOR");

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

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("CIUDAD");

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

        fecha_nac.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        fecha_ingreso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("FECHA NACIMIENTO");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setText("FECHA DE INGRESO");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setText("CELULAR");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("RESIDENCIAL");

        try {
            cedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-#######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        cedula.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaActionPerformed(evt);
            }
        });

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

        try {
            residencial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        residencial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

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

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel16.setText("ESTADO");

        estado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE...", "ACTIVO", "INACTIVO", " " }));
        estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("CALLE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(cedula))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limite_credito))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calle, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9))
                            .addGap(8, 8, 8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(sector, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                .addComponent(ciudad)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel12))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel13)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(residencial, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(fecha_nac, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(fecha_ingreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ciudad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fecha_nac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fecha_ingreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(limite_credito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(calle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(residencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 59, 620, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\salvar.png")); // NOI18N
        jButton1.setText("Guardar");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\modifi.png")); // NOI18N
        jButton4.setText("Modificar");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\anadir (1).png")); // NOI18N
        jButton6.setText("Nuevo");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\salida.png")); // NOI18N
        jButton3.setText("Salir");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, -1, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\equipo (3).png")); // NOI18N
        jLabel14.setText("REGISTRO DE EMPLEADO");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 13, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1093, 2, -1, -1));

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\lupa.png")); // NOI18N
        jButton7.setText("BUSCAR EMPLEADO");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 340, 230, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\Desktop\\Proyecto duany\\proyectoVentaO-M\\src\\iconos\\equipo (2).png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 260, 260));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 912, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idempleadoActionPerformed
        //validacion();
        

    }//GEN-LAST:event_idempleadoActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        apellido.requestFocus();        
    }//GEN-LAST:event_nombreActionPerformed

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        cedula.requestFocus();        
    }//GEN-LAST:event_apellidoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (idempleado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CODIGO ESTA EN BLANCO");
            idempleado.requestFocus();
            return;
        }
        if (nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL NOMBRE");
            nombre.requestFocus();
            return;            
        }
        if (apellido.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL APELLIDO");
            apellido.requestFocus();
            return;            
        }
        if (cedula.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CEDULA");
            cedula.requestFocus();
            return;            
        }
        if (limite_credito.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL LIMITE DE CREDITO");
            limite_credito.requestFocus();
            return;            
        }
        if (sector.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL SECTOR");
            sector.requestFocus();
            return;            
        }
        if (calle.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CALLE");
            calle.requestFocus();
            return;            
        }
        if (ciudad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CIUDAD");
            ciudad.requestFocus();
            return;            
        }
        if (fecha_nac.getDate() == null) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA FECHA NACIMIENTO");
            ciudad.requestFocus();
            return;            
        }
        if (fecha_ingreso.getDate() == null) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA FECHA DE INGRESO DEL EMPLEADO");
            ciudad.requestFocus();
            return;            
        }
        if (celular.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL TELEFONO CELULAR");
            celular.requestFocus();
            return;            
        }
        if (residencial.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL TELEFONO RESIDENCIAL");
            residencial.requestFocus();
            return;            
        }
        if (estado.getSelectedItem().equals("SELECCIONE...")) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR EL ESTADO DEL EMPLEADO");
            estado.requestFocus();
            return;            
        }
        guardar();
        guardar_residencial();
        guardar_celular();
        limpiar();
        actualizar_codigo();
         numero_serie();
        nombre.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (idempleado.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "EL CODIGO ESTA EN BLANCO");
            idempleado.requestFocus();
            return;
        }
        if (nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL NOMBRE");
            nombre.requestFocus();
            return;            
        }
        if (apellido.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL APELLIDO");
            apellido.requestFocus();
            return;            
        }
        if (cedula.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CEDULA");
            cedula.requestFocus();
            return;            
        }
        if (limite_credito.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL LIMITE DE CREDITO");
            limite_credito.requestFocus();
            return;            
        }
        if (sector.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL SECTOR");
            sector.requestFocus();
            return;            
        }
        if (calle.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CALLE");
            calle.requestFocus();
            return;            
        }
        if (ciudad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA CIUDAD");
            ciudad.requestFocus();
            return;            
        }
        if (fecha_nac.getDate() == null) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA FECHA NACIMIENTO");
            ciudad.requestFocus();
            return;            
        }
        if (fecha_ingreso.getDate() == null) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR LA FECHA DE INGRESO DEL EMPLEADO");
            ciudad.requestFocus();
            return;            
        }
        if (celular.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL TELEFONO CELULAR");
            celular.requestFocus();
            return;            
        }
        if (residencial.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR EL TELEFONO RESIDENCIAL");
            residencial.requestFocus();
            return;            
        }
        if (estado.getSelectedItem().equals("SELECCIONE...")) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR EL ESTADO DEL EMPLEADO");
            estado.requestFocus();
            return;            
        }
        fecha1();
        fecha_2();
        
        try {
            PreparedStatement psU = cn.prepareStatement("UPDATE empleado SET idempleado='" + idempleado.getText() + "', nombre='" + nombre.getText() + "'"
                    + ",apellido='" + apellido.getText() + "',limite_credito= '" + limite_credito.getText() + "',fecha_nacimiento='" + fecha01 + "',fecha_ingreso='" + fecha02 + "'" + ",cedula='" + cedula.getText() + "'"
                    + " ,sector = '" + sector.getText() + "',calle='" + calle.getText() + "',ciudad='" + ciudad.getText() + "',estado='" + estado.getSelectedItem() + "'  where idempleado='" + idempleado.getText() + "'");
            
            psU.executeUpdate();
            JOptionPane.showMessageDialog(null, "DATOS MODIFICADOS CON EXITO");
        } catch (Exception ex) {
            Logger.getLogger(crear_articulo.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizar_residencial();
        actualizar_celular();
        jButton1.setEnabled(false);
        limpiar();        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       limpiar();
        if(temporal.privilegiosUser[3].crear==1) jButton1.setEnabled(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void sectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sectorActionPerformed
        ciudad.requestFocus();        
    }//GEN-LAST:event_sectorActionPerformed

    private void calleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calleActionPerformed
        sector.requestFocus();        
    }//GEN-LAST:event_calleActionPerformed

    private void ciudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciudadActionPerformed
        celular.requestFocus();        
    }//GEN-LAST:event_ciudadActionPerformed

    private void cedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaActionPerformed
        limite_credito.requestFocus();        
    }//GEN-LAST:event_cedulaActionPerformed

    private void celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularActionPerformed
        residencial.requestFocus();        
    }//GEN-LAST:event_celularActionPerformed

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        
        char a = evt.getKeyChar();        
        
        if (!Character.isLetter(a) && a != KeyEvent.VK_SPACE) {
            evt.consume();
        }        
        
    }//GEN-LAST:event_nombreKeyTyped

    private void apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoKeyTyped
        
        char a = evt.getKeyChar();        
        
        if (!Character.isLetter(a) && a != KeyEvent.VK_SPACE)
            evt.consume();        
    }//GEN-LAST:event_apellidoKeyTyped

    private void sectorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sectorKeyTyped
        
        char a = evt.getKeyChar();        
        
        if (!Character.isLetter(a) && a != KeyEvent.VK_SPACE)
            evt.consume();        
    }//GEN-LAST:event_sectorKeyTyped

    private void calleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_calleKeyTyped
        

    }//GEN-LAST:event_calleKeyTyped

    private void ciudadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ciudadKeyTyped
        
        char a = evt.getKeyChar();        
        
        if (!Character.isLetter(a) && a != KeyEvent.VK_SPACE)
            evt.consume();        
    }//GEN-LAST:event_ciudadKeyTyped

    private void limite_creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limite_creditoActionPerformed
        calle.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_limite_creditoActionPerformed

    private void limite_creditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_limite_creditoKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9')
            evt.consume();           // TODO add your handling code here:
    }//GEN-LAST:event_limite_creditoKeyTyped

    private void estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoActionPerformed

    }//GEN-LAST:event_estadoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        buscar_empleados window = new buscar_empleados();
        window.setVisible(true);
         if(temporal.privilegiosUser[3].modificar==1){
             jButton4.setEnabled(true);
         }
        jButton1.setEnabled(false);
                window.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new empleado_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField apellido;
    public static javax.swing.JTextField calle;
    public static javax.swing.JFormattedTextField cedula;
    public static javax.swing.JFormattedTextField celular;
    public static javax.swing.JTextField ciudad;
    public static javax.swing.JComboBox<String> estado;
    public static com.toedter.calendar.JDateChooser fecha_ingreso;
    public static com.toedter.calendar.JDateChooser fecha_nac;
    public static javax.swing.JTextField idempleado;
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    public static javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JTextField limite_credito;
    public static javax.swing.JTextField nombre;
    public static javax.swing.JFormattedTextField residencial;
    public static javax.swing.JTextField sector;
    // End of variables declaration//GEN-END:variables

    String fecha01 = "", fecha02 = "", id = "";
    
    conectar con = new conectar();
    Connection cn = con.connection();
    
}
