package View;

import Constants.Constants;
import controller.MainController;
import models.EnumDianas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maria Latorre
 */
public class PanelRound extends JPanel {

   
	private JButton jBTeam1;
    private JButton jBTeam2;
    private JLabel jLExtraShot;

    private JComboBox jCBRound;
    private JComboBox jCBDeparture;

    private JLabel jLSRound;
    private JLabel jLSDeparture;

    private JPanel jPDataTeam1;
    private JScrollPane jSPDatateam1;
    private JPanel jPDataTeam2;
    private JScrollPane jSPDatateam2;

    private JPanel jPButtonsTeam1;
    private JPanel jPTableTeam1;

    private JPanel jPButtonsTeam2;
    private JPanel jPTableTeam2;

    private ArrayList<JButton> listJBPlayerTeam1;
    private ArrayList<JButton> listJBPlayerTeam2;

    private ArrayList<DefaultTableModel> dTMListPlayerTeam1;
    private ArrayList<JTable> jTListPlayerTeam1;
    private ArrayList<JScrollPane> jSPListPlayerTeam1;

    private ArrayList<DefaultTableModel> dTMListPlayerTeam2;
    private ArrayList<JTable> jTListPlayerTeam2;
    private ArrayList<JScrollPane> jSPListPlayerTeam2;

    private MainController mainController;
    private int stage;

    public PanelRound(int stage, MainController mainController) {
        this.mainController = mainController;
        this.stage = stage;
        this.jBTeam1 = new JButton("Equipo 1");
        this.jBTeam2 = new JButton("Equipo 2");

        this.jLSRound = new JLabel("Ronda: ");
        this.jLSDeparture = new JLabel("Partida: ");

        this.jCBDeparture = new JComboBox(GetNumberGames());
        this.jCBRound = new JComboBox(GetNumberRounds(1)); 

        this.jPDataTeam1 = new JPanel();
        this.jSPDatateam1 = new JScrollPane(jPDataTeam1);

        this.jPDataTeam2 = new JPanel();
        this.jSPDatateam2 = new JScrollPane(jPDataTeam2);

        this.listJBPlayerTeam1 = new ArrayList<JButton>();
        this.listJBPlayerTeam2 = new ArrayList<JButton>();

        this.dTMListPlayerTeam1 = new ArrayList<DefaultTableModel>();
        this.jTListPlayerTeam1 = new ArrayList<JTable>();
        this.jSPListPlayerTeam1 = new ArrayList<JScrollPane>();

        this.dTMListPlayerTeam2 = new ArrayList<DefaultTableModel>();
        this.jTListPlayerTeam2 = new ArrayList<JTable>();
        this.jSPListPlayerTeam2 = new ArrayList<JScrollPane>();

        this.jPButtonsTeam1 = new JPanel();
        this.jPTableTeam1 = new JPanel();

        this.jPButtonsTeam2 = new JPanel();
        this.jPTableTeam2 = new JPanel();

        init();
        initComponentsTables(1, 1);
    }

