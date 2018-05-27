package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaCompras;
import edu.uade.apd.tpo.repository.SistemaComprasRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaComprasRemote extends UnicastRemoteObject implements SistemaComprasRepository {

    private static SistemaComprasRemote instance;
    private SistemaCompras controller;

    private SistemaComprasRemote() throws RemoteException {
        this.controller = SistemaCompras.getInstance();
    }

    public static SistemaComprasRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaComprasRemote();
        }
        return instance;
    }

}

