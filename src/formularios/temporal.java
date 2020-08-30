
package formularios;



public class temporal { 
    public static String codigo = "";
     public static String nombre = "";
     public static  boolean entro=false;
     public static   privilegios[] privilegiosUser = new privilegios[50];
     
    public   temporal(){
        if(entro) return;
        for(int i=0;i<50;i++){
            privilegiosUser[i]=new privilegios();
        }
        entro=true;
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