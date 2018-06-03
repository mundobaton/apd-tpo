package edu.uade.apd.tpo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Pedido;

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

    public PedidoEntity findById(Long pedidoId) {
        String query = "select p from PedidoEntity p where p.id = :pedidoId";

        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query).setParameter("pedidoId", pedidoId);
            PedidoEntity result = q.getSingleResult();
            return result;
        }
    }

    public List<PedidoEntity> obtenerPedidosCompletos() {

        String query = "select p from PedidoEntity p inner join p.estados as e " +
                "where e.estado = 'COMPLETO'";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            List<PedidoEntity> entities = q.getResultList();
            return entities;
        }

    }

    public List<PedidoEntity> obtenerPedidosPendientes() {
        String query = "select p from PedidoEntity p inner join p.estados as e " +
                "where e.estado = 'PENDIENTE'";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            List<PedidoEntity> entities = q.getResultList();
            return entities;
        }
    }

    public List<PedidoEntity> obtenerPedidosPreAprobadosRevision() {
        String query = "select p from PedidoEntity p inner join p.estados as e " +
                "where e.estado in ('PREAPROBADO', 'EN_REVISION')";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            List<PedidoEntity> entities = q.getResultList();
            return entities;
        }
    }

    public List<PedidoEntity> obtenerPedidosListos() {
        String query = "select p from PedidoEntity p inner join p.estados as e " +
                "where e.estado = 'LISTO'";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            List<PedidoEntity> entities = q.getResultList();
            return entities;
        }
    }

    public List<PedidoEntity> obtenerPedidosVerificados() {
        String query = "select p from PedidoEntity p inner join p.estados as e " +
                "where e.estado = 'VERIFICADO'";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            List<PedidoEntity> entities = q.getResultList();
            return entities;
        }
    }

    public List<PedidoEntity> findAll(){
        String query = "select p from PedidoEntity p";
        try (Session session = getSession()) {
            Query<PedidoEntity> q = session.createQuery(query);
            return q.getResultList();
        }
    }


}