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
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.MotivoEgreso;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Posicion;
import edu.uade.apd.tpo.model.Remito;
import edu.uade.apd.tpo.model.Transportista;

public class SistemaDespacho {
	
	private static SistemaDespacho instance;

	private SistemaDespacho() {
	}

	public static SistemaDespacho getInstance() {
	     if (instance == null) {
	            instance = new SistemaDespacho();
	        }
	        return instance;
	}
	
	public void despacharPedido(Long pedidoId) {

		PedidoEntity p = buscarPedido(pedidoId);
		Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
		
		for(ItemPedido item : pedido.getItems()){

			item.getArticulo().getStock().agregarMovimientoEgreso(MotivoEgreso.VENTA, item.getCantidad());

			for(ItemLote lote : item.getLotes()){
				for(Posicion posicion : lote.getLote().getPosiciones()){
					//SistemaDeposito.getInstance().liberarPosicion(posicion.getCodigoUbicacion(), item.getCantidad());
				}
			}
			item.getArticulo().guardar();
		}
		SistemaFacturacion.getInstance().facturar(pedidoId);
	}
	
	public void alistarPedido(Long idPedido) {
		Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
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
