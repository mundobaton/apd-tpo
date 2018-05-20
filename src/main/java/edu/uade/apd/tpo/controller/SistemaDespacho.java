package edu.uade.apd.tpo.controller;

import java.util.List;

import edu.uade.apd.tpo.dao.impl.PedidoDao;
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
		
	}
	
	public List<Pedido> obtenerPedidosCompletos(){

		List<Pedido> pedidos = pedidoDao.obtenerPedidosCompletos();


		return null;
		
	}
	
	public Pedido buscarPedido(Long pedidoId) {
		return pedidoDao.findById(pedidoId);
	}
	
	
	
}
