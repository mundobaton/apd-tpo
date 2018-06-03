package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.repository.stub.MotivoIngresoStub;

public enum MotivoIngreso {
    COMPRA,
    AJUSTE_INV,
    LIBERA_RESERVA,
    OTROS;

    public static MotivoIngreso fromStub(MotivoIngresoStub stub) {
        return MotivoIngreso.valueOf(stub.name());
    }

    public MotivoIngresoStub toStub() {
        return MotivoIngresoStub.valueOf(name());
    }
=======
public enum MotivoIngreso {

    COMPRA,
    AJUSTE_INV,
    OTROS

>>>>>>> develop
}
