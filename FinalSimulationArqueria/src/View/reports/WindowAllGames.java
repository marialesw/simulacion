package View.reports;

import View.*;
import Constants.Constants;
import controller.MainController;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Usuario
 */
public class WindowAllGames extends JFrame {

    private PanelMain panelMain;

    private JTabbedPane jTPPestaEscenary;
    private PanelStage1R panelStage1R;

    private MainController mainController;

    public WindowAllGames(MainController mainController) {
        ImageIcon icon = new ImageIcon("iconProyect/arco.png");
        setIconImage(icon.getImage());
        this.mainController = mainController;

        this.jTPPestaEscenary = new JTabbedPane();
        this.panelStage1R = new PanelStage1R(mainController);
        init();
    }

    public void init() {
        this.setFont(Constants.FONT);
        this.setSize(900, 700);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.WHITE);

        jTPPestaEscenary.addTab("Reportes de Juego", panelStage1R);
        
        jTPPestaEscenary.setFont(Constants.FONT);
        this.getContentPane().add(jTPPestaEscenary);
    }

    public PanelStage1R getPanelStage1R() {
        return panelStage1R;
    }
}
