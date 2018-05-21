package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.ProveedorEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProveedorDao extends AbstractDao<ProveedorEntity> {

    private static ProveedorDao instance;

    private ProveedorDao() {
    }

    public static ProveedorDao getInstance() {
        if (instance == null) {
            instance = new ProveedorDao();
        }
        return instance;
    }

    public ProveedorEntity findById(Integer id) {
        String query = "select p from ProveedorEntity p where p.id = :id";
        try (Session session = getSession()) {
            Query<ProveedorEntity> q = session.createQuery(query);
            q.setParameter("id", id);
            List<ProveedorEntity> result = q.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }

}
