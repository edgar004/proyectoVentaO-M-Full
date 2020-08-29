/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author user
 */
public class sql_usuario {

    public sql_usuario() {
        rs = null;
        enlazabbdd = null;
        cn = null;
        con = new conectar.conectar();
    }

    public Object[] busca_permiso_usuario(String valor) {
        Object[] dato;
        cn = con.connection();
        try {

            String sql = "select *from permisos_usuarios where concat (aplicaciones_modulos) like '%" + valor + "%'";
            enlazabbdd = cn.prepareCall(sql);
            rs = enlazabbdd.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Object[]{rs, enlazabbdd, cn};
    }

  
//    public Object[] busca_usuario(String valor) {
//        Object[] dato;
//        cn = con.dameConexion();
//        try {
//
//            String sql = "select U.id_usuario,U.usuario,C.cargo from usuario as U \n"
//                    + "inner join empleado as E on U.id_empleado = E.id_empleado\n"
//                    + "inner join cargo as C on E.id_cargo = C.id_cargo where concat (U.usuario,'',C.cargo) like '%" + valor + "%'";
//            enlazabbdd = cn.prepareCall(sql);
//            rs = enlazabbdd.executeQuery();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return new Object[]{rs, enlazabbdd, cn};
//    }
//
//    public Object[] busca_id_usuario() {
//        Object[] dato;
//        try {
//            cn = con.dameConexion();
//            String sql = "{call dame_id_max_usuario()}";
//            enlazabbdd = cn.prepareCall(sql);
//            rs = enlazabbdd.executeQuery();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new Object[]{rs, enlazabbdd, cn};
//    }
//
//    public Object[] busca_id_usuario_usu(String usuario) {
//        Object[] dato;
//        cn = con.dameConexion();
//
//        try {
//            String sql = "{call busca_id_usuario_usu(?)}";
//            enlazabbdd = cn.prepareCall(sql);
//            enlazabbdd.setString(1, usuario);
//            rs = enlazabbdd.executeQuery();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new Object[]{rs, enlazabbdd, cn};
//    }
//
//    public Object[] busca_permisos_usuarios(int id_permiso_usuario) {
//        Object[] dato;
//        try {
//            cn = con.dameConexion();
//            String sql = "{call busca_permiso_usuario(?)}";
//            enlazabbdd = cn.prepareCall(sql);
//            enlazabbdd.setInt(1, id_permiso_usuario);
//            rs = enlazabbdd.executeQuery();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new Object[]{rs, enlazabbdd, cn};
//    }
//
    public Object[] busca_permiso_roles_usu(int id_usuario) {
        Object[] dato;
        cn = con.connection();
        try {
            String sql = "{call busca_permiso_usu(?)}";
            enlazabbdd = cn.prepareCall(sql);
            enlazabbdd.setInt(1, id_usuario);
            rs = enlazabbdd.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Object[]{rs, enlazabbdd, cn};
    }

//    public Object[] busca_id_usuario_empleado(int id_empleado) {
//        Object[] dato;
//        cn = con.dameConexion();
//        try {
//            String sql = "{call busca_id_usuario(?)}";
//            enlazabbdd = cn.prepareCall(sql);
//            enlazabbdd.setInt(1, id_empleado);
//            rs = enlazabbdd.executeQuery();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new Object[]{rs, enlazabbdd, cn};
//    }
    private ResultSet rs;
    private CallableStatement enlazabbdd;
    private Connection cn;
    private conectar.conectar con;

}
