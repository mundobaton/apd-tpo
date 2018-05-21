package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.OrdenCompraDao;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;

import java.util.Date;

public class OrdenCompra {

    private Integer id;
    private Articulo articulo;
    private EstadoCompra estado;
    private Proveedor proveedor;
    private Date fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public EstadoCompra getEstado() {
        return estado;
    }

    public void setEstado(EstadoCompra estado) {
        this.estado = estado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public OrdenCompra(Articulo art) {
        this.articulo = art;
        this.estado = EstadoCompra.PENDIENTE;
    }

    public OrdenCompra() {
    }

    public OrdenCompra guardar() {
        return OrdenCompra.fromEntity(OrdenCompraDao.getInstance().save(this.toEntity()));
    }

    public OrdenCompraEntity toEntity() {
        OrdenCompraEntity entity = new OrdenCompraEntity();
        entity.setId(id);
        entity.setArticulo(articulo != null ? articulo.toEntity() : null);
        entity.setEstado(estado);
        entity.setProveedor(proveedor != null ? proveedor.toEntity() : null);
        entity.setFecha(fecha);
        return entity;
    }

    public static OrdenCompra fromEntity(OrdenCompraEntity entity) {
        OrdenCompra oc = null;
        if (entity != null) {
            oc = new OrdenCompra();
            oc.setId(entity.getId());
            oc.setArticulo(Articulo.fromEntity(entity.getArticulo()));
            oc.setEstado(entity.getEstado());
            oc.setProveedor(Proveedor.fromEntity(entity.getProveedor()));
            oc.setFecha(entity.getFecha());
        }
        return oc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void ejecutar() {
        this.estado = EstadoCompra.COMPLETO;
    }
}