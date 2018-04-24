package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.remote.TransformUtils;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Usuario> findAll() {

        String queryStr = "select u from UsuarioEntity u";
        try (Session session = getSession()) {
            List<UsuarioEntity> resultList = session.createQuery(queryStr).getResultList();
            return resultList.parallelStream().map(u -> TransformUtils.to(u, Usuario.class)).collect(Collectors.toList());
        }
    }

    public void save(Usuario usuario) {
        UsuarioEntity entity = TransformUtils.to(usuario, UsuarioEntity.class);
        super.save(entity);
    }

    public void update(Usuario usuario) {
        UsuarioEntity entity = TransformUtils.to(usuario, UsuarioEntity.class);
        super.update(entity);
    }
}
