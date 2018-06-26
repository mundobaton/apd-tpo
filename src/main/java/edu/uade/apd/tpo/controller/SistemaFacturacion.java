package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.EstadoPedido;
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
        if (pedido.getEstado() != EstadoPedido.A_FACTURAR) {
            throw new BusinessException("El pedido no se encuentra en estado A FACTURAR, estado actual: '" + pedido.getEstado() + "'");
        }
        Factura factura = new Factura(pedido);
        factura.guardar();

        Remito remito = new Remito(pedido.getItems(), pedido.getDomicilio());
        remito.guardar();
    }
}
