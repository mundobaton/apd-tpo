package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.remote.TransformUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
        String query = "select f from FacturaEntity where id = :facturaId";

        try (Session session = getSession()) {
            Query<FacturaEntity> q = session.createQuery(query).setParameter("facturaId", facturaId);
            FacturaEntity result = q.getSingleResult();
            return TransformUtils.to(result, Factura.class);
        }
    }

    public void save(Factura factura) {
        FacturaEntity entity = TransformUtils.to(factura, FacturaEntity.class);
        super.save(entity);
    }

    public void update(Factura factura) {
        FacturaEntity entity = TransformUtils.to(factura, FacturaEntity.class);
        super.update(entity);
    }

}
