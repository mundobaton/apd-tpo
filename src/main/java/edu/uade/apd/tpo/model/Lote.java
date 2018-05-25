package edu.uade.apd.tpo.model;

import java.util.Date;
import java.util.List;

public class Lote {
	private Long id;
	private String codigo;
	private Date fechaVto;
	private Date elaboracion;
	private Articulo articulo;
	private List<Posicion> posiciones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(Date fechaVto) {
		this.fechaVto = fechaVto;
	}

	public Date getElaboracion() {
		return elaboracion;
	}

	public void setElaboracion(Date elaboracion) {
		this.elaboracion = elaboracion;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public List<Posicion> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

	public void guardar() {

	}

}
