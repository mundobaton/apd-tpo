package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaFacturacion;
import edu.uade.apd.tpo.repository.SistemaFacturacionRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaFacturacionRemote extends UnicastRemoteObject implements SistemaFacturacionRepository {

    private static SistemaFacturacionRemote instance;
    private SistemaFacturacion controller;

    private SistemaFacturacionRemote() throws RemoteException {
        this.controller = SistemaFacturacion.getInstance();
    }

    public static SistemaFacturacionRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaFacturacionRemote();
        }
        return instance;
    }
}
