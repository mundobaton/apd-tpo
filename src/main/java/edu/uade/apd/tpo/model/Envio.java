package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.DomicilioEntity;
import edu.uade.apd.tpo.entity.RemitoEntity;

public class Envio {

	private Long id;
	private Domicilio domicilio;
	private Transportista transportista;
	private Remito remito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Domicilio getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public Remito getRemito() {
		return remito;
	}

	public void setRemito(Remito remito) {
		this.remito = remito;
	}

	public void guardar() {

	}

	public float calcular() {
		return 0;
	}

}
