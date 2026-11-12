package py.edu.facitec.orionf.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.orionf.entidades.DetalleCompra;

public class ModeloTablaDetalleCompra extends AbstractTableModel {

    private final String[] columnas = {
        "Insumo",
        "Cantidad",
        "Precio unitario",
        "Subtotal"
    };

    private List<DetalleCompra> lista = new ArrayList<>();

    public void setLista(List<DetalleCompra> lista) {
        this.lista = lista != null ? lista : new ArrayList<>();
        fireTableDataChanged();
    }

    public DetalleCompra getDetalle(int fila) {
        if (fila < 0 || fila >= lista.size()) {
            return null;
        }

        return lista.get(fila);
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        DetalleCompra detalle = lista.get(fila);

        switch (columna) {
        case 0:
            return detalle.getInsumo() != null
                    ? detalle.getInsumo().getNombre()
                    : "";

        case 1:
            return detalle.getCantidad();

        case 2:
            return detalle.getPrecioUnitario();

        case 3:
            return detalle.getCantidad()
                    * detalle.getPrecioUnitario();

        default:
            return null;
        }
    }
}