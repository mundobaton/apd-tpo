package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.remote.TransformUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteDao extends AbstractDao<ClienteEntity> {

    private static ClienteDao instance;

    private ClienteDao() {

    }

    public static ClienteDao getInstance() {
        if (instance == null) {
            instance = new ClienteDao();
        }
        return instance;
    }

    public void save(Cliente cliente) {
        ClienteEntity entity = TransformUtils.to(cliente, ClienteEntity.class);
        super.save(entity);
    }

    public List<Cliente> findAll() {

        String queryStr = "select c from ClienteEntity c";
        try (Session session = getSession()) {
            List<ClienteEntity> resultList = session.createQuery(queryStr).getResultList();
            return resultList.parallelStream().map(u -> TransformUtils.to(u, Cliente.class)).collect(Collectors.toList());
        }
    }

    public Cliente findByEmail(String email) {
        String query = "select u from ClienteEntity u where email = :email";
        try (Session session = getSession()) {
            Query<UsuarioEntity> q = session.createQuery(query);
            q.setParameter("email", email);
            return TransformUtils.to(q.getSingleResult(), Cliente.class);
        }
    }
}
