
package py.edu.facitec.orionf.utilidades;

import javax.swing.SwingUtilities;

import py.edu.facitec.orionf.controladores.ProveedorController;
import py.edu.facitec.orionf.vista.ProveedorVentana;

public class PruebaProveedor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProveedorVentana ventana = new ProveedorVentana();

            new ProveedorController(ventana);

            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
