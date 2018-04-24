package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.remote.TransformUtils;
import edu.uade.apd.tpo.repository.stub.UsuarioStub;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDao extends AbstractDao<Usuario, UsuarioEntity> {

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
        CriteriaQuery<UsuarioEntity> query = this.getCriteria().createQuery(UsuarioEntity.class);
        Root<UsuarioEntity> from = query.from(UsuarioEntity.class);
        CriteriaQuery<UsuarioEntity> all = query.select(from);
        return resultList(all).parallelStream().map(u -> TransformUtils.to(u, Usuario.class)).collect(Collectors.toList());
    }

    public void save(Usuario usuario) {
        UsuarioEntity entity = TransformUtils.to(usuario, UsuarioEntity.class);
        super.save(entity);
    }
}
