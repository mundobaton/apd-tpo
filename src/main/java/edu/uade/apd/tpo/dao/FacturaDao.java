package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.FacturaEntity;
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
        String query = "select f from FacturaEntity f where f.id = :facturaId";

        try (Session session = getSession()) {
            Query<FacturaEntity> q = session.createQuery(query).setParameter("facturaId", facturaId);
            List<FacturaEntity> result = q.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }

    public void save(FacturaEntity factura) {
        super.save(factura);
    }

    public List<FacturaEntity> obtenerFacturasCliente(String email) {
        String query = "select f from FacturaEntity f inner join f.pedido as p inner join p.cliente as c " +
                "where c.email = :email order by f.fecha ASC";
        try (Session session = getSession()) {
            Query<FacturaEntity> q = session.createQuery(query);
            q.setParameter("email", email);
            return q.getResultList();
        }
    }
}
