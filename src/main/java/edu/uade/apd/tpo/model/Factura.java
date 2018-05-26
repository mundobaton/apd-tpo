package edu.uade.apd.tpo.model;

import java.util.Date;

public class Factura {

	private Long id;
	private Date fecha;
	private FacturaTipo tipo;
	private Pedido pedido;
	private float costoEnvio;
	private static float IMPUESTOS = 0.21f;
	private float total;
	private Transaccion transaccion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public FacturaTipo getTipo() {
		return tipo;
	}

	public void setTipo(FacturaTipo tipo) {
		this.tipo = tipo;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public float getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(float costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public void guardar() {

	}

	public static float getIMPUESTOS() {
		return IMPUESTOS;
	}

	public static void setIMPUESTOS(float iMPUESTOS) {
		IMPUESTOS = iMPUESTOS;
	}

}
