package edu.uade.apd.tpo.controller;

import java.util.Date;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.FacturaTipo;
import edu.uade.apd.tpo.model.Pedido;

public class SistemaFacturacion {
	
	private static SistemaFacturacion instance;
	private FacturaDao facturaDao;
	
	private SistemaFacturacion() {
		this.facturaDao = FacturaDao.getInstance();
	}

	public static SistemaFacturacion getInstance() {
	     if (instance == null) {
	            instance = new SistemaFacturacion();
	        }
	        return instance;
	}
	
	public void facturar(Long pedidoId) {
		Factura factura = new Factura();
		Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
		Cliente cliente = pedido.getCliente();
		factura.setPedido(pedido);
		factura.setFecha(new Date());
		if(cliente.getCondIva()cliente == CondicionIva.CONS_FINAL) {
			factura.setTipo(FacturaTipo.B);
		}else {
			factura.setTipo(FacturaTipo.A);
		}
		

	}

}
