package edu.uade.apd.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "articulos")
public class ArticuloEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "articulo_id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "codigo_barras")
    private String codigoBarras;
    @Column(name = "presentacion")
    private String presentacion;
    @Column(name = "tamano")
    private String tamano;
    @Column(name = "precio")
    private float precio;
    @Column(name = "unidad")
    private String unidad;
    @Column(name = "cant_compra")
    private int cantCompra;
    @Column(name = "stock")
    private int stock;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "articulo_id")
    private List<ItemLoteEntity> items;
    @Column(name = "cant_posicion")
    private int cantPosicion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getCantCompra() {
        return cantCompra;
    }

    public void setCantCompra(int cantCompra) {
        this.cantCompra = cantCompra;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<ItemLoteEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemLoteEntity> items) {
        this.items = items;
    }

    public int getCantPosicion() {
        return cantPosicion;
    }

    public void setCantPosicion(int cantPosicion) {
        this.cantPosicion = cantPosicion;
    }
}
