package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.impl.FacturaDao;
import edu.uade.apd.tpo.dao.impl.PedidoDao;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Remito;
import edu.uade.apd.tpo.model.Transaccion;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SistemaFacturacion {

	private static SistemaFacturacion instance;
	private PedidoDao pedidoDao;
	private FacturaDao facturaDao;

	private SistemaFacturacion() {
		this.pedidoDao = PedidoDao.getInstance();
		this.facturaDao = FacturaDao.getInstance();
	}

	public static SistemaFacturacion getInstance() {
		if (instance == null) {
			instance = new SistemaFacturacion();
		}
		return instance;
	}

	private Pedido buscarPedido(Long pedidoId) {
		return pedidoDao.findById(pedidoId);
	}

	public float procesarPago(String email, float importe, MedioPago mp, float saldo, float limite) {
		List<Factura> facturas = this.obtenerFacturasImpagasCliente(email);
		float importeRestante = (saldo + limite) + importe;
		
		//Ya vienen ordenadas desde la db
		for(Factura f : facturas) {
			if(f.getTotal() <= importeRestante) {
				Transaccion t = new Transaccion();
				t.setFecha(new Date());
				t.setImporte(f.getTotal());
				t.setMedioPago(mp);
				t.addFactura(f);
				f.setTransaccion(t);
				f.guardar();
				importeRestante -= t.getImporte();
			}
		}
		
		//devolvemos el importe que le sobro del pago de las facturas menos el limite que le extendimos
		return (importeRestante - limite);
	}

	public float procesarPago(Long facturaId, float importe, MedioPago mp, float saldo, float limite) {
		
		Factura f = obtenerFacturaById(facturaId);
		float importeRestante = saldo + limite + importe;
		if(f.getTotal() <= importeRestante){
			Transaccion t = new Transaccion();
			t.setFecha(new Date());
			t.setImporte(f.getTotal());
			t.setMedioPago(mp);
			t.addFactura(f);
			f.setTransaccion(t);
			f.guardar();
			importeRestante -= t.getImporte();
		}
		
		return importeRestante - limite;
	}

	private List<Factura> obtenerFacturasImpagasCliente(String email) {

		List<Factura> facturas = this.obtenerFacturasCliente(email);
		Iterator<Factura> iterator = facturas.iterator();

		while (iterator.hasNext()) {
			Factura f = iterator.next();
			if (f.getTransaccion() != null) {
				iterator.remove();
			}
		}

		return facturas;
	}

	private Factura obtenerFacturaById(Long facturaId){
		return FacturaDao.getInstance().findById(facturaId);
	}
	
	private List<Factura> obtenerFacturasCliente(String email) {
		return facturaDao.obtenerFacturasCliente(email);
	}

}
