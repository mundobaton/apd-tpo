package edu.uade.apd.tpo.entity;

import edu.uade.apd.tpo.model.EstadoPosicion;

public class PosicionEntity {
	private Long id;
	private String codUbicacion;
	private EstadoPosicion estado;
	private LoteEntity lote;
	private int cantidad;
	private char calle;
	private int bloque;
	private int estanteria;
	private int estante;
	private int numero;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodUbicacion() {
		return codUbicacion;
	}
	public void setCodUbicacion(String codUbicacion) {
		this.codUbicacion = codUbicacion;
	}
	public EstadoPosicion getEstado() {
		return estado;
	}
	public void setEstado(EstadoPosicion estado) {
		this.estado = estado;
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
	public char getCalle() {
		return calle;
	}
	public void setCalle(char calle) {
		this.calle = calle;
	}
	public int getBloque() {
		return bloque;
	}
	public void setBloque(int bloque) {
		this.bloque = bloque;
	}
	public int getEstanteria() {
		return estanteria;
	}
	public void setEstanteria(int estanteria) {
		this.estanteria = estanteria;
	}
	public int getEstante() {
		return estante;
	}
	public void setEstante(int estante) {
		this.estante = estante;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
}
