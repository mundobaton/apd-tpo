package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.remote.TransformUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

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

    public Factura findById(Long facturaId) {
        String query = "select f from FacturaEntity f where f.id = :facturaId";

        try (Session session = getSession()) {
            Query<FacturaEntity> q = session.createQuery(query).setParameter("facturaId", facturaId);
            List<FacturaEntity> results = q.getResultList();
            return results.isEmpty() ? null : TransformUtils.to(results.get(0), Factura.class);
        }
    }

    public void save(Factura factura) {
        FacturaEntity entity = TransformUtils.to(factura, FacturaEntity.class);
        super.save(entity);
    }

    public List<Factura> obtenerFacturasCliente(String email) {
        String query = "select f from FacturaEntity f inner join f.pedido as p inner join p.cliente as c " +
                "where c.email = :email order by f.fecha ASC";
        try (Session session = getSession()) {
            Query<FacturaEntity> q = session.createQuery(query);
            q.setParameter("email", email);
            List<FacturaEntity> entities = q.getResultList();
            return entities.parallelStream().map(u -> TransformUtils.to(u, Factura.class)).collect(Collectors.toList());
        }
    }

}
