package edu.uade.apd.tpo.model;

public enum ZonaEnvio {

    CABA(3),
    NORTE(6),
    SUR(4),
    OESTE(5);

    private float precio;

    ZonaEnvio(float precio) {
        this.precio = precio;
    }

    public float getPrecio() {
        return this.precio;
    }
}