    public void init() {

        this.setLayout(null);
    	this.setBackground(Color.LIGHT_GRAY);
        this.setBounds(20, 20, 1050, 650);

        this.jLSDeparture.setBounds(300, 10, 150, 40);
        this.jLSDeparture.setFont(Constants.FONT);
        this.add(jLSDeparture);

        this.jCBDeparture.setBounds(300, 50, 150, 30);
        this.jCBDeparture.setFont(Constants.FONT);
        this.jCBDeparture.addItemListener(new ItemListener() {
            //------------------------------------------------------------------------------------------------
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    jCBRound.setModel(new DefaultComboBoxModel(GetNumberRounds(jCBDeparture.getSelectedIndex())));
                    clearAll();
                    initComponentsTables(jCBDeparture.getSelectedIndex(), jCBRound.getSelectedIndex());
                    System.out.println("Partida Seleccionada: " + (jCBDeparture.getSelectedIndex() + 1));
                }
            }
        });
        this.add(jCBDeparture);

        this.jLSRound.setBounds(800, 10, 150, 40);
        this.jLSRound.setFont(Constants.FONT);
        this.add(jLSRound);

        this.jCBRound.setBounds(800, 50, 150, 30);
        this.jCBRound.setFont(Constants.FONT);
        this.add(jCBRound);

        this.jBTeam1.setBounds(50, 100, 470, 30);
        this.jBTeam1.setFont(Constants.FONT);
        this.jBTeam1.addActionListener(mainController);
        this.jBTeam1.setActionCommand(Constants.AC_BUTTON_TEAM1);
        this.jBTeam1.setFocusable(false);
        this.add(jBTeam1);

        this.jBTeam2.setBounds(550, 100, 470, 30);
        this.jBTeam2.setFont(Constants.FONT);
        this.jBTeam2.addActionListener(mainController);
        this.jBTeam2.setActionCommand(Constants.AC_BUTTON_TEAM2);
        this.jBTeam2.setFocusable(false);
        this.add(jBTeam2);

        this.jPButtonsTeam1.setLayout(null);
        this.jPButtonsTeam1.setPreferredSize(new Dimension(200, 4210));
        this.jPButtonsTeam1.setLocation(200, 0);
        this.jPButtonsTeam1.setBackground(Color.WHITE);

        this.jPTableTeam1.setLayout(null);
        this.jPTableTeam1.setBounds(0, 0, 300, 3000);
        this.jPTableTeam1.setBackground(Color.WHITE);

        this.jPButtonsTeam2.setLayout(null);
        this.jPButtonsTeam2.setPreferredSize(new Dimension(200, 4210));
        this.jPButtonsTeam2.setLocation(200, 0);
        this.jPButtonsTeam2.setBackground(Color.WHITE);

        this.jPTableTeam2.setLayout(null);
        this.jPTableTeam2.setBounds(0, 0, 300, 3000);
        this.jPTableTeam2.setBackground(Color.WHITE);

        this.jSPDatateam1.setBounds(50, 130, 470, 500);
        this.jPDataTeam1.setBackground(Color.WHITE);
        this.jPDataTeam1.setLayout(new GridLayout(1, 2, 1, 1));
        this.jPDataTeam1.add(jPButtonsTeam1);
        this.jPDataTeam1.add(jPTableTeam1);
        this.add(jSPDatateam1);

        this.jSPDatateam2.setBounds(550, 130, 470, 500);
        this.jPDataTeam2.setBackground(Color.WHITE);
        this.jPDataTeam2.setLayout(new GridLayout(1, 2, 1, 1));
        this.jPDataTeam2.add(jPButtonsTeam2);
        this.jPDataTeam2.add(jPTableTeam2);
        this.add(jSPDatateam2);
        
        
    }

    public void clearAll(){
        for (int i = 0; i < 20; i++) {
            jTListPlayerTeam1.clear();
            jTListPlayerTeam2.clear();
        }
    }
    
    public void initComponentsTables(int departure, int round) {

        String[] HeadboardPlayers = {"Id T", "Diana"};

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        for (int i = 0; i < Constants.NUMBER_GAMERS; i++) {
            listJBPlayerTeam1.add(new JButton((i + 1) + "-" + mainController.getManagerArchery().getGameEscenary().
                    getListGames().get(departure).getListTeams().get(0).getListPlayers().get(i).getName()));
            listJBPlayerTeam1.get(i).setSize(200, 180);
            listJBPlayerTeam1.get(i).setLocation(10, 20 + (i * 210));
            listJBPlayerTeam1.get(i).setFont(Constants.FONT);
            listJBPlayerTeam1.get(i).setToolTipText(mainController.getManagerArchery().getGameEscenary().
                    getListGames().get(departure).getListTeams().get(0).getListPlayers().get(i).abilities());
            jPButtonsTeam1.add(listJBPlayerTeam1.get(i));

            listJBPlayerTeam2.add(new JButton((i + 1) + "-" + mainController.getManagerArchery().getGameEscenary().
                    getListGames().get(departure).getListTeams().get(1).getListPlayers().get(i).getName()));
            listJBPlayerTeam2.get(i).setSize(200, 180);
            listJBPlayerTeam2.get(i).setLocation(10, 20 + (i * 210));
            listJBPlayerTeam2.get(i).setFont(Constants.FONT);
            listJBPlayerTeam2.get(i).setToolTipText(mainController.getManagerArchery().getGameEscenary().
                    getListGames().get(departure).getListTeams().get(1).getListPlayers().get(i).abilities());
            listJBPlayerTeam2.get(i).addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					switch (e.getStateChange()) {
					case ItemEvent.SELECTED:
						System.out.println("ALLA VOY");
						break;
					}
				}
			});
            
            jPButtonsTeam2.add(listJBPlayerTeam2.get(i));

            this.dTMListPlayerTeam1.add(new DefaultTableModel(HeadboardPlayers, 0) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            });
            this.jTListPlayerTeam1.add(new JTable(dTMListPlayerTeam1.get(i)));
            this.jSPListPlayerTeam1.add(new JScrollPane(jTListPlayerTeam1.get(i)));
            this.jSPListPlayerTeam1.get(i).setSize(220, 200);
            this.jSPListPlayerTeam1.get(i).setLocation(0, 10 + (i * 210));
            this.jPTableTeam1.add(jSPListPlayerTeam1.get(i));
            dataTeam1(i, departure, round);
            

            this.dTMListPlayerTeam2.add(new DefaultTableModel(HeadboardPlayers, 0) {
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            });
            this.jTListPlayerTeam2.add(new JTable(dTMListPlayerTeam2.get(i)));

            this.jSPListPlayerTeam2.add(new JScrollPane(jTListPlayerTeam2.get(i)));
            this.jSPListPlayerTeam2.get(i).setSize(220, 200);
            this.jSPListPlayerTeam2.get(i).setLocation(0, 10 + (i * 210));
            this.jPTableTeam2.add(jSPListPlayerTeam2.get(i));
            dataTableTeam2(i, departure, round);
        }
    }

    public Object[] addNewRow(String iDT, EnumDianas diana) {
        Object[] newRow2 = {iDT, diana + ""};
        return newRow2;
    }

    
    
    public void dataTeam1(int player, int departure, int round){
//        System.out.println("lanzamiento arreglado"+mainController.getManagerArchery().getListEscenary().get(stage).getListGames().get(departure).getListRoundGroups().get(round).getTeamAuxLaunching().get(0).getListPlayers().get(0).getListLaunching().size());
//        System.out.println("------------------------- Lanzamiteos del jugador" + mainController.getManagerArchery().getListEscenary().get(stage).getListGames().get(departure).getListTeams().get(0).getListPlayers().get(2).getListLaunching().size());
            for (int i = 0; i < mainController.getManagerArchery().getGameEscenary().getListGames().get(departure).getListTeams().get(0).getListPlayers().get(player).getListLaunching().size(); i++) {
            dTMListPlayerTeam1.get(player).addRow(addNewRow(mainController.getManagerArchery().getGameEscenary().getListGames().get(departure).getListTeams().get(0).getListPlayers().get(player).getListLaunching().get(i).getId() + "",
                    mainController.getManagerArchery().getGameEscenary().getListGames().get(departure).getListTeams().get(0).getListPlayers().get(player).getListLaunching().get(i).getDiana()));
        }
    }
    
    public void dataTableTeam1(int player, int departure, int round) {
        for (int i = 0; i < mainController.getManagerArchery().getGameEscenary().
                getListGames().get(departure).getListTeams().get(0).getListPlayers().get(player).getListLaunching().size(); i++) {
            dTMListPlayerTeam1.get(player).addRow(addNewRow(mainController.getManagerArchery().getGameEscenary().
                    getListGames().get(departure).getListTeams().get(0).getListPlayers().get(player).getListLaunching().get(i).getId() + "",
                    mainController.getManagerArchery().getGameEscenary().
                            getListGames().get(departure).getListTeams().get(0).getListPlayers().get(player).getListLaunching().get(i).getDiana()));
        }

    }

        public void dataTableTeam2(int player, int departure, int round) {
        for (int i = 0; i < mainController.getManagerArchery().getGameEscenary().
                getListGames().get(departure).getListTeams().get(1).getListPlayers().get(player).getListLaunching().size(); i++) {
            dTMListPlayerTeam1.get(player).addRow(addNewRow(mainController.getManagerArchery().getGameEscenary().
                    getListGames().get(departure).getListTeams().get(1).getListPlayers().get(player).getListLaunching().get(i).getId() + "",
                    mainController.getManagerArchery().getGameEscenary().
                            getListGames().get(departure).getListTeams().get(1).getListPlayers().get(player).getListLaunching().get(i).getDiana()));
        }

    }
    
    public Object[] GetNumberGames() {
        Object games[] = new Object[mainController.getManagerArchery().getGameEscenary().getListGames().size()];
        for (int i = 0; i < mainController.getManagerArchery().getGameEscenary().getListGames().size(); i++) {
            games[i] = i + 1;
        }
        return games;
    }

    public Object[] GetNumberRounds(int departure) {
    	System.out.println("DEPATURE " + departure);
    	int numberRounds = mainController.getManagerArchery().getGameEscenary().getListGames().get(departure).getListRoundGroups().size();
        System.out.println("Esssssssss" + mainController.getManagerArchery().getGameEscenary().getListGames().toString());
        Object rounds[] = new Object[numberRounds];
        for (int i = 0; i < numberRounds; i++) {
            rounds[i] = i + 1;
        }
        return rounds;
    }

    public void run() {
        while(true){
            try {
                Thread.sleep(1);
                jCBRound.setModel(new DefaultComboBoxModel(GetNumberRounds(jCBDeparture.getSelectedItem().hashCode())));
                System.out.println(jCBDeparture.getSelectedIndex() + "Este es---------------------");
            } catch (InterruptedException ex) {
                Logger.getLogger(PanelRound.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
