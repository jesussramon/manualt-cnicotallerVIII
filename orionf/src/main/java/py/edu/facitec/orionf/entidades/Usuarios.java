package py.edu.facitec.orionf.entidades;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "tb_usuarios")
public class Usuarios {

    @Id
    @GenericGenerator(name = "usu_generator", strategy = "increment")
    @GeneratedValue(generator = "usu_generator")
    @Column(name = "usu_id")
    private int id;

    @Column(name = "usu_nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "usu_apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "usu_cedula", nullable = false, length = 20)
    private String cedula;

    @Column(name = "usu_correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "usu_telefono", length = 20)
    private String telefono;

    @Column(name = "usu_usuario", nullable = false, length = 50)
    private String usuario;

    @Column(name = "usu_clave", nullable = false, length = 255)
    private String clave;

    @Column(name = "usu_estado", nullable = false)
    private Boolean estado;

    @ManyToOne
    private Rol rol;

    @ManyToOne
    private Facultades facultad;

    @OneToMany(mappedBy = "usuario")
    private List<Compras> compras;

    @OneToMany(mappedBy = "usuario")
    private List<Solicitudes> solicitudes;


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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Facultades getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultades facultad) {
        this.facultad = facultad;
    }

    public List<Compras> getCompras() {
        return compras;
    }

    public void setCompras(List<Compras> compras) {
        this.compras = compras;
    }

    public List<Solicitudes> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Solicitudes> solicitudes) {
        this.solicitudes = solicitudes;
    }
}