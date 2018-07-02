package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.Pedido;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
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

    public List<Pedido> findByEstado(EstadoPedido ep) {
        String query = "select p from PedidoEntity p where p.estado = :ep";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            q.setParameter("ep", ep);

            List<PedidoEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Pedido> pedidos = new ArrayList<>();
                for (PedidoEntity pe : result) {
                    pedidos.add(mapper.map(pe, Pedido.class));
                }
                return pedidos;
            }
            return null;
        }
    }

    public List<Pedido> findPendientesAprobar() {
        EstadoPedido[] estados = {EstadoPedido.SALDO_INSUFICIENTE, EstadoPedido.PREAPROBADO};
        String query = "select p from PedidoEntity p where p.estado in (:estados)";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            q.setParameterList("estados", estados);
            List<PedidoEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Pedido> pedidos = new ArrayList<>();
                for (PedidoEntity p : result) {
                    pedidos.add(mapper.map(p, Pedido.class));
                }
                return pedidos;
            }
            return null;
        }
    }

}
