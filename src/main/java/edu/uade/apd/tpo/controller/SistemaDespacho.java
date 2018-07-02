package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.Pedido;

import java.util.List;
import java.util.Random;

public class SistemaDespacho {

    private static SistemaDespacho instance;
    private static final String[] TRANSPORTISTAS = {"Perez y Hnos.", "El Rápido S.A", "El Efectivo S.R.L"};

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

    public List<Pedido> obtenerPedidosCompletos() {
        return SistemaAdministracion.getInstance().findPedidosByEstado(EstadoPedido.COMPLETO);
    }

    public String getTransportista() {
        Random random = new Random();
        return TRANSPORTISTAS[random.nextInt(3)];
    }
}
