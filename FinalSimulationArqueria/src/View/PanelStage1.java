
package View;

import Constants.Constants;
import controller.MainController;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Maria Latorre
 */
public class PanelStage1 extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelRound panelRound;
    private MainController mainController;
    
    private JButton jBReports;
    private JButton jBReportsGeneral;
            
    public PanelStage1(MainController mainController) {
        this.mainController = mainController;
        this.panelRound = new PanelRound(0, mainController);
        
        this.jBReports = new JButton("Reportes Individuales");
        this.jBReportsGeneral = new JButton("Reporte General");
        
        init();
    }

    public void init() {

        this.setLayout(null);
        this.setBackground(Color.darkGray);
        
        this.jBReports.setBounds(1100, 300, 200, 70);
        this.jBReports.setFont(Constants.FONT);
        this.jBReports.addActionListener(mainController);
        this.jBReports.setActionCommand(Constants.AC_BUTTON_VIEW_REPORTS);
        this.add(jBReports);
        
        
        this.jBReportsGeneral.setBounds(1100, 400, 200, 70);
        this.jBReportsGeneral.setFont(Constants.FONT);
        this.jBReportsGeneral.addActionListener(mainController);
        this.jBReportsGeneral.setActionCommand(Constants.AC_BUTTON_VIEW_REPORTS_GENERAL);
        this.add(jBReportsGeneral);
        
        this.add(panelRound);
    }
}
