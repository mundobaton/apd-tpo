package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.EstadoCompraStub;

public enum EstadoCompra {
	PENDIENTE,
	EMITIDA,
	ACEPTADA;

	public static EstadoCompra fromStub(EstadoCompraStub stub) {
		return EstadoCompra.valueOf(stub.name());
	}

	public EstadoCompraStub toStub() {
		return EstadoCompraStub.valueOf(name());
	}
}
