public class Personaje {
    public float hp;
    public int nivel;


    public Personaje(float hp, int nivel){
        this.hp = hp;
        this.nivel = nivel;
    }


    /*

    public float getHp(){return hp;}
    public void setHp(float hp){this.hp = hp;}

    public int getNivel(){return nivel;}
    public void setNivel(int nivel){this.nivel = nivel;}
    public char getRepresentacion(){
        return 'O';
    }

    public void recibirDanio(int dmg){
        this.hp = this.hp - dmg;
    }

    */

    /**
     * Calcula el ataque actual del personaje
     * @return: Retorna el ataque ya calculado
     */
    public float calcularAtaque(){
        return 3 * this.nivel;
    }
}
