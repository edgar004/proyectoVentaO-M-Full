
package facturacion_oym;


public class inventario_art {
   private String tipo_articulo;
    private String descripcion;
   
    private String existencia; 
    private String valor_inventario; 
    
     public inventario_art( String tipo_articulo, String descripcion, String existencia,String valor_inventario){
         this.tipo_articulo = tipo_articulo;
        this.descripcion = descripcion;
         this.existencia = existencia;  
         this.valor_inventario=valor_inventario; 
    }
     public inventario_art(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getTipo_articulo() {
        return tipo_articulo;
    }

    public void setTipo_articulo(String tipo_articulo) {
        this.tipo_articulo = tipo_articulo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
      public String getValorInventario() {
        return valor_inventario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
    }
    
     public void setValorInventario(String valor_inventario) {
        this.valor_inventario = valor_inventario;
    }
    
     
}
