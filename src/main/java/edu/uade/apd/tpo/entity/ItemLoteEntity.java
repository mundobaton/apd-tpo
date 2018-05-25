package edu.uade.apd.tpo.entity;

import java.io.Serializable;

public class ItemLoteEntity implements Serializable {
	private Long id;
	private LoteEntity lote;
	private int cantidad;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LoteEntity getLote() {
		return lote;
	}
	public void setLote(LoteEntity lote) {
		this.lote = lote;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
