/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class filtra_tab_permiso_usuario extends KeyAdapter {

    public filtra_tab_permiso_usuario(formularios.Registro_usuarios jframe_usuario) {
        this.jframe_usuario = jframe_usuario;
//        this.var_cat = var_cat;
        rs = null;
        dtm = new DefaultTableModel();
        car_table_usuario = new carga_tab_permisos_usuarios(jframe_usuario);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        car_table_usuario.carga_tab_permiso_usuario();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        String tecla_pulsada = String.valueOf(e.getKeyChar());
        if (tecla_pulsada.equals("'")) {
            e.consume();
        }
    }

    private formularios.Registro_usuarios jframe_usuario;
    private ResultSet rs;
    private DefaultTableModel dtm;
    private carga_tab_permisos_usuarios car_table_usuario;

}
