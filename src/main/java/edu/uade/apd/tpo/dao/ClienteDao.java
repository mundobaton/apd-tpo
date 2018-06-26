package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ClienteEntity;
import edu.uade.apd.tpo.model.Cliente;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;

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

    public Cliente findByNombreUsuario(String nombreUsuario) {
        String query = "select c from ClienteEntity c where c.nombreUsuario = :nombreUsuario";
        try (Session session = getSession()) {
            Query<ClienteEntity> q = session.createQuery(query);
            q.setParameter("nombreUsuario", nombreUsuario);

            List<ClienteEntity> result = q.getResultList();
            if (result != null && !result.isEmpty()) {
                return mapper.map(result.get(0), Cliente.class);
            }
            return null;
        }
    }

    public void save(Cliente cliente) {
        ClienteEntity entity = mapper.map(cliente, ClienteEntity.class);
        super.save(entity);
    }
}
