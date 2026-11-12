
package py.edu.facitec.orionf.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

import py.edu.facitec.orionf.tablas.ModeloTablaDetalleCompra;
import py.edu.facitec.orionf.utilidades.UtilidadesFecha;

public class CompraVentana extends JFrame {

    private static final long serialVersionUID = 1L;

    // Campos de datos generales
    private JFormattedTextField txtFechaCompra;
    private JTextField txtProveedor;
    private JTextField txtUsuario;

    // Campos para agregar insumos
    private JTextField txtInsumo;
    private JTextField txtCantidad;
    private JTextField txtPrecioUnitario;

    // Tabla de detalles
    private JTable tabla;
    private ModeloTablaDetalleCompra modelo;

    // Total de la compra
    private JLabel lblTotal;

    // Botones de selección
    private JButton btnBuscarProveedor;
    private JButton btnBuscarInsumo;

    // Botones de operaciones
    private JButton btnAgregarDetalle;
    private JButton btnQuitarDetalle;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JButton btnSalir;

    // Colores
    private final Color COLOR_FONDO =
            new Color(238, 241, 245);

    private final Color COLOR_AZUL =
            new Color(0, 120, 255);

    private final Color COLOR_TEXTO =
            new Color(45, 45, 45);

    public CompraVentana() {

        configurarVentana();

        construirInterfaz();
    }

    private void configurarVentana() {

        setTitle("ORIONF - Registro de compras");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(1050, 720);

        setMinimumSize(new Dimension(900, 650));

        setLocationRelativeTo(null);
    }

