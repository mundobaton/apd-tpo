package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.model.Articulo;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
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

    public Articulo findById(Long articuloId) {
        String query = "select a from ArticuloEntity a where a.id = :articuloId";
        try (Session session = getSession()) {
            Query<ArticuloEntity> q = session.createQuery(query);
            q.setParameter("articuloId", articuloId);

            List<ArticuloEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Articulo.class);
            }
            return null;
        }
    }

    public List<Articulo> findAll() {
        String query = "select a from ArticuloEntity a";
        try (Session session = getSession()) {
            Query<ArticuloEntity> q = session.createQuery(query);
            List<ArticuloEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
            	List<Articulo> articulos = new ArrayList<Articulo>();
            	for(ArticuloEntity entity : result) {
            		articulos.add(mapper.map(entity, Articulo.class));
            	}
            	return articulos;
            }
            return null;
        }
    }

    public void save(Articulo articulo) {
        ArticuloEntity entity = mapper.map(articulo, ArticuloEntity.class);
        save(entity);
    }

}
