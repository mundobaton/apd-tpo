package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ClienteDao;
import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Rol;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaAdministracionTest {

    private SistemaAdministracion sistema;

    @Before
    public void setup() {
        sistema = SistemaAdministracion.getInstance();
    }

    @Test
    public void testCrearCliente() throws BusinessException {
        sistema.crearCliente("mundobaton@gmail.com", "Agustin", 12345L, "12345", "Fake st", "123", "Cap.Fed", "Buenos Aires", "1406", 500f, 300f);
    }

    @Test
    public void testCrearUsuario() throws BusinessException {
        sistema.crearUsuario("A12345", "qwerty", Rol.ADMINISTRACION);
    }

    @Test
    public void testCrearPedido() throws BusinessException {
        sistema.crearPedido(1L, "Otra direccion", "111", "Cap.Fed", "Buenos Aires", "1406");
    }

    @Test
    public void testAgregarItemPedido() throws BusinessException {
        sistema.agregarItemPedido(1L, 1L, 10);
        sistema.agregarItemPedido(1L, 2L, 15);
    }

    @Test
    public void testFinalizarCargaItems() throws BusinessException {
        sistema.finalizarCargaItems(1L);
    }

    @Test
    public void aprobarPedidoSinMensaje() throws BusinessException {
        sistema.aprobarPedido(7L);
    }

    @Test
    public void aprobarPedidoConMensaje() throws BusinessException {
        sistema.aprobarPedido(1L, "un mensaje peligroso");
    }

    @Test
    public void testBuscarCliente() {
        Cliente cliente = ClienteDao.getInstance().findByEmail("baton");
        Assert.assertNotNull(cliente);
    }

    @Test
    public void testBuscarPedido() {
        Pedido pedido = PedidoDao.getInstance().findById(2L);
        Assert.assertNotNull(pedido);
    }

    @Test
    public void testReponer() throws BusinessException {
        SistemaDeposito.getInstance().reponer(1L, 50);
    }


}
