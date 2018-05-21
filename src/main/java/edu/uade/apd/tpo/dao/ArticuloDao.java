package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ArticuloDao extends AbstractDao<ArticuloEntity> {

    private static ArticuloDao instance;

    private ArticuloDao() {
    }

    public static ArticuloDao getInstance() {
        if (instance == null) {
            instance = new ArticuloDao();
        }
        return instance;
    }

    public ArticuloEntity findById(Integer articuloId) {
        String query = "select a from ArticuloEntity a where a.id = :articuloId";
        try (Session session = getSession()) {
            Query<ArticuloEntity> q = session.createQuery(query);
            q.setParameter("articuloId", articuloId);
            List<ArticuloEntity> result = q.getResultList();
            ArticuloEntity entity = null;
            if(!result.isEmpty()) {
                entity = result.get(0);
            }
            return entity;
        }
    }

    public List<ArticuloEntity> findAll() {
        String query = "select a from ArticuloEntity a";
        try (Session session = getSession()) {
            Query<ArticuloEntity> q = session.createQuery(query);
            return q.getResultList();
        }
    }

}
