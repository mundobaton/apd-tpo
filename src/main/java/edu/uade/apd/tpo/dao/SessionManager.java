package edu.uade.apd.tpo.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    private static SessionManager instance;
    private SessionFactory sessionFactory;

    private SessionManager() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();

            configuration.setProperty("hibernate.event.merge.entity_copy_observer", "allow");
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
