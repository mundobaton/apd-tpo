package edu.uade.apd.tpo.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClienteControllerRemote extends UnicastRemoteObject {


    private static ClienteControllerRemote instance;

    private ClienteControllerRemote() throws RemoteException {

    }

    public static ClienteControllerRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new ClienteControllerRemote();
        }
        return instance;
    }
}
