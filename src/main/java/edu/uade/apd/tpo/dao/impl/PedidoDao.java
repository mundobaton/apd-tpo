package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.remote.TransformUtils;
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

    public Pedido findById(Long pedidoId) {
        String query = "select p from PedidoEntity p where p.id = :pedidoId";

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
