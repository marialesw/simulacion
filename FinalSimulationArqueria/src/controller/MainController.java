package controller;

import Constants.Constants;
import View.FrameMain;
import View.FrameWaitSimulation;
import View.JFrameWelcome;
import View.WindowPlayersTeam;
import View.WindowaDataTeam;
import View.reports.WindowAllGames;
import View.reports.WindowReports;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.ManagerArchery;
import models.Game;
import models.Team;

/**
 *
 * @author Maria Latorre 
 */
public class MainController implements ActionListener {

	private ManagerArchery managerArchery;
	private WindowPlayersTeam windowPlayersTeam;
	private JFrameWelcome jFrameWelcome;
	private FrameMain frameMain;
	private WindowAllGames windowAllGames;
	private WindowReports windowReports;

	private WindowaDataTeam dataTeam;


	public MainController() {
		managerArchery = new ManagerArchery();
		jFrameWelcome = new JFrameWelcome(this);
		//dataTeam = new WindowaDataTeam(this, managerArchery.getListEscenary().get(0).getListGames().get(0).getListTeams().get(0));
		//    frameMain= new FrameMain(this);
	}

	/**
	 * Metodo para iniciar la aplicacion
	 */
	public void runPrincipal() {
		jFrameWelcome.setVisible(true);
		managerArchery.initPlay();
		windowPlayersTeam = new WindowPlayersTeam(this);
	}

	/**
	 * Metodo para abrir la ventana de los equipos
	 */
	public void openFrameInitPlay() {
		loadDataTeams();
		jFrameWelcome.setVisible(false);
		windowPlayersTeam.setVisible(true);
	}

	/**
	 * Cargar informacion a las tablas de los equipos
	 */
	private void loadDataTeams() {

		Team auxTeam1 = managerArchery.getManagerInitPlay().getListTeams().get(0);
		Team auxTeam2 = managerArchery.getManagerInitPlay().getListTeams().get(1);
		windowPlayersTeam.fillDataTeam1(String.valueOf(auxTeam1.getNumberWoman()),
				String.valueOf(auxTeam1.getNumberMan()));
		windowPlayersTeam.fillDataTeam2(String.valueOf(auxTeam2.getNumberWoman()),
				String.valueOf(auxTeam2.getNumberMan()));
		System.out.println(auxTeam1.getNameTeam());
		for (int i = 0; i < auxTeam1.getListPlayers().size(); i++) {
			addNewRowTeam(auxTeam1, i, 1);
		}
		for (int i = 0; i < auxTeam2.getListPlayers().size(); i++) {
			addNewRowTeam(auxTeam2, i, 2);
		}
	}

	private void addNewRowTeam(Team auxTeam2, int i, int n) {
		windowPlayersTeam.addNewRowTeam(
				String.valueOf(auxTeam2.getListPlayers().get(i).getId()),
				auxTeam2.getListPlayers().get(i).getName(),
				auxTeam2.getListPlayers().get(i).getGender().getNameGender(),
				String.valueOf(auxTeam2.getListPlayers().get(i).getResistence()),
				String.valueOf(auxTeam2.getListPlayers().get(i).getExperience()),
				String.valueOf(auxTeam2.getListPlayers().get(i).getLucky()), n);
	}

	/**
	 * Metodo para iniciar la simulacion
	 */
	private void startSimulation() {
		windowPlayersTeam.setVisible(false);
		managerArchery.startSimulation();
	}

	/**
	 * Cargar reportes iniciales
	 */
	public void loadReportsGames() {
		for (int j = 0; j < managerArchery.getGameEscenary().
				getListGames().size(); j++) {
			if (managerArchery.getGameEscenary().
					getListGames().get(j).getWinnerGame() != null) {
				Game game = managerArchery.getGameEscenary().getListGames().get(j);
				windowAllGames.getPanelStage1R().addNewRow(String.valueOf(game.getIdGame()),
						game.getListRoundGroups().toString(),game.getWinnerGame().getNameTeam(),
						String.valueOf(game.getCountScoreGameWinner()),
						String.valueOf(game.getPersonLucky().getName()),
						String.valueOf(game.getEnumGenderWinner().getNameGender()));
			}
		}
	}

	/**
	 * Cargar reportes Generales
	 */
	public void loadReportGeneral() {
		String nameLucky = managerArchery.getGameEscenary().getPlayerLucky().getName();//nombre del suertudo
		String nameExpert = managerArchery.getGameEscenary().getPlayerExpert().getName();//nombre del suertudo
		String nameGenderWinder = managerArchery.getGameEscenary().getGenderWinner().getNameGender();
		if (managerArchery.getGameEscenary().getTeamWinner() != null) {
			String nameTeam = managerArchery.getGameEscenary().getTeamWinner().getNameTeam();
			windowReports.addNewRow(nameTeam,nameLucky,nameExpert, nameGenderWinder);
		}else {
			windowReports.addNewRow("NO GANO NINGUNO", nameLucky,nameExpert, nameGenderWinder);
		}		

		String resultTeam=windowReports.getjLabel().getText()+" "+managerArchery.calculateTeamMostWinner().getNameTeam();
		windowReports.getjLabel().setText(resultTeam);

		String resultGender=windowReports.getjLabel1().getText()+" "+managerArchery.calculateGenderMostWinner().getNameGender();
		windowReports.getjLabel1().setText(resultGender);
	}

	public void openReports() {
		windowAllGames = new WindowAllGames(this);
		loadReportsGames();
		windowAllGames.setVisible(true);
	}

	public void openReportsGeneral() {
		windowReports = new WindowReports(this);
		loadReportGeneral();
		windowReports.setVisible(true);
	}

	/**
	 * Controlador de eventos
	 *
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case Constants.AC_BUTTON_START_SIMULATION:
			FrameWaitSimulation frameWaitSimulation = new FrameWaitSimulation();
			frameWaitSimulation.setVisible(true);
			this.startSimulation();
			frameWaitSimulation.dispose();
			this.frameMain = new FrameMain(this);
			frameMain.setVisible(true);

			break;
		case Constants.AC_BUTTON_VIEW_REPORTS:
			this.openReports();///aqui si va
			break;
		case Constants.AC_BUTTON_VIEW_REPORTS_GENERAL:
			this.openReportsGeneral();//aqui si va
			break;
		case Constants.AC_BUTTON_TEAM1:
			dataTeam = new WindowaDataTeam(this, managerArchery.getGameEscenary().getListGames().get(0).getListTeams().get(0));
			dataTeam.setVisible(true);
			break;
		case Constants.AC_BUTTON_TEAM2:
			dataTeam = new WindowaDataTeam(this, managerArchery.getGameEscenary().getListGames().get(0).getListTeams().get(1));
			dataTeam.setVisible(true);
			break;
		case Constants.AC_BUTTON_CLOSE_DATA_TEAM:
			dataTeam.dispose();
			break;

		}
	}

	public ManagerArchery getManagerArchery() {
		return managerArchery;
	}

}
