package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.RemitoEntity;
import edu.uade.apd.tpo.model.Remito;

public class RemitoDao extends AbstractDao<RemitoEntity> {

    private static RemitoDao instance;

    private RemitoDao() {

    }

    public static RemitoDao getInstance() {
        if (instance == null) {
            instance = new RemitoDao();
        }
        return instance;
    }

    public void save(Remito remito) {
        RemitoEntity entity = mapper.map(remito, RemitoEntity.class);
        super.save(entity);
    }

}
