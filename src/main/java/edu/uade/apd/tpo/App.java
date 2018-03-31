package edu.uade.apd.tpo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.uade.apd.tpo.config.ServiceModule;
import edu.uade.apd.tpo.controller.ClienteController;

public class App {

    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new ServiceModule());
            new Server(injector);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
