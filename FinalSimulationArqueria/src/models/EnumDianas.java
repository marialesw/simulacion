package models;
/**
 * Enumerado de Dianas
 * @author Maria
 *
 */
public enum EnumDianas {

	    CENTRAL("Central", 10),
	    INTERMEDIA("Intermedia", 8),
    	EXTERIOR("Exterior", 6),
    	ERROR("Error", 0);

	    private String nameDiana;
	    private int score;

	    private EnumDianas(String nameDiana, int score) {
	        this.nameDiana = nameDiana;
	        this.score = score;

	    }

	    public String getNameD() {
	        return nameDiana;
	    }

	    public int getScore() {
	        return score;
	    }

}
