package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.model.Usuario;

import javax.persistence.criteria.CriteriaBuilder;

public class UsuarioDao extends AbstractDao<Usuario> {

    public Usuario findByEmail(String email) {
        CriteriaBuilder cb = getCriteria();
        return null;


    }


}
