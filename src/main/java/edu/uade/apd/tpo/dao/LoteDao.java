package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.LoteEntity;

public class LoteDao extends AbstractDao<LoteEntity> {

    private static LoteDao instance;

    private LoteDao() {
    }

    public static LoteDao getInstance() {
        if (instance == null) {
            instance = new LoteDao();
        }
        return instance;
    }

}
