public class Equipamiento extends Item {

    private final String tipo;

    public Equipamiento(String nombre, String tipo){
        super(nombre);
        this.tipo = tipo;
    }

    public String getTipo(){return tipo;}

}
