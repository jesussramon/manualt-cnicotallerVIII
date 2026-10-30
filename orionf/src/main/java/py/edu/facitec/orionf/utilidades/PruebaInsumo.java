
package py.edu.facitec.orionf.utilidades;

import javax.swing.SwingUtilities;

import py.edu.facitec.orionf.controladores.InsumoController;
import py.edu.facitec.orionf.vista.InsumoVentana;

public class PruebaInsumo {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            InsumoVentana ventana = new InsumoVentana();

            new InsumoController(ventana);

            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
