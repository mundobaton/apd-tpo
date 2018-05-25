package edu.uade.apd.tpo.model;

import java.util.Date;

public abstract class Movimiento {
	protected Long id;
	protected Date fecha;
	protected int cantidad;

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

	public int obtenerCantidad() {
		return cantidad;
	}
}
