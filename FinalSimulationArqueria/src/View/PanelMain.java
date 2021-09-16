package View;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Maria Latorre
 */
public class PanelMain extends JPanel {

    public PanelMain() {

        init();
    }

    public void init() {

        this.setLayout(null);
        this.setBounds(100, 100, 700, 500);
        this.setBackground(Color.red);

    }
}
