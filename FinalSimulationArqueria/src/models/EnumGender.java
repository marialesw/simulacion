package models;

/**
 * Enumerado para generos de la persona
 *
 * @author Maria Latorre
 */
public enum EnumGender {

    MALE("Hombre", "0"),
    FEMALE("Mujer", "1");

    private String nameGender;
    private String codeGender;

    private EnumGender(String nameGender, String codeGender) {
        this.nameGender = nameGender;
        this.codeGender = codeGender;

    }

    public String getNameGender() {
        return nameGender;
    }

    public String getCodeGender() {
        return codeGender;
    }

}
