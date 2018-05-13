package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.model.ZonaEnvio;
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;
import edu.uade.apd.tpo.repository.stub.ClienteStub;
import edu.uade.apd.tpo.repository.stub.CondIvaStub;
import edu.uade.apd.tpo.repository.stub.RolStub;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;
import edu.uade.apd.tpo.repository.stub.ZonaEnvioStub;

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
    public void crearUsuario(String email, String password, RolStub rolStub) throws RemoteException {
        Rol rol = Rol.fromStub(rolStub);
        this.controller.crearUsuario(email, password, rol);
    }

    @Override
    public List<UsuarioStub> getUsuarios() throws RemoteException {
        return controller.getUsuarios().parallelStream().map(u -> u.toStub()).collect(Collectors.toList());
    }

    @Override
    public List<ClienteStub> getClientes() throws RemoteException {
        return controller.getClientes().parallelStream().map(c -> c.toStub()).collect(Collectors.toList());
    }

    @Override
    public void actualizarUsuario(UsuarioStub usuarioStub) throws RemoteException {
        Usuario u = Usuario.fromStub(usuarioStub);
        controller.actualizarUsuario(u);
    }

    @Override
    public void crearCliente(String email, String password, String nombre, long cuil, String telefono, CondIvaStub condIva, String calle, int numero, String codPostal, String localidad, String provincia, ZonaEnvioStub zona, float saldo, float limiteCredito) throws RemoteException {
        this.controller.crearCliente(email, password, nombre, cuil, telefono, CondIva.fromStub(condIva), calle, numero, codPostal, localidad, provincia, ZonaEnvio.fromStub(zona), saldo, limiteCredito);
    }
}
