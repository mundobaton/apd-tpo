package edu.uade.apd.tpo.controller;

import java.util.Date;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.dao.RemitoDao;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.FacturaTipo;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Remito;

public class SistemaFacturacion {
	
	private static SistemaFacturacion instance;
	private FacturaDao facturaDao;
	private RemitoDao remitoDao;
	
	private SistemaFacturacion() {
		this.facturaDao = FacturaDao.getInstance();
		this.remitoDao = RemitoDao.getInstance();
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
		if(cliente.getCondIva() == CondicionIva.CONS_FINAL) {
			factura.setTipo(FacturaTipo.B);
		}else {
			factura.setTipo(FacturaTipo.A);
		}
		float costoEnvio = pedido.calcularCostoEnvio();
		factura.setCostoEnvio(costoEnvio);
		float subTotal = pedido.obtenerTotal();
		float impuesto = subTotal * Factura.getIMPUESTOS();
		float total = subTotal + costoEnvio + impuesto;
		factura.setTotal(total);
		pedido.setFactura(factura);
		Remito remito = new Remito();
		remito.setFecha(new Date());
		pedido.getEnvio().setRemito(remito);
		pedido.setFechaEntrega(new Date());
		pedido.guardar();
		factura.guardar();
		remito.guardar();
	}

}
