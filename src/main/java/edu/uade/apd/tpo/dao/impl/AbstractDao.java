package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.exception.PersistenceException;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

public abstract class AbstractDao<T extends Serializable> {

    private SessionManager sessionManager;

    public AbstractDao() {
        this.sessionManager = SessionManager.getInstance();
    }

    public void save(T t) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistenceException("Error saving object", e);
        }
    }

    public void update(T t) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistenceException("Error updating object", e);
        }
    }

    protected CriteriaBuilder getCriteria() {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            return session.getCriteriaBuilder();
        } catch (Exception e) {
            throw new PersistenceException("Error creating criteria object", e);
        }
    }

}
