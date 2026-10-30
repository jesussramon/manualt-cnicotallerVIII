
package py.edu.facitec.orionf.controladores;

import java.util.List;

import javax.swing.ListSelectionModel;

import py.edu.facitec.orionf.dao.CategoriaDao;
import py.edu.facitec.orionf.entidades.Categoria;
import py.edu.facitec.orionf.interfaces.InterfaceAcciones;
import py.edu.facitec.orionf.tablas.ModeloTablaCategoria;
import py.edu.facitec.orionf.vista.CategoriaVentana;

public class CategoriaController implements InterfaceAcciones {

    private final CategoriaVentana vista;
    private final CategoriaDao dao;
    private final ModeloTablaCategoria modelo;

    private Categoria categoriaSeleccionada;
    private boolean modoEdicion;

    public CategoriaController(CategoriaVentana vista) {
        this.vista = vista;
        this.dao = new CategoriaDao();
        this.modelo = (ModeloTablaCategoria)
                vista.getTabla().getModel();

        configurarEventos();
        cargarCategorias();
        cancelar();
    }

    private void configurarEventos() {
        vista.getBtnAgregar().addActionListener(e -> agregar());
        vista.getBtnGuardar().addActionListener(e -> guardar());
        vista.getBtnModificar().addActionListener(e -> modificar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnCancelar().addActionListener(e -> cancelar());
        vista.getBtnSalir().addActionListener(e -> salir());

        vista.getTabla().getSelectionModel()
                .addListSelectionListener(e -> {
                    if (!e.getValueIsAdjusting()) {
                        seleccionarCategoria();
                    }
                });
    }

    private void cargarCategorias() {
        try {
            List<Categoria> categorias = dao.recuperarTodos();
            modelo.setLista(categorias);
        } catch (Exception e) {
            vista.mostrarError(
                    "No se pudieron cargar las categorías: "
                    + obtenerMensaje(e));
        }
    }

    private String obtenerMensaje(Exception e) {
        return e.getMessage() != null
                ? e.getMessage()
                : e.getClass().getSimpleName();
    }

    private void seleccionarCategoria() {
        int fila = vista.getTabla().getSelectedRow();

        if (fila < 0) {
            categoriaSeleccionada = null;
            return;
        }

        categoriaSeleccionada =
                modelo.getCategoria(
                    vista.getTabla().convertRowIndexToModel(fila));

        if (categoriaSeleccionada != null && !modoEdicion) {
            vista.getTxtNombre().setText(
                    categoriaSeleccionada.getNombre());

            vista.getTxtDescripcion().setText(
                    categoriaSeleccionada.getDescripcion() != null
                    ? categoriaSeleccionada.getDescripcion() : "");

            vista.getChkEstado().setSelected(
                    Boolean.TRUE.equals(
                            categoriaSeleccionada.getEstado()));
        }
    }

    @Override
    public void agregar() {
        modoEdicion = false;
        categoriaSeleccionada = null;

        vista.limpiarFormulario();
        vista.getTxtNombre().requestFocus();

        vista.getBtnGuardar().setEnabled(true);
        vista.getBtnCancelar().setEnabled(true);
        vista.getBtnAgregar().setEnabled(false);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);

        vista.getTabla().setEnabled(false);
    }

    @Override
    public void guardar() {
        String nombre = vista.getTxtNombre().getText().trim();
        String descripcion =
                vista.getTxtDescripcion().getText().trim();

        if (nombre.isEmpty()) {
            vista.mostrarMensaje("Ingresá el nombre de la categoría.");
            vista.getTxtNombre().requestFocus();
            return;
        }

        try {
            if (modoEdicion) {
                if (categoriaSeleccionada == null) {
                    vista.mostrarMensaje(
                            "Seleccioná una categoría para modificar.");
                    return;
                }

                categoriaSeleccionada.setNombre(nombre);
                categoriaSeleccionada.setDescripcion(
                        descripcion.isEmpty() ? null : descripcion);
                categoriaSeleccionada.setEstado(
                        vista.getChkEstado().isSelected());

                dao.actualizar(categoriaSeleccionada);
                vista.mostrarMensaje(
                        "Categoría modificada correctamente.");

            } else {
                Categoria categoria = new Categoria();
                categoria.setNombre(nombre);
                categoria.setDescripcion(
                        descripcion.isEmpty() ? null : descripcion);
                categoria.setEstado(
                        vista.getChkEstado().isSelected());

                dao.guardar(categoria);
                vista.mostrarMensaje(
                        "Categoría guardada correctamente.");
            }

            cargarCategorias();
            cancelar();

        } catch (Exception e) {
            vista.mostrarError(
                    "No se pudo guardar la categoría: "
                    + obtenerMensaje(e));
        }
    }

    @Override
    public void modificar() {
        if (categoriaSeleccionada == null) {
            vista.mostrarMensaje(
                    "Seleccioná una categoría de la tabla.");
            return;
        }

        modoEdicion = true;

        vista.getBtnGuardar().setEnabled(true);
        vista.getBtnCancelar().setEnabled(true);
        vista.getBtnAgregar().setEnabled(false);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);

        vista.getTabla().setEnabled(false);
        vista.getTxtNombre().requestFocus();
    }

    @Override
    public void eliminar() {
        if (categoriaSeleccionada == null) {
            vista.mostrarMensaje(
                    "Seleccioná una categoría para eliminar.");
            return;
        }

        if (!vista.confirmar(
                "¿Querés eliminar la categoría "
                + categoriaSeleccionada.getNombre() + "?")) {
            return;
        }

        try {
            dao.eliminar(categoriaSeleccionada.getId());
            vista.mostrarMensaje(
                    "Categoría eliminada correctamente.");

            cargarCategorias();
            cancelar();

        } catch (Exception e) {
            vista.mostrarError(
                    "No se pudo eliminar la categoría. "
                    + "Puede estar relacionada con otros registros. "
                    + obtenerMensaje(e));
        }
    }

    @Override
    public void cancelar() {
        modoEdicion = false;
        categoriaSeleccionada = null;

        vista.limpiarFormulario();

        vista.getBtnGuardar().setEnabled(false);
        vista.getBtnCancelar().setEnabled(false);
        vista.getBtnAgregar().setEnabled(true);
        vista.getBtnModificar().setEnabled(true);
        vista.getBtnEliminar().setEnabled(true);

        vista.getTabla().setEnabled(true);
    }

    @Override
    public void salir() {
        vista.dispose();
    }

    @Override
    public void actualizar() {
        cargarCategorias();
    }
}
