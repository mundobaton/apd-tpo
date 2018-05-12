package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.impl.FacturaDao;
import edu.uade.apd.tpo.dao.impl.PedidoDao;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Pedido;

import java.util.List;

public class SistemaFacturacion {

    private static SistemaFacturacion instance;
    private PedidoDao pedidoDao;
    private FacturaDao facturaDao;

    private SistemaFacturacion() {
        this.pedidoDao = PedidoDao.getInstance();
        this.facturaDao = FacturaDao.getInstance();
    }

    public static SistemaFacturacion getInstance() {
        if (instance == null) {
            instance = new SistemaFacturacion();
        }
        return instance;
    }

    private Pedido buscarPedido(Long pedidoId) {
        return pedidoDao.findById(pedidoId);
    }

    public float procesarPago(String email, float importe, MedioPago mp, float limite) {
        List<Factura> facturas = this.obtenerFacturasCliente(email);




        return 0;
    }

    private List<Factura> obtenerFacturasCliente(String email) {
        //TODO Filtrar el resultado y devolver facturas impagas
        return facturaDao.obtenerFacturasCliente(email);
    }

}
