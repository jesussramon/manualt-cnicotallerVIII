
package py.edu.facitec.orionf.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import py.edu.facitec.orionf.tablas.ModeloTablaCategoria;

public class CategoriaVentana extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTextField txtNombre = new JTextField(25);
    private final JTextField txtDescripcion = new JTextField(25);
    private final JCheckBox chkEstado = new JCheckBox("Activo", true);

    private final JButton btnAgregar = new JButton("Agregar");
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnModificar = new JButton("Modificar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnCancelar = new JButton("Cancelar");
    private final JButton btnSalir = new JButton("Salir");

    private final JTable tabla =
            new JTable(new ModeloTablaCategoria());

    public CategoriaVentana() {
        setTitle("ORIONF - Gestión de categorías");
        setSize(800, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formulario.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        formulario.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formulario.add(new JLabel("Descripción:"), gbc);

        gbc.gridx = 1;
        formulario.add(txtDescripcion, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formulario.add(chkEstado, gbc);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botones.add(btnAgregar);
        botones.add(btnGuardar);
        botones.add(btnModificar);
        botones.add(btnEliminar);
        botones.add(btnCancelar);
        botones.add(btnSalir);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(24);
        JScrollPane scroll = new JScrollPane(tabla);

        principal.add(formulario, BorderLayout.NORTH);
        principal.add(scroll, BorderLayout.CENTER);
        principal.add(botones, BorderLayout.SOUTH);

        setContentPane(principal);

        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public JCheckBox getChkEstado() {
        return chkEstado;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        chkEstado.setSelected(true);
        tabla.clearSelection();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmar operación",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
