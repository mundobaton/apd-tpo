package edu.uade.apd.tpo.model;

import java.util.List;

public class Stock {
	private Long id;
	private List<Movimiento> movimientos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	
}
