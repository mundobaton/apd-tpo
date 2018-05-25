package edu.uade.apd.tpo.entity;

import java.util.Date;

import edu.uade.apd.tpo.model.EstadoPedido;

public class EstadoEntity {
	
	private Long id;
	private EstadoPedido estado;
	private Date fecha;
	private String motivo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EstadoPedido getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
