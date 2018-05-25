package edu.uade.apd.tpo.entity;

import java.util.Date;
import java.util.List;

public class PedidoEntity {
	
	private Long id;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Date fechaDepacho;
	private List<ItemPedidoEntity> items;
	private List<EstadoEntity> estados;
	private ClienteEntity cliente;
	private EnvioEntity envio;
	private FacturaEntity factura;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Date getFechaDepacho() {
		return fechaDepacho;
	}
	public void setFechaDepacho(Date fechaDepacho) {
		this.fechaDepacho = fechaDepacho;
	}
	public List<ItemPedidoEntity> getItems() {
		return items;
	}
	public void setItems(List<ItemPedidoEntity> items) {
		this.items = items;
	}
	public List<EstadoEntity> getEstados() {
		return estados;
	}
	public void setEstados(List<EstadoEntity> estados) {
		this.estados = estados;
	}
	public ClienteEntity getCliente() {
		return cliente;
	}
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	public EnvioEntity getEnvio() {
		return envio;
	}
	public void setEnvio(EnvioEntity envio) {
		this.envio = envio;
	}
	public FacturaEntity getFactura() {
		return factura;
	}
	public void setFactura(FacturaEntity factura) {
		this.factura = factura;
	}
	
	
}
