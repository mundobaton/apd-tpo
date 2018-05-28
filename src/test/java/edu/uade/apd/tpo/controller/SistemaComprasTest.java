package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.dao.ProveedorDaoTest;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Proveedor;
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
        sistema.generarOrdenCompra(30L, 45L);
    }

    @Test
    public void testBuscarOrdenCompra() throws BusinessException {
        OrdenCompra oc = sistema.buscarOrdenCompra(25L);
        Assert.assertNotNull(oc);
    }

    @Test
    //Este metodo solo hay que llamarlo luego de reaizar el ingreso de una compra en deposito
    public void testAceptarOrdenCompra() throws BusinessException {
        sistema.aceptarOrdenCompra(27L);
    }

    @Test
    public void testObtenerOrdenesDeCompraEmitidas() {
        List<OrdenCompra> ocs = sistema.obtenerOrdenesDeCompraEmitidas();
        Assert.assertNotNull(ocs);
    }

    @Test
    public void testProcesarOrdenesComprasPendientes() {
        sistema.procesarOrdenesCompraPendientes();
    }

    @Test
    public void testCrearProveedor(){
        Proveedor p = new Proveedor();
        p.setTelefono("48987878");
        p.setCuit("20090998892");
        p.setNombre("Don Fermin");
        p.guardar();
    }
}
