package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.EstadoPedido;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Remito;

import java.util.List;
import java.util.stream.Collectors;

public class SistemaFacturacion {

    private static SistemaFacturacion instance;
    private FacturaDao facturaDao;

    private SistemaFacturacion() {
        this.facturaDao = FacturaDao.getInstance();
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

    public void pagarFactura(Long facturaId, Float importe, Long clienteId) throws BusinessException {
        Cliente cliente = SistemaAdministracion.getInstance().buscarClienteById(clienteId);
        cliente.acreditarImporte(importe);
        cliente.guardar();

        Factura factura = this.obtenerFactura(facturaId);
        if (factura == null) {
            throw new BusinessException("No se encontro la factura '" + facturaId + "'");
        }
        factura.pagar();
    }

    public Factura obtenerFactura(Long facturaId) {
        return facturaDao.findById(facturaId);
    }

    public List<Pedido> obtenerPedidosFacturar() {
        return SistemaAdministracion.getInstance().findPedidosByEstado(EstadoPedido.A_FACTURAR);
    }

    public void pagarImporte(Float importe, Long clienteId) throws BusinessException {
        Cliente cliente = SistemaAdministracion.getInstance().buscarClienteById(clienteId);
        cliente.acreditarImporte(importe);
        cliente.guardar();

        List<Factura> facturas = cliente.getCuentaCorriente().getFacturas().stream().filter(f -> f.getEstado() == 'P').collect(Collectors.toList());

        if (facturas != null && !facturas.isEmpty()) {
            for (Factura factura : facturas) {
                try {
                    factura.pagar();
                } catch (BusinessException be) {
                    //Continuo con otras facturas
                }
            }
        }
    }

    public List<Factura> obtenerFacturas() {
        return facturaDao.findAll();
    }
}
