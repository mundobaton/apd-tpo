package edu.uade.apd.tpo.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.entity.ItemPedidoEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.MotivoEgreso;
import edu.uade.apd.tpo.model.Remito;
import edu.uade.apd.tpo.model.Transportista;

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

		PedidoEntity p = buscarPedido(pedidoId);

		for(ItemPedidoEntity item : p.getItems()){

			item.getArticulo().getStock().agregarMovimientoEgreso(MotivoEgreso.VENTA, item.getCantidad());

			for(ItemLoteEntity lote : item.getLotes()){
				for(PosicionEntity posicion : lote.getLote().getPosiciones()){
					//SistemaDeposito.getInstance().liberarPosicion(posicion.getCodigoUbicacion(), item.getCantidad());
				}
			}
			item.getArticulo().guardar();
		}
		SistemaFacturacion.getInstance().facturar(pedidoId);
	}
	
	public List<PedidoEntity> obtenerPedidosCompletos(){
		return pedidoDao.obtenerPedidosCompletos();
	}
	
	public PedidoEntity buscarPedido(Long pedidoId) {
		return pedidoDao.findById(pedidoId);
	}
	
	public void alistarPedido(Long idPedido) {
		PedidoEntity pedido = buscarPedido(idPedido);
		Remito remito = SistemaFacturacion.getInstance().crearRemito();
		Factura factura = SistemaFacturacion.getInstance().crearFactura();
		Transportista transportista = seleccionarTransportista();
		pedido.setFactura(factura);
		pedido.pedidoListo(remito, transportista);
	}
	
	private Transportista seleccionarTransportista() {
		Random random = new Random();
    	int index = random.nextInt(2);
    	return Transportista.values()[index];
	}
}
