package View;

import Constants.Constants;
import controller.MainController;
import models.Team;

import java.awt.BorderLayout;
import java.awt.Component;
import static java.awt.Dialog.DEFAULT_MODALITY_TYPE;
import java.awt.Dimension;
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
public class WindowPlayersTeam extends JDialog {

    private JPanel jPanelDataTeam1;
    private JPanel jpanelTablePlayers1;
    private JPanel jPanelDataTeam2;
    private JPanel jpanelTablePlayers2;
    private JPanel jPanelTEAM1;
    private JPanel jPanelTEAM2;
    private DefaultTableModel dtm1;
    private DefaultTableModel dtm2;
    private JTable table1;
    private JTable table2;
    private TableCellRenderer editor;
    private Team team1;
    private Team team2;
    private MainController mainController;

    public WindowPlayersTeam(MainController mainController) {
        setTitle("Jugadores de Equipos");
//        ImageIcon icon = new ImageIcon("iconProyect/delincuente.png");
//        setIconImage(icon.getImage());
        this.setFont(Constants.FONT);
        this.team1 = mainController.getManagerArchery().getManagerInitPlay().getListTeams().get(0);
        this.team2 = mainController.getManagerArchery().getManagerInitPlay().getListTeams().get(1);
        setSize(1145, 500);
        this.setFont(Constants.FONT);
        getContentPane().setLayout(null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        ImageIcon icon = new ImageIcon("iconProyect/arco.jpg");
        setIconImage(icon.getImage());
        this.mainController = mainController;
        init();
//        initPanelDataTeam(String.valueOf(team.getNumberWoman()),
//                String.valueOf(team.getNumberMan()));
//        initPanelTablePlayers();
    }

    private void init() {
        jPanelTEAM1 = new JPanel();
        jPanelTEAM1.setBounds(10, 0, 550, 380);
        Border bordejpanel1 = new TitledBorder(new EtchedBorder(), team1.getNameTeam());
        jPanelTEAM1.setBorder(bordejpanel1);
        jPanelTEAM1.setLayout(new BorderLayout());

        jPanelTEAM2 = new JPanel();
        jPanelTEAM2.setBounds(575, 0, 550, 380);
        Border bordejpanel2 = new TitledBorder(new EtchedBorder(), team2.getNameTeam());
        jPanelTEAM2.setBorder(bordejpanel2);
        jPanelTEAM2.setLayout(new BorderLayout());

        getContentPane().add(jPanelTEAM1);
        getContentPane().add(jPanelTEAM2);

        initPanelTeams();
        JButton jbStartSimulation = new JButton("Iniciar Simulacion");
        jbStartSimulation.setFont(Constants.FONT);
        jbStartSimulation.setBounds(this.getWidth() / 2 - 150, 400, 300, 30);
        jbStartSimulation.setActionCommand(Constants.AC_BUTTON_START_SIMULATION);
        jbStartSimulation.addActionListener(mainController);
        getContentPane().add(jbStartSimulation);

    }

    private void initPanelTeams() {
        jPanelDataTeam1 = new JPanel();
        jPanelDataTeam1.setFont(Constants.FONT);
        jPanelDataTeam1.setLayout(new BorderLayout());
        jpanelTablePlayers1 = new JPanel();
       jpanelTablePlayers1.setFont(Constants.FONT);
        jpanelTablePlayers1.setLayout(new BorderLayout());
        jPanelTEAM1.add(jPanelDataTeam1, BorderLayout.NORTH);
        jPanelTEAM1.add(jpanelTablePlayers1, BorderLayout.CENTER);

        jPanelDataTeam2 = new JPanel();
        jPanelDataTeam2.setFont(Constants.FONT);
        jPanelDataTeam2.setLayout(new BorderLayout());
        jpanelTablePlayers2 = new JPanel();
        jpanelTablePlayers2.setFont(Constants.FONT);
        jpanelTablePlayers2.setLayout(new BorderLayout());
        jPanelTEAM2.add(jPanelDataTeam2, BorderLayout.NORTH);
        jPanelTEAM2.add(jpanelTablePlayers2, BorderLayout.CENTER);

    }

    public void fillDataTeam1(String numberWoman, String numberMan) {
        initPanelDataTeam1(numberWoman, numberMan);
        initPanelTablePlayers1();
    }

    public void fillDataTeam2(String numberWoman, String numberMan) {
        initPanelDataTeam2(numberWoman, numberMan);
        initPanelTablePlayers2();

    }

    private void initPanelDataTeam1(String numberWoman, String numberMan) {
        jPanelDataTeam1.setLayout(new BorderLayout());
        JLabel jlMujeres = new JLabel("Mujeres: " + numberWoman);
        JLabel jlHombres = new JLabel("Hombres: " + numberMan);
        jPanelDataTeam1.add(jlMujeres, BorderLayout.EAST);
        jPanelDataTeam1.add(jlHombres, BorderLayout.CENTER);
    }

    private void initPanelDataTeam2(String numberWoman, String numberMan) {
        jPanelDataTeam2.setLayout(new BorderLayout());
        JLabel jlMujeres = new JLabel("Mujeres: " + numberWoman);
        JLabel jlHombres = new JLabel("Hombres: " + numberMan);
        jPanelDataTeam2.add(jlMujeres, BorderLayout.EAST);
        jPanelDataTeam2.add(jlHombres, BorderLayout.CENTER);
    }

    private void initPanelTablePlayers1() {
        String[] columnNames = {"Id", "Nombre", "Genero",
            "Resistencia", "Experiencia", "Suerte"};
        Object[][] datos = {}; //vacio 
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
        
// Modificar celda especifica
//        dtm.setValueAt("XXX", 3, 3); // Row/Col
TableColumnModel columnModel = table1.getColumnModel();

    columnModel.getColumn(0).setPreferredWidth(50);
    columnModel.getColumn(1).setPreferredWidth(300);
    columnModel.getColumn(2).setPreferredWidth(300);
    columnModel.getColumn(3).setPreferredWidth(250);
    columnModel.getColumn(4).setPreferredWidth(250);
    columnModel.getColumn(5).setPreferredWidth(250);
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

    private void initPanelTablePlayers2() {
        String[] columnNames = {"Id", "Nombre", "Genero",
            "Resistencia", "Experiencia", "Suerte"};
        
        Object[][] datos = {}; //vacio 
        dtm2 = new DefaultTableModel(datos, columnNames);
        
        table2 = new JTable(dtm2);
        table2.setFont(Constants.FONT);
        editor = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {

                table2.repaint();

                return table2;
            }
        };
// Modificar celda especifica
//        dtm.setValueAt("XXX", 3, 3); // Row/Col
TableColumnModel columnModel = table2.getColumnModel();

    columnModel.getColumn(0).setPreferredWidth(50);
    columnModel.getColumn(1).setPreferredWidth(300);
    columnModel.getColumn(2).setPreferredWidth(300);
    columnModel.getColumn(3).setPreferredWidth(250);
    columnModel.getColumn(4).setPreferredWidth(250);
    columnModel.getColumn(5).setPreferredWidth(250);
    table2.setRowHeight(25);
        table2.setPreferredScrollableViewportSize(new Dimension(250, 150));
        JScrollPane scrollPane = new JScrollPane(table2);
        jpanelTablePlayers2.add(scrollPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public void addNewRowTeam(String id, String name, String gender,
            String resistence, String experience, String lucky, int n) {
        Object[] newRow = {id, name, gender, resistence, experience, lucky};
        if (n == 1) {
            dtm1.addRow(newRow);
		}else {
	        dtm2.addRow(newRow);
		}
    }
}
