
package formularios;


public class factura_credito {
   
    private String descripcion;
    private String cantidad;
    
    private String precio;
    private String importe;
      
    
    public factura_credito( String descripcion, String cantidad,String precio,  String importe){
        
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        
        this.precio = precio;
        this.importe = importe;
        
        
        
    }

    public factura_credito(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    public String getdescripcion(){
        return descripcion;
    }
    public void setdescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public String getcantidad(){
        return cantidad;
    }
    public void setcantidad(String cantidad){
        this.cantidad = cantidad;
    }
    
    
    public String getprecio(){
        return precio;
    }
    public void setprecio(String precio){
        this.precio = precio;
    }
   
    public String getimporte(){
        return importe;
    }
    public void setimporte(String importe){
        this.importe = importe;
    }
    
}
