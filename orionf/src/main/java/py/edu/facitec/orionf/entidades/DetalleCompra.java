package py.edu.facitec.orionf.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "tb_detalles_compra")
public class DetalleCompra {

    @Id
    @GenericGenerator(name = "detcomp_generator", strategy = "increment")
    @GeneratedValue(generator = "detcomp_generator")
    @Column(name = "detcomp_id")
    private int id;

    @Column(name = "detcomp_cantidad", nullable = false)
    private int cantidad;

    @Column(name = "detcomp_precio_unitario", nullable = false)
    private Double precioUnitario;

    @ManyToOne
    private Compras compra;

    @ManyToOne
    private Insumos insumo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Compras getCompra() {
        return compra;
    }

    public void setCompra(Compras compra) {
        this.compra = compra;
    }

    public Insumos getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumos insumo) {
        this.insumo = insumo;
    }
}