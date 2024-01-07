import java.util.List;

public class Mundo {
    private int nivel;
    private final int ancho;
    private List<List<Visible>> mapa;
    private final int alto;

    public Mundo(int nivel, int alto, int ancho){
        this.nivel = nivel;
        this.alto = alto;
        this.ancho = ancho;
    }

    public int getNivel(){
        return nivel;
    }

    public List<List<Visible>> getMapa(){
        return mapa;
    }

    public void setMapa(List<List<Visible>> mapa){
        this.mapa = mapa;
    }

    /**
     * Imprime por pantalla el mapa del mundo
     */
    public void mostrar(){
        System.out.println();
        for(int i = 0; i < alto; i++){
            for(int j = 0; j < ancho; j++){
                Visible obj = mapa.get(i).get(j);
                char letra = obj.getRepresentacion();
                System.out.print(letra + "    ");
            }
            System.out.println("\n");
        }
    }

    /**
     * Obtiene la cantidad de enemigos en el mapa
     * @return: Retorna la cantidad total de enemigos
     */
    public int cant_enemigos_mapa(){
        int enemigos = 0;
        for(int i = 0; i < alto; i++){
            for(int j = 0; j < ancho; j++){
                Visible obj = mapa.get(i).get(j);
                char letra = obj.getRepresentacion();
                if(letra == 'O'){
                    enemigos = enemigos + 1;
                }
            }
        }
        return enemigos;
    }

    /**
     * Aumenta el nivel del mundo
     */
    public void nuevoNivel(){
        nivel += 1;
    }
}

