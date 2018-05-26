package edu.uade.apd.tpo.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.entity.ItemLoteEntity;
import edu.uade.apd.tpo.entity.ItemPedidoEntity;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.Lote;
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
	
	public Pedido buscarPedido(Long pedidoId) {
		return SistemaAdministracion.getInstance().buscarPedido(pedidoId);
	}
	
	public void despacharPedido(Long pedidoId) {
		Pedido pedido = buscarPedido(pedidoId);
		List<ItemPedido> items = pedido.getItems();
		for(ItemPedido item : items) {
			Articulo articulo = item.getArticulo();
			int cantidadVenta = item.getCantidad();
			articulo.vender(cantidadVenta);
			List<ItemLote> itemLotes = item.getLotes();
			for(ItemLote itemLote : itemLotes) {
				Lote lote = itemLote.getLote();
				List<Posicion> posiciones = lote.getPosiciones();
				int cantidadLote = itemLote.getCantidad();
				int index = 0;
				while(!posiciones.isEmpty() && cantidadLote > 0) {
					String ubicacion = posiciones.get(index).getCodUbicacion();
					cantidadLote -= SistemaDeposito.getInstance().liberarPosicion(ubicacion, cantidadLote);
					index ++;
				}
			}
		}
		pedido.setFechaDepacho(new Date());
		SistemaFacturacion.getInstance().facturar(pedidoId);
	}
	
	public void alistarPedido(Long idPedido) {
		Pedido pedido = buscarPedido(idPedido);
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
