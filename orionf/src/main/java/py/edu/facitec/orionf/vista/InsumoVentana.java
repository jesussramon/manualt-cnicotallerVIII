package py.edu.facitec.orionf.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import py.edu.facitec.orionf.entidades.Insumos;
import py.edu.facitec.orionf.tablas.ModeloTablaInsumo;

public class InsumoVentana extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtUnidadMedida;
    private JTextField txtCantidad;
    private JTextField txtStockMinimo;
    private JTextField txtCategoria;
    private JTextField txtBuscar;

    private JCheckBox chkEstado;

    private JButton btnBuscarCategoria;
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
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        getContentPane().setLayout(null);

        crearBotones();

        JPanel barraAcciones = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 6, 8)
        );
        barraAcciones.setBounds(10, 10, 420, 55);
        getContentPane().add(barraAcciones);

        barraAcciones.add(btnAgregar);
        barraAcciones.add(btnModificar);
        barraAcciones.add(btnEliminar);
        barraAcciones.add(btnSalir);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        panelFormulario.setBounds(10, 110, 340, 360);
        getContentPane().add(panelFormulario);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(10, 20, 100, 20);
        panelFormulario.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(115, 20, 217, 21);
        panelFormulario.add(txtNombre);

        JLabel lblDescripcion = new JLabel("Descripción");
        lblDescripcion.setBounds(10, 65, 100, 20);
        panelFormulario.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(115, 65, 217, 21);
        panelFormulario.add(txtDescripcion);

        JLabel lblUnidadMedida = new JLabel("Unidad de medida");
        lblUnidadMedida.setBounds(10, 110, 100, 20);
        panelFormulario.add(lblUnidadMedida);

        txtUnidadMedida = new JTextField();
        txtUnidadMedida.setBounds(115, 110, 217, 21);
        panelFormulario.add(txtUnidadMedida);

        JLabel lblCantidad = new JLabel("Cantidad actual");
        lblCantidad.setBounds(10, 155, 100, 20);
        panelFormulario.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(115, 155, 217, 21);
        panelFormulario.add(txtCantidad);

        JLabel lblStockMinimo = new JLabel("Stock mínimo");
        lblStockMinimo.setBounds(10, 200, 100, 20);
        panelFormulario.add(lblStockMinimo);

        txtStockMinimo = new JTextField();
        txtStockMinimo.setBounds(115, 200, 217, 21);
        panelFormulario.add(txtStockMinimo);

        JLabel lblCategoria = new JLabel("Categoría");
        lblCategoria.setBounds(10, 250, 100, 20);
        panelFormulario.add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setEditable(false);
        txtCategoria.setBounds(115, 250, 167, 21);
        panelFormulario.add(txtCategoria);

        btnBuscarCategoria = new JButton("...");
        btnBuscarCategoria.setToolTipText("Buscar categoría");
        btnBuscarCategoria.setBounds(287, 250, 45, 21);
        panelFormulario.add(btnBuscarCategoria);

        chkEstado = new JCheckBox("Activo", true);
        chkEstado.setBounds(115, 300, 100, 21);
        panelFormulario.add(chkEstado);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setBounds(450, 82, 50, 20);
        getContentPane().add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(505, 82, 375, 21);
        getContentPane().add(txtBuscar);

        tabla = new JTable(new ModeloTablaInsumo());
        tabla.setFillsViewportHeight(true);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.setBounds(450, 110, 430, 360);
        getContentPane().add(scrollTabla);

        configurarAnchoColumnas();
        configurarAlertaStock();
        configurarFiltroTabla();

        btnCancelar.setBounds(10, 495, 116, 33);
        getContentPane().add(btnCancelar);

        btnGuardar.setBounds(225, 495, 116, 33);
        getContentPane().add(btnGuardar);

        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    private void crearBotones() {
        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnSalir = new JButton("Salir");
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
    }

    private void configurarAnchoColumnas() {
        int[] anchos = {
            45, 110, 130, 120, 70, 90, 110, 80
        };

        for (int i = 0; i < anchos.length; i++) {
            tabla.getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(anchos[i]);
        }
    }

    private void configurarAlertaStock() {
        tabla.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable tabla,
                            Object valor,
                            boolean seleccionado,
                            boolean tieneFoco,
                            int fila,
                            int columna) {

                        Component componente =
                                super.getTableCellRendererComponent(
                                        tabla,
                                        valor,
                                        seleccionado,
                                        tieneFoco,
                                        fila,
                                        columna
                                );

                        if (seleccionado) {
                            return componente;
                        }

                        int filaModelo =
                                tabla.convertRowIndexToModel(fila);

                        ModeloTablaInsumo modelo =
                                (ModeloTablaInsumo) tabla.getModel();

                        Insumos insumo = modelo.getInsumo(filaModelo);

                        if (insumo.getCantidad() <= 0) {
                            componente.setBackground(
                                    new Color(255, 199, 206)
                            );
                            componente.setForeground(Color.BLACK);

                        } else if (insumo.getCantidad()
                                <= insumo.getStockMinimo()) {

                            componente.setBackground(
                                    new Color(255, 235, 156)
                            );
                            componente.setForeground(Color.BLACK);

                        } else {
                            componente.setBackground(Color.WHITE);
                            componente.setForeground(Color.BLACK);
                        }

                        return componente;
                    }
                }
        );
    }

    private void configurarFiltroTabla() {
        TableRowSorter<TableModel> sorter =
                new TableRowSorter<>(tabla.getModel());

        tabla.setRowSorter(sorter);

        txtBuscar.getDocument().addDocumentListener(
                new DocumentListener() {

                    private void filtrar() {
                        String texto = txtBuscar.getText().trim();

                        if (texto.isEmpty()) {
                            sorter.setRowFilter(null);
                            return;
                        }

                        sorter.setRowFilter(
                                RowFilter.regexFilter(
                                        "(?i)" + Pattern.quote(texto)
                                )
                        );
                    }

                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        filtrar();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        filtrar();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        filtrar();
                    }
                }
        );
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextField getTxtUnidadMedida() {
        return txtUnidadMedida;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public JTextField getTxtStockMinimo() {
        return txtStockMinimo;
    }

    public JTextField getTxtCategoria() {
        return txtCategoria;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JCheckBox getChkEstado() {
        return chkEstado;
    }

    public JButton getBtnBuscarCategoria() {
        return btnBuscarCategoria;
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
        txtUnidadMedida.setText("");
        txtCantidad.setText("");
        txtStockMinimo.setText("");
        txtCategoria.setText("");
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