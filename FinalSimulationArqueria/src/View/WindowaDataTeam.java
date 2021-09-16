/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Constants.Constants;
import controller.MainController;
import models.Team;

import java.awt.BorderLayout;
import java.awt.Component;
import static java.awt.Dialog.DEFAULT_MODALITY_TYPE;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Maria Latorre
 */
public class WindowaDataTeam extends JDialog {
    

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JPanel jPanelDataTeam1;
    private JPanel jpanelTablePlayers1;
    private JPanel jPanelTEAM1;
    private DefaultTableModel dtm1;
    private JTable table1;
    private TableCellRenderer editor;
    private Team team1;
    private MainController mainController;
    
    private JButton jBclose;

    public WindowaDataTeam(MainController mainController, Team team) {
        this.team1 = team;
        setTitle("Jugadores del Equipo" + team.getId());
        this.setFont(Constants.FONT);
        setSize(572, 500);
        this.setFont(Constants.FONT);
        getContentPane().setLayout(null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        this.jBclose = new JButton("CERRAR");
        ImageIcon icon = new ImageIcon("iconProyect/arco.jpg");
        setIconImage(icon.getImage());
        this.mainController = mainController;
        
        init();

    }

    private void init() {
        jPanelTEAM1 = new JPanel();
        jPanelTEAM1.setBounds(10, 0, 550, 380);
        Border bordejpanel1 = new TitledBorder(new EtchedBorder(), team1.getNameTeam());
        jPanelTEAM1.setBorder(bordejpanel1);
        jPanelTEAM1.setLayout(new BorderLayout());

        this.jBclose.setBounds(220, 400, 122, 30);
        this.jBclose.setActionCommand(Constants.AC_BUTTON_CLOSE_DATA_TEAM);
        getContentPane().add(jBclose);
                
        getContentPane().add(jPanelTEAM1);
        initPanelTeam();

    }

    private void initPanelTeam() {
        jPanelDataTeam1 = new JPanel();
        jPanelDataTeam1.setFont(Constants.FONT);
        jPanelDataTeam1.setLayout(new BorderLayout());
        jpanelTablePlayers1 = new JPanel();
        jpanelTablePlayers1.setFont(Constants.FONT);
        jpanelTablePlayers1.setLayout(new BorderLayout());
        jPanelTEAM1.add(jPanelDataTeam1, BorderLayout.NORTH);
        jPanelTEAM1.add(jpanelTablePlayers1, BorderLayout.CENTER);

    }

    public void fillDataTeam(String numberWoman, String numberMan) {
        initPanelDataTeam(numberWoman, numberMan);
        initPanelTablePlayers();
    }

    private void initPanelDataTeam(String numberWoman, String numberMan) {
        jPanelDataTeam1.setLayout(new BorderLayout());
        JLabel jlMujeres = new JLabel("Mujeres: " + numberWoman);
        JLabel jlHombres = new JLabel("Hombres: " + numberMan);
        jPanelDataTeam1.add(jlMujeres, BorderLayout.EAST);
        jPanelDataTeam1.add(jlHombres, BorderLayout.CENTER);
    }

    private void initPanelTablePlayers() {
        String[] columnNames = {"Id", "Nombre", "Genero",
            "Resistencia", "Experiencia", "Suerte"};
        Object[][] datos = {};
        dtm1 = new DefaultTableModel(datos, columnNames);
        table1 = new JTable(dtm1);
        table1.setFont(Constants.FONT);
        editor = new TableCellRenderer() {
           
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {

                table1.repaint();

                return table1;
            }
        };
        
TableColumnModel columnModel = table1.getColumnModel();

    columnModel.getColumn(0).setPreferredWidth(50);
    columnModel.getColumn(1).setPreferredWidth(300);
    columnModel.getColumn(2).setPreferredWidth(300);
    columnModel.getColumn(3).setPreferredWidth(250);
    columnModel.getColumn(4).setPreferredWidth(250);
    columnModel.getColumn(5).setPreferredWidth(250);
    columnModel.getColumn(6).setPreferredWidth(250);
    columnModel.getColumn(7).setPreferredWidth(250);
    table1.setRowHeight(25);
        table1.setPreferredScrollableViewportSize(new Dimension(250, 150));
        JScrollPane scrollPane = new JScrollPane(table1);
        jpanelTablePlayers1.add(scrollPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public void fillTable(){
        for (int i = 0; i < this.team1.getListPlayers().size(); i++) {
            addNewRowTeam(team1.getListPlayers().get(i).getId() + "", 
            		team1.getListPlayers().get(i).getName() + "", 
            		team1.getListPlayers().get(i).getGender() + "", 
            		team1.getListPlayers().get(i).getResistence() + "",
            		team1.getListPlayers().get(i).getExperience() + "", 
            		team1.getListPlayers().get(i).getLucky() + "");
        }
    }
    
    public void addNewRowTeam(String id, String name, String gender,
            String resistence, String experience, String lucky) {
        Object[] newRow = {id, name, gender, resistence, experience, lucky};
        dtm1.addRow(newRow);

    }

}

