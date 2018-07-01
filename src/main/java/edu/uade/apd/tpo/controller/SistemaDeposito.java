package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.ReposicionDao;
import edu.uade.apd.tpo.dao.UbicacionDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.Reposicion;
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
    private ReposicionDao reposicionDao;

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.ubicacionDao = UbicacionDao.getInstance();
        this.reposicionDao = ReposicionDao.getInstance();
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
     * @param reposicion
     * @throws BusinessException
     */
    public void almacenar(Reposicion reposicion) throws BusinessException {
        ItemPedido item = reposicion.getItemPedido();
        ItemLote itemLote = obtenerLote();
        item.agregarLote(itemLote);
        List<Ubicacion> ubicacionesDisponibles = this.buscarUbicacionesDisponibles();
        if (ubicacionesDisponibles == null || ubicacionesDisponibles.isEmpty()) {
            throw new BusinessException("No existen ubicaciones disponibles en el deposito");
        }
        Articulo articulo = item.getArticulo();
        int itemsPendientes = reposicion.getCantidad();
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
        lote.vaciar();
    }

    public List<Articulo> getArticulos() {
        return articuloDao.findAll();
    }

    public List<Reposicion> obtenerReposicionesPendientes() {
        return reposicionDao.obtenerReposicionesPorEstado('P');
    }

    public void reponer(Long reposicionId, int cant) throws BusinessException {
        Reposicion reposicion = buscarReposicion(reposicionId);
        if (reposicion == null) {
            throw new BusinessException("La reposicion con id '"+reposicionId+"' no existe");
        }
        reposicion.procesar(cant);
    }

    private Reposicion buscarReposicion(Long reposicionId) {
        return reposicionDao.findById(reposicionId);
    }



}
