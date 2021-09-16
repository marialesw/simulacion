package logic;

import java.util.ArrayList;
import java.util.Collections;

import models.EnumDianas;
import models.EnumGender;
import models.Launching;
import models.Person;
import models.Team;

/**
 * @author Maria Lisbeth
 * @author Fabian Simbaqueba
 */
public class RoundGroup {
	public static int id = 1;
	private int idRound;
	private String winnerRoundGroup;
	private Person winnerRoundIndividual;
	private ArrayList<Person> listAuxAllPlayers;
	private ArrayList<Team> teamAuxLaunching;
	private int countGenderFemale;
	private int countGenderMale;
	private ArrayList<Person> listPlayerWinner;
	private MethodsRandom method;
	private Person playerA;
	private Person playerB;
	private int contA;
	private int contB;
	private Person playerLucky;

	public RoundGroup() {
		idRound = RoundGroup.id++;
		winnerRoundGroup = "";
		winnerRoundIndividual = null;
		listAuxAllPlayers = new ArrayList<>();
		teamAuxLaunching= new ArrayList<>();
		listPlayerWinner=new ArrayList<>();
		countGenderFemale=0;
		countGenderMale=0;
		method = new MethodsRandom();
	}
	//to string para ver en la tabla reportes sencilla
	@Override
	public String toString() {
		return "R"+idRound+"->"+"Eq: "+winnerRoundGroup+'\n';
	}

	/**
	 * Metodo que genera los lanzamientos n hasta que se agote le resistencia
	 * del jugador
	 *
	 * @param player jugador
	 */
	private void generateLaunchingsOnePlayer(Person player) {
		System.out.println("--------------New player---------------");
		int resistenceCurrent = player.getResistence();
		while (resistenceCurrent >= 5) {
			Launching launching = new Launching(randomDianas(player.getGender()));
			player.getListLaunching().add(launching);
			EnumDianas diana = player.getListLaunching().get(player.getListLaunching().size() - 1).getDiana();
			System.out.println(player.getName() + " Lanzamiento: " + diana.getNameD() + " Puntaje " + diana.getScore());
			resistenceCurrent = resistenceCurrent - 5;
			System.out.println("-----------------------------");
		}
		Launching.id = 1;
	}

	/**
	 * Metodo para calcular una Diana aleatoria dependiendo del género
	 * @param el genero de la persona para calcular
	 * @return
	 */
	private EnumDianas randomDianas(EnumGender gender) {
		int numberAl = randomLaunching();
		EnumDianas res = null;
		if (gender == EnumGender.FEMALE) {
			if (numberAl < 30) {
				res = EnumDianas.CENTRAL;
			}else if (numberAl < 68) {
				res = EnumDianas.INTERMEDIA;
			}else if (numberAl < 95) {
				res = EnumDianas.EXTERIOR;
			}else {
				res = EnumDianas.ERROR;
			}
		}else if (gender == EnumGender.MALE) {
			if (numberAl < 20) {
				res = EnumDianas.CENTRAL;
			}else if (numberAl < 53) {
				res = EnumDianas.INTERMEDIA;
			}else if (numberAl < 93) {
				res = EnumDianas.EXTERIOR;
			}else {
				res = EnumDianas.ERROR;
			}
		}
		return res;
	}
	/**
	 * Obtener un número pseudoaleatorio entre 0 y 99 para un lanzamiento 
	 * @param 
	 * @return
	 */
	private int randomLaunching() {
		int res = 0;
		MethodsRandom method = new MethodsRandom();
		method.RandomUniform(1, 99, 0);
		res = (int) method.getListRandomUniform().get(0).doubleValue();
		return res;
	}

	/**
	 * Metodo auxiliar para hacer un solo lanzamiento (especial)
	 *
	 * @param player
	 */
	private int generateOneLaunch(Person player) {
		Launching launchingSpecial = new Launching(randomDianas(player.getGender()));
		int score = launchingSpecial.getDiana().getScore();
		player.getListLaunchingEspecial().add(launchingSpecial);
		return score;
	}

