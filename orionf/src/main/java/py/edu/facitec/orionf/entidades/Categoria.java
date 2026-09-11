package py.edu.facitec.orionf.entidades;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "tb_categorias")
public class Categoria {

    @Id
    @GenericGenerator(name = "cat_generator", strategy = "increment")
    @GeneratedValue(generator = "cat_generator")
    @Column(name = "cat_id")
    private int id;

    @Column(name = "cat_nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "cat_descripcion", length = 255)
    private String descripcion;

    @Column(name = "cat_estado", nullable = false)
    private Boolean estado;

    @OneToMany(mappedBy = "categoria")
    private List<Insumos> insumos;


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

    public List<Insumos> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<Insumos> insumos) {
        this.insumos = insumos;
    }
}