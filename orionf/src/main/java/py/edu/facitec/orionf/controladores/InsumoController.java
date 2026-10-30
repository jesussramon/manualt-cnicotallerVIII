
package py.edu.facitec.orionf.controladores;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import py.edu.facitec.orionf.dao.CategoriaDao;
import py.edu.facitec.orionf.dao.InsumoDao;
import py.edu.facitec.orionf.entidades.Categoria;
import py.edu.facitec.orionf.entidades.Insumos;
import py.edu.facitec.orionf.interfaces.InterfaceAcciones;
import py.edu.facitec.orionf.tablas.ModeloTablaInsumo;
import py.edu.facitec.orionf.vista.InsumoVentana;

public class InsumoController implements InterfaceAcciones {

    private final InsumoVentana vista;
    private final InsumoDao dao;
    private final CategoriaDao categoriaDao;
    private final ModeloTablaInsumo modelo;

    private Insumos insumoSeleccionado;
    private boolean modoEdicion = false;

    public InsumoController(InsumoVentana vista) {
        this.vista = vista;
        this.dao = new InsumoDao();
        this.categoriaDao = new CategoriaDao();
        this.modelo = (ModeloTablaInsumo) vista.getTabla().getModel();

        configurarEventos();
        cargarCategorias();
        actualizar();
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
            .addListSelectionListener((ListSelectionEvent e) -> {
                if (!e.getValueIsAdjusting()) {
                    seleccionarInsumo();
                }
            });
    }

    private void cargarCategorias() {
        try {
            vista.getCmbCategoria().removeAllItems();

            List<Categoria> categorias = categoriaDao.recuperarTodos();

            for (Categoria categoria : categorias) {
                vista.getCmbCategoria().addItem(categoria);
            }

        } catch (Exception e) {
            vista.mostrarError(
                "No se pudieron cargar las categorías: " + e.getMessage()
            );
        }
    }

    private void cargarInsumos() {
        try {
            modelo.setLista(dao.recuperarTodos());
        } catch (Exception e) {
            vista.mostrarError(
                "No se pudieron cargar los insumos: " + e.getMessage()
            );
        }
    }

    private void seleccionarInsumo() {
        if (modoEdicion) {
            return;
        }

        int fila = vista.getTabla().getSelectedRow();

        if (fila < 0) {
            return;
        }

        int filaModelo = vista.getTabla()
            .convertRowIndexToModel(fila);

        insumoSeleccionado = modelo.getInsumo(filaModelo);

        if (insumoSeleccionado == null) {
            return;
        }

        vista.getTxtNombre().setText(insumoSeleccionado.getNombre());
        vista.getTxtDescripcion().setText(
            insumoSeleccionado.getDescripcion()
        );
        vista.getTxtUnidadMedida().setText(
            insumoSeleccionado.getUnidadMedida()
        );
        vista.getTxtStockMinimo().setText(
            String.valueOf(insumoSeleccionado.getStockMinimo())
        );
        vista.getChkEstado().setSelected(
            Boolean.TRUE.equals(insumoSeleccionado.getEstado())
        );
        vista.getCmbCategoria().setSelectedItem(
            insumoSeleccionado.getCategoria()
        );
    }

    @Override
    public void agregar() {
        modoEdicion = false;
        insumoSeleccionado = null;

        vista.limpiarFormulario();
        vista.getTabla().clearSelection();
        vista.getTabla().setEnabled(false);

        vista.getBtnAgregar().setEnabled(false);
        vista.getBtnGuardar().setEnabled(true);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
        vista.getBtnCancelar().setEnabled(true);

        vista.getTxtNombre().requestFocus();
    }

    @Override
    public void guardar() {
        try {
            String nombre = vista.getTxtNombre().getText().trim();
            String descripcion =
                vista.getTxtDescripcion().getText().trim();
            String unidadMedida =
                vista.getTxtUnidadMedida().getText().trim();
            String stockTexto =
                vista.getTxtStockMinimo().getText().trim();

            if (nombre.isEmpty() || unidadMedida.isEmpty()
                    || stockTexto.isEmpty()) {
                vista.mostrarMensaje(
                    "Completá el nombre, la unidad de medida y el stock mínimo."
                );
                return;
            }

            int stockMinimo = Integer.parseInt(stockTexto);

            if (stockMinimo < 0) {
                vista.mostrarMensaje(
                    "El stock mínimo no puede ser negativo."
                );
                return;
            }

            Categoria categoria =
                (Categoria) vista.getCmbCategoria().getSelectedItem();

            if (categoria == null) {
                vista.mostrarMensaje(
                    "Primero tenés que registrar y seleccionar una categoría."
                );
                return;
            }

            Insumos insumo;

            if (modoEdicion && insumoSeleccionado != null) {
                insumo = insumoSeleccionado;
            } else {
                insumo = new Insumos();
            }

            insumo.setNombre(nombre);
            insumo.setDescripcion(
                descripcion.isEmpty() ? null : descripcion
            );
            insumo.setUnidadMedida(unidadMedida);
            insumo.setStockMinimo(stockMinimo);
            insumo.setEstado(vista.getChkEstado().isSelected());
            insumo.setCategoria(categoria);

            if (modoEdicion) {
                dao.actualizar(insumo);
                vista.mostrarMensaje("Insumo modificado correctamente.");
            } else {
                dao.guardar(insumo);
                vista.mostrarMensaje("Insumo guardado correctamente.");
            }

            actualizar();
            cancelar();

        } catch (NumberFormatException e) {
            vista.mostrarMensaje(
                "El stock mínimo debe ser un número entero."
            );
        } catch (Exception e) {
            vista.mostrarError(
                "No se pudo guardar el insumo: " + e.getMessage()
            );
        }
    }

    @Override
    public void modificar() {
        if (insumoSeleccionado == null) {
            vista.mostrarMensaje(
                "Seleccioná un insumo de la tabla para modificarlo."
            );
            return;
        }

        modoEdicion = true;
        vista.getTabla().setEnabled(false);

        vista.getBtnAgregar().setEnabled(false);
        vista.getBtnGuardar().setEnabled(true);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
        vista.getBtnCancelar().setEnabled(true);

        vista.getTxtNombre().requestFocus();
    }

    @Override
    public void eliminar() {
        if (insumoSeleccionado == null) {
            vista.mostrarMensaje(
                "Seleccioná un insumo de la tabla para eliminarlo."
            );
            return;
        }

        if (!vista.confirmar(
                "¿Estás seguro de eliminar el insumo seleccionado?")) {
            return;
        }

        try {
            dao.eliminar(insumoSeleccionado.getId());
            vista.mostrarMensaje("Insumo eliminado correctamente.");

            actualizar();
            cancelar();

        } catch (Exception e) {
            vista.mostrarError(
                "No se pudo eliminar el insumo. Puede estar relacionado "
                + "con compras o solicitudes.\n" + e.getMessage()
            );
        }
    }

    @Override
    public void cancelar() {
        modoEdicion = false;
        insumoSeleccionado = null;

        vista.limpiarFormulario();
        vista.getTabla().clearSelection();
        vista.getTabla().setEnabled(true);

        vista.getBtnAgregar().setEnabled(true);
        vista.getBtnGuardar().setEnabled(false);
        vista.getBtnModificar().setEnabled(true);
        vista.getBtnEliminar().setEnabled(true);
        vista.getBtnCancelar().setEnabled(false);
    }

    @Override
    public void salir() {
        vista.dispose();
    }

    @Override
    public void actualizar() {
        cargarInsumos();
    }
}
