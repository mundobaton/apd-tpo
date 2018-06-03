package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.model.Factura;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class FacturaDao extends AbstractDao<FacturaEntity> {

    private static FacturaDao instance;

    private FacturaDao() {

    }

    public static FacturaDao getInstance() {
        if (instance == null) {
            instance = new FacturaDao();
        }
        return instance;
    }

    public FacturaEntity findById(Long facturaId) {
        String query = "select f from FacturaEntity f where f.id = :id";
        try (Session session = getSession()) {
            Query<FacturaEntity> q = session.createQuery(query);
            q.setParameter("id", facturaId);
            List<FacturaEntity> result = q.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }
}
