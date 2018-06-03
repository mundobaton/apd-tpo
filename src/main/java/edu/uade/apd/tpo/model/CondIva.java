package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.CondIvaStub;

public enum CondIva {

    RESP_INSCRIPTO,
    EXENTO,
    CONS_FINAL;

    public static CondIva fromStub(CondIvaStub stub) {
        return CondIva.valueOf(stub.name());
    }

}
