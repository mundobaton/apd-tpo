package edu.uade.apd.tpo.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import edu.uade.apd.tpo.entity.LoteEntity;
import edu.uade.apd.tpo.model.Lote;
import edu.uade.apd.tpo.remote.TransformUtils;

public class LoteDao extends AbstractDao<LoteEntity>{

private static LoteDao instance;
	
	private LoteDao() {
    }

    public static LoteDao getInstance() {
        if (instance == null) {
            instance = new LoteDao();
        }
        return instance;
    }
    
    public Lote findById(Long loteId) {
        String query = "select f from LoteEntity where id = :loteId";

        try (Session session = getSession()) {
            Query<LoteEntity> q = session.createQuery(query).setParameter("loteId", loteId);
            LoteEntity result = q.getSingleResult();
            return TransformUtils.to(result, Lote.class);
        }
    }
    
    public void save(Lote lote) {
        LoteEntity entity = TransformUtils.to(lote, LoteEntity.class);
        super.save(entity);
    }
}
