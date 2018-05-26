package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
            OrdenCompraEntity result = q.getSingleResult();
            return result;
        }
    }
}
