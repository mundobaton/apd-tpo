package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.OrdenCompraDao;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.exception.BusinessException;
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

	public void generarOrdenCompra(Long articuloId, Long pedidoId) throws BusinessException {
		Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
		Articulo articulo = SistemaDeposito.getInstance().buscarArticulo(articuloId);
		if (pedido != null) {
			if (articulo != null) {
				OrdenCompra ordenCompra = new OrdenCompra();
				ordenCompra.setArticulo(articulo);
				ordenCompra.setEstado(EstadoCompra.PENDIENTE);
				ordenCompra.setPedido(pedido);
				ordenCompra.guardar();
			} else {
				throw new BusinessException("No existe articulo");
			}
		} else {
			throw new BusinessException("No existe pedido");
		}
	}

	public OrdenCompra buscarOrdenCompra(Long ordenId) {
		return ordenCompraDao.getInstance().findById(ordenId);
	}

	public void aceptarOrdenCompra(Long ordenId) throws BusinessException {
		OrdenCompra orden = buscarOrdenCompra(ordenId);
		if (orden != null) {
			orden.setEstado(EstadoCompra.ACEPTADA);
			orden.guardar();
		} else {
			throw new BusinessException("No existe orden");
		}
	}

}
