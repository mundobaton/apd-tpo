package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.RolStub;

public enum Rol {
<<<<<<< HEAD
    TODOS,
    ADMINISTRACION,
    DEPOSITO,
    DESPACHO,
    FACTURACION,
    COMPRAS,
    CLIENTE;

=======

    TODOS, ADMINISTRACION, DEPOSITO, DESPACHO, FACTURACION, COMPRAS, CLIENTE;
>>>>>>> develop

    public static Rol fromStub(RolStub stub) {
        return Rol.valueOf(stub.name());
    }

<<<<<<< HEAD
    public RolStub toStub() {
        return RolStub.valueOf(name());
    }
=======
>>>>>>> develop
}
