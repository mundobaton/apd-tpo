package edu.uade.apd.tpo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.dao.RemitoDao;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.FacturaTipo;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Remito;
import edu.uade.apd.tpo.model.Transaccion;

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
	
	public Factura buscarFactura(Long facturaId) {
		return facturaDao.getInstance().findById(facturaId);
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
	
	public float procesarPago(Long facturaId, float importe, MedioPago mp, float saldo, float limiteCred) {
		Factura factura = buscarFactura(facturaId);
		float importeRestante = saldo + limiteCred + importe;
		float total = factura.getTotal();
		if(total <= importeRestante) {
			Transaccion transaccion = new Transaccion();
			transaccion.setFecha(new Date());
			transaccion.setImporte(total);
			transaccion.setMedioPago(mp);
			transaccion.agregarFactura(factura);
			factura.setTransaccion(transaccion);
			factura.guardar();
		}
		return importeRestante - limiteCred; 
	}
	
	public List<Factura> obtenerFacturasImpagas(Long cuil){
		Cliente cliente = SistemaAdministracion.getInstance().buscarCliente(cuil);
		List<Pedido> pedidos = cliente.getPedidos();
		List<Factura> facturasImpagas = new ArrayList();
		for(Pedido pedido : pedidos) {
			if(pedido.getFactura().getTransaccion() == null){
				facturasImpagas.add(pedido.getFactura());
			}
		}
		return facturasImpagas;
	}
	
	public float procesarPagoImporte(Long cuil, float importe, MedioPago mp, float saldo, float limiteCred) {
		List<Factura> facturas = obtenerFacturasImpagas(cuil);
		float importeRestante = saldo + limiteCred + importe;
		for(Factura factura : facturas) {
			float total = factura.getTotal();
			if(total <= importeRestante) {
				Transaccion transaccion = new Transaccion();
				transaccion.setFecha(new Date());
				transaccion.setImporte(total);
				transaccion.setMedioPago(mp);
				transaccion.agregarFactura(factura);
				factura.setTransaccion(transaccion);
				importeRestante -= total;
			}
		}
		return importeRestante - limiteCred; 
	}

}
