package edu.uade.apd.tpo.controller;

<<<<<<< HEAD
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.apd.tpo.dao.impl.ArticuloDao;
import edu.uade.apd.tpo.dao.impl.ClienteDao;
import edu.uade.apd.tpo.dao.impl.PedidoDao;
import edu.uade.apd.tpo.dao.impl.PosicionDao;
import edu.uade.apd.tpo.dao.impl.UsuarioDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.Lote;
import edu.uade.apd.tpo.model.MotivoIngreso;
import edu.uade.apd.tpo.model.Posicion;
import edu.uade.apd.tpo.model.Stock;

public class SistemaDeposito {
	
    private static SistemaDeposito instance;
    private ArticuloDao articuloDao;
    private PosicionDao posicionDao;

    private static final Logger logger = LoggerFactory.getLogger(SistemaDeposito.class);

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.posicionDao = PosicionDao.getInstance();
=======
import edu.uade.apd.tpo.dao.impl.ArticuloDao;
import edu.uade.apd.tpo.dao.impl.ItemLoteDao;
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
>>>>>>> develop
    }

    public static SistemaDeposito getInstance() {
        if (instance == null) {
            instance = new SistemaDeposito();
        }
        return instance;
    }
<<<<<<< HEAD
    
    public void liberarPosicion(String codigoUbicacion, int cantidad) {
    	Posicion posicion = posicionDao.findById(codigoUbicacion);
    	posicion.liberar(cantidad);
    }
    
    public void almacenar(Long articuloId, int cantidad) {
    	Articulo articulo = this.buscarArticulo(articuloId);
    	Stock stock = articulo.getStock();
    	stock.agregarMovimientoIngreso(MotivoIngreso.COMPRA, cantidad);
    }
    
    //Se pasa la cantidad para verificar con el articuloDao?
    //Es necesario que el ItemLote tenga cantidad?
    //No seria mejor que Lote sepa que cantidad tiene en posiciones?
    public List<ItemLote> obtenerLotesPorArticulo(Long articuloId, int cantidad) {
    	List<ItemLote> items = (List<ItemLote>) new ItemLote();
    	Articulo articulo = articuloDao.getInstance().findById(articuloId);
    	if(articulo.getStock().getCantidad() >= cantidad) {
    		List<Lote> lotes = articulo.getLotes();
    		for (Lote lote : lotes) {
				ItemLote item = new ItemLote();
				item.setLote(lote);
				items.add(item);
			}
    	}else {
    		return null;
    	}
    	return items;
    }
    
    public Articulo buscarArticulo(Long articuloId) {
    	return articuloDao.getInstance().findById(articuloId);
    }
=======

    /**
     * Obtiene la menor cantidad de lotes necesarios
     * @param articuloId
     * @param cant
     * @return
     */
    public List<ItemLote> obtenerLotesPorArticulo(Long articuloId, int cant) {
        List<ItemLote> result = new ArrayList<>();
        int cantidadParcial = 0;
        List<ItemLote> itemLotes = itemLoteDao.findByArticuloId(articuloId);
        Iterator<ItemLote> it = itemLotes.iterator();
        while (cantidadParcial < cant && it.hasNext()) {
            ItemLote il = it.next();
            cantidadParcial += il.getCantidad();
            result.add(il);
        }
        return result;
    }

    public Articulo buscarArticulo(Long articuloId) {
        return articuloDao.findById(articuloId);
    }

>>>>>>> develop
}
