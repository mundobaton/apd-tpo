package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.FacturaTipoStub;

public enum FacturaTipo {
    A,
    B;

    public static FacturaTipo fromStub(FacturaTipoStub stub) {
        return FacturaTipo.valueOf(stub.name());
    }

    public FacturaTipoStub toStub() {
        return FacturaTipoStub.valueOf(name());
    }
}
