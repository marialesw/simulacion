package models;

import java.util.ArrayList;
import java.util.Iterator;
import logic.MethodsRandom;
import logic.RoundGroup;

/**
 * Clase correspondiente a una partida
 *
 * @author Ivan Valderrama
 * @author Maria Lisbeth
 */
public class Game {

	private static final int NUMBER_ROUNDS = 10;
	public static int id = 1;
	private int idGame;
	private ArrayList<RoundGroup> listRoundGroups; 
	private int countVictoriesTeamA;
	private int countVictoriesTeamB;
	private int countScoreGameWinner;
	private ArrayList<Team> listTeams;
	private Team winnerGame;
	private ArrayList<RoundGroup> auxListRound;
	private ArrayList<Person> listAuxAllPlayers;
	private int countGenderFemale;
	private int countGenderMale;
	private MethodsRandom method; 
	private Person playerA;
	private Person playerB;
	private int contA;
	private int contB;
	private EnumGender enumGenderWinner;
	private Person personLucky;
 
	public Game(ArrayList<Team> listTeams) {
		idGame = Game.id++;
		this.countVictoriesTeamA = 0;
		this.countVictoriesTeamB = 0;
		this.countGenderFemale=0;
		this.countGenderMale=0;
		this.countScoreGameWinner = 0;

		listRoundGroups = new ArrayList<>();
		this.listTeams = listTeams;
		this.winnerGame = null;
		auxListRound=new ArrayList<>();
		listAuxAllPlayers = new ArrayList<>();
		method = new MethodsRandom();
		startGame();
	}

	/**
	 * Iniciar Partida
	 *
	 * @param valueEscenary
	 */
	private void startGame() {
		//Realizar 10 rondas grupales 
		for (int i = 0; i < NUMBER_ROUNDS; i++) {
			RoundGroup roundGroup = new RoundGroup();
			roundGroup.playRoundIndividual(listTeams, playerA, playerB, contA, contB);
			playerA = roundGroup.getPlayerA();
			playerB = roundGroup.getPlayerB();
			contA = roundGroup.getContA();
			contB = roundGroup.getContB(); 
			Team team1=listTeams.get(0);
			Team team2=listTeams.get(1);
			roundGroup.getTeamAuxLaunching().add(team1);
			roundGroup.getTeamAuxLaunching().add(team2);
			countGenderFemale+=roundGroup.getCountGenderFemale();
			countGenderMale+=roundGroup.getCountGenderMale();
			if (countGenderFemale < countGenderMale) {
				enumGenderWinner = EnumGender.MALE;
			}else {
				enumGenderWinner = EnumGender.FEMALE;
			}
			System.out.println("*****Ganador ronda grupal: -->" + roundGroup.getWinnerRound());
			if (roundGroup.getWinnerRound().equals("A")) {
				countVictoriesTeamA += 1;
			} else if (roundGroup.getWinnerRound().equals("B")) {
				countVictoriesTeamB += 1;
			}
			auxListRound.add(roundGroup);
			//            System.out.println("11111"+auxListRound.get(0).getTeamAuxLaunching().get(0).getListPlayers().get(0)
			//                    .getListLaunching().toString());
			listRoundGroups.add(roundGroup);
			personLucky = roundGroup.getPlayerLucky();
			resetearValoresNuevaRonda();
			//            System.out.println(auxListRound.get(0).getTeamAuxLaunching().
			//                    get(0).getListPlayers().get(0).getListLaunching().size());
		}	 

		System.out.println("------------------------FIN DE PARTIDA-----------------------------");
		if (countVictoriesTeamA > countVictoriesTeamB) {
			winnerGame = listTeams.get(0);
			System.out.println("el ganador de partida es equipo a");
			countScoreGameWinner = listTeams.get(0).getScoreTeam();
		} else if (countVictoriesTeamB > countVictoriesTeamA) {
			winnerGame = listTeams.get(1);
			System.out.println("el ganador de partida es equipo b");
			countScoreGameWinner = listTeams.get(1).getScoreTeam();
		}
		calculatePlayerLucky(listTeams);
		getWinnerRoundsGame(listTeams);
		resetearValoresNuevaPartida();
	}

	private void calculatePlayerLucky(ArrayList<Team> listTeams2) {
		for (int i = 0; i < listTeams.size(); i++) {
			for (int j = 0; j < listTeams.get(i).getListPlayers().size(); j++) {
				Person player = listTeams.get(i).getListPlayers().get(j);
			}
		}
		
	}

