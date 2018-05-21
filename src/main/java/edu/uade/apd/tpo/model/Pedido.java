package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.dao.impl.PedidoDao;
import edu.uade.apd.tpo.exception.ArticulosFaltantesException;
import edu.uade.apd.tpo.remote.TransformUtils;
import edu.uade.apd.tpo.repository.stub.PedidoStub;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Pedido {

	private Long id;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Date fechaDespacho;
	private List<ItemPedido> items;
	private Cliente cliente;
	private Domicilio domicilio;
	private List<Estado> estados;

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

	public Date getFechaDespacho() {
		return fechaDespacho;
	}

	public void setFechaDespacho(Date fechaDespacho) {
		this.fechaDespacho = fechaDespacho;
	}

	public List<ItemPedido> getItems() {
		return items;
	}

	public void setItems(List<ItemPedido> items) {
		this.items = items;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public void addEstado(Estado estado) {
		if (this.estados == null) {
			estados = new ArrayList<>();
		}
		this.estados.add(estado);
	}

	public void addItem(ItemPedido itemPedido) {
		if (this.items == null) {
			items = new ArrayList<>();
		}
		this.items.add(itemPedido);
	}

	public void guardar() {
		PedidoDao.getInstance().save(this);
	}

	public void aprobar() throws ArticulosFaltantesException {
		Estado e = new Estado();
		e.setMotivo("Aprobación del pedido");
		e.setEstado(EstadoPedido.APROBADO);
		e.setFecha(new Date());
		this.addEstado(e);
		SistemaDeposito depo = SistemaDeposito.getInstance();
		Iterator<ItemPedido> it = this.getItems().iterator();
		while (it.hasNext()) {
			ItemPedido ip = it.next();
			ip.setLotes(depo.obtenerLotesPorArticulo(ip.getArticulo().getId(), ip.getCantidad()));
			if (!ip.esCompleto()) {
				throw new ArticulosFaltantesException(ip.getArticulo(), ip.getCantidad());
			}
		}
		e = new Estado();
		e.setMotivo("Pedido completo");
		e.setEstado(EstadoPedido.COMPLETO);
		e.setFecha(new Date());
		this.addEstado(e);
		guardar();
	}

	public void rechazar(String motivo) {
		Estado e = new Estado();
		e.setMotivo(motivo);
		e.setEstado(EstadoPedido.RECHAZADO);
		e.setFecha(new Date());
		this.addEstado(e);
	}

	public void cerrar() {
		Estado e = new Estado();
		e.setMotivo("Cierre del pedido");
		e.setEstado(EstadoPedido.PENDIENTE);
		e.setFecha(new Date());
		this.addEstado(e);
	}

	public void iniciar() {
		Estado estado = new Estado();
		estado.setMotivo("Creacion del pedido");
		estado.setEstado(EstadoPedido.INICIADO);
		estado.setFecha(new Date());
		this.addEstado(estado);
	}

	public void agregarItem(Articulo articulo, int cantidad) {
		ItemPedido item = new ItemPedido();
		item.setArticulo(articulo);
		item.setCantidad(cantidad);
		addItem(item);
	}

	public EstadoPedido getEstado() {
		return this.estados == null ? EstadoPedido.NO_INICIADO : this.estados.get(this.estados.size() - 1).getEstado();
	}

	public void marcarPendiente() {
		cerrar();
	}

	public PedidoStub toStub() {
		return TransformUtils.to(this, PedidoStub.class);
	}

}
