package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.ItemLoteDao;
import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemLote;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SistemaDeposito {

    private ArticuloDao articuloDao;
    private ItemLoteDao itemLoteDao;

    private static SistemaDeposito instance;

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.itemLoteDao = ItemLoteDao.getInstance();
    }

    public static SistemaDeposito getInstance() {
        if (instance == null) {
            instance = new SistemaDeposito();
        }
        return instance;
    }

    /**
     * Obtiene la menor cantidad de lotes necesarios
     *
     * @param articuloId
     * @param cant
     * @return
     */
    public List<ItemLote> obtenerLotesPorArticulo(Integer articuloId, int cant) {
        List<ItemLote> result = new ArrayList<>();
        int cantidadParcial = 0;
        List<ItemLoteEntity> ilEntities = itemLoteDao.findByArticuloId(articuloId);
        List<ItemLote> itemLotes = new ArrayList<>();
        if (ilEntities != null && !ilEntities.isEmpty()) {
            for (ItemLoteEntity ile : ilEntities) {
                itemLotes.add(ItemLote.fromEntity(ile));
            }
        }
        Iterator<ItemLote> it = itemLotes.iterator();
        while (cantidadParcial < cant && it.hasNext()) {
            ItemLote il = it.next();
            cantidadParcial += il.getCantidad();
            result.add(il);
        }
        return result;
    }

    public Articulo buscarArticulo(Long articuloId) {
        return Articulo.fromEntity(articuloDao.findById(articuloId));
    }

}
