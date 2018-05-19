package edu.uade.apd.tpo.dao.impl;

import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.remote.TransformUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ItemLoteDao extends AbstractDao<ItemLoteEntity> {

    private static ItemLoteDao instance;

    private ItemLoteDao() {

    }

    public static ItemLoteDao getInstance() {
        if (instance == null) {
            instance = new ItemLoteDao();
        }
        return instance;
    }

    /**
     * Devuelve una lista de itemLotes ordenados por cant ascendiente, de manera que utilicemos primero los que estan
     * parcialmente llenos.
     * @param articuloId
     * @return
     */
    public List<ItemLote> findByArticuloId(Long articuloId) {
        String query = "select il from ItemLoteEntity il inner join il.lote as l inner join l.articulo as art " +
                "where art.id = :articuloId order by il.cantidad asc";
        try (Session session = getSession()) {
            Query<ItemLoteEntity> q = session.createQuery(query);
            q.setParameter("articuloId", articuloId);
            return q.getResultList().parallelStream().map(il -> TransformUtils.to(il, ItemLote.class)).collect(Collectors.toList());
        }
    }


}
