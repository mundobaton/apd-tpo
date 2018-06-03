package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.repository.stub.EstadoPosicionStub;

public enum EstadoPosicion {
    DISPONIBLE,
    OCUPADO;

    public static EstadoPosicion fromStub(EstadoPosicionStub stub) {
        return EstadoPosicion.valueOf(stub.name());
    }

    public EstadoPosicionStub toStub() {
        return EstadoPosicionStub.valueOf(name());
    }
=======
public enum EstadoPosicion {

    DISPONIBLE,
    OCUPADO

>>>>>>> develop
}
