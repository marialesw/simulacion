
package View;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author Maria Latorre
 */
public class FrameWaitSimulation extends JFrame{

    private JLabel jLWateSimulation;

    public FrameWaitSimulation() {

        init();

    }

    public void init() {
        this.setSize(600, 200);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.getContentPane().setBackground(Color.WHITE);

        this.jLWateSimulation = new JLabel("Porfavor espere mientras se genera la simulacion...");
        this.jLWateSimulation.setBounds(20, 20, 360, 40);
        
        this.add(jLWateSimulation);
    }
}