    private void construirInterfaz() {

        JPanel panelPrincipal =
                new JPanel(new BorderLayout(10, 10));

        panelPrincipal.setBackground(COLOR_FONDO);

        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 20, 15, 20
                )
        );

        panelPrincipal.add(
                construirEncabezado(),
                BorderLayout.NORTH
        );

        panelPrincipal.add(
                construirContenido(),
                BorderLayout.CENTER
        );

        panelPrincipal.add(
                construirBotones(),
                BorderLayout.SOUTH
        );

        setContentPane(panelPrincipal);
    }

    private JPanel construirEncabezado() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setOpaque(false);

        JLabel titulo = new JLabel(
                "REGISTRO DE COMPRAS",
                SwingConstants.CENTER
        );

        titulo.setFont(
                new Font("Arial", Font.BOLD, 23)
        );

        titulo.setForeground(COLOR_TEXTO);

        panel.add(titulo, BorderLayout.CENTER);

        return panel;
    }

    private JPanel construirContenido() {

        JPanel panel = new JPanel(new BorderLayout(10, 15));

        panel.setOpaque(false);

        panel.add(
                construirDatosCompra(),
                BorderLayout.NORTH
        );

        panel.add(
                construirDetalleCompra(),
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel construirDatosCompra() {

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Datos generales de la compra"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fecha

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("Fecha de compra:"), gbc);

        txtFechaCompra = new JFormattedTextField(
                UtilidadesFecha.getFormato()
        );

        txtFechaCompra.setColumns(10);
        txtFechaCompra.setValue(new java.util.Date());

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(txtFechaCompra, gbc);

        // Proveedor

        gbc.gridx = 2;
        gbc.weightx = 0;

        panel.add(new JLabel("Proveedor:"), gbc);

        JPanel panelProveedor =
                new JPanel(new BorderLayout(5, 0));

        txtProveedor = new JTextField();

        txtProveedor.setEditable(false);

        btnBuscarProveedor = new JButton("...");

        panelProveedor.add(
                txtProveedor,
                BorderLayout.CENTER
        );

        panelProveedor.add(
                btnBuscarProveedor,
                BorderLayout.EAST
        );

        gbc.gridx = 3;
        gbc.weightx = 1;

        panel.add(panelProveedor, gbc);

        // Usuario

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        panel.add(new JLabel("Usuario responsable:"), gbc);

        txtUsuario = new JTextField();

        txtUsuario.setEditable(false);

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1;

        panel.add(txtUsuario, gbc);

        gbc.gridwidth = 1;

        return panel;
    }

    private JPanel construirDetalleCompra() {

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        panel.setOpaque(false);

        panel.add(
                construirFormularioDetalle(),
                BorderLayout.NORTH
        );

        panel.add(
                construirTabla(),
                BorderLayout.CENTER
        );

        panel.add(
                construirTotal(),
                BorderLayout.SOUTH
        );

        return panel;
    }

    private JPanel construirFormularioDetalle() {

        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Agregar insumos a la compra"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Insumo

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("Insumo:"), gbc);

        JPanel panelInsumo =
                new JPanel(new BorderLayout(5, 0));

        txtInsumo = new JTextField();

        txtInsumo.setEditable(false);

        btnBuscarInsumo = new JButton("...");

        panelInsumo.add(
                txtInsumo,
                BorderLayout.CENTER
        );

        panelInsumo.add(
                btnBuscarInsumo,
                BorderLayout.EAST
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(panelInsumo, gbc);

        // Cantidad

        gbc.gridx = 2;
        gbc.weightx = 0;

        panel.add(new JLabel("Cantidad:"), gbc);

        txtCantidad = new JTextField(7);

        gbc.gridx = 3;

        panel.add(txtCantidad, gbc);

        // Precio unitario

        gbc.gridx = 4;

        panel.add(new JLabel("Precio unitario:"), gbc);

        txtPrecioUnitario = new JTextField(10);

        gbc.gridx = 5;

        panel.add(txtPrecioUnitario, gbc);

        // Botón agregar detalle

        btnAgregarDetalle = new JButton("Agregar");

        gbc.gridx = 6;

        panel.add(btnAgregarDetalle, gbc);

        return panel;
    }

    private JScrollPane construirTabla() {

        modelo = new ModeloTablaDetalleCompra();

        tabla = new JTable(modelo);

        tabla.setRowHeight(25);

        tabla.setFont(
                new Font("Arial", Font.PLAIN, 13)
        );

        tabla.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        tabla.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Detalle de los insumos"
                )
        );

        return scroll;
    }

    private JPanel construirTotal() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 15, 12, 15
                )
        );

        btnQuitarDetalle = new JButton("Quitar detalle");

        panel.add(
                btnQuitarDetalle,
                BorderLayout.WEST
        );

        lblTotal = new JLabel(
                "TOTAL: Gs. 0",
                SwingConstants.RIGHT
        );

        lblTotal.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        lblTotal.setForeground(COLOR_AZUL);

        panel.add(lblTotal, BorderLayout.EAST);

        return panel;
    }

    private JPanel construirBotones() {

        JPanel panel = new JPanel();

        panel.setOpaque(false);

        btnNuevo = new JButton("Nuevo");

        btnGuardar = new JButton("Guardar compra");

        btnCancelar = new JButton("Cancelar");

        btnSalir = new JButton("Salir");

        panel.add(btnNuevo);

        panel.add(btnGuardar);

        panel.add(btnCancelar);

        panel.add(btnSalir);

        return panel;
    }

    public void limpiarFormulario() {

        txtFechaCompra.setValue(new java.util.Date());

        txtProveedor.setText("");

        txtInsumo.setText("");

        txtCantidad.setText("");

        txtPrecioUnitario.setText("");

        txtUsuario.setText("");

        modelo.setLista(null);

        lblTotal.setText("TOTAL: Gs. 0");
    }

    public void mostrarMensaje(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "ORIONF",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void mostrarError(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public boolean confirmar(String mensaje) {

        return JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;
    }

    // Getters

    public JFormattedTextField getTxtFechaCompra() {
        return txtFechaCompra;
    }

    public JTextField getTxtProveedor() {
        return txtProveedor;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JTextField getTxtInsumo() {
        return txtInsumo;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public JTextField getTxtPrecioUnitario() {
        return txtPrecioUnitario;
    }

    public JTable getTabla() {
        return tabla;
    }

    public ModeloTablaDetalleCompra getModelo() {
        return modelo;
    }

    public JLabel getLblTotal() {
        return lblTotal;
    }

    public JButton getBtnBuscarProveedor() {
        return btnBuscarProveedor;
    }

    public JButton getBtnBuscarInsumo() {
        return btnBuscarInsumo;
    }

    public JButton getBtnAgregarDetalle() {
        return btnAgregarDetalle;
    }

    public JButton getBtnQuitarDetalle() {
        return btnQuitarDetalle;
    }

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}
