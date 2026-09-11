package py.edu.facitec.orionf.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "tb_inventarios")
public class Inventarios {

    @Id
    @GenericGenerator(name = "inv_generator", strategy = "increment")
    @GeneratedValue(generator = "inv_generator")
    @Column(name = "inv_id")
    private int id;

    @Column(name = "inv_stock_actual", nullable = false)
    private int stockActual;

    @Column(name = "inv_ubicacion", length = 100)
    private String ubicacion;

    @ManyToOne
    private Insumos insumo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Insumos getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumos insumo) {
        this.insumo = insumo;
    }
}