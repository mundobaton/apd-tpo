package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SistemaComprasTest {

    private SistemaCompras sistema;

    @Before
    public void setup() {
        sistema = SistemaCompras.getInstance();

    }

    @Test
    public void testGenerarOrdenCompra() throws BusinessException {
        sistema.generarOrdenCompra(1L, 2L);
    }

    @Test
    public void testBuscarOrdenCompra() throws BusinessException {
        OrdenCompra oc = sistema.buscarOrdenCompra(1L);
        Assert.assertNotNull(oc);
    }

    @Test
    public void testAceptarOrdenCompra() throws BusinessException {
        sistema.aceptarOrdenCompra(1L);
    }

    //TODO esta trayendo problemas con el Enum
    @Test
    public void testObtenerOrdenesDeCompraEmitidas() {
        List<OrdenCompra> ocs = sistema.obtenerOrdenesDeCompraEmitidas();
        Assert.assertNotNull(ocs);
    }

    @Test
    public void testProcesarOrdenesComprasPendientes() {
        sistema.procesarOrdenesCompraPendientes("un nombre prov", "123123", "4123-4567");
    }
}
