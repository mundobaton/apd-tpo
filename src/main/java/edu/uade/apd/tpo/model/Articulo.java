package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.LoteEntity;
import edu.uade.apd.tpo.repository.stub.ArticuloStub;
import edu.uade.apd.tpo.repository.stub.LoteStub;

import java.util.ArrayList;
import java.util.List;

public class Articulo {
    private Long id;
    private String codBarras;
    private String descripcion;
    private String presentacion;
    private String unidad;
=======
import edu.uade.apd.tpo.dao.impl.ArticuloDao;

import java.util.List;

import edu.uade.apd.tpo.dao.impl.ArticuloDao;

public class Articulo {

    private Long id;
    private String codigoBarras;
    private String descripcion;
    private String presentacion;
    private String unidad;
    private float precio;
>>>>>>> develop
    private int cantCompra;
    private Stock stock;
    private List<Lote> lotes;
    private int volumen;
<<<<<<< HEAD
    private float precio;
=======
    private List<OrdenCompra> ordenesCompra;
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
=======
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

>>>>>>> develop
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

<<<<<<< HEAD
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

    public static Articulo fromStub(ArticuloStub stub) {
        Articulo articulo = null;
        if (stub != null) {
            articulo = new Articulo();
            articulo.setId(stub.getId());
            articulo.setCodBarras(stub.getCodBarras());
            articulo.setDescripcion(stub.getDescripcion());
            articulo.setPresentacion(stub.getPresentacion());
            articulo.setUnidad(stub.getUnidad());
            articulo.setPrecio(stub.getPrecio());
            articulo.setCantCompra(stub.getCantCompra());
            articulo.setStock(Stock.fromStub(stub.getStock()));
            if (stub.getLotes() != null) {
                articulo.setLotes(new ArrayList<>());
                for (LoteStub le : stub.getLotes()) {
                    articulo.getLotes().add(Lote.fromStub(le));
                }
            }
            articulo.setVolumen(stub.getVolumen());
        }
        return articulo;
    }

    public ArticuloStub toStub() {
        ArticuloStub stub = new ArticuloStub();
        stub.setId(id);
        stub.setCodBarras(codBarras);
        stub.setDescripcion(descripcion);
        stub.setPresentacion(presentacion);
        stub.setUnidad(unidad);
        stub.setPrecio(precio);
        stub.setCantCompra(cantCompra);
        stub.setStock(stock != null ? stock.toStub() : null);
        if (lotes != null) {
            stub.setLotes(new ArrayList<>());
            for (Lote lote : lotes) {
                stub.getLotes().add(lote.toStub());
            }
        }
        stub.setVolumen(volumen);
        return stub;
=======
    public List<OrdenCompra> getOrdenesCompra() {
        return ordenesCompra;
    }

    public void setOrdenesCompra(List<OrdenCompra> ordenesCompra) {
        this.ordenesCompra = ordenesCompra;
    }

    public void guardar(){
        ArticuloDao.getInstance().save(this);
>>>>>>> develop
    }
}
