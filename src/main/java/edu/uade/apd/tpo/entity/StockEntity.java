package edu.uade.apd.tpo.entity;

import java.util.List;

public class StockEntity {
	private Long id;
	private List<MovimientoEntity> movimientos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<MovimientoEntity> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(List<MovimientoEntity> movimientos) {
		this.movimientos = movimientos;
	}
	
	
}
