package models;

import java.util.ArrayList;

/**
 * Clase que almacena lo correspondiente a un equipo
 *
 * @author Maria Latorre
 */
public class Team {

    private int id;
    private String nameTeam;
    private ArrayList<Person> listPlayers;
    private int numberWoman;
    private int numberMan;
    private int scoreTeam;

    public Team(int id, String nameTeam, ArrayList<Person> listPlayers) {
        this.id = id;
        this.nameTeam = nameTeam;
        this.numberWoman = 0;
        this.numberMan = 0;
        this.listPlayers = listPlayers;
        this.scoreTeam = 0;
    }

    public void numberGender() {
        for (int i = 0; i < listPlayers.size(); i++) {
            if (listPlayers.get(i).getGender().equals(EnumGender.FEMALE)) {
                numberWoman += 1;
            } else {
                numberMan += 1;
            }
        }
    }

    public void addScoreGlobal(int score) {
    	scoreTeam = scoreTeam + score;
    }
    public int getScoreTeam() {
		return scoreTeam;
	}
    public void setScoreTeam(int scoreTeam) {
		this.scoreTeam = scoreTeam;
	}
    
    public int getNumberWoman() {
        return numberWoman;
    }

    public int getNumberMan() {
        return numberMan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public ArrayList<Person> getListPlayers() {
        return listPlayers;
    }

    public void setListPlayers(ArrayList<Person> listPlayers) {
        this.listPlayers = listPlayers;
    }

}
