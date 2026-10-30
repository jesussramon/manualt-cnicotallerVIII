
package py.edu.facitec.orionf.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import py.edu.facitec.orionf.entidades.Categoria;

public class ModeloTablaCategoria extends AbstractTableModel {

    private final String[] columnas = {
        "ID", "Nombre", "Descripción", "Estado"
    };

    private List<Categoria> lista = new ArrayList<>();

    public void setLista(List<Categoria> lista) {
        this.lista = lista != null ? lista : new ArrayList<>();
        fireTableDataChanged();
    }

    public Categoria getCategoria(int fila) {
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
        Categoria categoria = lista.get(fila);

        switch (columna) {
            case 0:
                return categoria.getId();
            case 1:
                return categoria.getNombre();
            case 2:
                return categoria.getDescripcion();
            case 3:
                return Boolean.TRUE.equals(categoria.getEstado())
                        ? "Activo" : "Inactivo";
            default:
                return null;
        }
    }
}
