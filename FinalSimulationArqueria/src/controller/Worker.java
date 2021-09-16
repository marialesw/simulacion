package controller;

import java.awt.List;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * Clase que trabaja para la barra de progreso
 *
 */
public class Worker extends SwingWorker {
//---------------------------ATRIBUTOS---------------------------------------//

    private final JProgressBar progreso;
    private MainController mainController;
//---------------------------CONSTRUCTOR-------------------------------------//

    public Worker(JProgressBar barra, MainController mainController) {
        progreso = barra;
        this.mainController = mainController;
    }
//-----------------------------METODOS---------------------------------------//

    @Override
    public Double doInBackground() throws Exception {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            publish(i + 1);
//            System.out.println(i);
            progreso.setValue(i);
        }
        return 100.00;
    }

    /**
     * Proceso que desarrollara cuando haya acabado la accion
     */
    @Override
    protected void done() {
        mainController.openFrameInitPlay();
        //cierrelo
    }

    protected void process(List chunks) {
        //Actualizando la barra de progreso. Datos del publish.
        progreso.setValue(5);

    }
}
