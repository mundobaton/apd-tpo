package edu.uade.apd.tpo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.dao.RemitoDao;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.exception.BusinessException;
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
		FacturaEntity entity = facturaDao.getInstance().findById(facturaId);
		Factura factura = Factura.fromEntity(entity);
		return factura;
	}
	
	public void facturar(Long pedidoId) throws BusinessException{
		Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
		if(pedido == null) throw new BusinessException("No existe el pedido nro. "+pedidoId);

		Cliente cliente = SistemaAdministracion.getInstance().obtenerClientePorPedido(pedido.getId());
		if(cliente == null) throw new BusinessException("No existe el cliente asociado.");

		Factura factura = new Factura();
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
			factura.guardar();
		}
		return importeRestante - limiteCred; 
	}
	
	public List<Factura> obtenerFacturasImpagas(Long cuil){
		Cliente cliente = SistemaAdministracion.getInstance().buscarCliente(cuil);
		List<Pedido> pedidos = cliente.getPedidos();
		List<Factura> facturasImpagas = new ArrayList();
		for(Pedido pedido : pedidos) {
			//TODO Buscar si la factura tiene transaccion asociada
			/*
			if(pedido.getFactura().getTransaccion() == null){
				facturasImpagas.add(pedido.getFactura());
			}
			*/
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
				importeRestante -= total;
			}
		}
		return importeRestante - limiteCred; 
	}

}
