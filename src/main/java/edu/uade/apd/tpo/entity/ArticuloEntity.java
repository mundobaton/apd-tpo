package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
=======
import edu.uade.apd.tpo.model.Stock;

>>>>>>> develop
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
<<<<<<< HEAD
import javax.persistence.Transient;
import java.io.Serializable;
=======
>>>>>>> develop
import java.util.List;

@Entity
@Table(name = "articulos")
<<<<<<< HEAD
public class ArticuloEntity implements Serializable {
=======
public class ArticuloEntity extends BaseEntity {
>>>>>>> develop

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articulo_id")
    private Long id;
<<<<<<< HEAD
    @Column(name = "codigo_barras")
    private String codBarras;
=======
    @Column(name = "cod_barras")
    private String codigoBarras;
>>>>>>> develop
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "presentacion")
    private String presentacion;
    @Column(name = "unidad")
    private String unidad;
<<<<<<< HEAD
=======
    @Column(name = "precio")
    private float precio;
>>>>>>> develop
    @Column(name = "cant_compra")
    private int cantCompra;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private StockEntity stock;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "articulo_id")
    private List<LoteEntity> lotes;
<<<<<<< HEAD
    @JoinColumn(name = "volumen")
    private int volumen;
    @Column(name = "precio")
    private float precio;
=======
    @Column(name = "volumen")
    private int volumen;
    //TODO falta orden de compra
>>>>>>> develop


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
=======
    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
>>>>>>> develop
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

<<<<<<< HEAD
    public int getCantCompra() {
        return cantCompra;
    }

    public void setCantCompra(int cantCompra) {
        this.cantCompra = cantCompra;
    }

    public StockEntity getStock() {
        return stock;
    }

    public void setStock(StockEntity stock) {
        this.stock = stock;
=======
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantCompra() {
        return cantCompra;
    }

    public void setCantCompra(int cantCompra) {
        this.cantCompra = cantCompra;
>>>>>>> develop
    }

    public List<LoteEntity> getLotes() {
        return lotes;
    }

    public void setLotes(List<LoteEntity> lotes) {
        this.lotes = lotes;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

<<<<<<< HEAD
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
=======
    public StockEntity getStock() {
        return stock;
    }

    public void setStock(StockEntity stock) {
        this.stock = stock;
>>>>>>> develop
    }
}
