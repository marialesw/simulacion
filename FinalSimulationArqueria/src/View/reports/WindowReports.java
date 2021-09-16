package View.reports;

import View.*;
import Constants.Constants;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import controller.MainController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Maria Latorre
 */
public class WindowReports extends JFrame {

    private PanelMain panelMain;
private DefaultTableModel dtm;
    private JTable table;
    private TableCellRenderer editor;
    private MainController mainController;
   private JLabel jLabel;
   private JLabel jLabel1;
    public WindowReports(MainController mainController) {
        this.mainController = mainController;
        init();
    }

    public void init() {
        this.setSize(900, 350);
        this.setFont(Constants.FONT);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("iconProyect/arco.png");
        setIconImage(icon.getImage());
        this.getContentPane().setBackground(Color.WHITE);
        initializeTable();
        otherData();
    }
public void otherData(){
    jLabel=new JLabel("EQUIPO GANADOR VICTORIAS TOTALES:");
    jLabel.setFont(Constants.FONT);
    jLabel.setBounds(15,250,300,35);
    jLabel1=new JLabel("GENERO GANADOR VICTORIAS TOTALES:");
    jLabel1.setFont(Constants.FONT);
    jLabel.setBounds(15,290,300,35);
this.add(jLabel,BorderLayout.CENTER);
this.add(jLabel1,BorderLayout.SOUTH);
}
    public void initializeTable() {
        String[] columnNames = {"Equipo victorioso", "Jugador Suertudo","Jugador Experto","Genero Victorioso"};
        Object[][] datos = {}; //vacio 
        dtm = new DefaultTableModel(datos, columnNames);
        table = new JTable(dtm);
        table.setFont(Constants.FONT);
        table.setRowHeight(20);
        JTableHeader th;
        th = table.getTableHeader();
        th.setFont(Constants.FONT);
        editor = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                table.repaint();
                return table;
            }
        };
        table.setPreferredScrollableViewportSize(new Dimension(250, 100));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.NORTH);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }

    public void addNewRow(String teamWinner, String playerLucky,
            String playerExpert,String genderWinner) {
        Object[] newRow = {teamWinner, playerLucky,playerExpert,genderWinner};
        dtm.addRow(newRow);

    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public void setjLabel(JLabel jLabel) {
        this.jLabel = jLabel;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

}
