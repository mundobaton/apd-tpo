package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.RolStub;
import edu.uade.apd.tpo.repository.stub.ZonaStub;

public enum Zona {
    CABA(3),
    NORTE(6),
    SUR(4),
    OESTE(5);

    private float precio;

    Zona(float precio) {
        this.precio = precio;
    }

    public float getPrecio() {
        return this.precio;
    }

    public static Zona fromStub(ZonaStub stub) {
        return Zona.valueOf(stub.name());
    }

    public ZonaStub toStub() {
        return ZonaStub.valueOf(name());
    }
}
