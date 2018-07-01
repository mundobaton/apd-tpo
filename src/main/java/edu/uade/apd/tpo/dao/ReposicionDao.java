package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ReposicionEntity;
import edu.uade.apd.tpo.model.Reposicion;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ReposicionDao extends AbstractDao<ReposicionEntity> {

    private static ReposicionDao instance;

    private ReposicionDao() {

    }

    public static ReposicionDao getInstance() {
        if (instance == null) {
            instance = new ReposicionDao();
        }
        return instance;
    }

    public void save(Reposicion reposicion) {
        ReposicionEntity entity = mapper.map(reposicion, ReposicionEntity.class);
        save(entity);
    }

    public List<Reposicion> findAll() {
        String query = "select r from ReposicionEntity r";
        try (Session session = getSession()) {
            Query<ReposicionEntity> q = session.createQuery(query);
            List<ReposicionEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Reposicion> reposiciones = new ArrayList<>();
                for (ReposicionEntity re : result) {
                    reposiciones.add(mapper.map(re, Reposicion.class));
                }
                return reposiciones;
            }
            return null;
        }
    }

    public List<Reposicion> obtenerReposicionesPorEstado(char estado) {
        String query = "select r from ReposicionEntity r where r.estado = :estado";
        try (Session session = getSession()) {
            Query<ReposicionEntity> q = session.createQuery(query);
            q.setParameter("estado", estado);
            List<ReposicionEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                List<Reposicion> reposiciones = new ArrayList<>();
                for (ReposicionEntity re : result) {
                    reposiciones.add(mapper.map(re, Reposicion.class));
                }
                return reposiciones;
            }
            return null;
        }
    }

    public Reposicion findById(Long reposicionId) {
        String query = "select r from ReposicionEntity r where r.id = :reposicionId";
        try (Session session = getSession()) {
            Query<ReposicionEntity> q = session.createQuery(query);
            q.setParameter("reposicionId", reposicionId);
            List<ReposicionEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Reposicion.class);
            }
            return null;
        }
    }
}
