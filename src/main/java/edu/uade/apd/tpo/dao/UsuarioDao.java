package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.UsuarioEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class UsuarioDao extends AbstractDao<UsuarioEntity> {

    private static UsuarioDao instance;

    private UsuarioDao() {
    }

    public static UsuarioDao getInstance() {
        if (instance == null) {
            instance = new UsuarioDao();
        }
        return instance;
    }

    public UsuarioEntity findByEmail(String email) {
        String query = "select u from UsuarioEntity u where email = :email";
        try (Session session = getSession()) {
            Query<UsuarioEntity> q = session.createQuery(query);
            q.setParameter("email", email);
            List<UsuarioEntity> result = q.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }

}
