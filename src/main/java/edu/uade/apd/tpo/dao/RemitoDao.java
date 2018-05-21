package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.RemitoEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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

    public RemitoEntity findById(Long remitoId) {
        String query = "select r from RemitoEntity r where r.id = :remitoId";
        try (Session session = getSession()) {
            Query<RemitoEntity> q = session.createQuery(query).setParameter("remitoId", remitoId);
            List<RemitoEntity> result = q.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }
}