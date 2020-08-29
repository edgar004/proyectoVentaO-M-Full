package formularios;


import conectar.conectar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class facturas extends javax.swing.JFrame {

    
    public facturas() {
        initComponents();
         idfactura.requestFocus();
        Date sistema_fecha =new Date();
        SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
        fecha17.setText(formato.format(sistema_fecha));
        sumar();
    }
 void fecha1(){
       String fecha11;
      java.util.Date fecha = fecha1.getDate();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      fecha11 = formato.format(fecha);
      fecha01 = fecha11;
   }
    void fecha_2(){
       String fecha22;
      java.util.Date fecha = fecha2.getDate();
      SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
      fecha22 = formato.format(fecha);
      fecha02 = fecha22;
   }
      void cargar_fechas(){
          fecha1();
          fecha_2();
         DefaultTableModel modelo2 = (DefaultTableModel)tabla_fact.getModel();
     modelo2.getDataVector().clear();
        String [] registros = new String [9];
         String [] vector = new String [9];
        String sql ="SELECT factura_normal.fecha,factura_normal.tipo_factura,factura_normal.no_factura,factura_normal.itbis,factura_normal.monto FROM factura_normal where factura_normal.fecha between '"+fecha01+"' and '"+fecha02+"'  ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                
               
                registros[0]=rs.getString("fecha");
                registros[1]=rs.getString("tipo_factura");
                registros[2]=rs.getString("no_factura");
                registros[3]=rs.getString("itbis");
                registros[4]=rs.getString("monto");
               
                               
                
                modelo2.addRow(registros);
               
            }
          tabla_fact.setModel(modelo2); 
          total.setText(registros[5]);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     void sumar() {
        DecimalFormat formateador = new DecimalFormat("RD$###,###,###,###.##");
         String jtotal = "";
         float cv1 = 0 ;
         float suma1 = 0, suma2 = 0, suma3 = 0;
         
          for (int j = 0; j < tabla_fact.getRowCount(); j++) {
            jtotal = (tabla_fact.getValueAt(j, 4).toString());
            cv1 = Float.parseFloat(jtotal);
            suma1 = suma1 + cv1;
           }
          total.setText(formateador.format(suma1));
         
              
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
   void cargar(){
         DefaultTableModel modelo2 = (DefaultTableModel)tabla_fact.getModel();
     modelo2.getDataVector().clear();
        String [] registros = new String [9];
        String [] vector = new String [9];
        String sql ="SELECT factura_normal.fecha,factura_normal.tipo_factura,factura_normal.no_factura,factura_normal.itbis,factura_normal.monto FROM factura_normal where  factura_normal.no_factura ='"+idfactura.getText()+"' ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
               
                registros[0]=rs.getString("fecha");
                registros[1]=rs.getString("tipo_factura");
                registros[2]=rs.getString("no_factura");
                registros[3]=rs.getString("itbis");
                registros[4]=rs.getString("monto");
               
                
                modelo2.addRow(registros);
               
            }
          tabla_fact.setModel(modelo2); 
          total.setText(registros[5]);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_fact = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_detalle = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idfactura = new javax.swing.JTextField();
        fecha17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        fecha2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        fecha1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FACTURAS AL CONTADO");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabla_fact.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tabla_fact.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FECHA", "TIPO FACTURA", "NUMERO FACTURA", "ITBIS", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_fact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_factMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_fact);
        if (tabla_fact.getColumnModel().getColumnCount() > 0) {
            tabla_fact.getColumnModel().getColumn(0).setResizable(false);
            tabla_fact.getColumnModel().getColumn(1).setResizable(false);
            tabla_fact.getColumnModel().getColumn(2).setResizable(false);
            tabla_fact.getColumnModel().getColumn(3).setResizable(false);
            tabla_fact.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setText("TOTAL");

        total.setEditable(false);
        total.setBackground(new java.awt.Color(0, 204, 255));
        total.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        tabla_detalle.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
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

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("No. FACTURA");

        idfactura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        idfactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idfacturaActionPerformed(evt);
            }
        });

        fecha17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fecha17.setForeground(new java.awt.Color(0, 0, 153));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/buscar.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        fecha2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("HASTA");

        fecha1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel2.setText("DESDE");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setText("FECHA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idfactura, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(fecha17, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(idfactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(fecha1, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fecha17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 607, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idfacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idfacturaActionPerformed
        cargar();
        sumar();
        
    }//GEN-LAST:event_idfacturaActionPerformed

    private void tabla_factMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_factMouseClicked
        int fila = tabla_fact.getSelectedRow();
        if (fila >= 0) {

            idfacture=(tabla_fact.getValueAt(fila, 2).toString());

        }

        cargar_detalle();
        
    }//GEN-LAST:event_tabla_factMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargar_fechas();  
        sumar();
    }//GEN-LAST:event_jButton1ActionPerformed

        public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new facturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser fecha1;
    private javax.swing.JLabel fecha17;
    private com.toedter.calendar.JDateChooser fecha2;
    private javax.swing.JTextField idfactura;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabla_detalle;
    private javax.swing.JTable tabla_fact;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
 String fecha01 = "",fecha02 ="",idusuario="",iduser = "", nom = "",cod_cli="",idfacture = "";
    
      conectar con = new conectar();
      Connection cn = con.connection();

}
