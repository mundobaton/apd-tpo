package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PosicionDao extends AbstractDao<PosicionEntity> {

    private static PosicionDao instance;

    private PosicionDao() {
    }

    public static PosicionDao getInstance() {
        if (instance == null) {
            instance = new PosicionDao();
        }
        return instance;
    }

    public List<PosicionEntity> findPosicionesDisponibles() {
        String query = "select p from PosicionEntity p where p.estado = 'DISPONIBLE'";
        try (Session session = getSession()) {
            Query<PosicionEntity> q = session.createQuery(query);
            return q.getResultList();
        }
    }

    public PosicionEntity findById(Integer posicionId) {
        String query = "select p from PosicionEntity p where p.id = :posicionId";
        try (Session session = getSession()) {
            Query<PosicionEntity> q = session.createQuery(query);
            q.setParameter("posicionId", posicionId);
            List<PosicionEntity> result = q.getResultList();
            PosicionEntity pos = null;
            if (!result.isEmpty()) {
                pos = result.get(0);
            }
            return pos;
        }
    }

    public PosicionEntity save(PosicionEntity t) {
        try (Session session = getSession()) {
            session.beginTransaction();
            session.save(t);
            session.flush();
            session.getTransaction().commit();
        }
        return t;
    }

}
