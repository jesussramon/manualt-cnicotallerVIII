package py.edu.facitec.orionf.entidades;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "tb_facultades")
public class Facultades {

    @Id
    @GenericGenerator(name = "fac_generator", strategy = "increment")
    @GeneratedValue(generator = "fac_generator")
    @Column(name = "fac_id")
    private int id;

    @Column(name = "fac_nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "fac_descripcion", length = 255)
    private String descripcion;

    @Column(name = "fac_estado", nullable = false)
    private Boolean estado;

    @OneToMany(mappedBy = "facultad")
    private List<Usuarios> usuarios;


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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
}