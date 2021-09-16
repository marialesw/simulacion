package View;

import controller.MainController;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Maria
 */
public class FrameMain extends JFrame {


    private JTabbedPane jTPPestaEscenary;

    private PanelStage1 panelStage1;

    private MainController mainController;
    public FrameMain(MainController mainController) {
        this.mainController = mainController;
        this.jTPPestaEscenary= new JTabbedPane();
        this.panelStage1 = new PanelStage1(mainController);
        init();
    }

    public void init() {
        this.setSize(1350, 750);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("iconProyect/arco.jpg");
        setIconImage(icon.getImage());
        jTPPestaEscenary.addTab("SIMULACION", panelStage1);
    	this.getContentPane().setBackground(Color.DARK_GRAY);
        this.getContentPane().add(jTPPestaEscenary);
    }

}
