package edu.uade.apd.tpo;

import edu.uade.apd.tpo.remote.ClienteControllerRemote;
import edu.uade.apd.tpo.repository.ClienteControllerRepository;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public Server() throws RemoteException {
        this.init();
    }

    private void init() throws RemoteException {
        ClienteControllerRepository or = ClienteControllerRemote.getInstance();
        try {
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//127.0.0.1/cliente", or);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
