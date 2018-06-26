package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Pedido;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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

    public Pedido save(Pedido pedido) {
        PedidoEntity entity = mapper.map(pedido, PedidoEntity.class);
        entity = super.save(entity);
        return mapper.map(entity, Pedido.class);
    }

    public Pedido findById(Long pedidoId) {
        String query = "select p from PedidoEntity p where p.id = :pedidoId";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            q.setParameter("pedidoId", pedidoId);

            List<PedidoEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Pedido.class);
            }
            return null;
        }
    }

}
