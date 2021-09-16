package models;

/**
 * Clase que almacena los datos del metodo cuadrados medios
 *
 * @author Maria Latorre
 */
public class DataRowSquare {
//-------------------------atributos--------------------------------------------

    private int id;
    private String xi;
    private String xElevated;
    private int size;
    private String extraction;
    private float ri;
//---------------------------------constructor--------------------------------------

    public DataRowSquare(int id, String xi, String xElevated, int size, String extraction, float ri) {
        this.id = id;
        this.xi = xi;
        this.xElevated = xElevated;
        this.size = size;
        this.extraction = extraction;
        this.ri = ri;
    }
//--------------------------------------metodos---------------------------------------------

    public int getId() {
        return id;
    }

    public String getXi() {
        return xi;
    }

    public String getxElevated() {
        return xElevated;
    }

    public int getSize() {
        return size;
    }

    public String getExtraction() {
        return extraction;
    }

    public float getRi() {
        return ri;
    }

}

