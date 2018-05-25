package edu.uade.apd.tpo.dao;

import org.hibernate.Session;

import java.io.Serializable;

public class AbstractDao<T extends Serializable> {

    private SessionManager sessionManager;

    public AbstractDao() {
        this.sessionManager = SessionManager.getInstance();
    }

    protected void save(T t) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(t);
            session.getTransaction().commit();
            session.flush();
        }
    }

    protected Session getSession() {
        return sessionManager.getSessionFactory().openSession();
    }


}
