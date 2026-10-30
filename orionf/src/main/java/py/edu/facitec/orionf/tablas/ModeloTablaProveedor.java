
package py.edu.facitec.orionf.tablas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import py.edu.facitec.orionf.entidades.Proveedores;

public class ModeloTablaProveedor extends AbstractTableModel {

    private final String[] columnas = {
        "ID",
        "Razón Social",
        "RUC",
        "Dirección",
        "Teléfono",
        "Correo",
        "Estado"
    };

    private List<Proveedores> lista = new ArrayList<>();

    public void setLista(List<Proveedores> lista) {
        this.lista = lista != null
                ? lista
                : new ArrayList<>();

        fireTableDataChanged();
    }

    public Proveedores getProveedor(int fila) {
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
        Proveedores proveedor = lista.get(fila);

        switch (columna) {
            case 0:
                return proveedor.getIdProveedor();

            case 1:
                return proveedor.getRazonSocial();

            case 2:
                return proveedor.getRuc();

            case 3:
                return proveedor.getDireccion();

            case 4:
                return proveedor.getTelefono();

            case 5:
                return proveedor.getCorreo();

            case 6:
                return Boolean.TRUE.equals(proveedor.getEstado())
                        ? "Activo"
                        : "Inactivo";

            default:
                return null;
        }
    }
}
