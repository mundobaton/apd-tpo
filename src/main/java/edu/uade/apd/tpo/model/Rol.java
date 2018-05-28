package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.RolStub;

public enum Rol {
    TODOS,
    ADMINISTRACION,
    DEPOSITO,
    DESPACHO,
    FACTURACION,
    COMPRAS,
    CLIENTE;


    public static Rol fromStub(RolStub stub) {
        return Rol.valueOf(stub.name());
    }

    public RolStub toStub() {
        return RolStub.valueOf(name());
    }
}
