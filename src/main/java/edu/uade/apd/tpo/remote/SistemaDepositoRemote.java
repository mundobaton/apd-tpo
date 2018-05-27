package edu.uade.apd.tpo.remote;

import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.repository.SistemaDepositoRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SistemaDepositoRemote extends UnicastRemoteObject implements SistemaDepositoRepository {

    private static SistemaDepositoRemote instance;
    private SistemaDeposito controller;

    private SistemaDepositoRemote() throws RemoteException {
        this.controller = SistemaDeposito.getInstance();
    }

    public static SistemaDepositoRemote getInstance() throws RemoteException {
        if (instance == null) {
            instance = new SistemaDepositoRemote();
        }
        return instance;
    }
}
