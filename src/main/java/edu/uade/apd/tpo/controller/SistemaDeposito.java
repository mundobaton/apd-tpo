package edu.uade.apd.tpo.controller;


import edu.uade.apd.tpo.dao.impl.ArticuloDao;
import edu.uade.apd.tpo.dao.impl.ItemLoteDao;
import edu.uade.apd.tpo.dao.impl.PosicionDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.MotivoIngreso;
import edu.uade.apd.tpo.model.Posicion;
import edu.uade.apd.tpo.model.Stock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SistemaDeposito {

    private ArticuloDao articuloDao;
    private ItemLoteDao itemLoteDao;
    private PosicionDao posicionDao;

    private static SistemaDeposito instance;

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.itemLoteDao = ItemLoteDao.getInstance();
        this.posicionDao = PosicionDao.getInstance();
    }
    
    public void liberarPosicion(String codigoUbicacion, int cantidad) {
    	Posicion posicion = posicionDao.findById(codigoUbicacion);
    	posicion.liberar(cantidad);
    	posicionDao.save(posicion);
    }
    
    public void almacenar(Long articuloId, List<ItemLote> itemsLotes, int cantidad) {
    	Articulo articulo = this.buscarArticulo(articuloId);
    	Stock stock = articulo.getStock();
    	stock.agregarMovimientoIngreso(MotivoIngreso.COMPRA, cantidad);
    	List<Posicion> posiciones = posicionDao.obtenerObtenerPosicionesVacias();
    	if(posiciones.size() * 21 >= cantidad) {
	    	for (ItemLote item : itemsLotes) {
		    	int cantidadLote = item.getCantidad(); 
	    		while(cantidadLote > 0) {
		        	Posicion posicion = new Posicion();
		        	posicion.setLote(item.getLote());
		    		if(cantidad > 21) {
		    			posicion.setCantidad(21);
		    			cantidadLote = cantidadLote - 21;
		    		}else {
		    			posicion.setCantidad(cantidadLote);
		    	    	cantidadLote = 0;
		    		}
	    	    	posicionDao.save(posicion);
		    	}
	    	}
    	}
    }

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

}