	/**
	 * Lanzamiento especial con el jugador mas suertudo
	 * @param listTeams 
	 */
	private void generateSpecialLaunching(ArrayList<Team> listTeams) {
		//ordenamos el array del equipo 1, por suerte
		Collections.sort(listTeams.get(0).getListPlayers());
		//ordenamos el array del equipo 2 por suerte
		Collections.sort(listTeams.get(1).getListPlayers());

		Person personLuckyA = listTeams.get(0).getListPlayers().get(listTeams.get(0).getListPlayers().size()-1);
		Person personLuckyB = listTeams.get(1).getListPlayers().get(listTeams.get(1).getListPlayers().size()-1);
		//se obtiene el que esta en la primera posicion  y se le pone a lanzar
		listTeams.get(0).addScoreGlobal(generateOneLaunch(personLuckyA));//se agrega al puntaje global el puntaje del lanzamiento
		listTeams.get(1).addScoreGlobal(generateOneLaunch(personLuckyB));
		verifyExtraByPlayer(personLuckyA, personLuckyB, listTeams);

		if (personLuckyA.getLucky() > personLuckyB.getLucky()) {
			playerLucky = personLuckyA;
		}else {
			playerLucky = personLuckyB;
		}
		if(listTeams.get(0).getListPlayers().get(0).getListLaunching().get(
				listTeams.get(0).getListPlayers().get(0).getListLaunching().
				size()-1).getDiana().getScore() <
				listTeams.get(1).getListPlayers().get(0).getListLaunching().get(
						listTeams.get(1).getListPlayers().get(0).getListLaunching().
						size()-1).getDiana().getScore()){
		}else{
			System.out.println("EQUIPO 1 " + "El mas suertudo es " + personLuckyA);
			System.out.println("EQUIPO 2 " + "El mas suertudo es " + personLuckyB);
		}
	}
	/**
	 * Verifica si hay un jugador que ha completado 3 lanzamientos extras seguidos
	 * @param personLuckyA Persona que acaba de ganar extra en el equipo A
	 * @param personLuckyB Persona que acaba de ganar extra en el equipo B
	 * @param listTeams 
	 */
	private void verifyExtraByPlayer(Person personLuckyA, Person personLuckyB, ArrayList<Team> listTeams) {
		if (playerA == null) {
			playerA = personLuckyA;

		}else {
			if (playerA.getName().equals(personLuckyA.getName())) {
				contA += 1;
			}else {
				contA = 0;
				playerA = personLuckyA;
			}
		}
		if(playerB == null) {
			playerB = personLuckyB;
		}else {
			if(playerB.getName().equals(personLuckyB.getName())) {
				contB += 1;
			}else {
				contB = 0;
				playerB = personLuckyB;
			}
		}
		if (contA == 2) {
			System.out.println("____LA PERSONA de A " + personLuckyA.getName() + " HA GANADO OTRO LANZAMIENTOP EXTRA");
			listTeams.get(0).addScoreGlobal(generateOneLaunch(personLuckyA));//se agrega al puntaje global el puntaje del lanzamiento
		}else if (contB == 2) {
			System.out.println("____LA PERSONA de B " + personLuckyB.getName() + " HA GANADO OTRO LANZAMIENTOP EXTRA");
			listTeams.get(1).addScoreGlobal(generateOneLaunch(personLuckyB));
		}
	}
	/**
	 * Jugar La ronda individual por cada uno de los jugadores de los dos
	 * equipos.
	 *
	 * @param listTeams
	 * @param contB 
	 * @param contA 
	 * @param playerB 
	 * @param playerA 
	 */
	public void playRoundIndividual(ArrayList<Team> listTeams, Person playerA, Person playerB, int contA, int contB) {
		this.playerA = playerA;
		this.playerB = playerB;
		this.contA = contA;
		this.contB = contB;
		for (int i = 0; i < listTeams.size(); i++) { //recorremos la lista de equipos
			System.out.println("EQUIPO " + listTeams.get(i).getNameTeam());
			//por cada equipo para todos sus jugadores se crearan sus respectivos lanzamientos
			for (int j = 0; j < listTeams.get(i).getListPlayers().size(); j++) {
				generateLaunchingsOnePlayer(listTeams.get(i).getListPlayers().get(j));
				listTeams.get(i).getListPlayers().get(j).setTotalScore(
						calculateTotalScore(listTeams.get(i).getListPlayers().get(j)));
			}
		}
		generateSpecialLaunching(listTeams); //lanzamiento especial
        System.out.println("*****TERMINAMOS RONDA GRUPAL DOS EQUIPOS****");
		getWinnerRoundIndividual(listTeams); //ganador de la ronda individual
		getWinnerRoundGroup(listTeams);
		//System.out.println("!!!!!"
		//        + listTeams.get(0).getListPlayers().get(0).getListLaunching().size());
		//        fillDatViewLaunch(listTeams);
		//                =listTeams;//vvisual

	}
	public void fillDatViewLaunch(ArrayList<Team> listTeams){
		for (int i = 0; i < listTeams.size(); i++) {
			teamAuxLaunching.add(listTeams.get(i));
		}

	}
	
