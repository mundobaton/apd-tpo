package edu.uade.apd.tpo.entity;

import java.io.Serializable;
import java.util.List;


public class ArticuloEntity implements Serializable {
	
	private Long id;
	private String codBarras;
	private String descripcion;
	private String presentacion;
	private String unidad;
	private int cantCompra;
	private StockEntity stock;
	private List<LoteEntity> lotes;
	private int volumen;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public int getCantCompra() {
		return cantCompra;
	}
	public void setCantCompra(int cantCompra) {
		this.cantCompra = cantCompra;
	}
	public StockEntity getStock() {
		return stock;
	}
	public void setStock(StockEntity stock) {
		this.stock = stock;
	}
	public List<LoteEntity> getLotes() {
		return lotes;
	}
	public void setLotes(List<LoteEntity> lotes) {
		this.lotes = lotes;
	}
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	
	
	
	
}
