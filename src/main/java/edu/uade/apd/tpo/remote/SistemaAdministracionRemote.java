package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Domicilio;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;
import edu.uade.apd.tpo.repository.stub.ClienteStub;
import edu.uade.apd.tpo.repository.stub.DomicilioStub;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;

public class SistemaAdministracionRemote extends UnicastRemoteObject implements SistemaAdministracionRepository {

    private static SistemaAdministracionRemote instance;
    private SistemaAdministracion controller;

    private SistemaAdministracionRemote() throws RemoteException {
        this.controller = SistemaAdministracion.getInstance();
    }

    public static SistemaAdministracionRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaAdministracionRemote();
        }
        return instance;
    }

    @Override
    public void crearUsuario(String email, String password) throws RemoteException {
        this.controller.crearUsuario(email, password);
    }

    @Override
    public List<UsuarioStub> getUsuarios() {
        return controller.getUsuarios().parallelStream().map(u -> SerializationUtils.toStub(u, UsuarioStub.class)).collect(Collectors.toList());
    }
}
