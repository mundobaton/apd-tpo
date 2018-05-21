package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.LoteEntity;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;

import java.util.ArrayList;
import java.util.List;

public class Articulo {

    private Integer id;
    private String codigoBarras;
    private String descripcion;
    private String presentacion;
    private String unidad;
    private float precio;
    private int cantCompra;
    private Stock stock;
    private List<Lote> lotes;
    private int volumen;
    private List<OrdenCompra> ordenesCompra;

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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public List<OrdenCompra> getOrdenesCompra() {
        return ordenesCompra;
    }

    public void setOrdenesCompra(List<OrdenCompra> ordenesCompra) {
        this.ordenesCompra = ordenesCompra;
    }

    public void guardar() {
        ArticuloDao.getInstance().save(this.toEntity());
    }

    public ArticuloEntity toEntity() {
        ArticuloEntity entity = new ArticuloEntity();
        entity.setId(id);
        entity.setCodigoBarras(codigoBarras);
        entity.setDescripcion(descripcion);
        entity.setPresentacion(presentacion);
        entity.setUnidad(unidad);
        entity.setPrecio(precio);
        entity.setCantCompra(cantCompra);
        entity.setStock(stock == null ? null : stock.toEntity());
        if (this.getLotes() != null) {
            List<LoteEntity> lotes = new ArrayList<>();
            for (Lote l : this.getLotes()) {
                lotes.add(l.toEntity());
            }
            entity.setLotes(lotes);
        }
        entity.setVolumen(volumen);
        if (this.getOrdenesCompra() != null) {
            List<OrdenCompraEntity> ordenesCompra = new ArrayList<>();
            for (OrdenCompra oc : this.getOrdenesCompra()) {
                ordenesCompra.add(oc.toEntity());
            }
            entity.setOrdenesCompra(ordenesCompra);
        }

        return entity;
    }

    public static Articulo fromEntity(ArticuloEntity entity) {
        Articulo art = null;
        if (entity != null) {
            art = new Articulo();
            art.setId(entity.getId());
            art.setCodigoBarras(entity.getCodigoBarras());
            art.setDescripcion(entity.getDescripcion());
            art.setPresentacion(entity.getPresentacion());
            art.setUnidad(entity.getUnidad());
            art.setPrecio(entity.getPrecio());
            art.setCantCompra(entity.getCantCompra());
            art.setStock(Stock.fromEntity(entity.getStock()));
            if (entity.getLotes() != null) {
                List<Lote> lotes = new ArrayList<>();
                for (LoteEntity le : entity.getLotes()) {
                    lotes.add(Lote.fromEntity(le));
                }
                art.setLotes(lotes);
            }
            art.setVolumen(entity.getVolumen());
            if (entity.getOrdenesCompra() != null) {
                List<OrdenCompra> ordenesCompra = new ArrayList<>();
                for (OrdenCompraEntity oce : entity.getOrdenesCompra()) {
                    ordenesCompra.add(OrdenCompra.fromEntity(oce));
                }
                art.setOrdenesCompra(ordenesCompra);
            }
        }
        return art;
    }
}
