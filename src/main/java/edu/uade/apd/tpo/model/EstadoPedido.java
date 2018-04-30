package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.EstadoPedidoStub;

public enum EstadoPedido {

    INICIADO,
    APROBADO,
    RECHAZADO,
    PENDIENTE,
    COMPLETO,
    ENVIADO;

    public static EstadoPedido fromStub(EstadoPedidoStub stub) {
        return EstadoPedido.valueOf(stub.name());
    }

}