	public Person getPlayerLucky() {
		return playerLucky;
	}
	
	public Person getWinnerRoundsGame(ArrayList<Team> listTeams) {
		for (int i = 0; i < listTeams.size(); i++) {
			for (int j = 0; j < listTeams.get(i).getListPlayers().size(); j++) {
				listAuxAllPlayers.add(listTeams.get(i).getListPlayers().get(j));
			}
		}
		//elegimos a la persona del puntaje mas alto
		Person playerWinner = listAuxAllPlayers.get(0);
		Person playerWinner2 = null;
		System.out.println(" ***** " + listAuxAllPlayers.get(0).getName() + " rondas ganadas " + listAuxAllPlayers.get(0).getTotalRoundsIndividualWinner());
		for (int i = 1; i < listAuxAllPlayers.size(); i++) {
			System.out.println(" ***** " + listAuxAllPlayers.get(i).getName() + " score " + listAuxAllPlayers.get(i).getTotalRoundsIndividualWinner());
			if (listAuxAllPlayers.get(i).getTotalRoundsIndividualWinner() > playerWinner.getTotalRoundsIndividualWinner()) {
				playerWinner = listAuxAllPlayers.get(i);
				playerWinner2 = null;
			}else if (listAuxAllPlayers.get(i).getTotalRoundsIndividualWinner() == playerWinner.getTotalRoundsIndividualWinner()) {
				playerWinner2 = listAuxAllPlayers.get(i);
			}
		}
		if (playerWinner2 != null) {
//			System.out.println(" HAY DOS IGUALES "+ playerWinner2.getName() + " && " + playerWinner.getName());
			int n1 = generateOneLaunch(playerWinner);
			int n2 = generateOneLaunch(playerWinner2);
			System.out.println("LANZA 1 " + n1);
			System.out.println("LANZA 2 " + n2);
			while (n1 == n2 ) {
				n1 = generateOneLaunch(playerWinner);
				n2 = generateOneLaunch(playerWinner2);
				
			}
			if (n1 < n2) {
//				System.out.println("GANADOR " + playerWinner2.getName());
				playerWinner = playerWinner2;
			}
			
		}else {
//			System.out.println("GANADOR " + playerWinner.getName());
		}
		//adicionamos a la lista de victorias por genero
		winnerRoundIndividual = playerWinner;
		if(winnerRoundIndividual.getGender()==EnumGender.FEMALE){
			countGenderFemale+=1;
		}else if(winnerRoundIndividual.getGender()==EnumGender.MALE){
			countGenderMale+=1;
		}
		System.out.println("**************** :)  GANADOR DEFINITIVO " + winnerRoundIndividual.getId() + " " + winnerRoundIndividual.getName());
		return playerWinner;
	}

	/**
	 * Calcula el total de puntos que tiene un jugador en sus
	 * lanzamientos.
	 *
	 * @param player
	 * @return
	 */
	public float calculateTotalScore(Person player) {
		float totalPerson = 0;
		for (int i = 0; i < player.getListLaunching().size(); i++) {
			totalPerson += player.getListLaunching().get(i).getDiana().getScore();
		}
		return totalPerson;
	}

