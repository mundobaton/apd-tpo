package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.OrdenCompraDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SistemaCompras {

    private static SistemaCompras instance;
    private OrdenCompraDao ordenCompraDao;
    private static final Logger logger = LoggerFactory.getLogger(SistemaCompras.class);

    private SistemaCompras() {
        this.ordenCompraDao = OrdenCompraDao.getInstance();
    }

    public static SistemaCompras getInstance() {
        if (instance == null) {
            instance = new SistemaCompras();
        }
        return instance;
    }

    public void generarOrdenCompra(ItemPedido ip, Pedido pedido) {
        List<OrdenCompra> ocs = OrdenCompraDao.getInstance().buscarPorItemPedido(ip.getId(), pedido.getId());
        if (ocs == null || ocs.isEmpty()) {
            OrdenCompra oc = new OrdenCompra(ip, pedido);
            oc = oc.guardar();
            logger.info("Se ha generado la orden de compra '" + oc.getId() + "' exitosamente");
        }
    }

    public void procesarOrdenCompra(Long ordenCompraId) throws BusinessException {
        OrdenCompra oc = buscarOrdenCompra(ordenCompraId);
        if (oc == null) {
            throw new BusinessException("La orden de compra '" + ordenCompraId + "' no existe");
        }
        oc.procesar();
        logger.info("Se ha procesado la orden de compra '" + oc.getId() + "' exitosamente");
    }

    public void procesarOrdenesCompra() throws BusinessException {
        for (OrdenCompra oc : buscarOrdenesCompraPendientes()) {
            oc.procesar();
        }
    }

    private OrdenCompra buscarOrdenCompra(Long ordenCompraId) {
        return ordenCompraDao.findById(ordenCompraId);
    }

    public List<OrdenCompra> obtenerOrdenesCompraPendientesPorArticulo(Long articuloId) {
        return ordenCompraDao.buscarPendientesPorArticulo(articuloId);
    }

    private List<OrdenCompra> buscarOrdenesCompraPendientes() {
        return ordenCompraDao.findByEstado('P');
    }

}
