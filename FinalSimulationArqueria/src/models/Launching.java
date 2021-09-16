package models;

/**
 * Clase que maneja un lanzamiento
 *
 * @author Maria Latorre
 */
public class Launching {

    public static int id = 1;
    private int idLanuch;
    private EnumDianas diana;

    public Launching(EnumDianas dianas) {
        this.idLanuch = Launching.id++;
        this.diana = dianas;
    }

    public Launching(int idLaunch, EnumDianas dianas) {
        this.idLanuch = idLaunch;
    }

    public String toString() {
        return "Id tiro: " + idLanuch + " Diana: " + diana + ".";
    }

    public int getId() {
        return idLanuch;
    }
    public EnumDianas getDiana() {
		return diana;
	}
    public void setDiana(EnumDianas diana) {
		this.diana = diana;
	}

    public void setId(int id) {
        this.id = id;
    }
}
