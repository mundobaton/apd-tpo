package edu.uade.apd.tpo.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.remote.TransformUtils;

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

    public void save(OrdenCompra oc) {
        OrdenCompraEntity entity = TransformUtils.to(oc, OrdenCompraEntity.class);
        super.save(entity);
    }
    
    public OrdenCompra findById(Long ordenId) {
    	String query = "select o from OrdenCompraEntity o where id = :ordenId";
    	try(Session session = getSession()){
    		Query<OrdenCompraEntity> q = session.createQuery(query);
            q.setParameter("ordenId", ordenId);
            return TransformUtils.to(q.getSingleResult(), OrdenCompra.class);
    	}
    }
    
    public List<OrdenCompra> findAll(){
    	String query = "select a from OrdenCompraEntity a";
        try (Session session = getSession()) {
            Query<OrdenCompraEntity> q = session.createQuery(query);
            List<OrdenCompraEntity> entities = q.getResultList();
            return entities.parallelStream().map(u -> TransformUtils.to(u, OrdenCompra.class)).collect(Collectors.toList());
        }
    }
}
