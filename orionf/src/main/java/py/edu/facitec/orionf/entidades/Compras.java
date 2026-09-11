package py.edu.facitec.orionf.entidades;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "tb_compras")
public class Compras {

    @Id
    @GenericGenerator(name = "comp_generator", strategy = "increment")
    @GeneratedValue(generator = "comp_generator")
    @Column(name = "comp_id")
    private int id;

    @Column(name = "comp_fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @Column(name = "comp_total_compra", nullable = false)
    private Double totalCompra;

    @Column(name = "comp_estado", nullable = false, length = 20)
    private String estado;

    @ManyToOne
    private Proveedores proveedor;

    @ManyToOne
    private Usuarios usuario;

    @OneToMany(mappedBy = "compra")
    private List<DetalleCompra> detallesCompra;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List<DetalleCompra> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }
}