package py.edu.facitec.orionf.vista;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import java.awt.GridLayout;

import java.util.List;

import javax.swing.BorderFactory;

import javax.swing.JButton;

import javax.swing.JCheckBox;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.JTextArea;

import javax.swing.JTextField;

import py.edu.facitec.orionf.entidades.Categoria;

import py.edu.facitec.orionf.tablas.ModeloTablaInsumo;

public class InsumoVentana extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNombre;

    private JTextArea txtDescripcion;

    private JTextField txtUnidadMedida;

    private JTextField txtStockMinimo;

    private JComboBox<Categoria> cmbCategoria;

    private JCheckBox chkEstado;

    private JButton btnAgregar;

    private JButton btnGuardar;

    private JButton btnModificar;

    private JButton btnEliminar;

    private JButton btnCancelar;

    private JButton btnSalir;

    private JTable tabla;

    public InsumoVentana() {

        setTitle("ORIONF - Gestión de Insumos");

        setSize(900, 600);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        inicializarComponentes();

    }

    private void inicializarComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 8, 8));

        panelFormulario.setBorder(

            BorderFactory.createTitledBorder("Datos del insumo")

        );

        txtNombre = new JTextField();

        txtDescripcion = new JTextArea(3, 20);

        txtDescripcion.setLineWrap(true);

        txtDescripcion.setWrapStyleWord(true);

        txtUnidadMedida = new JTextField();

        txtStockMinimo = new JTextField();

        cmbCategoria = new JComboBox<>();

        chkEstado = new JCheckBox("Activo", true);

        panelFormulario.add(new JLabel("Nombre:"));

        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Descripción:"));

        panelFormulario.add(new JScrollPane(txtDescripcion));

        panelFormulario.add(new JLabel("Unidad de medida:"));

        panelFormulario.add(txtUnidadMedida);

        panelFormulario.add(new JLabel("Stock mínimo:"));

        panelFormulario.add(txtStockMinimo);

        panelFormulario.add(new JLabel("Categoría:"));

        panelFormulario.add(cmbCategoria);

        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelEstado.add(chkEstado);

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));

        panelSuperior.add(panelFormulario, BorderLayout.CENTER);

        panelSuperior.add(panelEstado, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        tabla = new JTable(new ModeloTablaInsumo());

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));

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

        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.setEnabled(false);

        btnCancelar.setEnabled(false);

    }

    public JTextField getTxtNombre() {

        return txtNombre;

    }

    public JTextArea getTxtDescripcion() {

        return txtDescripcion;

    }

    public JTextField getTxtUnidadMedida() {

        return txtUnidadMedida;

    }

    public JTextField getTxtStockMinimo() {

        return txtStockMinimo;

    }

    public JComboBox<Categoria> getCmbCategoria() {

        return cmbCategoria;

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

    public void cargarCategorias(List<Categoria> categorias) {

        cmbCategoria.removeAllItems();

        if (categorias != null) {

            for (Categoria categoria : categorias) {

                cmbCategoria.addItem(categoria);

            }

        }

        cmbCategoria.setSelectedIndex(-1);

    }

    public void limpiarFormulario() {

        txtNombre.setText("");

        txtDescripcion.setText("");

        txtUnidadMedida.setText("");

        txtStockMinimo.setText("");

        cmbCategoria.setSelectedIndex(-1);

        chkEstado.setSelected(true);

    }

    public void mostrarMensaje(String mensaje) {

        JOptionPane.showMessageDialog(this, mensaje);

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

            "Confirmar operación",

            JOptionPane.YES_NO_OPTION

        ) == JOptionPane.YES_OPTION;

    }

}