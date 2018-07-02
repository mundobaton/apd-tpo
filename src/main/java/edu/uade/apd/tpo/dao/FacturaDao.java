package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.Pedido;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class FacturaDao extends AbstractDao<FacturaEntity> {

    private static FacturaDao instance;

    private FacturaDao() {

    }

    public static FacturaDao getInstance() {
        if (instance == null) {
            instance = new FacturaDao();
        }
        return instance;
    }

    public void save(Factura factura) {
        FacturaEntity entity = mapper.map(factura, FacturaEntity.class);
        save(entity);
    }

    public Factura findById(Long facturaId) {
        String query = "select f from FacturaEntity f where f.id = :facturaId";
        try (Session session = getSession()) {
            Query<FacturaEntity> q = session.createQuery(query);
            q.setParameter("facturaId", facturaId);

            List<FacturaEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Factura.class);
            }
            return null;
        }
    }

    public List<Factura> findByEstado(char estado) {
        String query = "select f from FacturaEntity f where f.estado = :estado order by f.fecha asc";
        try (Session session = getSession()) {
            Query<FacturaEntity> q = session.createQuery(query);

            List<FacturaEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Factura> facturas = new ArrayList<>();
                for (FacturaEntity fe : result) {
                    facturas.add(mapper.map(fe, Factura.class));
                }
                return facturas;
            }
            return null;
        }
    }

    public List<Factura> findAll() {
        String query = "select f from FacturaEntity f";
        try (Session session = getSession()) {
            Query<FacturaEntity> q = session.createQuery(query);
            List<FacturaEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Factura> facturas = new ArrayList<>();
                for (FacturaEntity f : result) {
                    facturas.add(mapper.map(f, Factura.class));
                }
                return facturas;
            }
            return null;
        }
    }

}
