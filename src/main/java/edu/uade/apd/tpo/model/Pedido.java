package edu.uade.apd.tpo.model;

import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.uade.apd.tpo.dao.PedidoDao;

public class Pedido {

	private Long id;
	private Date fechaPedido;
	private Date fechaEntrega;
	private Date fechaDespacho;
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

	public void iniciar() {
		Estado iniciar = new Estado();
		iniciar.setEstado(EstadoPedido.INICIADO);
		iniciar.setFecha(new Date());
		iniciar.setMotivo(null);
		this.setFechaPedido(new Date());
		this.estados.add(iniciar);
	}

	public void agregarItem(Articulo articulo, int cantidad) {
		int i = 0;
		boolean temp = false;
		ItemPedido items = new ItemPedido();
		for(ItemPedido item : this.items) {
			if(item.getArticulo().getId() == articulo.getId()) {
				this.items.get(i).actualizar(cantidad);
				temp = true;
			}
			i++;
		}
		if(!temp) {
			items.setArticulo(articulo);
			items.setCantidad(cantidad);
			this.items.add(items);
		}
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
	
	public void preAprobar() {
		Estado preAprobar = new Estado();
		preAprobar.setEstado(EstadoPedido.PREAPROBADO);
		preAprobar.setFecha(new Date());
		this.estados.add(preAprobar);
	}
	
	public void revision() {
		Estado revision = new Estado();
		revision.setEstado(EstadoPedido.EN_REVISION);
		revision.setFecha(new Date());
		this.estados.add(revision);
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
	
	public void verificado() {
		Estado verificado = new Estado();
		verificado.setEstado(EstadoPedido.VERIFICADO);
		verificado.setFecha(new Date());
		this.estados.add(verificado);

	}

	public void marcarPendiente() {
		Estado pendiente = new Estado();
		pendiente.setEstado(EstadoPedido.PENDIENTE);
		pendiente.setFecha(new Date());
		this.estados.add(pendiente);

	}
	
	public void pedidoListo(Remito remito, Transportista transportista) {
		Estado listo = new Estado();
		listo.setFecha(new Date());
		listo.setEstado(EstadoPedido.LISTO);
		this.estados.add(listo);
		this.envio.setRemito(remito);
		this.envio.setTransportista(transportista);
	}
	
	public float obtenerTotal() {
		float total = 0;
		for(ItemPedido item : this.items){
			total = total + item.getSubTotal(); 
		}
		return total;
	}

	public void guardar() {
		PedidoDao.getInstance().save(this);
	}
	
	public float calcularCostoEnvio() {
		return envio.calcular();
	}

}
