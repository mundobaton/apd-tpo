package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;

public class CuentaCorrienteDao extends AbstractDao<CuentaCorrienteEntity> {

    private static CuentaCorrienteDao instance;

    private CuentaCorrienteDao() {
    }

    public static CuentaCorrienteDao getInstance() {
        if (instance == null) {
            instance = new CuentaCorrienteDao();
        }
        return instance;
    }

}
