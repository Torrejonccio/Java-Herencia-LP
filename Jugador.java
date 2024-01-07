import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Jugador extends Personaje {
    String nombre;
    int xp;
    List<Item> inventario;
    Map<String, Equipamiento> equipamiento;
    Arma arma;
    int pos_horizontal;
    int pos_vertical;

    public Jugador(float hp ,int nivel ,String nombre, int xp, List<Item> inventario, Map<String, Equipamiento> equipamiento, int pos_horizontal, int pos_vertical){
        super(hp, nivel);
        this.nombre = nombre;
        this.xp = xp;
        this.inventario = inventario;
        this.equipamiento = equipamiento;
        this.pos_horizontal = pos_horizontal;
        this.pos_vertical = pos_vertical;
    }

    //public char getRepresentacion(){
    //    return 'J';
    //}

    /**
     * Aumenta el XP del jugador
     * @param xp: Cantidad de XP a aumentar
     */
    public void ganarXp(int xp){
        this.xp = this.xp + xp;
        if(this.xp % 100 == 0){
            nivel += 1;
        }
    }

    /**
     * Equipa un arma al jugador
     * @param arma: Arma a equipar
     */
    public void equipar(Arma arma){
        //Arma auxiliar = this.arma;
        this.arma = arma;
        //inventario.add(auxiliar);
    }

    /**
     * Equipa un tipo de equipamiento al jugador
     * @param equipamiento: Equipamiento a equipar
     */
    public void equipar(Equipamiento equipamiento){
        if(this.equipamiento.containsKey(equipamiento.getTipo())){
          Equipamiento auxiliar = this.equipamiento.get(equipamiento.getTipo());
          if(!Objects.equals(auxiliar.getNombre(), "VACÍO")){
              inventario.add(auxiliar);
          }
        }
        this.equipamiento.put(equipamiento.getTipo(), equipamiento);
    }


    /**
     * Imprime por consola las estadisticas del jugador
     */
    public void ver_estadisticas(){

        System.out.println(nombre);
        System.out.println("Nivel: " + nivel);
        System.out.println("XP: " + xp);
        System.out.println("HP: " + hp);
        System.out.println("Ataque: "+ calcularAtaque());
        System.out.println("Equipamiento:\n- Armadura: " + equipamiento.get("Armadura").getNombre() + "\n- Botas: " + equipamiento.get("Botas").getNombre() + "\n- Amuleto: " + equipamiento.get("Amuleto").getNombre());
        System.out.println("Arma: " + arma.getNombre());

    }

    /**
     * Mueve el jugador a una nueva posición
     * @param direccion: Dirección en la que se mueve el jugador
     * @param mundo: Objeto mundo para obtener el mapa y actualizarlo con la nueva posición del jugador
     * @return getRepresentacion: Retorna el caracter encontrado en la nueva posición del jugador
     */
    public char mover_jugador(char direccion, Mundo mundo){
        Visible nuevo_n = new Visible();
        nuevo_n.setRepresentacion('N');
        mundo.getMapa().get(pos_vertical).set(pos_horizontal, nuevo_n);

        if(direccion == 'N'){
            pos_vertical -= 1;
        }
        else if(direccion == 'S'){
            pos_vertical += 1;
        }
        else if(direccion == 'O'){
            pos_horizontal -= 1;
        }
        else if(direccion == 'E'){
            pos_horizontal += 1;
        }

        //if(pos_horizontal >= 0 || pos_vertical >= 0){} ((No me dio para hacer algo que detenga la función en caso de que se salga del mapa))

        Visible represent_jugador = new Visible();
        represent_jugador.setRepresentacion('J');
        Visible represent = mundo.getMapa().get(pos_vertical).get(pos_horizontal);


        mundo.getMapa().get(pos_vertical).set(pos_horizontal, represent_jugador);
        return represent.getRepresentacion();
    }

}
