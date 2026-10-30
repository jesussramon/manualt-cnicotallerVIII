
package py.edu.facitec.orionf.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import py.edu.facitec.orionf.tablas.ModeloTablaProveedor;

public class ProveedorVentana extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtRazonSocial;
    private JTextField txtRuc;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JCheckBox chkEstado;

    private JButton btnAgregar;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnSalir;

    private JTable tabla;

    public ProveedorVentana() {
        setTitle("Gestión de Proveedores - ORIONF");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelFormulario = new JPanel(new GridLayout(0, 4, 8, 8));
        panelFormulario.setBorder(
                BorderFactory.createTitledBorder("Datos del proveedor"));

        txtRazonSocial = new JTextField();
        txtRuc = new JTextField();
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        txtCorreo = new JTextField();

        chkEstado = new JCheckBox("Activo", true);

        panelFormulario.add(new JLabel("Razón social:"));
        panelFormulario.add(txtRazonSocial);
        panelFormulario.add(new JLabel("RUC:"));
        panelFormulario.add(txtRuc);

        panelFormulario.add(new JLabel("Dirección:"));
        panelFormulario.add(txtDireccion);
        panelFormulario.add(new JLabel("Teléfono:"));
        panelFormulario.add(txtTelefono);

        panelFormulario.add(new JLabel("Correo:"));
        panelFormulario.add(txtCorreo);
        panelFormulario.add(new JLabel("Estado:"));
        panelFormulario.add(chkEstado);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        btnAgregar = new JButton("Agregar");
        btnGuardar = new JButton("Guardar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");
        btnSalir = new JButton("Salir");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnSalir);

        tabla = new JTable(new ModeloTablaProveedor());
        tabla.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoCreateRowSorter(true);

        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBorder(
                BorderFactory.createTitledBorder("Lista de proveedores"));

        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    public void limpiarFormulario() {
        txtRazonSocial.setText("");
        txtRuc.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        chkEstado.setSelected(true);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmar(String mensaje) {
        return JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmar operación",
                JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;
    }

    public JTextField getTxtRazonSocial() {
        return txtRazonSocial;
    }

    public JTextField getTxtRuc() {
        return txtRuc;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
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
}
