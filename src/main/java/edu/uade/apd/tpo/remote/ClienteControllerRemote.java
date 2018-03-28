package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.ClienteController;
import edu.uade.apd.tpo.repository.ClienteControllerRepository;
import edu.uade.apd.tpo.repository.model.Cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClienteControllerRemote extends UnicastRemoteObject implements ClienteControllerRepository {

    private static ClienteControllerRemote instance;
    private ClienteController controller;

    private ClienteControllerRemote() throws RemoteException {
        controller = ClienteController.getInstance();
    }

    public static ClienteControllerRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ClienteControllerRemote();
        }
        return instance;
    }

    @Override
    public Cliente login(Long cuil, String password) throws RemoteException {
        return controller.login(cuil, password);
    }
}
