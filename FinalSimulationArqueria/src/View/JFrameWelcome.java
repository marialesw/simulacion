package View;

import Constants.Constants;
import controller.MainController;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import controller.Worker;

/**
 * Frame de bienvenida a la aplicacion
 *
 * @author Maria Latorre
 */
public class JFrameWelcome extends JFrame {
    //-------------------------------------ATRIBUTOS.----------------------//                 

    private JButton jButton1;
    private JProgressBar jProgressBar1;
    private ImageIcon imIconLoading;
    private JLabel jLImageLoading;
    private final MainController mainController;

    //--------------------------------CONSTRUCTOR-----------------------------//
    public JFrameWelcome(MainController mainController) {
        this.mainController = mainController;
        this.setFont(Constants.FONT);
        initComponents();
        Border border = BorderFactory.createTitledBorder("Leyendo...");
        jProgressBar1.setBorder(border);
        jProgressBar1.setFont(Constants.FONT);
        this.add(jProgressBar1, BorderLayout.NORTH);
        this.setTitle("JUEGO DE ARQUERIA");
        this.setFont(Constants.FONT);
        this.add(jButton1, BorderLayout.SOUTH);
        this.add(jLImageLoading, BorderLayout.CENTER);
        setSize(300, 300);
        this.setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("iconProyect/arco.jpg");
        setIconImage(icon.getImage());

    }
    //----------------------------METODOS--------------------------------//               

    private void initComponents() {
        addJProgressBar();
        addJButton();
        addJLimage();
    }

    /**
     * Agregar la barra de progreso
     */
    public void addJProgressBar() {

        jProgressBar1 = new JProgressBar();
        jProgressBar1.setMaximum(100);
        jProgressBar1.setStringPainted(true);
    }

    /**
     * agregar el boton de comienzo
     */
    public void addJButton() {
        jButton1 = new JButton("Start");
        jButton1.setFont(Constants.FONT);
        jButton1.setActionCommand("start");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Worker worker = new Worker(jProgressBar1, mainController);
                worker.execute();
            }
        });
    }

    /**
     * Agregar la imagen
     */
    public void addJLimage() {
        this.imIconLoading = new ImageIcon("iconProyect/arco.jpg");
        this.jLImageLoading = new JLabel();
        this.jLImageLoading.setIcon(new ImageIcon(imIconLoading.getImage().
                getScaledInstance(280, 200, Image.SCALE_DEFAULT)));
        this.jLImageLoading.setBounds(20, 20, 250, 250);
    }
}
