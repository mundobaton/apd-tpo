package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.repository.stub.MedioPagoStub;

public enum MedioPago {
	EFECTIVO,
	TARJETA,
	CHEQUE;

	public static MedioPago fromStub(MedioPagoStub stub) {
		return MedioPago.valueOf(stub.name());
	}

	public MedioPagoStub toStub() {
		return MedioPagoStub.valueOf(name());
	}
=======
public enum MedioPago {

    EFECTIVO,
    TARJETA,
    CHEQUE

>>>>>>> develop
}
