package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;
import edu.uade.apd.tpo.repository.stub.ClienteStub;
import edu.uade.apd.tpo.repository.stub.DomicilioStub;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaAdministracionRemote extends UnicastRemoteObject implements SistemaAdministracionRepository {

    private static SistemaAdministracionRemote instance;
    private SistemaAdministracion controller;

    private SistemaAdministracionRemote(SistemaAdministracion controller) throws RemoteException {
        this.controller = controller;
    }

    public static SistemaAdministracionRemote getInstance(SistemaAdministracion controller) throws RemoteException {
        if (instance == null) {
            instance = new SistemaAdministracionRemote(controller);
        }
        return instance;
    }

    @Override
    public UsuarioStub login(String email, String password) throws RemoteException {
        return SerializationUtils.toStub(controller.login(email, password), UsuarioStub.class);
    }

    @Override
    public ClienteStub crearCliente(String email, String password, DomicilioStub dom) throws RemoteException {
        Domicilio domicilio = SerializationUtils.fromStub(dom, Domicilio.class);
        ClienteStub stub = SerializationUtils.toStub(controller.crearCliente(email, password, 123L, "un nombre", domicilio, CondIva.CONS_FINAL), ClienteStub.class);
        return stub;
    }
}
