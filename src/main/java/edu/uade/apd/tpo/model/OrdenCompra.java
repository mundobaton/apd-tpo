package edu.uade.apd.tpo.model;

import java.sql.Date;
import java.util.List;

import edu.uade.apd.tpo.dao.impl.OrdenCompraDao;

public class OrdenCompra {

    private Long id;
    private Articulo articulo;
    private EstadoCompra estado;
    private Proveedor proveedor;
    private Date fecha;
    private List<ItemLote> itemLotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getFecha() {
    	return fecha;
    }
    
    public void setFecha(Date fecha) {
    	this.fecha = fecha;
    }

	public List<ItemLote> getItemLotes() {
		return itemLotes;
	}

	public void setItemLotes(List<ItemLote> itemLotes) {
		this.itemLotes = itemLotes;
	}
    
    public OrdenCompra(Articulo art) {
        this.articulo = art;
        this.estado = EstadoCompra.PENDIENTE;
    }

    public OrdenCompra() {
    }

    public void guardar() {
        OrdenCompraDao.getInstance().save(this);
    }
}
