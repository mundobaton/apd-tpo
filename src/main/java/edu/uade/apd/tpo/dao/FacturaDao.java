package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.model.Factura;

public class FacturaDao extends AbstractDao<FacturaEntity> {

    private static FacturaDao instance;

    private FacturaDao() {

    }

    public static FacturaDao getInstance() {
        if (instance == null) {
            instance = new FacturaDao();
        }
        return instance;
    }

    public void save(Factura factura) {
        FacturaEntity entity = mapper.map(factura, FacturaEntity.class);
        save(entity);
    }

}
