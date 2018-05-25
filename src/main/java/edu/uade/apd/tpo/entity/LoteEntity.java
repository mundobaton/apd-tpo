package edu.uade.apd.tpo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LoteEntity implements Serializable {
	private Long id;
	private String codigo;
	private Date fechaVto;
	private Date elaboracion;
	private ArticuloEntity articulo;
	private List<PosicionEntity> posiciones;
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
	public ArticuloEntity getArticulo() {
		return articulo;
	}
	public void setArticulo(ArticuloEntity articulo) {
		this.articulo = articulo;
	}
	public List<PosicionEntity> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<PosicionEntity> posiciones) {
		this.posiciones = posiciones;
	}
	
	
}
