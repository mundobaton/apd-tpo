package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.remote.TransformUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

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

    public Articulo findById(Long articuloId) {
        String query = "select a from ArticuloEntity a where id = :articuloId";
        try (Session session = getSession()) {
            Query<ArticuloEntity> q = session.createQuery(query);
            q.setParameter("articuloId", articuloId);
            return TransformUtils.to(q.getSingleResult(), Articulo.class);
        }
    }

    public List<Articulo> findAll() {
        String query = "select a from ArticuloEntity a";
        try (Session session = getSession()) {
            Query<ArticuloEntity> q = session.createQuery(query);
            List<ArticuloEntity> entities = q.getResultList();
            return entities.parallelStream().map(u -> TransformUtils.to(u, Articulo.class)).collect(Collectors.toList());
        }
    }
}
