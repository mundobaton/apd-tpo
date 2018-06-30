package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Pedido;
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
        String query = "select a from ArticuloEntity";
        try (Session session = getSession()) {
            Query<ArticuloEntity> q = session.createQuery(query);
            List<ArticuloEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return result.parallelStream().map(a -> mapper.map(a, Articulo.class)).collect(Collectors.toList());
            }
            return null;
        }
    }

    public void save(Articulo articulo) {
        ArticuloEntity entity = mapper.map(articulo, ArticuloEntity.class);
        save(entity);
    }

}
