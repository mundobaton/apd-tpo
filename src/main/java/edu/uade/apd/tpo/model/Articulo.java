package edu.uade.apd.tpo.model;

import java.util.List;

public class Articulo {
	private Long id;
	private String codBarras;
	private String descripcion;
	private String presentacion;
	private String unidad;
	private int cantCompra;
	private Stock stock;
	private List<Lote> lotes;
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

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}

	public int getVolumen() {
		return volumen;
	}

	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	public void vender(int cantidad) {

	}

	public void guardar() {

	}

}
