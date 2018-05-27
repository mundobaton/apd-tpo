package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.LoteEntity;

import java.util.ArrayList;
import java.util.List;

public class Articulo {
    private Long id;
    private String codBarras;
    private String descripcion;
    private String presentacion;
    private String unidad;
    private int cantCompra;
    private Stock stock;
    private List<Lote> lotes;
    private int volumen;
    private float precio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void vender(int cantidad) {
        this.stock.agregarMovimientoEgreso(MotivoEgreso.VENTA, cantidad);

    }

    public void guardar() {
        ArticuloDao.getInstance().save(this.toEntity());
    }

    public static Articulo fromEntity(ArticuloEntity entity) {
        Articulo articulo = null;
        if (entity != null) {
            articulo = new Articulo();
            articulo.setId(entity.getId());
            articulo.setCodBarras(entity.getCodBarras());
            articulo.setDescripcion(entity.getDescripcion());
            articulo.setPresentacion(entity.getPresentacion());
            articulo.setUnidad(entity.getUnidad());
            articulo.setPrecio(entity.getPrecio());
            articulo.setCantCompra(entity.getCantCompra());
            articulo.setStock(Stock.fromEntity(entity.getStock()));
            if (entity.getLotes() != null) {
                articulo.setLotes(new ArrayList<>());
                for (LoteEntity le : entity.getLotes()) {
                    articulo.getLotes().add(Lote.fromEntity(le));
                }
            }
            articulo.setVolumen(entity.getVolumen());
        }
        return articulo;
    }

    public ArticuloEntity toEntity() {
        ArticuloEntity entity = new ArticuloEntity();
        entity.setId(id);
        entity.setCodBarras(codBarras);
        entity.setDescripcion(descripcion);
        entity.setPresentacion(presentacion);
        entity.setUnidad(unidad);
        entity.setPrecio(precio);
        entity.setCantCompra(cantCompra);
        entity.setStock(stock != null ? stock.toEntity() : null);
        if (lotes != null) {
            entity.setLotes(new ArrayList<>());
            for (Lote lote : lotes) {
                entity.getLotes().add(lote.toEntity());
            }
        }
        entity.setVolumen(volumen);
        return entity;
    }

}
