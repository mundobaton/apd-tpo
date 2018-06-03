package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.repository.stub.BaseStub;

public interface Stubeable<T extends BaseStub> {

    T toStub();

}
