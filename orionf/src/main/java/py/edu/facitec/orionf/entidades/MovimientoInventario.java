package py.edu.facitec.orionf.entidades;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "tb_movimientos_inventario")
public class MovimientoInventario {

    @Id
    @GenericGenerator(name = "mov_generator", strategy = "increment")
    @GeneratedValue(generator = "mov_generator")
    @Column(name = "mov_id")
    private int id;

    @Column(name = "mov_tipo_movimiento", nullable = false, length = 20)
    private String tipoMovimiento;

    @Column(name = "mov_cantidad", nullable = false)
    private int cantidad;

    @Column(name = "mov_fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "mov_observacion", length = 255)
    private String observacion;

    @ManyToOne
    private Insumos insumo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Insumos getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumos insumo) {
        this.insumo = insumo;
    }
}