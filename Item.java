public class Item {
    //private char representacion;
    private final String nombre;
    //--------------------------//

    public Item(String nombre){
        //this.representacion = representacion;
        this.nombre = nombre;
    }

    //public char getRepresentacion(){return representacion;}
    //public void setRepresentacion(char representacion){this.representacion = representacion;}

    public String getNombre(){return nombre;}
}
