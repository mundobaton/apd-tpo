package edu.uade.apd.tpo.entity;

import java.util.Date;

import edu.uade.apd.tpo.model.EstadoCompra;

public class OrdenCompraEntity {
	private Long id;
	private ArticuloEntity articulo;
	private EstadoCompra estado;
	private ProveedorEntity proveedor;
	private Date fecha;
	private PedidoEntity pedido;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArticuloEntity getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}
	public EstadoCompra getEstado() {
		return estado;
	}
	public void setEstado(EstadoCompra estado) {
		this.estado = estado;
	}
	public ProveedorEntity getProveedor() {
		return proveedor;
	}
	public void setProveedor(ProveedorEntity proveedor) {
		this.proveedor = proveedor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public PedidoEntity getPedido() {
		return pedido;
	}
	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}
	
	
}
