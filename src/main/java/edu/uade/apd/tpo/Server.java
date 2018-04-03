package edu.uade.apd.tpo;

import com.google.inject.Injector;
import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.remote.SistemaAdministracionRemote;
import edu.uade.apd.tpo.repository.SistemaAdministracionRepository;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    private Injector injector;

    public Server(Injector injector) throws RemoteException {
        this.injector = injector;
        this.init();
    }

    private void init() throws RemoteException {
        SistemaAdministracionRepository administracion = SistemaAdministracionRemote.getInstance(injector.getInstance(SistemaAdministracion.class));
        try {
            LocateRegistry.createRegistry(1099);
            Naming.rebind("//127.0.0.1/administracion", administracion);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
