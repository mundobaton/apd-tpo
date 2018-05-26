package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;
import edu.uade.apd.tpo.entity.DomicilioEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    private static SessionManager instance;
    private SessionFactory sessionFactory;

    private SessionManager() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(UsuarioEntity.class);
            configuration.addAnnotatedClass(ClienteEntity.class);
            configuration.addAnnotatedClass(DomicilioEntity.class);
            configuration.addAnnotatedClass(CuentaCorrienteEntity.class);
            configuration.addAnnotatedClass(PedidoEntity.class);
            configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
