package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.BaseEntity;
import edu.uade.apd.tpo.exception.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<T, R extends BaseEntity> {

    private SessionManager sessionManager;

    public AbstractDao() {
        this.sessionManager = SessionManager.getInstance();
    }

    protected void save(R t) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(t);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistenceException("Error saving object", e);
        }
    }

    protected List<R> resultList(CriteriaQuery<R> cq) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            Query<R> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenceException("Error executing query", e);
        }
    }

    protected R singleResult(CriteriaQuery<R> cq) {
        try (Session session = sessionManager.getSessionFactory().openSession()) {
            Query<R> query = session.createQuery(cq);
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
