package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.OrdenCompraDao;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.EstadoCompra;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;

public class SistemaCompras {

	private static SistemaCompras instance;
	private OrdenCompraDao ordenCompraDao;
	private ArticuloDao articuloDao;
	private PedidoDao pedidoDao;

	private SistemaCompras() {
		this.ordenCompraDao = OrdenCompraDao.getInstance();
		this.articuloDao = ArticuloDao.getInstance();
		this.pedidoDao = PedidoDao.getInstance();
	}

	public static SistemaCompras getInstance() {
	     if (instance == null) {
	            instance = new SistemaCompras();
	        }
	        return instance;
	}
	
	public void generarOrdenCompra(Long articuloId, Long pedidoId) {
		Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
		OrdenCompra ordenCompra = new OrdenCompra();
		Articulo articulo = SistemaAdministracion.getInstance().buscarArticulo(articuloId);
		ordenCompra.setArticulo(articulo);
		ordenCompra.setEstado(EstadoCompra.PENDIENTE);
		ordenCompra.setPedido(pedido);
		ordenCompra.guardar();
	}
	
	public OrdenCompra buscarOrdenDeCompra(Long ordenId) {
		return ordenCompraDao.getInstance().findById(ordenId);
		
	}

	public void aceptarOrdenCompra(Long ordenId) {
		OrdenCompra orden =  buscarOrdenDeCompra(ordenId);
		orden.setEstado(EstadoCompra.ACEPTADA);
		orden.guardar();
	}
	
	
	
	
}
