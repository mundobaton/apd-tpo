package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

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

    public OrdenCompra save(OrdenCompra oc) {
        OrdenCompraEntity entity = mapper.map(oc, OrdenCompraEntity.class);
        entity = save(entity);
        return mapper.map(entity, OrdenCompra.class);
    }

    public OrdenCompra findById(Long ordenCompraId) {
        String query = "select o from OrdenCompraEntity o where o.id = :ordenCompraId";
        try (Session session = getSession()) {
            Query<OrdenCompraEntity> q = session.createQuery(query);
            q.setParameter("ordenCompraId", ordenCompraId);

            List<OrdenCompraEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), OrdenCompra.class);
            }
            return null;
        }
    }

    public List<OrdenCompra> buscarPendientesPorArticulo(Long articuloId) {
        String query = "select o from OrdenCompraEntity o inner join o.item as ip inner join ip.articulo as art where art.id = :articuloId and o.estado = 'P'";
        try (Session session = getSession()) {
            Query<OrdenCompraEntity> q = session.createQuery(query);
            q.setParameter("articuloId", articuloId);
            List<OrdenCompraEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return result.parallelStream().map(o -> mapper.map(o, OrdenCompra.class)).collect(Collectors.toList());
            }
            return null;
        }
    }


}
