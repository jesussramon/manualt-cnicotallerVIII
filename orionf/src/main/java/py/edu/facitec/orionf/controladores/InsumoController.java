package py.edu.facitec.orionf.controladores;

import java.util.List;

import javax.swing.event.ListSelectionEvent;

import py.edu.facitec.orionf.buscadores.BuscadorCategoria;
import py.edu.facitec.orionf.dao.InsumoDao;
import py.edu.facitec.orionf.entidades.Categoria;
import py.edu.facitec.orionf.entidades.Insumos;
import py.edu.facitec.orionf.interfaces.InterfaceAcciones;
import py.edu.facitec.orionf.interfaces.InterfaceCategoria;
import py.edu.facitec.orionf.tablas.ModeloTablaInsumo;
import py.edu.facitec.orionf.vista.InsumoVentana;

public class InsumoController
        implements InterfaceAcciones, InterfaceCategoria {

    private final InsumoVentana vista;
    private final InsumoDao dao;
    private final ModeloTablaInsumo modelo;

    private Insumos insumoSeleccionado;
    private Categoria categoriaSeleccionada;
    private boolean modoEdicion = false;

    public InsumoController(InsumoVentana vista) {
        this.vista = vista;
        this.dao = new InsumoDao();
        this.modelo = (ModeloTablaInsumo) vista.getTabla().getModel();

        configurarEventos();
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

        vista.getBtnBuscarCategoria()
                .addActionListener(e -> abrirBuscadorCategoria());

        vista.getTabla().getSelectionModel()
                .addListSelectionListener((ListSelectionEvent e) -> {
                    if (!e.getValueIsAdjusting()) {
                        seleccionarInsumo();
                    }
                });
    }

    private void abrirBuscadorCategoria() {
        BuscadorCategoria buscador = new BuscadorCategoria();
        buscador.setInterface(this);
        buscador.setVisible(true);
    }

    @Override
    public void seleccionarCategoria(Categoria categoria) {
        categoriaSeleccionada = categoria;
        vista.getTxtCategoria().setText(categoria.getNombre());
    }

    private void cargarInsumos() {
        try {
            modelo.setLista(dao.recuperarTodos());
        } catch (Exception e) {
            vista.mostrarError(
                    "No se pudieron cargar los insumos: "
                    + e.getMessage()
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
        vista.getTxtCantidad().setText(
                String.valueOf(insumoSeleccionado.getCantidad())
        );
        vista.getTxtStockMinimo().setText(
                String.valueOf(insumoSeleccionado.getStockMinimo())
        );
        vista.getChkEstado().setSelected(
                Boolean.TRUE.equals(insumoSeleccionado.getEstado())
        );

        categoriaSeleccionada = insumoSeleccionado.getCategoria();

        if (categoriaSeleccionada != null) {
            vista.getTxtCategoria().setText(
                    categoriaSeleccionada.getNombre()
            );
        } else {
            vista.getTxtCategoria().setText("");
        }
    }

    @Override
    public void agregar() {
        modoEdicion = false;
        insumoSeleccionado = null;
        categoriaSeleccionada = null;

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
            String cantidadTexto =
                    vista.getTxtCantidad().getText().trim();
            String stockTexto =
                    vista.getTxtStockMinimo().getText().trim();

            if (nombre.isEmpty() || unidadMedida.isEmpty()
                    || cantidadTexto.isEmpty() || stockTexto.isEmpty()) {
                vista.mostrarMensaje(
                        "Completá el nombre, la unidad de medida, "
                        + "la cantidad y el stock mínimo."
                );
                return;
            }

            if (categoriaSeleccionada == null) {
                vista.mostrarMensaje(
                        "Seleccioná una categoría con el botón (...)."
                );
                return;
            }

            int cantidad = Integer.parseInt(cantidadTexto);
            int stockMinimo = Integer.parseInt(stockTexto);

            if (cantidad < 0 || stockMinimo < 0) {
                vista.mostrarMensaje(
                        "La cantidad y el stock mínimo no pueden "
                        + "ser negativos."
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
            insumo.setCantidad(cantidad);
            insumo.setStockMinimo(stockMinimo);
            insumo.setEstado(vista.getChkEstado().isSelected());
            insumo.setCategoria(categoriaSeleccionada);

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
                    "La cantidad y el stock mínimo deben ser "
                    + "números enteros."
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
                    "No se pudo eliminar el insumo. Puede estar "
                    + "relacionado con compras o solicitudes.\n"
                    + e.getMessage()
            );
        }
    }

    @Override
    public void cancelar() {
        modoEdicion = false;
        insumoSeleccionado = null;
        categoriaSeleccionada = null;

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