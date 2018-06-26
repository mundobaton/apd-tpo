package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.UbicacionDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.Ubicacion;
import edu.uade.apd.tpo.util.RandomString;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class SistemaDeposito {

    private static SistemaDeposito instance;
    private ArticuloDao articuloDao;
    private UbicacionDao ubicacionDao;

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.ubicacionDao = UbicacionDao.getInstance();
    }

    public static SistemaDeposito getInstance() {
        if (instance == null) {
            instance = new SistemaDeposito();
        }
        return instance;
    }

    public Articulo buscarArticulo(Long articuloId) {
        return articuloDao.findById(articuloId);
    }


    /**
     * Obtiene un lote de articulos segun la cantidad necesaria de compra y los almacena en el deposito
     *
     * @param item
     * @throws BusinessException
     */
    public void almacenar(ItemPedido item) throws BusinessException {
        ItemLote itemLote = obtenerLote();
        item.agregarLote(itemLote);
        List<Ubicacion> ubicacionesDisponibles = this.buscarUbicacionesDisponibles();
        if (ubicacionesDisponibles == null || ubicacionesDisponibles.isEmpty()) {
            throw new BusinessException("No existen ubicaciones disponibles en el deposito");
        }
        Articulo articulo = item.getArticulo();
        int itemsPendientes = articulo.getCantCompra();
        Iterator<Ubicacion> it = ubicacionesDisponibles.iterator();
        while (itemsPendientes > 0 && it.hasNext()) {
            Ubicacion ubicacion = it.next();
            ubicacion.setEstado('O');
            itemLote.getUbicaciones().add(ubicacion);
            itemsPendientes = itemsPendientes - articulo.getCantPosicion();
        }
        articulo.setStock(articulo.getStock() + articulo.getCantCompra());
        articulo.agregarItem(itemLote);
    }

    //Helper para generar lotes
    public ItemLote obtenerLote() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        String codigo = new RandomString().nextString();

        ItemLote itemLote = new ItemLote(codigo, cal.getTime());
        return itemLote;
    }

    private List<Ubicacion> buscarUbicacionesDisponibles() {
        return ubicacionDao.findByEstado('D');
    }

    public void obtenerMercaderia(ItemLote lote) {
        for (Ubicacion ubicacion : lote.getUbicaciones()) {
            ubicacion.liberar();
            ubicacion.guardar();
        }

        lote.setUbicaciones(null);
    }
}
