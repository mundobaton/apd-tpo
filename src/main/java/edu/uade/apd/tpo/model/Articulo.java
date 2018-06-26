package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.ArticuloDao;

import java.util.ArrayList;
import java.util.List;

public class Articulo {

    private Long id;
    private String nombre;
    private String codigoBarras;
    private String presentacion;
    private String tamano;
    private float precio;
    private String unidad;
    private int cantCompra;
    private int stock;
    private List<ItemLote> items;
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

    public List<ItemLote> getItems() {
        return items;
    }

    public void setItems(List<ItemLote> items) {
        this.items = items;
    }

    public int getCantPosicion() {
        return cantPosicion;
    }

    public void setCantPosicion(int cantPosicion) {
        this.cantPosicion = cantPosicion;
    }

    public boolean contieneStock(int cant) {
        return this.stock >= cant;
    }

    public void agregarItem(ItemLote item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }

    public void guardar() {
        ArticuloDao.getInstance().save(this);
    }
}
