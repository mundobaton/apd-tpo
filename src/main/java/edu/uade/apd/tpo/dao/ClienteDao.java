package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

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

    public ClienteEntity findByEmail(String email) {
        String query = "select c from ClienteEntity c where c.email = :email";
        try (Session session = getSession()) {
            Query<ClienteEntity> q = session.createQuery(query);
            q.setParameter("email", email);
            List<ClienteEntity> result = q.getResultList();
            ClienteEntity cli = result.isEmpty() ? null : result.get(0);
            if(cli != null) {
                Hibernate.initialize(cli.getCuentaCorriente().getTransacciones());
            }
            return cli;
        }
    }

}
