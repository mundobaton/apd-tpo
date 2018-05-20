package edu.uade.apd.tpo.model;

public enum Transportista {

    OCA (20),
    CORREO_ARGENTINO (25),
    ANDREANI (28);

    private float precio;
	
	Transportista(float precio) {
		this.precio = precio;
	}
	
	public float getPrecio() {
		return this.precio;
	}
}
