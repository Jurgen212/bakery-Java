public class alimento extends producto{

    private tipo_alimento tipo;
    
    public alimento(String nombre, int precio, tipo_alimento tipo ) {
        super(nombre, precio);

        this.tipo = tipo;
    };

    public tipo_alimento getTipo() {
        return tipo;
    };

    public void setTipo(tipo_alimento tipo) {
        this.tipo = tipo;
    }; 
}
