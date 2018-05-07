package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.RemitoEntity;
import edu.uade.apd.tpo.model.Remito;
import edu.uade.apd.tpo.remote.TransformUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RemitoDao extends AbstractDao<RemitoEntity> {

    private static RemitoDao instance;

    private RemitoDao() {
    }

    public static RemitoDao getInstance() {
        if (instance == null) {
            instance = new RemitoDao();
        }
        return instance;
    }

    public Remito findById(Long remitoId) {
        String query = "select r from RemitoEntity where id = :remitoId";
        try (Session session = getSession()) {
            Query<RemitoEntity> q = session.createQuery(query).setParameter("remitoId", remitoId);
            RemitoEntity result = q.getSingleResult();
            return TransformUtils.to(result, Remito.class);
        }
    }

    public void save(Remito remito) {
        RemitoEntity entity = TransformUtils.to(remito, RemitoEntity.class);
        super.save(entity);
    }

    public void update(Remito remito) {
        RemitoEntity entity = TransformUtils.to(remito, RemitoEntity.class);
        super.update(entity);
    }
}
