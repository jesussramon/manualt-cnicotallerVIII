
package py.edu.facitec.orionf.controladores;

import java.util.List;

import py.edu.facitec.orionf.dao.ProveedorDao;
import py.edu.facitec.orionf.entidades.Proveedores;
import py.edu.facitec.orionf.interfaces.InterfaceAcciones;
import py.edu.facitec.orionf.tablas.ModeloTablaProveedor;
import py.edu.facitec.orionf.vista.ProveedorVentana;

public class ProveedorController implements InterfaceAcciones {

    private final ProveedorVentana vista;
    private final ProveedorDao dao;
    private final ModeloTablaProveedor modelo;

    private Proveedores proveedorSeleccionado;
    private boolean modoEdicion = false;

    public ProveedorController(ProveedorVentana vista) {
        this.vista = vista;
        this.dao = new ProveedorDao();
        this.modelo = (ModeloTablaProveedor) vista.getTabla().getModel();

        configurarEventos();
        cargarProveedores();
        cancelar();
    }

    private void configurarEventos() {
        vista.getBtnAgregar().addActionListener(e -> agregar());
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnModificar().addActionListener(e -> modificar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnCancelar().addActionListener(e -> cancelar());
        vista.getBtnSalir().addActionListener(e -> salir());

        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !modoEdicion) {
                seleccionarProveedor();
            }
        });
    }

    private void cargarProveedores() {
        try {
            List<Proveedores> proveedores = dao.recuperarTodos();
            modelo.setLista(proveedores);
        } catch (Exception e) {
            vista.mostrarError(
                "No se pudieron cargar los proveedores.\n" + e.getMessage()
            );
        }
    }

    private void seleccionarProveedor() {
        int filaVista = vista.getTabla().getSelectedRow();

        if (filaVista < 0) {
            proveedorSeleccionado = null;
            return;
        }

        int filaModelo = vista.getTabla().convertRowIndexToModel(filaVista);
        proveedorSeleccionado = modelo.getProveedor(filaModelo);

        if (proveedorSeleccionado != null) {
            vista.getTxtRazonSocial().setText(
                proveedorSeleccionado.getRazonSocial()
            );
            vista.getTxtRuc().setText(
                proveedorSeleccionado.getRuc()
            );
            vista.getTxtDireccion().setText(
                proveedorSeleccionado.getDireccion() != null
                    ? proveedorSeleccionado.getDireccion() : ""
            );
            vista.getTxtTelefono().setText(
                proveedorSeleccionado.getTelefono() != null
                    ? proveedorSeleccionado.getTelefono() : ""
            );
            vista.getTxtCorreo().setText(
                proveedorSeleccionado.getCorreo() != null
                    ? proveedorSeleccionado.getCorreo() : ""
            );
            vista.getChkEstado().setSelected(
                Boolean.TRUE.equals(proveedorSeleccionado.getEstado())
            );
        }
    }

    @Override
    public void agregar() {
        modoEdicion = false;
        proveedorSeleccionado = null;

        vista.getTabla().clearSelection();
        vista.limpiarFormulario();

        vista.getBtnAgregar().setEnabled(false);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
        vista.getBtnGuardar().setEnabled(true);
        vista.getBtnCancelar().setEnabled(true);
        vista.getBtnSalir().setEnabled(false);

        vista.getTabla().setEnabled(false);
        vista.getTxtRazonSocial().requestFocus();
    }

    @Override
    public void guardar() {
        String razonSocial = vista.getTxtRazonSocial().getText().trim();
        String ruc = vista.getTxtRuc().getText().trim();

        if (razonSocial.isEmpty()) {
            vista.mostrarMensaje("Ingresá la razón social del proveedor.");
            vista.getTxtRazonSocial().requestFocus();
            return;
        }

        if (ruc.isEmpty()) {
            vista.mostrarMensaje("Ingresá el RUC del proveedor.");
            vista.getTxtRuc().requestFocus();
            return;
        }

        try {
            if (modoEdicion && proveedorSeleccionado != null) {
                proveedorSeleccionado.setRazonSocial(razonSocial);
                proveedorSeleccionado.setRuc(ruc);
                proveedorSeleccionado.setDireccion(
                    textoONull(vista.getTxtDireccion().getText())
                );
                proveedorSeleccionado.setTelefono(
                    textoONull(vista.getTxtTelefono().getText())
                );
                proveedorSeleccionado.setCorreo(
                    textoONull(vista.getTxtCorreo().getText())
                );
                proveedorSeleccionado.setEstado(
                    vista.getChkEstado().isSelected()
                );

                dao.actualizar(proveedorSeleccionado);
                vista.mostrarMensaje("Proveedor modificado correctamente.");

            } else {
                Proveedores proveedor = new Proveedores();

                proveedor.setRazonSocial(razonSocial);
                proveedor.setRuc(ruc);
                proveedor.setDireccion(
                    textoONull(vista.getTxtDireccion().getText())
                );
                proveedor.setTelefono(
                    textoONull(vista.getTxtTelefono().getText())
                );
                proveedor.setCorreo(
                    textoONull(vista.getTxtCorreo().getText())
                );
                proveedor.setEstado(
                    vista.getChkEstado().isSelected()
                );

                dao.guardar(proveedor);
                vista.mostrarMensaje("Proveedor guardado correctamente.");
            }

            cargarProveedores();
            cancelar();

        } catch (Exception e) {
            vista.mostrarError(
                "No se pudo guardar el proveedor.\n" + e.getMessage()
            );
        }
    }

    private String textoONull(String texto) {
        String valor = texto.trim();
        return valor.isEmpty() ? null : valor;
    }

    @Override
    public void modificar() {
        if (proveedorSeleccionado == null) {
            vista.mostrarMensaje("Seleccioná un proveedor de la tabla.");
            return;
        }

        modoEdicion = true;

        vista.getBtnAgregar().setEnabled(false);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
        vista.getBtnGuardar().setEnabled(true);
        vista.getBtnCancelar().setEnabled(true);
        vista.getBtnSalir().setEnabled(false);

        vista.getTabla().setEnabled(false);
        vista.getTxtRazonSocial().requestFocus();
    }

    @Override
    public void eliminar() {
        if (proveedorSeleccionado == null) {
            vista.mostrarMensaje("Seleccioná un proveedor para eliminar.");
            return;
        }

        boolean confirmar = vista.confirmar(
            "¿Estás seguro de eliminar al proveedor "
            + proveedorSeleccionado.getRazonSocial() + "?"
        );

        if (!confirmar) {
            return;
        }

        try {
            dao.eliminar(proveedorSeleccionado.getIdProveedor());

            vista.mostrarMensaje("Proveedor eliminado correctamente.");

            cargarProveedores();
            cancelar();

        } catch (Exception e) {
            vista.mostrarError(
                "No se pudo eliminar el proveedor.\n"
                + "Es posible que tenga compras asociadas.\n"
                + e.getMessage()
            );
        }
    }

    @Override
    public void cancelar() {
        modoEdicion = false;
        proveedorSeleccionado = null;

        vista.limpiarFormulario();
        vista.getTabla().clearSelection();
        vista.getTabla().setEnabled(true);

        vista.getBtnAgregar().setEnabled(true);
        vista.getBtnModificar().setEnabled(true);
        vista.getBtnEliminar().setEnabled(true);
        vista.getBtnGuardar().setEnabled(false);
        vista.getBtnCancelar().setEnabled(false);
        vista.getBtnSalir().setEnabled(true);
    }

    @Override
    public void salir() {
        vista.dispose();
    }

    @Override
    public void actualizar() {
        cargarProveedores();
    }
}
