
package formularios;



public class temporal { 
    public static String codigo = "";
     public static String nombre = "";
     public static   privilegios[] privilegiosUser = new privilegios[50];
     
    public   temporal(){
        for(int i=0;i<50;i++){
            privilegiosUser[i]=new privilegios();
        }
    }
    
    public static String getTexto ()
    {
        return codigo;
    }
    public static void setTexto(String aCodigo){
        codigo = aCodigo;
    }
    
      public static String getNombre ()
    {
        return nombre;
    }
    public static void setNombre(String anombre){
       nombre = anombre;
    }
            
}