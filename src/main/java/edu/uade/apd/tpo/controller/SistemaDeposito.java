package edu.uade.apd.tpo.controller;

import java.util.Date;
import java.util.List;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.Lote;
import edu.uade.apd.tpo.model.MotivoEgreso;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Posicion;
import edu.uade.apd.tpo.model.Stock;

public class SistemaDeposito {


	private static SistemaDeposito instance;
	private ArticuloDao articuloDao;

	private SistemaDeposito() {
		this.articuloDao = ArticuloDao.getInstance();
	}

	public static SistemaDeposito getInstance() {
	     if (instance == null) {
	            instance = new SistemaDeposito();
	        }
	        return instance;
	}
	
	public void completarPedido(Long pedidoId) {
		int cantidad = 0;
		Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
		List<ItemPedido> items = pedido.getItems();
		for(ItemPedido item : items) {
			Articulo articulo = item.getArticulo();
			int cantidadItem = item.getCantidad();
			Stock stock = articulo.getStock();
			stock.agregarMovimientoEgreso(MotivoEgreso.VENTA, cantidadItem);
			while(cantidadItem >= 0) {
				List<Posicion> posiciones = extraerArticulosPosicion(articulo);
			}
		}
	}
	
	private List<Posicion> extraerArticulosPosicion(Articulo articulo){
		Date fechaVtoAnterio = articulo.getLotes().get(0).getFechaVto();
		List<Posicion> posiciones;
		for(Lote lote : articulo.getLotes()) {
			if(fechaVtoAnterio.compareTo(lote.getFechaVto()) > 0 ) {
				fechaVtoAnterio = lote.getFechaVto();
				posiciones = lote.getPosiciones();
			}
		}
	}
}
