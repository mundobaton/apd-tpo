package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class SistemaDespachoTest {

    private SistemaDespacho sistema;

    @Before
    public void setup() {
        sistema = SistemaDespacho.getInstance();
    }

    @Test
    public void testObtenerOrdenesDeCompraEmitidas() {
        List<OrdenCompra> oc = sistema.obtenerOrdenesDeCompraEmitidas();
        Assert.assertNotNull(oc);
    }

    @Test
    public void testBuscarPedido() {
        Pedido p = sistema.buscarPedido(1L);
        Assert.assertNotNull(p);
    }

    @Test
    public void testDespacharPedido() {
        sistema.despacharPedido(3L);
    }

    @Test
    public void testAlistarPedido() throws BusinessException {
        sistema.alistarPedido(45L);//probemos de nuevo
    }

    @Test
    public void testObtenerPedidosCompletos() {
        List<Pedido> completos = sistema.obtenerPedidosCompletos();
        Assert.assertNotNull(completos);
    }

    @Test
    public void testNotificarFechaDeEnvioAsignada() {
        sistema.notificarFechaDeEnvioAsignada(new Date());
    }
}
//Varias