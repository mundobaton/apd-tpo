package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.persistence.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public abstract class AbstractDao<T extends Serializable> {

    private SessionFactory sessionFactory;

    public AbstractDao() {
        this.sessionFactory = SessionManager.getInstance().getSessionFactory();
    }

    public void save(T t) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        }
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
