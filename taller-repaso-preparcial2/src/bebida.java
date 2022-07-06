public class bebida extends producto{
 
    private tipo_bebida tipo;

       
    public bebida(String nombre, int precio, tipo_bebida tipo) {
        super(nombre, precio);
        
        this.tipo = tipo;
    }

    public tipo_bebida getTipo() {
        return tipo;
    }


    public void setTipo(tipo_bebida tipo) {
        this.tipo = tipo;
    }


    
}
