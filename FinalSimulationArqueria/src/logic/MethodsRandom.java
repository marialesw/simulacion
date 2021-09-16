package logic;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

import models.DataRowSquare;
import models.DataRowUniform;

/**
 * Clase donde se desarrollan los metodos matematicos de generacion de numeros
 * pseudoaleatorios
 *
 * @author Maria Latorre
 * @author Andres Simbaqueva
 */
public class MethodsRandom {

    private ArrayList<DataRowSquare> listDataRowSquareMedium;
    private ArrayList<Float> listNumberRandomSquareMedium;
    private double m_lineal;
    private int a_lineal;
    private int numberXo_lineal;
    private int numberC_lineal;
    private int m_multiply;
    private int a_multiply;
    private ArrayList<DataRowUniform> listDataRowUniforms;
    private ArrayList<Double> listRandomUniform;

    public MethodsRandom() {
        listDataRowSquareMedium = new ArrayList<>();
        listNumberRandomSquareMedium = new ArrayList<>();
        listDataRowUniforms = new ArrayList<>();
        listRandomUniform = new ArrayList<>();
        m_lineal = -1;
        a_lineal = -1;
        numberXo_lineal = -1;
        numberC_lineal = -1;
        m_multiply = -1;
        a_multiply = -1;
    }

    /**
     * Metodo para validad el tama�o de una semilla en el metodo de cuadrados
     * medios
     *
     * @param seedJoined
     * @return
     */
    public boolean validateSeed(String seedJoined) {
        boolean result = false;
        if (seedJoined.length() > 3) {
            result = true;
        } else {
            result = true;
        }
        return result;
    }

