package edu.uade.apd.tpo.dao;

import org.hibernate.Session;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

public abstract class AbstractDao<T extends Serializable> {

    private SessionManager sessionManager;
    protected ModelMapper mapper;

    public AbstractDao() {
        this.sessionManager = SessionManager.getInstance();
        this.mapper = new ModelMapper();
    }

    public T save(T t) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(t);
            session.getTransaction().commit();
            return t;
        }
    }

    protected Session getSession() {
        return sessionManager.getSessionFactory().openSession();
    }

}
