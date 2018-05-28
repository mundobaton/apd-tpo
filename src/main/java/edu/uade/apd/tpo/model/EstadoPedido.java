package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.EstadoPedidoStub;

public enum EstadoPedido {
	INICIADO,
	PREAPROBADO,
	EN_REVISION,
	APROBADO,
	RECHAZADO,
	PENDIENTE,
	VERIFICADO,
	COMPLETO,
	LISTO,
	ENVIADO;

	public static EstadoPedido fromStub(EstadoPedidoStub stub) {
		return EstadoPedido.valueOf(stub.name());
	}

	public EstadoPedidoStub toStub() {
		return EstadoPedidoStub.valueOf(name());
	}
}
