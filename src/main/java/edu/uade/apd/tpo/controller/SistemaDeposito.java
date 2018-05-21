package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.ItemLoteDao;
import edu.uade.apd.tpo.dao.LoteDao;
import edu.uade.apd.tpo.dao.PosicionDao;
import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.EstadoPosicion;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.Lote;
import edu.uade.apd.tpo.model.Posicion;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SistemaDeposito {

    private ArticuloDao articuloDao;
    private ItemLoteDao itemLoteDao;
    private PosicionDao posicionDao;
    private LoteDao loteDao;

    private static SistemaDeposito instance;

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.itemLoteDao = ItemLoteDao.getInstance();
        this.posicionDao = PosicionDao.getInstance();
        this.loteDao = LoteDao.getInstance();
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

    public Articulo buscarArticulo(Integer articuloId) throws BusinessException {
        ArticuloEntity art = articuloDao.findById(articuloId);
        if (art == null) {
            throw new BusinessException("El articulo '" + articuloId + "' no existe!");
        }
        return Articulo.fromEntity(art);
    }

    public void almacenar(Integer articuloId, int cant, Integer loteId) throws BusinessException {
        Articulo art = this.buscarArticulo(articuloId);
        List<PosicionEntity> entities = PosicionDao.getInstance().findPosicionesDisponibles();
        if (entities != null && !entities.isEmpty()) {
            List<Posicion> posiciones = new ArrayList<>();
            for (PosicionEntity pe : entities) {
                posiciones.add(Posicion.fromEntity(pe));
            }
            Iterator<Posicion> it = posiciones.iterator();
            while (it.hasNext() && cant > 0) {
                Posicion p = it.next();
                Lote lote = new Lote();
                lote.setArticulo(art);
                lote.setCodigo("codigo de barras");
                lote.setFechaElaboracion(new Date());
                lote.setFechaVto(new Date());
                lote = Lote.fromEntity(loteDao.save(lote.toEntity()));
                p.setLote(lote);
                p.setEstado(EstadoPosicion.OCUPADO);
                p.setCantidad(cant > Posicion.CAPACIDAD ? Posicion.CAPACIDAD : cant);
                cant -= Posicion.CAPACIDAD;
                posicionDao.save(p.toEntity());

            }
        } else {
            //Tengo el deposito lleno
            throw new BusinessException("El deposito está repleto de unidades..");
        }
    }

}
