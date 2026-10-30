
package py.edu.facitec.orionf.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.orionf.entidades.Insumos;

public class ModeloTablaInsumo extends AbstractTableModel {

    private final String[] columnas = {
        "ID",
        "Nombre",
        "Descripción",
        "Unidad de medida",
        "Stock mínimo",
        "Categoría",
        "Estado"
    };

    private List<Insumos> lista = new ArrayList<>();

    public void setLista(List<Insumos> lista) {
        this.lista = lista != null ? lista : new ArrayList<>();
        fireTableDataChanged();
    }

    public Insumos getInsumo(int fila) {
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
        Insumos insumo = lista.get(fila);

        switch (columna) {
            case 0:
                return insumo.getId();

            case 1:
                return insumo.getNombre();

            case 2:
                return insumo.getDescripcion();

            case 3:
                return insumo.getUnidadMedida();

            case 4:
                return insumo.getStockMinimo();

            case 5:
                return insumo.getCategoria() != null
                    ? insumo.getCategoria().getNombre()
                    : "";

            case 6:
                return Boolean.TRUE.equals(insumo.getEstado())
                    ? "Activo"
                    : "Inactivo";

            default:
                return null;
        }
    }
}
