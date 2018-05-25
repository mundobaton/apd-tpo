package edu.uade.apd.tpo.model;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class Pedido {

	private Long id;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Date fechaDespacho;
	private List<ItemPedido> items;
	private List<Estado> estados;
	private Usuario cliente;
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
		return fechaDespacho;
	}

	public void setFechaDepacho(Date fechaDepacho) {
		this.fechaDespacho = fechaDepacho;
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

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
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

	public void iniciar() {
		Estado iniciar = new Estado();
		iniciar.setEstado(EstadoPedido.INICIADO);
		iniciar.setFecha(new Date());
		this.setFechaPedido(new Date());
		this.estados.add(iniciar);

	}

	public void agregarItem(Articulo articulo, int cantidad) {

	}

	public void cerrar() {
		Estado cerrar = new Estado();
		cerrar.setEstado(EstadoPedido.INICIADO);
		cerrar.setFecha(new Date());
		this.estados.add(cerrar);

	}

	public void aprobar() {
		Estado aprobado = new Estado();
		aprobado.setMotivo("Aprobación del pedido");
		aprobado.setEstado(EstadoPedido.APROBADO);
		aprobado.setFecha(new Date());
		this.estados.add(aprobado);
	}

	public void rechazar(String motivo) {
		Estado rechazado = new Estado();
		rechazado.setEstado(EstadoPedido.RECHAZADO);
		rechazado.setFecha(new Date());
		rechazado.setMotivo(motivo);
		this.estados.add(rechazado);
	}

	public void completar() {
		Estado completo = new Estado();
		completo.setEstado(EstadoPedido.COPMPLETO);
		completo.setFecha(new Date());
		this.estados.add(completo);

	}

	public void marcarPendiente() {
		Estado pendiente = new Estado();
		pendiente.setEstado(EstadoPedido.PENDIENTE);
		pendiente.setFecha(new Date());
		this.estados.add(pendiente);

	}
	
	public void pedidoListo(Remito remito) {
		Estado listo = new Estado();
		listo.setFecha(new Date());
		listo.setEstado(EstadoPedido.LISTO);
		this.estados.add(listo);
		this.envio.setRemito(remito);
		this.envio.setTransportista(seleccionarTransportista());
	}
	
	private Transportista seleccionarTransportista() {
		Random random = new Random();
    	int index = random.nextInt(2);
    	return Transportista.values()[index];
	}

	public void guardar() {

	}

}
