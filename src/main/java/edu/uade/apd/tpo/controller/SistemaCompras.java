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

	private SistemaCompras() {
		this.ordenCompraDao = OrdenCompraDao.getInstance();
	}

	public static SistemaCompras getInstance() {
	     if (instance == null) {
	            instance = new SistemaCompras();
	        }
	        return instance;
	}
	
	public void generarOrdenCompra(Long articuloId, Long pedidoId) {
		//TODO implementar las excepciones
		Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
		Articulo articulo = SistemaDeposito.getInstance().buscarArticulo(articuloId);
		OrdenCompra ordenCompra = new OrdenCompra();
		ordenCompra.setArticulo(articulo);
		ordenCompra.setEstado(EstadoCompra.PENDIENTE);
		ordenCompra.setPedido(pedido);
		ordenCompra.guardar();
	}

	public OrdenCompra buscarOrdenCompra(Long ordenId) {
		return ordenCompraDao.getInstance().findById(ordenId);
	}

	public void aceptarOrdenCompra(Long ordenId) {
		//TODO implementar excepcion
		OrdenCompra orden =  buscarOrdenCompra(ordenId);
		orden.setEstado(EstadoCompra.ACEPTADA);
		orden.guardar();
	}

}
