package py.edu.facitec.orionf.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "tb_detalles_solicitud")
public class DetalleSolicitud {

    @Id
    @GenericGenerator(name = "detsol_generator", strategy = "increment")
    @GeneratedValue(generator = "detsol_generator")
    @Column(name = "detsol_id")
    private int id;

    @Column(name = "detsol_cantidad", nullable = false)
    private int cantidad;

    @Column(name = "detsol_observacion", length = 255)
    private String observacion;

    @ManyToOne
    private Solicitudes solicitud;

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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Solicitudes getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitudes solicitud) {
        this.solicitud = solicitud;
    }

    public Insumos getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumos insumo) {
        this.insumo = insumo;
    }
}