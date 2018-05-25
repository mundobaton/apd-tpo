package edu.uade.apd.tpo.entity;

import java.util.Date;

import edu.uade.apd.tpo.model.FacturaTipo;

public class FacturaEntity {
	
	private Long id;
	private Date fecha;
	private FacturaTipo tipo;
	private PedidoEntity pedido;
	private float costoEnvio;
	private float impuesto;
	private float total;
	private TransaccionEntity transaccion;
	
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
	public PedidoEntity getPedido() {
		return pedido;
	}
	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}
	public float getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(float costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	public float getImpuesto() {
		return impuesto;
	}
	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public TransaccionEntity getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(TransaccionEntity transaccion) {
		this.transaccion = transaccion;
	}
	
	

}
