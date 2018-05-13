package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.ZonaEnvioStub;

public enum ZonaEnvio {

    CABA,
    NORTE,
    SUR,
    OESTE;

    public static ZonaEnvio fromStub(ZonaEnvioStub stub) {
        return ZonaEnvio.valueOf(stub.name());
    }

    }
