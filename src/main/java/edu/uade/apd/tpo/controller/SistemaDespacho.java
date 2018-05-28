package edu.uade.apd.tpo.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Transportista;

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

    public List<OrdenCompra> obtenerOrdenesDeCompraEmitidas() {
        return SistemaCompras.getInstance().obtenerOrdenesDeCompraEmitidas();
    }

    public Pedido buscarPedido(Long pedidoId) {
        return SistemaAdministracion.getInstance().buscarPedido(pedidoId);
    }

    public void despacharPedido(Long pedidoId) {
        Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
        pedido.setFechaDespacho(new Date());
        Date fechaEnvio = new Date();
        Calendar f = Calendar.getInstance();
        f.setTime(fechaEnvio);
        f.add(Calendar.DATE, 4);
        fechaEnvio = f.getTime();
        pedido.setFechaEntrega(fechaEnvio);
        notificarFechaDeEnvioAsignada(fechaEnvio);
        pedido.enviado();
        pedido.guardar();
    }

    public void alistarPedido(Long idPedido) throws BusinessException {
        Cliente cli = SistemaAdministracion.getInstance().obtenerClientePorPedido(idPedido);
        if(cli == null) throw new BusinessException("No existe un cliente con el pedido "+idPedido);

        Pedido pedido = cli.obtenerPedido(idPedido);
        if(pedido == null) throw new BusinessException("Pedido no encontrado.");

        pedido.alistar(seleccionarTransportista());
        cli.guardar();

        SistemaFacturacion.getInstance().facturar(pedido.getId());
    }

    private Transportista seleccionarTransportista() {
        Random random = new Random();
        int index = random.nextInt(2);
        return Transportista.values()[index];
    }

    public List<Pedido> obtenerPedidosCompletos() {
        return SistemaAdministracion.getInstance().obtenerPedidoCompletos();
    }

    public void notificarFechaDeEnvioAsignada(Date fechaEnvio) {

    }
}
