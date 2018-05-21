package edu.uade.apd.tpo.controller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaDespachoTest {
	
    private SistemaDespacho sistemaDespacho = SistemaDespacho.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(SistemaDespacho.class);
    
    @Test
    public void testDespacharPedido() {
    		Long pedidoId = new Long(1);
    		sistemaDespacho.despacharPedido(pedidoId);
    }
    
    @Test
    public void testObtenerPedidosCompletos() {
    		sistemaDespacho.obtenerPedidosCompletos();
    }
    
    @Test
    public void testBuscarPedido() {
    		Long pedidoId = new Long(1);
    		sistemaDespacho.buscarPedido(pedidoId);
    }

}
