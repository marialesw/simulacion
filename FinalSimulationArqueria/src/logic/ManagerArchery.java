package logic;

import models.EnumGender;
import models.Escenary;
import models.Game;
import models.Person;
import models.Team;

/**
 * delimita el numero de juegos
 * @author Maria
 */
public class ManagerArchery {

	private static final int NUMBER_GAMES = 1000;
	private ManagerInitPlay managerInitPlay;
	//    private ArrayList<Escenary> listEscenary;
	private Escenary gameEscenary;
	private int totalGlobalWinnerTeam1;
	private int totalGlobalWinnerTeam2;
	private int totalCountGenderFemale;
	private int totalCountGenderMale;
	private int totalScoreGlobal;

	public ManagerArchery() {
		managerInitPlay = new ManagerInitPlay();
		//        listEscenary = new ArrayList<>();

	}

	/**
	 * Metodo para iniciar el juego
	 */
	public void initPlay() {
		gameEscenary = new Escenary();
		managerInitPlay.createTeams();
	}

	/**
	 * Metodo que controla la simulacion en general
	 */
	public void startSimulation() {
		totalGlobalWinnerTeam1 = 0;
		totalGlobalWinnerTeam2 = 0;
		totalCountGenderFemale = 0;
		totalCountGenderMale = 0;
		totalScoreGlobal = 0;
		for (int j = 0; j < NUMBER_GAMES; j++) {
			Game game = new Game(managerInitPlay.getListTeams());

			gameEscenary.getListGames().add(game);
			if (game.getWinnerGame() != null) {
				if (game.getWinnerGame().getId() == 1) {
					totalGlobalWinnerTeam1 += 1;
				} else if (game.getWinnerGame().getId() == 2) {
					totalGlobalWinnerTeam2 += 1;
				}
				totalScoreGlobal += game.getWinnerGame().getScoreTeam();
				System.out.println("-.-----AIUUUDAAAAA "+ totalScoreGlobal);
			}
		}
		Team teamWinner = calculateTeamWinner();
		System.out.println("_______________" + totalScoreGlobal);
		gameEscenary.setTeamWinner(teamWinner); 
		gameEscenary.setPlayerLucky(calculatePlayerLucky());
		gameEscenary.setPlayerExpert(calculatePlayerExpert());
		gameEscenary.setGenderWinner(calculateGenderWinder());
		//            System.out.println("-----------------------------------------------------------------------------------------------------");
		//            System.err.println("                 FIN PARTIDAS ESCENARIO: " + listEscenary.get(i).getTypeEscenary().getNameEscenary());
		Game.id = 1;
	}
	public EnumGender calculateGenderMostWinner(){
		EnumGender winner=null;
		if(totalCountGenderFemale>totalCountGenderMale){
			winner=EnumGender.FEMALE;
		}else{
			winner=EnumGender.MALE;
		}
		return winner;
	}
	public Team calculateTeamMostWinner(){
		Team winner=null;
		if(totalGlobalWinnerTeam1>totalGlobalWinnerTeam2){
			winner=managerInitPlay.getListTeams().get(0);
		}else{
			winner=managerInitPlay.getListTeams().get(1);
		}
		return winner;
	}
	public Team calculateTeamWinner() {
		Team teamWinner = null;
		System.out.println("TOTAL EQ 1 " +  totalGlobalWinnerTeam1 + "TOTAL EQ 2 " + totalGlobalWinnerTeam2);
		if (totalGlobalWinnerTeam1 > totalGlobalWinnerTeam2) {
			teamWinner = managerInitPlay.getListTeams().get(0);
			teamWinner.setScoreTeam(totalGlobalWinnerTeam1);
		} else if (totalGlobalWinnerTeam2 > totalGlobalWinnerTeam1) {
			teamWinner = managerInitPlay.getListTeams().get(1);
			teamWinner.setScoreTeam(totalGlobalWinnerTeam2);
		}
		return teamWinner;
	}
	//jugador mas suertudo de todo un escenario

	public Person calculatePlayerLucky() {
		Person playerLucky = gameEscenary.getListGames().get(0).
				getListRoundGroups().get(0).getListAuxAllPlayers().get(0);
		for (int i = 0; i < gameEscenary.getListGames().size(); i++) {
			for (int j = 0; j < gameEscenary.getListGames().get(i).
					getListRoundGroups().size();j++){
				for(int k=0;k<gameEscenary.getListGames().get(i).
						getListRoundGroups().get(j).getListAuxAllPlayers().size();k++){
					if(gameEscenary.getListGames().get(i).
							getListRoundGroups().get(j).getListAuxAllPlayers().
							get(k).getLucky() > playerLucky.getLucky()){
						playerLucky = gameEscenary.getListGames().get(i).
								getListRoundGroups().get(j).getListAuxAllPlayers().
								get(k);
					}
				}
			}
		}

		return playerLucky;
	}

	public Person calculatePlayerExpert() {
		Person playerExpert = gameEscenary.getListGames().get(0).
				getListRoundGroups().get(0).getListAuxAllPlayers().get(0);
		for (int i = 0; i < gameEscenary.getListGames().size(); i++) {
			for (int j = 0; j < gameEscenary.getListGames().get(i).
					getListRoundGroups().size();j++){
				for(int k=0;k<gameEscenary.getListGames().get(i).
						getListRoundGroups().get(j).getListAuxAllPlayers().size();k++){
					if(gameEscenary.getListGames().get(i).
							getListRoundGroups().get(j).getListAuxAllPlayers().
							get(k).getExperience() > playerExpert.getExperience()){
						playerExpert=gameEscenary.getListGames().get(i).
								getListRoundGroups().get(j).getListAuxAllPlayers().
								get(k);
					}
				}
			}
		}

		return playerExpert;
	}

	public EnumGender calculateGenderWinder() {
		EnumGender genderWinner =null;
		for (int i = 0; i < gameEscenary.getListGames().size(); i++) {
			totalCountGenderFemale += gameEscenary.getListGames().get(i).getCountGenderFemale();
			totalCountGenderMale += gameEscenary.getListGames().get(i).getCountGenderMale();
		}
		if(totalCountGenderFemale>totalCountGenderMale){
			genderWinner = EnumGender.FEMALE;
		}else{
			genderWinner = EnumGender.MALE;
		}//prueba
		return genderWinner;
	}

	public ManagerInitPlay getManagerInitPlay() {
		return managerInitPlay;
	}

	public void setManagerInitPlay(ManagerInitPlay managerInitPlay) {
		this.managerInitPlay = managerInitPlay;
	}

	public Escenary getGameEscenary() {
		return gameEscenary;
	}
}
