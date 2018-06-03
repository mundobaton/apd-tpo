package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.ZonaEnvioStub;

public enum ZonaEnvio {

	CABA (3), 
	NORTE (6), 
	SUR (4), 
	OESTE  (5);

	private float precio;
	
	ZonaEnvio (float precio){
		this.precio = precio;
	}
	
	public float getPrecio() {
		return this.precio;
	}
	
	public static ZonaEnvio fromStub(ZonaEnvioStub stub) {
		return ZonaEnvio.valueOf(stub.name());
	}

}
