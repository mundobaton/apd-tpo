package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.exception.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<T extends Serializable> {

    private SessionManager sessionManager;

    public AbstractDao() {
        this.sessionManager = SessionManager.getInstance();
    }

    public void save(T t) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistenceException("Error saving object", e);
        }
    }

    protected List<T> resultList(CriteriaQuery<T> cq) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenceException("Error executing query", e);
        }
    }

    protected T singleResult(CriteriaQuery<T> cq) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery(cq);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new PersistenceException("Error executing query", e);
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
