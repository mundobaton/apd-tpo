package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.EstadoPedidoStub;

public enum EstadoPedido {

    INICIADO,
    APROBADO,
    RECHAZADO,
    PENDIENTE,
    COMPLETO,
    NO_INICIADO,
    ENVIADO;

    public static EstadoPedido fromStub(EstadoPedidoStub stub) {
        return EstadoPedido.valueOf(stub.name());
    }

}
