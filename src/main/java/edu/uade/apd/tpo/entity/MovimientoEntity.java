package edu.uade.apd.tpo.entity;

import java.io.Serializable;
import java.util.Date;

public class MovimientoEntity implements Serializable {
	private Long id;
	private Date fecha;
	private int cantidad;
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
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
