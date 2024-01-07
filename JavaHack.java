import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.HashMap;
import java.lang.Math;
import java.util.Random;



public class JavaHack {
    public static void main(String[] args) {

        //-------------------- Inicialización del jugador y mundo --------------------------//

        Scanner scanner = new Scanner(System.in);

        Arma espada_basica = new Arma("Espada Básica");

        Equipamiento armad_inicial = new Equipamiento("VACÍO", "Armadura");
        Equipamiento botas_inicial = new Equipamiento("VACÍO", "Botas");
        Equipamiento amulet_inicial = new Equipamiento("VACÍO", "Amuleto");

        Item completo = new Item("Completo Italiano: Restaura 40 de HP");
        Item completo_con_chucrut = new Item("Completo tradicional con chucrut: Restaura 5 de HP");

        List<Item> lista_items_posibles = new ArrayList<>();
        lista_items_posibles.add(completo);
        lista_items_posibles.add(completo_con_chucrut);

        List<Item> inventario = new ArrayList<>();

        Map<String, Equipamiento> equipamientoMap = new HashMap<>();

        System.out.print("Ingrese nombre de tu personaje: ");
        Jugador jugador = new Jugador(100.0f, 1, scanner.nextLine(), 100, inventario, equipamientoMap, 0, 0);

        System.out.print("Ingrese ancho del mundo: ");
        int ancho = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese alto del mundo: ");
        int alto = Integer.parseInt(scanner.nextLine());

        Mundo mundo = new Mundo(1, alto, ancho);

        List<List<Visible>> mapa = generar_mapa(ancho, alto, mundo.getNivel());

        mundo.setMapa(mapa);

        while (mundo.cant_enemigos_mapa() == 0) {
            mundo.nuevoNivel();
            List<List<Visible>> nuevo_mapa = generar_mapa(ancho, alto, mundo.getNivel());
            mundo.setMapa(nuevo_mapa);
        }

        jugador.equipar(armad_inicial);
        jugador.equipar(botas_inicial);
        jugador.equipar(amulet_inicial);

        jugador.equipar(espada_basica);

        //---------------------------------------------------------------------//

        while (true){
            mundo.mostrar();

            System.out.print("Seleccione alguna acción:\n(1) Ver estadisticas\n(2) Mover\n(3) Ver inventario\n(4) Salir\n");

            String opcion_str = scanner.nextLine();

            int opcion = Integer.parseInt(opcion_str);

            System.out.println();

            if (opcion == 4) {
                break;
            } else if (opcion == 1) {
                jugador.ver_estadisticas();
            } else if (opcion == 3) {
                ver_inventario(jugador.inventario);
            }

            //System.out.println("¿Desea equipar/utilizar algún item?\n(1) Si\n(2) No");
            //int opcion_inv = scanner.nextInt();
            //if(opcion_inv == 1){
            //System.out.println("Indique número del item que desea equipar/utilizar: ");
            //int opcion_item = scanner.nextInt();
            //Item objeto = inventario.get(opcion_item-1);


            else if (opcion == 2) {
            System.out.println("Seleccione dirección:\n(N) Arriba\n(S) Abajo\n(O) Izquierda\n(E) Derecha\n");
            String input = scanner.nextLine();
            char direccion = input.charAt(0);
            char casilla_actual = jugador.mover_jugador(direccion, mundo);
            int num = accion_casilla(casilla_actual, lista_items_posibles);
            if (num == 9999) {
                jugador.ganarXp(50);
            }
            else{
                jugador.inventario.add(lista_items_posibles.get(num));
            }
        }
    }
}


    /**
     * Genera el array 2D del mundo a partir de un ancho, alto, y nivel
     * @param ancho: Corresponde al ancho del mapa creado
     * @param alto: Corresponde al alto del mapa creado
     * @param nivel: Corresponde al nivel del mapa creado
     * @return mapa: Corresponde al mapa (array 2D) ya generado
     */
    private static List<List<Visible>> generar_mapa(int ancho, int alto, int nivel) {
        List<List<Visible>> mapa = new ArrayList<>();

        for(int i = 0; i < alto; i++){

            List<Visible> fila = new ArrayList<>();

            for(int j = 0; j < ancho; j++){

                Visible visible = new Visible();

                if(j == 0 && i == 0){visible.setRepresentacion('J');}

                else{
                    float r = (float)(Math.random());

                    if(r <= (Math.min(0.05 + 0.01 * nivel, 20))){

                        visible.setRepresentacion('I');
                    }
                    else if((Math.min(0.05 + 0.01 * nivel, 20) < r) && (r <= (Math.min(0.2 + 0.01 * nivel, 55)))){

                        visible.setRepresentacion('O');
                    }
                    else {

                        visible.setRepresentacion('N');
                    }
                }
                fila.add(visible);
            }
            mapa.add(fila);
        }
        return mapa;
    }

    /**
     * Ejecuta una acción o evento a partir de la casilla en la que quedó el jugador al moverse
     * @param casilla: Casilla en la que queda el jugador después de moverse
     * @param lista_items_posibles: Lista de los items posibles que pueden aparecer aleatoriamente
     * @return num_aleatorio: El index del item en la lista que recoge el jugador
     */
    private static int accion_casilla(char casilla, List<Item> lista_items_posibles){
        if(casilla == 'I'){

            Random random = new Random();

            int num_aleatorio = random.nextInt(lista_items_posibles.size());

            System.out.println("Has recogido un item: " + lista_items_posibles.get(num_aleatorio).getNombre());

            return num_aleatorio;
        }
        else if(casilla == 'O'){
            //Random random = new Random();
            System.out.println("Ha aparecido un enemigo");
            //float hp_aleatorio = (float)random.nextInt(51) + 50;
            //int nivel_aleatorio = random.nextInt(3) + 1;
            //Personaje enemigo = new Personaje(hp_aleatorio, nivel_aleatorio * mundo.getNivel());

            return 9999;
        }
        return 9999;
    }

    /**
     * Imprime por consola el inventario actual del jugador
     * @param inventario: Inventario actual del jugador
     */
    private static void ver_inventario(List<Item> inventario){
        System.out.print("Inventario: \n");

        if(inventario.size() == 0){return;}

        for(int i = 0; i < inventario.size(); i++){

            System.out.print("- ("+ (i+1) +") " + inventario.get(i).getNombre() + "\n");
        }
    }
}