/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import formularios.Registro_usuarios;
import formularios.buscarUsuarios;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author user
 */
public class evento_mouse_tab_permiso_usu extends MouseAdapter {

    public evento_mouse_tab_permiso_usu(formularios.Registro_usuarios jframe_usuario) {
        crud_usuario = new clases.crud_usuario();
        rs = null;
    }

    public void actualiza_roles_usu() {
        if (!(buscarUsuarios.tabla_usu.getSelectedRow() == -1) && Registro_usuarios.jtable_permiso.isEnabled() == true) {

            int id_permiso_usu = 0, id_usuario = 0, fila = 0,fila_tabla_usuario=0;
            String permiso_usu = "";
            boolean acceso = false, crear = false, modificar = false, borrar = false, anular = false, imprimir = false, exportar = false;
            fila = Registro_usuarios.jtable_permiso.getSelectedRow();
            fila_tabla_usuario = buscarUsuarios.tabla_usu.getSelectedRow();
            id_usuario = Integer.parseInt(buscarUsuarios.tabla_usu.getValueAt(fila_tabla_usuario, 0).toString());
            permiso_usu = Registro_usuarios.jtable_permiso.getValueAt(fila, 0).toString();
            acceso = Boolean.parseBoolean(Registro_usuarios.jtable_permiso.getValueAt(fila, 1).toString());
            crear = Boolean.parseBoolean(Registro_usuarios.jtable_permiso.getValueAt(fila, 2).toString());
            modificar = Boolean.parseBoolean(Registro_usuarios.jtable_permiso.getValueAt(fila, 3).toString());
            borrar = Boolean.parseBoolean(Registro_usuarios.jtable_permiso.getValueAt(fila, 4).toString());
//            anular = Boolean.parseBoolean(Registro_usuarios.jtable_permiso.getValueAt(fila, 5).toString());
  //          imprimir = Boolean.parseBoolean(Registro_usuarios.jtable_permiso.getValueAt(fila, 6).toString());
    //        exportar = Boolean.parseBoolean(Registro_usuarios.jtable_permiso.getValueAt(fila, 7).toString());
            id_permiso_usu = crud_usuario.busca_id_permisos(permiso_usu);
            try {
                crud_usuario.actualiza_roles_usuarios(id_permiso_usu, id_usuario, acceso, crear, modificar, borrar, anular, imprimir, exportar);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(Registro_usuarios.boton_modificar.isEnabled()==false) return;
        actualiza_roles_usu();
        JOptionPane.showMessageDialog(null, "Actualizado");
    }
    private clases.crud_usuario crud_usuario;
    private ResultSet rs;
}
