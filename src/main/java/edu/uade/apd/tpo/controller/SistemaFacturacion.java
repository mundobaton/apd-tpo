package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Remito;

public class SistemaFacturacion {

    private static SistemaFacturacion instance;

    private SistemaFacturacion() {

    }

    public static SistemaFacturacion getInstance() {
        if (instance == null) {
            instance = new SistemaFacturacion();
        }
        return instance;
    }

    public void facturar(Long pedidoId) throws BusinessException {
        Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
        pedido.facturar();

        Remito remito = new Remito(pedido.getItems(), pedido.getDomicilio());
        remito.guardar();
    }

    public void pagarFactura(Long facturaId, Float importe) throws BusinessException {
        Factura factura = this.obtenerFactura(facturaId);
        if (factura == null) {
            throw new BusinessException("No se encontro la factura '" + facturaId + "'");
        }
        factura.pagar(importe);
    }

    private Factura obtenerFactura(Long facturaId) {
        return FacturaDao.getInstance().findById(facturaId);
    }
}
