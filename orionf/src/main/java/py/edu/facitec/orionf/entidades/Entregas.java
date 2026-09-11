package py.edu.facitec.orionf.entidades;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "tb_entregas")
public class Entregas {

    @Id
    @GenericGenerator(name = "ent_generator", strategy = "increment")
    @GeneratedValue(generator = "ent_generator")
    @Column(name = "ent_id")
    private int id;

    @Column(name = "ent_fecha_entrega", nullable = false)
    private Date fechaEntrega;

    @Column(name = "ent_responsable_entrega", nullable = false, length = 100)
    private String responsableEntrega;

    @Column(name = "ent_observacion", length = 255)
    private String observacion;

    @ManyToOne
    private Solicitudes solicitud;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getResponsableEntrega() {
        return responsableEntrega;
    }

    public void setResponsableEntrega(String responsableEntrega) {
        this.responsableEntrega = responsableEntrega;
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
}