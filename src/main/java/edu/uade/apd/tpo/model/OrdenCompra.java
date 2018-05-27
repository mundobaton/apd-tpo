package edu.uade.apd.tpo.model;

import java.util.Date;

import edu.uade.apd.tpo.dao.OrdenCompraDao;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;

public class OrdenCompra {
	private Long id;
	private Articulo articulo;
	private EstadoCompra estado;
	private Proveedor proveedor;
	private Date fecha;
	private Pedido pedido;

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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void guardar() {
		OrdenCompraDao.getInstance().save(this);
	}

	public static OrdenCompra fromEntity(OrdenCompraEntity entity) {
		OrdenCompra oc = null;
		if(entity != null){
			oc = new OrdenCompra();
			oc.setId(entity.getId());
			oc.setFecha(entity.getFecha());
			oc.setEstado(entity.getEstado());
			oc.setArticulo(Articulo.fromEntity(entity.getArticulo()));
			oc.setProveedor(Proveedor.fromEntity(entity.getProveedor()));
			oc.setPedido(Pedido.fromEntity(entity.getPedido(), Cliente.fromEntity(entity.getPedido().getCliente())));
		}
		return oc;
	}

	public OrdenCompraEntity toEntity(){
		OrdenCompraEntity entity = new OrdenCompraEntity();
		entity.setId(id);
		entity.setFecha(fecha);
		entity.setEstado(estado);
		entity.setArticulo(articulo.toEntity());
		entity.setProveedor(proveedor.toEntity());
		entity.setPedido(pedido.toEntity());
		return entity;
	}

}