    /**
     * Metodo que generacion de numeros pseudoaleatorios 
     * con cuadrados medios
     *
     * @param seedJoined //semilla ingresada
     * @param iterations //numero de iteraciones deseada
     */
    public void squareMediumMethod(String seedJoined, String iterations) {
        int totalSizeLimit = 0;
        String sElevado, extract, sSeedNumber;
        int sizeSeed, sizeElevado, i, beginIndex;
        long seedNumber, elevated, iteraNumber;
        sizeSeed = seedJoined.length();         //numero de digitos de la semilla ingresada
        String numberSTotalElevated = "";
        for (int j = 0; j < sizeSeed; j++) {
            numberSTotalElevated += "9";
        }
        long numberElevatedTotal = (long) Math.pow(Long.parseLong(numberSTotalElevated), 2);
        numberSTotalElevated = String.valueOf(numberElevatedTotal);
        totalSizeLimit = numberSTotalElevated.length();//total de cifras para completar el x^2
        seedNumber = Integer.parseInt(seedJoined);//se convierte la semilla ingresada a_lineal un valor de tipo int, asi como el 
        iteraNumber = Integer.parseInt(iterations); //parametro de iteraciones.
        for (i = 1; i <= iteraNumber; i++) { //se realizaran las iteraciones (iteraNumber) ingresadas por el usuario
            elevated = (long) Math.pow(seedNumber, 2);//calcularemos el elevado del valor semilla 
            sElevado = Long.toString(elevated);//se convertira a_lineal String el elevado para poder hacer la extraccion.
            sizeElevado = sElevado.length(); //se calcula el tama�o, en cuanto numero de digitos, del valor elevado
            if (sizeElevado < totalSizeLimit) { //si la cifra elevada tiene menos digitos de las posibles se debera completar a_lineal 0 a_lineal la izquierda
                int faltan = totalSizeLimit - sizeElevado;
                String aux = sElevado;
                sElevado = "";
                for (int j = 0; j < faltan; j++) {
                    sElevado += "0";
                }
                sElevado += aux;
            }
            sizeElevado = sElevado.length();
            beginIndex = (sizeElevado - sizeSeed) / 2;//calcularemos la mitad del numero elevado
            try {
                extract = sElevado.substring(beginIndex, beginIndex + sizeSeed);//se obtendra la extraccion del numero que servira para la siguiente iteracion
                float numberRandomSquare = (float) ((Integer.parseInt(extract)) / (Math.pow(10, sizeSeed))); //creamos un objeto dataFill para almacenar los datos
                sSeedNumber = String.valueOf(seedNumber);
                DataRowSquare dataFill = new DataRowSquare(i, sSeedNumber, sElevado, sizeElevado,
                        extract, numberRandomSquare);
                listDataRowSquareMedium.add(dataFill);//agregamos el objeto a_lineal la lista.
                listNumberRandomSquareMedium.add(numberRandomSquare);
//                sSeedNumber = extract;

                seedNumber = Integer.parseInt(extract);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Alerta", "Señor usuario no fue posible"
                        + "realizar mas de " + i + " iteraciones",
                        JOptionPane.ERROR_MESSAGE);
                break;
            }

        }
    }

    /**
     * Metodo para validar que el numero ingresado sea un entero
     *
     * @param valueTovalidate
     * @return
     */
    public boolean validateInteger(String valueTovalidate) {
        int number;
        try {
            //convertimos los valores string a_lineal tipo numerico verificando que sea entero. 
            number = Integer.parseInt(valueTovalidate.trim());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Metodo que gestiona los primeros valores necesarios para el metodo de
     * congruencia lineal
     *
     * @param valueK
     * @param valueG
     */
    public void firstValues(String valueK, String valueG) {
        int numberK = -1;
        int numberG = -1;
        if (validateInteger(valueK) == true && validateInteger(valueG) == true) {
            numberK = Integer.parseInt(valueK);
            numberG = Integer.parseInt(valueG);
        }
//hallamos el valor de m_lineal
        m_lineal = Math.pow((double) 2, (double) numberG);
//Hallamos el valor de a_lineal
        a_lineal = 1 + 2 * (numberK);
    }

    /**
     * Metodo que gestiona el valor inicia y el valor de C para el metodo de
     * congruencia lineal
     *
     * @param valueXo
     * @param valueC
     */
    public void secondValues(String valueXo, String valueC) {
        numberXo_lineal = Integer.parseInt(valueXo);
        numberC_lineal = Integer.parseInt(valueC);
        //validamos que tanto Xo, como c, cumplan las condiciones del metodo
        if (numberXo_lineal >= 0 && numberXo_lineal <= (m_lineal - 1)) {
//            System.out.println("Xo cumple!");
        } else {
//            System.out.println("Xo no cumple, ingrese otro valor.");
            JOptionPane.showMessageDialog(null, "Alerta", "Señor usuario el valor Xo "
                    + "debe ser un valor mayor a 0 y menor de " + (m_lineal - 1) + "!",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (numberC_lineal >= 0 && numberC_lineal <= m_lineal) {
//            System.out.println("c cumple");
        } else {
//            System.out.println("c no cumple, ingrese otro valor");
            JOptionPane.showMessageDialog(null, "Alerta", "Señor usuario el valor c "
                    + "debe ser un valor mayor a 0 y menor que " + m_lineal + "!",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Metodo para validar el valor de C del metodo de congruencia lineal
     *
     * @param valueC
     * @return
     */
    public boolean validateNumberc(String valueC) {
        boolean result = false;
        if (Integer.parseInt(valueC) % 2 == 0) {
//            System.out.println("El número es par");
            result = false;
        } else {
            result = true;
//            System.out.println("El número es impar");
        }
        return result;
    }

    //----------------------------------Distribucio Uniforme----------------------------------------
    /**
     * Metodo para calcular el Ni de la distribucion uniforme
     *
     * @param upperLimit limite superior
     * @param lowerLimit limite inferior
     * @param data
     */
    public double uniformNi(int upperLimit, int lowerLimit, double data) {
        return lowerLimit + (upperLimit - lowerLimit) * data;
    }

    /**
     * Metodo para calcular el limite superior para un intervalo
     *
     * @param numberSmaller
     * @param numberBigger
     * @param lowerLimitInterval
     * @param numberIntervals
     */
    public double upperLimitInterval(double numberSmaller, double numberBigger, double lowerLimitInterval, int numberIntervals) {
        return lowerLimitInterval + ((numberBigger - numberSmaller) / numberIntervals);
    }

    /**
     * Metodo para calcular la frecuencia obteniada de los Ni por intervalos
     *
     * @param upperLimitInterval
     * @param lowerLimitInterval
     * @param listNumbers
     */
    public int frequencyObtained(double upperLimitInterval, double lowerLimitInterval, ArrayList listNumbers) {
        int result = 0;
        for (int i = 0; i < listNumbers.size(); i++) {
            if (upperLimitInterval <= (double) listNumbers.get(i) && (double) listNumbers.get(i) < lowerLimitInterval) {
                result++;
            }
        }

        return result;
    }

    /**
     * Metodo para calcular la frecuencia esperada de los Ni
     *
     * @param numberData
     * @param numberIntervals
     */
    public double frequencyExpected(int numberData, int numberIntervals) {
        return numberData / numberIntervals;
    }

    /**
     * Metodo para calcular el chi2
     *
     * @param frequencyObtained
     * @param frequencyExpected
     */
    public double chi2(int frequencyObtained, double frequencyExpected) {
        return Math.pow((frequencyObtained - frequencyExpected), 2) / frequencyExpected;
    }

    /**
     * Metodo para calcular una semilla aleatoria
     *
     */
    public int randomSeed() {
        Random random = new Random();
        String randomRes = (random.nextInt(9) + 1) + "";
        for (int i = 0; i < 3; i++) {
            randomRes += random.nextInt(10);
        }
        return Integer.parseInt(randomRes);
    }

    /**
     * Metodo para encontrar un numero pseudoaleatorio dentro del rango indicado
     *
     * @param iterations numero de iteraciones
     * @param upperLimit limite superior
     * @param lowerLimit limite inferior
     */
    public void RandomUniform(int iterations, int upperLimit, int lowerLimit) {
    	listRandomUniform = new ArrayList<Double>();
        squareMediumMethod(randomSeed() + "", iterations + "");
        for (int i = 0; i < iterations; i++) {
            listRandomUniform.add(uniformNi(upperLimit, lowerLimit, listNumberRandomSquareMedium.get(i)));
        }
    }

    /**
     * Metodo para calcular el numero menor de la distribucion uniforme
     *
     */
    public double minorData() {
        double res = listRandomUniform.get(0);
        for (int i = 1; i < listRandomUniform.size(); i++) {
            if (listRandomUniform.get(i) < res) {
                res = listRandomUniform.get(i);
            }
        }
        return res;
    }

    /**
     * Calcula el numero mayor de la distribucion uniforme
     *
     * @return
     */
    public double biggerData() {
        double res = listRandomUniform.get(0);
        for (int i = 1; i < listRandomUniform.size(); i++) {
            if (listRandomUniform.get(i) > res) {
                res = listRandomUniform.get(i);
            }
        }
        return res;
    }

    /**
     *
     * @param numberIntervals
     */
    public void tableDataRowUniform(int numberIntervals) {
        double lowerLimitInterval = minorData();
        double upperLimitInterval = upperLimitInterval(minorData(), biggerData(), lowerLimitInterval, numberIntervals);
        int frequencyObtained = frequencyObtained(lowerLimitInterval, upperLimitInterval, listRandomUniform);
        double frequencyExpected = frequencyExpected(listRandomUniform.size(), numberIntervals);
        for (int i = 0; i < numberIntervals; i++) {
            if ((i - 1) == numberIntervals) {
                frequencyObtained = frequencyObtained + 1;
            }
            listDataRowUniforms.add(new DataRowUniform(lowerLimitInterval, upperLimitInterval, frequencyObtained, frequencyExpected, chi2(frequencyObtained, frequencyExpected)));
            lowerLimitInterval = upperLimitInterval;
            upperLimitInterval = upperLimitInterval(minorData(), biggerData(), lowerLimitInterval, numberIntervals);
            frequencyObtained = frequencyObtained(lowerLimitInterval, upperLimitInterval, listRandomUniform);
            frequencyExpected = frequencyExpected(listRandomUniform.size(), numberIntervals);
        }
    }

    /**
     * Mostrar tabla de distribucion uniforme
     *
     * @return
     */
    public String showTableDataRowUniform() {
        String res = "Inicial --- Final --- Frec.O --- Frec.E --- Chi2 \n";
        for (int i = 0; i < listDataRowUniforms.size(); i++) {
            res += listDataRowUniforms.get(i).toString() + "\n";
        }
        return res;
    }

    /**
     * mostrar uniformidad
     *
     * @return
     */
    public String showUniform() {
        String res = "";
//        System.out.println("N---Ri---------Ni");
        for (int i = 0; i < listRandomUniform.size(); i++) {
            res += i + "--" + listNumberRandomSquareMedium.get(i) + "---------" + listRandomUniform.get(i) + "\n";
        }
        return res;
    }

//------------------------------get y set----------------------------------
    public ArrayList<DataRowSquare> getListDataRowSquareMedium() {
        return listDataRowSquareMedium;
    }

    public double getM() {
        return m_lineal;
    }

    public int getA() {
        return a_lineal;
    }

    public int getNumberXo() {
        return numberXo_lineal;
    }

    public int getNumberC() {
        return numberC_lineal;
    }

    public ArrayList<Float> getListNumberRandomSquareMedium() {
        return listNumberRandomSquareMedium;
    }

    public void setListNumberRandomSquareMedium(ArrayList<Float> listNumberRandomSquareMedium) {
        this.listNumberRandomSquareMedium = listNumberRandomSquareMedium;
    }

    public ArrayList<DataRowUniform> getListDataRowUniforms() {
        return listDataRowUniforms;
    }

    public void setListDataRowUniforms(ArrayList<DataRowUniform> listDataRowUniforms) {
        this.listDataRowUniforms = listDataRowUniforms;
    }

    public ArrayList<Double> getListRandomUniform() {
        return listRandomUniform;
    }

    public void setListRandomUniform(ArrayList<Double> listRandomUniform) {
        this.listRandomUniform = listRandomUniform;
    }

}
