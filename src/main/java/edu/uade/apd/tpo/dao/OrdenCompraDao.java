package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.OrdenCompraEntity;

public class OrdenCompraDao extends AbstractDao<OrdenCompraEntity> {

    private static OrdenCompraDao instance;

    private OrdenCompraDao() {
    }

    public static OrdenCompraDao getInstance() {
        if (instance == null) {
            instance = new OrdenCompraDao();
        }
        return instance;
    }

    public void save(OrdenCompraEntity oc) {
        super.save(oc);
    }
}
