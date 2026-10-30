
package py.edu.facitec.orionf.entidades;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "tb_insumos")
public class Insumos {

    @Id
    @GenericGenerator(name = "ins_generator", strategy = "increment")
    @GeneratedValue(generator = "ins_generator")
    @Column(name = "ins_id")
    private int id;

    @Column(name = "ins_nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "ins_descripcion", length = 255)
    private String descripcion;

    @Column(name = "ins_unidad_medida", nullable = false, length = 50)
    private String unidadMedida;

    @Column(name = "ins_stock_minimo", nullable = false)
    private int stockMinimo;

    @Column(name = "ins_estado", nullable = false)
    private Boolean estado;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "insumo")
    private List<DetalleCompra> detallesCompra;

    @OneToMany(mappedBy = "insumo")
    private List<DetalleSolicitud> detallesSolicitud;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<DetalleCompra> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }

    public List<DetalleSolicitud> getDetallesSolicitud() {
        return detallesSolicitud;
    }

    public void setDetallesSolicitud(
            List<DetalleSolicitud> detallesSolicitud) {
        this.detallesSolicitud = detallesSolicitud;
    }
}
