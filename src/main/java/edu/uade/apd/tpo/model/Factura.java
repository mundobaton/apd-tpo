package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.FacturaEntity;

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

	public static Factura fromEntity(FacturaEntity entity) {
		Factura f = null;
		if(entity != null){
			f = new Factura();
			f.setId(entity.getId());
			f.setFecha(entity.getFecha());
			f.setTipo(entity.getTipo());
			f.setPedido(Pedido.fromEntity(entity.getPedido(), Cliente.fromEntity(entity.getPedido().getCliente())));
			f.setCostoEnvio(entity.getCostoEnvio());
			f.setTotal(entity.getTotal());
			f.setTransaccion(Transaccion.fromEntity(entity.getTransaccion()));
		}
		return f;
	}

	public FacturaEntity toEntity(){
		FacturaEntity entity = new FacturaEntity();
		entity.setId(id);
		entity.setFecha(fecha);
		entity.setTipo(tipo);
		entity.setPedido(pedido.toEntity());
		entity.setCostoEnvio(costoEnvio);
		entity.setTotal(total);
		entity.setTransaccion(transaccion.toEntity());
		return entity;
	}
}
