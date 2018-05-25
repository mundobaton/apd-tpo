package edu.uade.apd.tpo.entity;

import java.io.Serializable;
import java.util.Date;

public class RemitoEntity implements Serializable {
	
	private Long id;
	private Date fecha;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	

}
