package py.edu.facitec.orionf.buscadores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import py.edu.facitec.orionf.dao.CategoriaDao;
import py.edu.facitec.orionf.entidades.Categoria;
import py.edu.facitec.orionf.interfaces.InterfaceCategoria;
import py.edu.facitec.orionf.tablas.ModeloTablaCategoria;

public class BuscadorCategoria extends JDialog {

    private static final long serialVersionUID = 1L;

    private JTable table;
    private JTextField tfBuscador;
    private CategoriaDao categoriaDao;
    private List<Categoria> categorias;
    private InterfaceCategoria interfaceCategoria;
    private ModeloTablaCategoria modeloTablaCategoria;

    public void setInterface(InterfaceCategoria interfaceCategoria) {
        this.interfaceCategoria = interfaceCategoria;
    }

    public BuscadorCategoria() {
        setTitle("Buscador de categorías");
        setBounds(100, 100, 450, 500);
        setModal(true);
        setLocationRelativeTo(this);
        getContentPane().setLayout(null);

        tfBuscador = new JTextField();
        tfBuscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    filtrarCategorias();
                }
            }
        });
        tfBuscador.setBounds(10, 10, 416, 19);
        getContentPane().add(tfBuscador);
        tfBuscador.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 39, 416, 414);
        getContentPane().add(scrollPane);

        modeloTablaCategoria = new ModeloTablaCategoria();
        table = new JTable();
        table.setModel(modeloTablaCategoria);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seleccionarRegistro(table.getSelectedRow());
                }
            }
        });

        scrollPane.setViewportView(table);

        categoriaDao = new CategoriaDao();
        filtrarCategorias();
    }

    private void filtrarCategorias() {
        categorias = categoriaDao.filtrarCategorias(tfBuscador.getText());
        modeloTablaCategoria.setLista(categorias);
    }

    private void seleccionarRegistro(int fila) {
        if (fila < 0) {
            return;
        }

        Categoria categoria = modeloTablaCategoria.getCategoria(fila);

        if (categoria == null) {
            return;
        }

        interfaceCategoria.seleccionarCategoria(categoria);
        dispose();
    }
}