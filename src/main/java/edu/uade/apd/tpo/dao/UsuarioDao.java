package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

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

    public Usuario findByLegajo(String legajo) {
        String query = "select u from UsuarioEntity u where u.legajo = :legajo";
        try (Session session = getSession()) {
            Query<UsuarioEntity> q = session.createQuery(query);
            q.setParameter("legajo", legajo);

            List<UsuarioEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Usuario.class);
            }
            return null;
        }
    }

    public void save(Usuario usuario) {
        UsuarioEntity entity = mapper.map(usuario, UsuarioEntity.class);
        super.save(entity);
    }


}
