
package py.edu.facitec.orionf.utilidades;

import javax.swing.SwingUtilities;

import py.edu.facitec.orionf.controladores.CategoriaController;
import py.edu.facitec.orionf.vista.CategoriaVentana;

public class PruebaCategoria {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CategoriaVentana ventana = new CategoriaVentana();
            new CategoriaController(ventana);
            ventana.setVisible(true);
        });
    }
}
