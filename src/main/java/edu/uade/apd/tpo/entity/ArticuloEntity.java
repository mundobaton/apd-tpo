package edu.uade.apd.tpo.entity;

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
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "articulos")
public class ArticuloEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articulo_id")
    private Integer id;
    @Column(name = "cod_barras")
    private String codigoBarras;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "presentacion")
    private String presentacion;
    @Column(name = "unidad")
    private String unidad;
    @Column(name = "precio")
    private float precio;
    @Column(name = "cant_compra")
    private int cantCompra;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private StockEntity stock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private List<LoteEntity> lotes;
    @Column(name = "volumen")
    private int volumen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articulo")
    private List<OrdenCompraEntity> ordenesCompra;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
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

    public StockEntity getStock() {
        return stock;
    }

    public void setStock(StockEntity stock) {
        this.stock = stock;
    }

    public List<OrdenCompraEntity> getOrdenesCompra() {
        return ordenesCompra;
    }

    public void setOrdenesCompra(List<OrdenCompraEntity> ordenesCompra) {
        this.ordenesCompra = ordenesCompra;
    }
}
