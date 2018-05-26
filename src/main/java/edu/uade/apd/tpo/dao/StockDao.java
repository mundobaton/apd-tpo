package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.StockEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StockDao extends AbstractDao<StockEntity> {

    private static StockDao instance;

    private StockDao() {

    }

    public static StockDao getInstance() {
        if (instance == null) {
            instance = new StockDao();
        }
        return instance;
    }

    public StockEntity findById(Long stockId) {
        String query = "select s from StockEntity s where s.id = :stockId";
        try (Session session = getSession()) {
            Query<StockEntity> q = session.createQuery(query);
            q.setParameter("stockId", stockId);
            List<StockEntity> result = q.getResultList();
            return result == null ? null : result.get(0);
        }
    }
}

