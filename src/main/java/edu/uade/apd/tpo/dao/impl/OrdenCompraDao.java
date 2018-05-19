package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Usuario;
import edu.uade.apd.tpo.remote.TransformUtils;

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

    public void save(OrdenCompra oc) {
        OrdenCompraEntity entity = TransformUtils.to(oc, OrdenCompraEntity.class);
        super.save(entity);
    }
}
