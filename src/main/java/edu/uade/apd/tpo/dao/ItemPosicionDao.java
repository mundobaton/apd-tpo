package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ItemPosicionEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ItemPosicionDao extends AbstractDao<ItemPosicionEntity> {

    private static ItemPosicionDao instance;

    private ItemPosicionDao() {

    }

    public static ItemPosicionDao getInstance() {
        if (instance == null) {
            instance = new ItemPosicionDao();
        }
        return instance;
    }

    public ItemPosicionEntity findById(Long itemPosicionId) {
        String query = "select ip from ItemPosicionEntity ip where ip.id = :itemPosicionId";
        try (Session session = getSession()) {
            Query<ItemPosicionEntity> q = session.createQuery(query);
            q.setParameter("itemPosicionId", itemPosicionId);
            List<ItemPosicionEntity> result = q.getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }

}
