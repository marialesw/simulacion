package models;

/**
 * @author Maria 
 */
public class DataRowUniform {

    private double initial;
    private double finalI;
    private int frequencyObtained;
    private double frequencyExpected;
    private double chi2;

    public DataRowUniform(double initial, double finalI, int frequencyObtained, double frequencyExpected, double chi2) {
        this.initial = initial;
        this.finalI = finalI;
        this.frequencyObtained = frequencyObtained;
        this.frequencyExpected = frequencyExpected;
        this.chi2 = chi2;
    }

    public String toString() {
        return initial + "---" + finalI + "---" + frequencyObtained + "---" + frequencyExpected + "---" + chi2;
    }

    public double getInitial() {
        return initial;
    }

    public void setInitial(double initial) {
        this.initial = initial;
    }

    public double getFinalI() {
        return finalI;
    }

    public void setFinalI(double finalI) {
        this.finalI = finalI;
    }

    public int getFrequencyObtained() {
        return frequencyObtained;
    }

    public void setFrequencyObtained(int frequencyObtained) {
        this.frequencyObtained = frequencyObtained;
    }

    public double getFrequencyExpected() {
        return frequencyExpected;
    }

    public void setFrequencyExpected(double frequencyExpected) {
        this.frequencyExpected = frequencyExpected;
    }

    public double getChi2() {
        return chi2;
    }

    public void setChi2(double chi2) {
        this.chi2 = chi2;
    }

}
