package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.model.EstadoCompra;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrdenCompraDao extends AbstractDao<OrdenCompraEntity> {

    private static OrdenCompraDao instance;

    private OrdenCompraDao() {

    }

    public static OrdenCompraDao getInstance() {
        if (instance == null) {
            instance = new OrdenCompraDao();
        }
        return instance;
    }

    public OrdenCompraEntity findById(Long ordenId) {
        String query = "select oc from OrdenCompraEntity oc where oc.id = :ordenId";
        try (Session session = getSession()) {
            Query<OrdenCompraEntity> q = session.createQuery(query).setParameter("ordenId", ordenId);
            List<OrdenCompraEntity> result = q.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }

    public List<OrdenCompraEntity> findByEstado(EstadoCompra estado) {
        String query = "select oc from OrdenCompraEntity oc where oc.estado = :estado";
        try (Session session = getSession()) {
            Query<OrdenCompraEntity> q = session.createQuery(query);
            q.setParameter("estado", estado.toString());
            return q.getResultList();
        }
    }
}
