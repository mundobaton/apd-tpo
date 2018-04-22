package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.model.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsuarioDao extends AbstractDao<Usuario> {

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
        CriteriaQuery<Usuario> query = this.getCriteria().createQuery(Usuario.class);
        Root<Usuario> from = query.from(Usuario.class);
        CriteriaQuery<Usuario> all = query.select(from);
        return resultList(all);
    }
}
