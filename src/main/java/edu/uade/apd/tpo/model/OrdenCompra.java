package edu.uade.apd.tpo.model;

import java.util.Date;
<<<<<<< HEAD

import edu.uade.apd.tpo.dao.OrdenCompraDao;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.repository.stub.OrdenCompraStub;

public class OrdenCompra {
=======
import java.util.List;

import edu.uade.apd.tpo.dao.impl.OrdenCompraDao;

public class OrdenCompra {

>>>>>>> develop
    private Long id;
    private Articulo articulo;
    private EstadoCompra estado;
    private Proveedor proveedor;
    private Date fecha;
<<<<<<< HEAD
    private Pedido pedido;
=======
    private List<ItemLote> itemLotes;
>>>>>>> develop

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
<<<<<<< HEAD
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void guardar() {
        OrdenCompraDao.getInstance().save(this.toEntity());
    }

    public static OrdenCompra fromEntity(OrdenCompraEntity entity) {
        OrdenCompra oc = null;
        if (entity != null) {
            oc = new OrdenCompra();
            oc.setId(entity.getId());
            oc.setFecha(entity.getFecha());
            oc.setEstado(entity.getEstado());
            oc.setArticulo(Articulo.fromEntity(entity.getArticulo()));
            oc.setProveedor(Proveedor.fromEntity(entity.getProveedor()));
            oc.setPedido(Pedido.fromEntity(entity.getPedido()));
        }
        return oc;
    }

    public static OrdenCompra fromStub(OrdenCompraStub stub) {
        OrdenCompra oc = null;
        if (stub != null) {
            oc = new OrdenCompra();
            oc.setId(stub.getId());
            oc.setFecha(stub.getFecha());
            oc.setEstado(EstadoCompra.fromStub(stub.getEstado()));
            oc.setArticulo(Articulo.fromStub(stub.getArticulo()));
            oc.setProveedor(Proveedor.fromStub(stub.getProveedor()));
            oc.setPedido(Pedido.fromStub(stub.getPedido()));
        }
        return oc;
    }

    public OrdenCompraEntity toEntity() {
        OrdenCompraEntity entity = new OrdenCompraEntity();
        entity.setId(id);
        entity.setFecha(fecha);
        entity.setEstado(estado);
        entity.setArticulo(articulo != null ? articulo.toEntity() : null);
        entity.setProveedor(proveedor != null ? proveedor.toEntity() : null);
        entity.setPedido(pedido != null ? pedido.toEntity() : null);
        return entity;
    }

    public OrdenCompraStub toStub() {
        OrdenCompraStub stub = new OrdenCompraStub();
        stub.setId(id);
        stub.setFecha(fecha);
        stub.setEstado(estado.toStub());
        stub.setArticulo(articulo != null ? articulo.toStub() : null);
        stub.setProveedor(proveedor != null ? proveedor.toStub() : null);
        stub.setPedido(pedido != null ? pedido.toStub() : null);
        return stub;
    }

=======
    	return fecha;
    }
    
    public void setFecha(Date date) {
    	this.fecha = date;
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
>>>>>>> develop
}
