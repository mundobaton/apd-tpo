package edu.uade.apd.tpo.model;

import java.util.Date;
import java.util.List;

public class Pedido {
	
	private Long id;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Date fechaDepacho;
	private List<ItemPedido> items;
	private List<Estado> estados;
	private Cliente cliente;
	private Factura factura;
	private Envio envio;
	
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
	public List<ItemPedido> getItems() {
		return items;
	}
	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Envio getEnvio() {
		return envio;
	}
	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
	
	

}
