/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 import java.sql.*;
import javax.swing.JOptionPane;


public class conectar {
    public Statement sentencia ;
Connection cn;

public Connection connection(){
try{
    Class.forName("org.gjt.mm.mysql.Driver");
    cn = DriverManager.getConnection("jdbc:mysql://localhost/facturacion99?characterEncoding=latin1","root","duany2015");
    sentencia = cn.createStatement();
    System.out.println("Se Realizo la Conexion");
    
 
    
     /*try {
            FileReader leer = new FileReader("DGII_RNC.txt");
            BufferedReader b= new BufferedReader(leer);
            String dato=b.readLine();
            while(dato!=null){
              

                try {
                     String sql125= "";
           sql125 = "INSERT INTO tabla_rnc (rnc,nombre) VALUES('"+dato.split("~")[0]+"',"
                   + "'"+dato.split("~")[1]+"')";
                  
                  
           
                  
           PreparedStatement psk22 = cn.prepareStatement(sql125);
           
             int n ;
             n = psk22.executeUpdate();
             if (n > 0){
                 System.out.println();
                 
                }
       } catch (Exception ex) {
                       System.out.println("Entro 1");

           //JOptionPane.showMessageDialog(null, ex);
          // JOptionPane.showMessageDialog(null, "ERROR EN GUARDAR DATOS ","NO SE PUEDE REGISTRAR",JOptionPane.ERROR_MESSAGE);
       }
              
                dato=b.readLine();
            }
            leer.close();
            b.close();
          JOptionPane.showMessageDialog(null, "DATOS REGISTRADOS CON EXITO");
        } catch (IOException ex) {
            System.out.println("Entro");
//             JOptionPane.showMessageDialog(null,"El archivo no existe","Error",JOptionPane.ERROR_MESSAGE);
        }*/
        
    
     
    
}   catch (Exception e) {  
     JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

      
        System.out.println(e.getMessage());
    }return cn;
}

Statement createStatement(){
    throw new UnsupportedOperationException("No Soportado");
}

    void conexion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }






}