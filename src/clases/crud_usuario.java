/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class crud_usuario {

    public crud_usuario() {

        con = new conectar.conectar();
        cn = null;
        enlasa_bbdd = null;
        rs = null;

    }

  
    public int busca_id_permisos(String permiso) {
        int id_permiso = 0;
        cn = con.connection();
        try {
            String sql = "{call busca_id_permiso_usuario(?)}";
            enlasa_bbdd = cn.prepareCall(sql);
            enlasa_bbdd.setObject(1, permiso);
            rs = enlasa_bbdd.executeQuery();
            while (rs.next()) {
                id_permiso = rs.getInt("id_permiso_usuario");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                enlasa_bbdd.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                cn.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return id_permiso;

    }

 

 

    public boolean inserta_role_usuarios(int id_permiso_usu, int id_usuario, boolean acceso, boolean crear, boolean modificar, boolean borrar, boolean anular, boolean imprimir, boolean exportar) {
        cn = con.connection();
        try {
            String sql = "{call inserta_roles_usuario(?,?,?,?,?,?,?,?,?)}";
            enlasa_bbdd = cn.prepareCall(sql);
            enlasa_bbdd.setInt(1, id_permiso_usu);
            enlasa_bbdd.setInt(2, id_usuario);
            enlasa_bbdd.setBoolean(3, acceso);
            enlasa_bbdd.setBoolean(4, crear);
            enlasa_bbdd.setBoolean(5, modificar);
            enlasa_bbdd.setBoolean(6, borrar);
            enlasa_bbdd.setBoolean(7, anular);
            enlasa_bbdd.setBoolean(8, imprimir);
            enlasa_bbdd.setBoolean(9, exportar);
            int res = enlasa_bbdd.executeUpdate();
            if (res > 0) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                enlasa_bbdd.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                cn.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return true;
    }

    public boolean actualiza_roles_usuarios(int id_permiso_usu, int id_usuario, boolean acceso, boolean crear, boolean modificar, boolean borrar, boolean anular, boolean imprimir, boolean exportar) {
        cn = con.connection();
        try {
            String sql = "{call actualiza_roles_usuario(?,?,?,?,?,?,?,?,?)}";
            enlasa_bbdd = cn.prepareCall(sql);
            enlasa_bbdd.setInt(1, id_permiso_usu);
            enlasa_bbdd.setInt(2, id_usuario);
            enlasa_bbdd.setBoolean(3, acceso);
            enlasa_bbdd.setBoolean(4, crear);
            enlasa_bbdd.setBoolean(5, modificar);
            enlasa_bbdd.setBoolean(6, borrar);
            enlasa_bbdd.setBoolean(7, anular);
            enlasa_bbdd.setBoolean(8, imprimir);
            enlasa_bbdd.setBoolean(9, exportar);
            int res = enlasa_bbdd.executeUpdate();
            if (res > 0) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                enlasa_bbdd.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
            try {
                cn.close();
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        return true;
    }

    private conectar.conectar con;
    private Connection cn;
    private CallableStatement enlasa_bbdd;
    private ResultSet rs;

}
