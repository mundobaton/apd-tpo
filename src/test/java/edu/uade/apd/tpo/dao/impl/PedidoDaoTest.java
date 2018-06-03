package edu.uade.apd.tpo.dao.impl;

import org.junit.Test;

public class PedidoDaoTest {

	private PedidoDao pedidoDao = PedidoDao.getInstance();
	
	@Test
	public void testListarPedidos(){
		pedidoDao.obtenerPedidosCompletos();
	}
	
}
