package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.ItemLoteEntity;

public class ItemLote {
	private Long id;
	private Lote lote;
	private int cantidad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void guardar() {

	}

	public static ItemLote fromEntity(ItemLoteEntity entity) {
		return null;
	}

	public ItemLoteEntity toEntity() {
		return null;
	}

}
