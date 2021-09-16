package models;

import java.util.ArrayList;

/**
 *
 * @author Maria Latorre
 */
public class Escenary {

    private int id = 1;
    private ArrayList<Game> listGames;
private Team teamWinner;
private Person playerLucky;
private Person playerExpert;
private EnumGender genderWinner;

    public Escenary() {
        this.listGames = new ArrayList<>();
        this.id += 1;
        teamWinner=null;
        playerLucky=null;
        playerExpert=null;
        genderWinner=null;
    }

   
    
    public Team getTeamWinner() {
        return teamWinner;
    }

    public void setTeamWinner(Team teamWinner) {
        this.teamWinner = teamWinner;
    }

    public Person getPlayerLucky() {
        return playerLucky;
    }

    public void setPlayerLucky(Person playerLucky) {
        this.playerLucky = playerLucky;
    }

    public Person getPlayerExpert() {
        return playerExpert;
    }

    public void setPlayerExpert(Person playerExpert) {
        this.playerExpert = playerExpert;
    }

    public EnumGender getGenderWinner() {
        return genderWinner;
    }

    public void setGenderWinner(EnumGender genderWinner) {
        this.genderWinner = genderWinner;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Game> getListGames() {
        return listGames;
    }

    public void setListGames(ArrayList<Game> listGames) {
        this.listGames = listGames;
    }

}