	public void resetearValoresNuevaRonda() {
		for (int i = 0; i < listTeams.size(); i++) {
			for (int j = 0; j < listTeams.get(i).getListPlayers().size(); j++) {
				Person player = listTeams.get(i).getListPlayers().get(j);
				//				 System.out.println("pERSONA " + listTeams.get(i).getListPlayers().get(j).getName());
				player.getListLaunching().clear();
				//				 System.out.println("VALOR inicial: " + listTeams.get(i).getListPlayers().get(j).getResistenceInit());
				if (player.isDiscount()) {
					player.setResistence(player.getResistence() - randomResistence()); // randomResistence hace referencia a generar el valor del cansancio aleatorio
				}else {
					if (player.getNoLoseResis() < 3) {
						//						System.out.println("************************* NO SE LE DESCUENTA");
						player.addNoLoseResis();
					}else {
						//						System.out.println("YA SE LE ACABARON LOS DESCUENTOS");
						player.setDiscount(true);
						player.setNoLoseResis(0);
						player.setResistence(player.getResistence() - randomResistence()); // randomResistence hace referencia a generar el valor del cansancio aleatorio
					}
				}
				listTeams.get(i).addScoreGlobal((int)player.getTotalScore());
				player.setTotalScore(0); 
				player.setLucky(randomLucky());
			}
		}
	}

	/**
	 * Obtener el valor de la suerte aleatoria usando todos los decimales para una mayor precision
	 *
	 * @return
	 */
	public float randomLucky() {
		float res = 0;
		MethodsRandom method = new MethodsRandom();
		method.RandomUniform(1, Constants.MAXIMUM_LUCKY, 1);
		res = (float) method.getListRandomUniform().get(0).doubleValue();
		return res;
	}

	public void resetearValoresNuevaPartida() {
		for (int i = 0; i < listTeams.size(); i++) {
			for (int j = 0; j < listTeams.get(i).getListPlayers().size(); j++) {
				//                listTeams.get(i).getListPlayers().get(j).getListLaunching().clear();
				listTeams.get(i).getListPlayers().get(j).setResistence(listTeams.get(i).getListPlayers().get(j).getResistenceInit());
				listTeams.get(i).getListPlayers().get(j).setTotalScore(0);
				//                listTeams.get(i).getListPlayers().get(j).setExperience(10);
				listTeams.get(i).getListPlayers().get(j).setLucky(randomLucky());
				Person player = listTeams.get(i).getListPlayers().get(j);

				player.setDiscount(true);
				player.setNoLoseResis(0);
				player.setExperienceInit(player.getExperience());
			}
			listTeams.get(i).setScoreTeam(0);
		}
		RoundGroup.id = 1;
	}	 
	
	public Person getWinnerRoundsGame(ArrayList<Team> listTeams) {
		for (int i = 0; i < listTeams.size(); i++) {
			for (int j = 0; j < listTeams.get(i).getListPlayers().size(); j++) {
				listAuxAllPlayers.add(listTeams.get(i).getListPlayers().get(j));
			}
		}
		//elegimos a la persona del puntaje mas alto
		Person playerWinner = listAuxAllPlayers.get(0);
		//			System.out.println(" ***** " + listAuxAllPlayers.get(0).getName() + " rondas ganadas " + listAuxAllPlayers.get(0).getTotalRoundsIndividualWinner());
		for (int i = 1; i < listAuxAllPlayers.size(); i++) {
			//				System.out.println(" ***** " + listAuxAllPlayers.get(i).getName() + " score " + listAuxAllPlayers.get(i).getTotalRoundsIndividualWinner());
			if (listAuxAllPlayers.get(i).getTotalRoundsIndividualWinner() > playerWinner.getTotalRoundsIndividualWinner()) {
				playerWinner = listAuxAllPlayers.get(i);
			}
		}
		System.out.println("*****GANO MAS RONDAS O GANADOR INDIVIDUAL " + playerWinner.getId() + " " + playerWinner.getName());

		return playerWinner;
	}


	/**
	 * Obtener un número pseudoaleatorio entre 1 y 2 para un disminuir resistencia 
	 * @param 
	 * @return
	 */
	private int randomResistence() {
		int res = 0;
		MethodsRandom method = new MethodsRandom();
		method.RandomUniform(1, 3, 1);
		res = (int) method.getListRandomUniform().get(0).doubleValue();
		return res;
	}

	public Team getWinnerGame() {
		return winnerGame;
	}
	public EnumGender getEnumGenderWinner() {
		return enumGenderWinner;
	}

	public int getCountScoreGameWinner() {
		return countScoreGameWinner;
	}
	
	public void setWinnerGame(Team winnerGame) {
		this.winnerGame = winnerGame;
	}

	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}
	public Person getPersonLucky() {
		return personLucky;
	}

	public ArrayList<RoundGroup> getListRoundGroups() {
		return listRoundGroups;
	}

	public void setListRoundGroups(ArrayList<RoundGroup> listRoundGroups) {
		this.listRoundGroups = listRoundGroups;
	}

	public ArrayList<Team> getListTeams() {
		return listTeams;
	}

	public void setListTeams(ArrayList<Team> listTeams) {
		this.listTeams = listTeams;
	}

	public int getCountGenderFemale() {
		return countGenderFemale;
	}

	public void setCountGenderFemale(int countGenderFemale) {
		this.countGenderFemale = countGenderFemale;
	}

	public int getCountGenderMale() {
		return countGenderMale;
	}

	public void setCountGenderMale(int countGenderMale) {
		this.countGenderMale = countGenderMale;
	}
}
