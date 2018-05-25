package edu.uade.apd.tpo.entity;

import java.util.List;

public class ItemPedidoEntity {
	
	private Long id;
	private ArticuloEntity articulo;
	private int cantidad;
	private float subTotal;
	private List<ItemLoteEntity> lotes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ArticuloEntity getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloEntity articulo) {
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
	public List<ItemLoteEntity> getLotes() {
		return lotes;
	}
	public void setLotes(List<ItemLoteEntity> lotes) {
		this.lotes = lotes;
	} 
	
	
}
