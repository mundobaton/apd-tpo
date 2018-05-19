package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.remote.TransformUtils;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class PedidoDao extends AbstractDao<PedidoEntity> {

    private static PedidoDao instance;

    private PedidoDao() {
    }

    public static PedidoDao getInstance() {
        if (instance == null) {
            instance = new PedidoDao();
        }
        return instance;
    }
    
    public List<Pedido> obtenerPedidos(String email){
       
    	String query = "select p from PedidoEntity p inner join p.cliente as c " +
                "where c.email = :email";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            q.setParameter("email", email);
            List<PedidoEntity> entities = q.getResultList();
            return entities.parallelStream().map(u -> TransformUtils.to(u, Pedido.class)).collect(Collectors.toList());
        } 
    	
    }

    public Pedido findById(Long pedidoId) {
        String query = "select p from PedidoEntity where id = :pedidoId";

        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query).setParameter("pedidoId", pedidoId);
            PedidoEntity result = q.getSingleResult();
            return TransformUtils.to(result, Pedido.class);
        }
    }

    public void save(Pedido pedido) {
        PedidoEntity entity = TransformUtils.to(pedido, PedidoEntity.class);
        super.save(entity);
    }
}
