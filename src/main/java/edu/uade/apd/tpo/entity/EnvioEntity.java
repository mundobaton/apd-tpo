package edu.uade.apd.tpo.entity;

import java.io.Serializable;

import edu.uade.apd.tpo.model.Transportista;

public class EnvioEntity implements Serializable {
	
	private Long id;
	private DomicilioEntity domicilio;
	private Transportista transportista;
	private RemitoEntity remito;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DomicilioEntity getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(DomicilioEntity domicilio) {
		this.domicilio = domicilio;
	}
	public Transportista getTransportista() {
		return transportista;
	}
	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}
	public RemitoEntity getRemito() {
		return remito;
	}
	public void setRemito(RemitoEntity remito) {
		this.remito = remito;
	}
	
	
}
