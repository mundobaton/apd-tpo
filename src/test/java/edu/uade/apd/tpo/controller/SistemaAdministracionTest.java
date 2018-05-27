package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.CondicionIva;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.Zona;
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
    public void testCrearUsuario() {
        sistema.crearUsuario("test@otrotest.com", "12345", Rol.FACTURACION);
    }

    @Test
    public void testCrearCliente() throws BusinessException {
        sistema.crearCliente(1235431124L, "test3@otrocliente.com", "123", "fulano", "4123-4567", "una calle", 1234L, "1406", "Capital", "Bsas", CondicionIva.CONS_FINAL, Zona.CABA, 1000, 500);
    }

    @Test
    public void testCrearPedido() throws BusinessException {
        Pedido p = sistema.generarPedido(1235431124L, "una calle", 123L, "1406", "Capital", "Buenos Aires", Zona.CABA);
        Assert.assertNotNull(p.getId());
    }

    @Test
    public void testAgregarItemPedido() throws BusinessException {
        sistema.agregarItemPedido(36L, 1235431124L, 16L, 700);
    }

    @Test
    public void testCerrarPedido() throws BusinessException {
        sistema.cerrarPedido(36L, 1235431124L);
    }
}
