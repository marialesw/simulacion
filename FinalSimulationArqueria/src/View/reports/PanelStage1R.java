package View.reports;

import Constants.Constants;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import controller.MainController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Usuario
 */
public class PanelStage1R extends JPanel {

    private DefaultTableModel dtm;
    private JTable table;
    private MainController mainController;

    public PanelStage1R(MainController mainController) {
        this.mainController = mainController;
        init();
    }

    public void init() {
this.setLayout(new BorderLayout());
        this.setBounds(10, 10, 680, 480);
        initializeTable();
//        this.setBackground(Color.BLACK);
    }

    public void initializeTable() {
        String[] columnNames = {"Partida", "Rondas", "Ganador Partida","Puntaje", "Jugador suertudo","Genero Ganador"};
        Object[][] datos = {}; //vacio 
        dtm = new DefaultTableModel(datos, columnNames);
                
        table = new JTable(dtm);
        table.setFont(Constants.FONT);
        table.setRowHeight(70);
        table.setDefaultRenderer(Object.class,
                new MultiLineCellRenderer());
        table.setDefaultEditor(Object.class, new MultiLineCellEditor());
        
        JTableHeader th;
        th = table.getTableHeader();
        th.setFont(Constants.FONT);

        table.setPreferredScrollableViewportSize(new Dimension(250, 100));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void addNewRow(String game, String rounds, String winnerGame, String score, String aLucky,
            String genVic) {
        Object[] newRow = {game, rounds, winnerGame, score, aLucky, genVic};
        dtm.addRow(newRow);

    }
    
    // Sino te gusta ver esta clase ahii puedes sacarla aparte XD
    class MultiLineCellEditor extends AbstractCellEditor implements TableCellEditor {
    
        private JTextArea jTextArea;
        public MultiLineCellEditor(){          
            jTextArea = new JTextArea();
            jTextArea.setLineWrap(true);
            jTextArea.setFont(Constants.FONT);
            
    }

        @Override
        public Object getCellEditorValue() {
            return jTextArea.getText();
        }

        @Override
        public boolean isCellEditable(EventObject e) {
            return true;        }
        
        

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
         jTextArea.setText((String) value);
         JScrollPane pane = new JScrollPane(jTextArea);

         return pane;
        }
    }
}
