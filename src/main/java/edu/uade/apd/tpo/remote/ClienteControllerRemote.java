package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.ClienteController;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.repository.ClienteControllerRepository;
import edu.uade.apd.tpo.repository.stub.ClienteStub;

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
    public ClienteStub login(Long cuil, String password) throws RemoteException {
        return SerializationUtils.toStub(controller.login(cuil, password), ClienteStub.class);
    }

    @Override
    public void test(ClienteStub stub) throws RemoteException {
        Cliente cliente = SerializationUtils.fromStub(stub, Cliente.class);
        System.out.println(cliente.getNombre());
    }
}
