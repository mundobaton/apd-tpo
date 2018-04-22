package edu.uade.apd.tpo;

import edu.uade.apd.tpo.remote.SistemaAdministracionRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public Server() throws RemoteException {
        this.init();
    }

    private void init() throws RemoteException {
        try {
            logger.info("Initializing server...");
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//127.0.0.1/administracion", SistemaAdministracionRemote.getInstance());
            logger.info("Server initialized and running..");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
