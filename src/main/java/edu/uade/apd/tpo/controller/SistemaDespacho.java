package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Pedido;

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

    public void despacharPedido(Long pedidoId) throws BusinessException {
        Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
        pedido.despachar();
    }

}
