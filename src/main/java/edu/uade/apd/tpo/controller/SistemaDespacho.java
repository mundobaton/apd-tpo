package edu.uade.apd.tpo.controller;

import java.util.List;

import edu.uade.apd.tpo.dao.impl.PedidoDao;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.Pedido;

public class SistemaDespacho {

	private static SistemaDespacho instance;
	private PedidoDao pedidoDao;

	private SistemaDespacho() {
		this.pedidoDao = PedidoDao.getInstance();
	}

	public static SistemaDespacho getInstance() {
	     if (instance == null) {
	            instance = new SistemaDespacho();
	        }
	        return instance;
	}
	
	public void despacharPedido(Long pedidoId) {

		Pedido p = buscarPedido(pedidoId);

		for(ItemPedido item : p.getItems()){



		}


		SistemaFacturacion.getInstance().facturar(pedidoId);
	}
	
	public List<Pedido> obtenerPedidosCompletos(){
		return pedidoDao.obtenerPedidosCompletos();
	}
	
	public Pedido buscarPedido(Long pedidoId) {
		return pedidoDao.findById(pedidoId);
	}
	
	
	
}
