package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;
import edu.uade.apd.tpo.repository.dto.ClienteDTO;
import edu.uade.apd.tpo.repository.dto.RolDTO;
import edu.uade.apd.tpo.repository.dto.UsuarioDTO;
import edu.uade.apd.tpo.repository.exception.RemoteBusinessException;
import org.modelmapper.ModelMapper;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaAdministracionRemote extends UnicastRemoteObject implements SistemaAdministracionRepository {

    private static SistemaAdministracionRemote instance;
    private SistemaAdministracion controller;
    private ModelMapper mapper;

    private SistemaAdministracionRemote() throws RemoteException {
        this.controller = SistemaAdministracion.getInstance();
        this.mapper = new ModelMapper();
    }

    public static SistemaAdministracionRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaAdministracionRemote();
        }
        return instance;
    }

    @Override
    public void crearCliente(String nombreUsuario, String password, String calle, int numero, String localidad, String provincia, String codPostal, float saldo, float credito) throws RemoteException {
        try {
            controller.crearCliente(nombreUsuario, password, calle, numero, localidad, provincia, codPostal, saldo, credito);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public void crearUsuario(String legajo, String password, RolDTO rol) throws RemoteException {
        Rol r = mapper.map(rol, Rol.class);
        try {
            controller.crearUsuario(legajo, password, r);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public ClienteDTO loginCliente(String email, String password) throws RemoteException {
        try {
            Cliente cliente = controller.loginCliente(email, password);
            return mapper.map(cliente, ClienteDTO.class);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public UsuarioDTO loginUsuario(String legajo, String password) throws RemoteException {
        try {
            Usuario usuario = controller.loginUsuario(legajo, password);
            return mapper.map(usuario, UsuarioDTO.class);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }

    @Override
    public ClienteDTO findClienteById(Long clienteId) throws RemoteException {
        try {
            Cliente cliente = controller.buscarClienteById(clienteId);
            return mapper.map(cliente, ClienteDTO.class);
        } catch (BusinessException be) {
            throw new RemoteBusinessException(be.getMessage());
        }
    }
}
