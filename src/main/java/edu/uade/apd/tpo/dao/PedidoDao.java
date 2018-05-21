package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ItemPedidoEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import org.hibernate.Hibernate;
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

    public List<PedidoEntity> obtenerPedidosCompletos() {

        String query = "select p from PedidoEntity p inner join p.estados as e " +
                "where e.estadoPedido = 'COMPLETO'";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            return q.getResultList();
        }

    }

    public PedidoEntity findById(Integer pedidoId) {
        String query = "select p from PedidoEntity p where p.id = :pedidoId";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query).setParameter("pedidoId", pedidoId);
            List<PedidoEntity> result = q.getResultList();
            PedidoEntity entity = null;
            if (!result.isEmpty()) {
                entity = result.get(0);
                Hibernate.initialize(entity.getItems());
                if(entity.getItems() != null && !entity.getItems().isEmpty()) {
                    for(ItemPedidoEntity ipe : entity.getItems()) {
                        Hibernate.initialize(ipe.getLotes());

                    }
                }
                Hibernate.initialize(entity.getCliente().getCuentaCorriente().getTransacciones());
            }
            return entity;
        }
    }
}
