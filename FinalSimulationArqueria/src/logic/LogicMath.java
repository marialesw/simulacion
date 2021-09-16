package logic;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que contiene la logica de los calculos matematicos necesarios
 *
 * @author Maria Latorre
 */
public class LogicMath {

    private ArrayList<Integer> listNumberRandomTimeDay;
    private int position;

    public LogicMath() {
        listNumberRandomTimeDay = new ArrayList<>();
        position = 0;
    }

    /**
     * Generar numero aleatorio
     *
     * @return
     */
    public float generateNumberRandom() {

        float numberSelected = listNumberRandomTimeDay.get(position);
        position++;
        return numberSelected;
    }

    /**
     * random segundos al dia
     *
     * @param size
     * @return
     */
    public int randomSeed(int size) {
        String res = "";
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(10);
            res += number;
        }
        return Integer.parseInt(res);
    }

    /**
     * Extraer aleatorio
     *
     * @param cad
     * @return
     */
    public int extractRandon(long cad) {
        String aux = String.valueOf(cad);
        String aux2 = "";
//        System.out.println(aux);
        for (int i = aux.length() - 1; i >= aux.length() - 3; i--) {
            aux2 += aux.charAt(i);

        }
        int val = Integer.parseInt(aux2);
        if (val < 100) {
            val += 100;
        }
        return val;
    }

    /**
     * Extraer mitad de un numero
     *
     * @param number
     * @return
     */
    public int extractMiddle(int number) {
        String aux = String.valueOf(number);
        return Integer.parseInt(aux.charAt(2) + "");

    }

    /**
     * Extraer el ultimo digito de un numero
     *
     * @param number
     * @return
     */
    public int extractLast(int number) {
        String aux = String.valueOf(number);
        return Integer.parseInt(aux.charAt(0) + "");
    }

    /*
    aleatorio de dias
     */
    public ArrayList<Integer> ourMethodRandomTimeDay(int iterations) {

        int seed = randomSeed(4);
        long elevated = 0;
//        System.out.println("i   semilla  x^3    5cifras      mitad      toyal");
        for (int i = 0; i < iterations; i++) {
            elevated = (long) Math.pow(seed, 2);
            int extracted = extractRandon(elevated);
            if (extractLast(seed) % extractLast(extracted) == 0) {
                listNumberRandomTimeDay.add(extractMiddle(extracted) + 61);
            } else {
                listNumberRandomTimeDay.add(extractMiddle(extracted) + 60);
            }
//            System.out.println(i + " - " + seed + " - " + elevated + " - " + extracted + " - "
//                    + extractMiddle(extracted) + "-" + (extractMiddle(extracted) + 60));
            seed = extracted;
        }
        return listNumberRandomTimeDay;
    }

    public String printArray(ArrayList listNumbers) {
        String aux = listNumbers.get(0) + "\n";
        for (int i = 1; i < listNumbers.size(); i++) {
            aux += listNumbers.get(i) + "\n";
        }
        return aux;
    }

    //---------------------------------------------------------------------------------
    //-----------------------------------RANDOM duracion DE CLIENTES DIARIOS-----------------
    public void randomDurationXClient(int timeDay) {
        int minDuration = (timeDay / 73) * 1000;
        int maxDuration = (timeDay / 103) * 1000;

    }
}
