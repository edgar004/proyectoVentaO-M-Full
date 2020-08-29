/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import clases.sql_usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author user
 */
public class carga_tab_permisos_usuarios extends WindowAdapter {

    public carga_tab_permisos_usuarios(formularios.Registro_usuarios jframe_usuario) {

        this.jframe_usuario = jframe_usuario;
        sql_usuario = new sql_usuario();
        rs = null;
        dtm = new DefaultTableModel();
        dtm1 = new DefaultTableModel();
    }

    public boolean carga_tab_permiso_usuario() {

        String[] Columnas = {"Aplicaciones/Modulos", "Acceso", "Crear", "Modificar", "Borrar"};
        dtm.setColumnIdentifiers(Columnas);
        jframe_usuario.jtable_permiso.setModel(dtm);
        dtm.getDataVector().clear();
        JTableHeader thead = jframe_usuario.jtable_permiso.getTableHeader();
        thead.setForeground(Color.BLUE);
        thead.setBackground(Color.WHITE);
        thead.setFont(new Font("Arial", Font.BOLD, 25));
        int[] anchos = {250, 50, 50, 90, 50, 50, 70, 70};
        for (int i = 0; i < jframe_usuario.jtable_permiso.getColumnCount(); i++) {
            jframe_usuario.jtable_permiso.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        Object datos[] = sql_usuario.busca_permiso_usuario(jframe_usuario.txtfiltro_permiso.getText());
        rs = (ResultSet) datos[0];
        try {
            while (rs.next()) {
                Object[] filas = new Object[5];
                for (int i = 0; i < 5; i++) {
                    filas[0] = rs.getString("aplicaciones_modulos");
                    filas[1] = false;
                    filas[2] = false;
                    filas[3] = false;
                    filas[4] = false;
                    if (i > 0) {
                        chex(i, jframe_usuario.jtable_permiso);
                        
                    }
                }
                dtm.addRow(filas);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connection con = (Connection) datos[2];
            CallableStatement enlaza_bbdd = (CallableStatement) datos[1];
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                enlaza_bbdd.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return true;
    }
    
    
    
    public boolean limpiar_permiso() {

        String[] Columnas = {"Aplicaciones/Modulos", "Acceso", "Crear", "Modificar", "Borrar"};
        dtm.setColumnIdentifiers(Columnas);
        jframe_usuario.jtable_permiso.setModel(dtm);
        dtm.getDataVector().clear();
        JTableHeader thead = jframe_usuario.jtable_permiso.getTableHeader();
        thead.setForeground(Color.BLUE);
        thead.setBackground(Color.WHITE);
        thead.setFont(new Font("Arial", Font.BOLD, 25));
        int[] anchos = {250, 50, 50, 90, 50, 50, 70, 70};
        for (int i = 0; i < jframe_usuario.jtable_permiso.getColumnCount(); i++) {
            jframe_usuario.jtable_permiso.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
        Object datos[] = sql_usuario.busca_permiso_usuario("0000000");
        rs = (ResultSet) datos[0];
        try {
            while (rs.next()) {
                Object[] filas = new Object[5];
                for (int i = 0; i < 5; i++) {
                    filas[0] = rs.getString("aplicaciones_modulos");
                    filas[1] = false;
                    filas[2] = false;
                    filas[3] = false;
                    filas[4] = false;
                    if (i > 0) {
                        chex(i, jframe_usuario.jtable_permiso);
                        
                    }
                }
                dtm.addRow(filas);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connection con = (Connection) datos[2];
            CallableStatement enlaza_bbdd = (CallableStatement) datos[1];
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                enlaza_bbdd.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return true;
    }

    public void chex(int column, JTable table) {
        TableColumn tc = table.getColumnModel().getColumn(column);
        tc.setCellEditor(table.getDefaultEditor(Boolean.class));
        tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));

    }


    @Override
    public void windowOpened(WindowEvent e) {
        carga_tab_permiso_usuario();

    }

    private clases.sql_usuario sql_usuario;
    private formularios.Registro_usuarios jframe_usuario;
    private ResultSet rs;
    private DefaultTableModel dtm;
    private DefaultTableModel dtm1;

}
