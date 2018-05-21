package edu.uade.apd.tpo.controller;


import edu.uade.apd.tpo.dao.impl.ArticuloDao;
import edu.uade.apd.tpo.dao.impl.ItemLoteDao;
import edu.uade.apd.tpo.dao.impl.OrdenCompraDao;
import edu.uade.apd.tpo.dao.impl.PosicionDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.Lote;
import edu.uade.apd.tpo.model.MotivoIngreso;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Posicion;
import edu.uade.apd.tpo.model.Stock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SistemaDeposito {

    private ArticuloDao articuloDao;
    private ItemLoteDao itemLoteDao;
    private PosicionDao posicionDao;
    private OrdenCompraDao ordenCompraDao;

    private static SistemaDeposito instance;

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.itemLoteDao = ItemLoteDao.getInstance();
        this.posicionDao = PosicionDao.getInstance();
        this.ordenCompraDao = OrdenCompraDao.getInstance();
    }
    
    public static SistemaDeposito getInstance() {
        if (instance == null) {
            instance = new SistemaDeposito();
        }
        return instance;
    }
    
    public void liberarPosicion(String codigoUbicacion, int cantidad) {
    	Posicion posicion = posicionDao.findById(codigoUbicacion);
    	posicion.liberar(cantidad);
    	posicion.guardar();
    }
    
    public void ingresarCompra(Long ordenId, List<ItemLote> itemLotes) {
    	OrdenCompra orden = SistemaCompras.getInstance().buscarOrdenCompra(ordenId);
    	Articulo articulo = orden.getArticulo();
    	int cantCompra = orden.getArticulo().getCantCompra();
    	almacenar(articulo, itemLotes, cantCompra);
    	SistemaCompras.getInstance().aceptarOrdenCompra(orden.getId());
    }
    
    public void almacenar(Articulo articulo, List<ItemLote> itemLotes, int cantidad) {
    	List<Lote> lotes = articulo.getLotes();
    	Stock stock = articulo.getStock();
    	stock.agregarMovimientoIngreso(MotivoIngreso.COMPRA, cantidad);
    	articulo.setStock(stock);
    	List<Posicion> posiciones = posicionDao.obtenerObtenerPosicionesVacias();
    	if(posiciones.size() * 21 >= cantidad) {
	    	for (ItemLote item : itemLotes) {
		    	int cantidadLote = item.getCantidad(); 
		    	lotes.add(item.getLote());
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
	    	    	posicion.guardar();
		    	}
	    	}
	    	articulo.setLotes(lotes);
	    	articulo.guardar();
    	}else {
    		//TODO Arrojar exception porque no hay lugar suficiente
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
