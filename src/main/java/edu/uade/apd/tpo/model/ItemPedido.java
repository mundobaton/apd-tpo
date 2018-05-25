package edu.uade.apd.tpo.model;

import java.util.List;

public class ItemPedido {
	
	private Long id;
	private Articulo articulo;
	private int cantidad;
	private float subTotal;
	private List<ItemLote> lotes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	public List<ItemLote> getLotes() {
		return lotes;
	}
	public void setLotes(List<ItemLote> lotes) {
		this.lotes = lotes;
	} 
	
	
}
