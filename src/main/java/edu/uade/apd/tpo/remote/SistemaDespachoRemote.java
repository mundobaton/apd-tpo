package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaDespacho;
import edu.uade.apd.tpo.repository.SistemaDespachoRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaDespachoRemote extends UnicastRemoteObject implements SistemaDespachoRepository {
    private static SistemaDespachoRemote instance;
    private SistemaDespacho controller;

    private SistemaDespachoRemote() throws RemoteException {
        this.controller = SistemaDespacho.getInstance();
    }

    public static SistemaDespachoRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaDespachoRemote();
        }
        return instance;
    }
}