	/**
	 * Objetner el jugador ganador de una ronda individual
	 *
	 * @param listTeams
	 */
	public void getWinnerRoundIndividual(ArrayList<Team> listTeams) {
		//pasamos a una lista auxiliar todos los jugadores, para comparar sus puntajes
		for (int i = 0; i < listTeams.size(); i++) {
			for (int j = 0; j < listTeams.get(i).getListPlayers().size(); j++) {
				listAuxAllPlayers.add(listTeams.get(i).getListPlayers().get(j));
			}
		}
		//elegimos a la persona del puntaje mas alto
		Person playerWinner = listAuxAllPlayers.get(0);
		Person playerWinner2 = null;
		for (int i = 1; i < listAuxAllPlayers.size(); i++) {
			if (listAuxAllPlayers.get(i).getTotalScore() > playerWinner.getTotalScore()) {
				playerWinner = listAuxAllPlayers.get(i);
				playerWinner2 = null;
			}else if (listAuxAllPlayers.get(i).getTotalScore() == playerWinner.getTotalScore()) {
				playerWinner2 = listAuxAllPlayers.get(i);
			}
		}
		if (playerWinner2 != null) {
//			System.out.println(" HAY DOS IGUALES "+ playerWinner2.getName() + " && " + playerWinner.getName());
			int n1 = generateOneLaunch(playerWinner);
			int n2 = generateOneLaunch(playerWinner2);
			System.out.println("LANZA 1 " + n1);
			System.out.println("LANZA 2 " + n2);
			while (n1 == n2 ) {
				n1 = generateOneLaunch(playerWinner);
				n2 = generateOneLaunch(playerWinner2);
				
			}
			if (n1 < n2) {
//				System.out.println("GANADOR " + playerWinner2.getName());
				playerWinner = playerWinner2;
			}
			
		}else {
//			System.out.println("GANADOR " + playerWinner.getName());
		}
		//adicionamos a la lista de victorias por genero
		winnerRoundIndividual = playerWinner;
		if(winnerRoundIndividual.getGender()==EnumGender.FEMALE){
			countGenderFemale+=1;
		}else if(winnerRoundIndividual.getGender()==EnumGender.MALE){
			countGenderMale+=1;
		}
		System.out.println("GANADOR DEFINITIVO   :) _" + winnerRoundIndividual.getId() + " " + winnerRoundIndividual.getName());
		for (int i = 0; i < listTeams.size(); i++) {
			for (int j = 0; j < listTeams.get(i).getListPlayers().size(); j++) {
				if (listTeams.get(i).getListPlayers().get(j).equals(winnerRoundIndividual)) {
					listPlayerWinner.add(winnerRoundIndividual);
//					System.out.println(listTeams.get(i).getListPlayers().get(j).getId() + "---" + winnerRoundIndividual.getId());
					Person person = listTeams.get(i).getListPlayers().get(j); 
				//	newExperience+=listTeams.get(i).getListPlayers().get(j).getExperience()+2;
//					System.out.println("casiii" + person.getName());
//					System.out.println("ANTERIOR EXP " + person.getExperience());
					person.setExperience(person.getExperience() + 3);
//					System.out.println("NUEVA EXPERIENCIA DE " + person.getExperience());
					int totalCurrent = listTeams.get(i).getListPlayers().get(j).getTotalRoundsIndividualWinner();
					listTeams.get(i).getListPlayers().get(j).setTotalRoundsIndividualWinner(totalCurrent + 1);
				}
			}
		}
		//        System.out.println("Ganador Ronda Individual:" + winnerRoundIndividual.getName());
	}

	
	/**
	 * Obtener el ganador de las rondas
	 *
	 * @param listTeams
	 */
	private void getWinnerRoundGroup(ArrayList<Team> listTeams) {
		float totalDistanceTeamA = 0;
		float totalDistanceTeamB = 0;
		//recorre la lista de jugadores del equipo 1
		for (int j = 0; j < listTeams.get(0).getListPlayers().size(); j++) {
			totalDistanceTeamA += listTeams.get(0).getListPlayers().get(j).getTotalScore();
		}
		for (int j = 0; j < listTeams.get(1).getListPlayers().size(); j++) {
			totalDistanceTeamB += listTeams.get(1).getListPlayers().get(j).getTotalScore();
		}
		//        System.out.println("total distancia eqA: " + totalDistanceTeamA);
		//        System.out.println("total distancia eqB: " + totalDistanceTeamB);
		if (totalDistanceTeamA > totalDistanceTeamB) {
			winnerRoundGroup = "A";
		} else if (totalDistanceTeamB > totalDistanceTeamA) {
			winnerRoundGroup = "B";
		}
		//        System.out.println("ganador ronda Grupal:" + winnerRoundGroup);
	}


	public String getWinnerRound() {
		return winnerRoundGroup;
	}

	public Person getWinnerRoundIndividual() {
		return winnerRoundIndividual;
	}

	public void setWinnerRoundIndividual(Person winnerRoundIndividual) {
		this.winnerRoundIndividual = winnerRoundIndividual;
	}

	public ArrayList<Team> getTeamAuxLaunching() {
		return teamAuxLaunching;
	}

	public void setTeamAuxLaunching(ArrayList<Team> teamAuxLaunching) {
		this.teamAuxLaunching = teamAuxLaunching;
	}

	public ArrayList<Person> getListAuxAllPlayers() {
		return listAuxAllPlayers;
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

	public ArrayList<Person> getListPlayerWinner() {
		return listPlayerWinner;
	}
	public Person getPlayerA() {
		return playerA;
	}
	public void setPlayerA(Person playerA) {
		this.playerA = playerA;
	}
	public Person getPlayerB() {
		return playerB;
	}
	public void setPlayerB(Person playerB) {
		this.playerB = playerB;
	}
	public int getContA() {
		return contA;
	}
	public void setContA(int contA) {
		this.contA = contA;
	}
	public int getContB() {
		return contB;
	}
	public void setContB(int contB) {
		this.contB = contB;
	}
}
