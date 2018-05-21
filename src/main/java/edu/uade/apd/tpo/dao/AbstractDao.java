package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.Persistible;
import edu.uade.apd.tpo.persistence.SessionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public abstract class AbstractDao<T extends Persistible> {

    private SessionFactory sessionFactory;

    public AbstractDao() {
        this.sessionFactory = SessionManager.getInstance().getSessionFactory();
    }

    public T save(T t) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(t);
            session.flush();
            session.getTransaction().commit();
        }
        return t;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }
}
