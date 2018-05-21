package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.entity.ProveedorEntity;
import org.hibernate.Hibernate;
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

    public OrdenCompraEntity findById(Integer id) {
        String query = "select oc from OrdenCompraEntity oc where oc.id = :id";
        try (Session session = getSession()) {
            Query<OrdenCompraEntity> q = session.createQuery(query);
            q.setParameter("id", id);
            OrdenCompraEntity entity = null;
            List<OrdenCompraEntity> result = q.getResultList();
            if(!result.isEmpty()) {
                entity = result.get(0);
            }
            return entity;
        }
    }
}
