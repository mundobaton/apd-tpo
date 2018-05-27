package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.PosicionEntity;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PosicionDao extends AbstractDao<PosicionEntity> {

    private static PosicionDao instance;

    private PosicionDao() {

    }

    public static PosicionDao getInstance() {
        if (instance == null) {
            instance = new PosicionDao();
        }
        return instance;
    }

    public PosicionEntity findByCodigo(String codigo) {
        String query = "select p from PosicionEntity p where p.codUbicacion = :codigo";
        try (Session session = getSession()) {
            Query<PosicionEntity> q = session.createQuery(query);
            q.setParameter("codigo", codigo);
            List<PosicionEntity> result = q.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }

    public List<PosicionEntity> obtenerObtenerPosicionesVacias() {
        String query = "select p from PosicionEntity p where p.estado = 'DISPONIBLE'";
        try (Session session = getSession()) {
            Query<PosicionEntity> q = session.createQuery(query);
            return q.getResultList();
        }
    }

    public PosicionEntity findByUbicacion(String ubicacion) {
        String query = "select p from PosicionEntity p where p.codUbicacion = :ubicacion";
        try (Session session = getSession()) {
            Query<PosicionEntity> q = session.createQuery(query);
            q.setParameter("ubicacion", ubicacion);
            List<PosicionEntity> result = q.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }
    
    public List<PosicionEntity> findAll() {
        String query = "select p from PosicionEntity p";
        try (Session session = getSession()) {
            Query<PosicionEntity> q = session.createQuery(query);
            return q.getResultList();
        }
    }
}
