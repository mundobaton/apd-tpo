package edu.uade.apd.tpo;

<<<<<<< HEAD
import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.remote.SistemaAdministracionRemote;
import edu.uade.apd.tpo.remote.SistemaComprasRemote;
import edu.uade.apd.tpo.remote.SistemaDepositoRemote;
import edu.uade.apd.tpo.remote.SistemaDespachoRemote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public Server() throws Exception {
        this.init();
    }

    private void init() throws Exception {
        logger.info("Initializing server...");
        LocateRegistry.createRegistry(1099);
        Naming.rebind("//127.0.0.1/administracion", SistemaAdministracionRemote.getInstance());
        Naming.rebind("//127.0.0.1/deposito", SistemaDepositoRemote.getInstance());
        Naming.rebind("//127.0.0.1/despacho", SistemaDespachoRemote.getInstance());
        Naming.rebind("//127.0.0.1/compras", SistemaComprasRemote.getInstance());
        logger.info("Server initialized and running..");

=======
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
>>>>>>> develop
    }
}
