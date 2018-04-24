package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.BaseEntity;
import edu.uade.apd.tpo.exception.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<R extends BaseEntity> {

    private SessionManager sessionManager;

    public AbstractDao() {
        this.sessionManager = SessionManager.getInstance();
    }

    protected void save(R r) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(r);
            session.getTransaction().commit();
        }
    }

    protected void update(R r) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(r);
            session.getTransaction().commit();
        }
    }

    protected void delete(R r) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(r);
            session.getTransaction().commit();
        }
    }

    protected Session getSession() {
        return sessionManager.getSessionFactory().openSession();
    }

}
