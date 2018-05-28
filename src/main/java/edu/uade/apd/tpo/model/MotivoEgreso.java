package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.MotivoEgresoStub;

public enum MotivoEgreso {
    VENTA,
    VENCIMIENTO,
    RESERVA,
    DETERIORO,
    AJUSTE_INV,
    OTROS;

    public static MotivoEgreso fromStub(MotivoEgresoStub stub) {
        return MotivoEgreso.valueOf(stub.name());
    }

    public MotivoEgresoStub toStub() {
        return MotivoEgresoStub.valueOf(name());
    }
}
