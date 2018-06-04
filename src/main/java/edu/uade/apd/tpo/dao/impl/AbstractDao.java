package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.BaseEntity;
import edu.uade.apd.tpo.exception.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<R extends Serializable> {

    private SessionManager sessionManager;

    public AbstractDao() {
        this.sessionManager = SessionManager.getInstance();
    }

    protected void save(R r) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(r);
            session.getTransaction().commit();
            session.flush();
        }
    }

    protected void delete(R r) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(r);
            session.getTransaction().commit();
            session.flush();
        }
    }

    protected Session getSession() {
        return sessionManager.getSessionFactory().openSession();
    }

}
