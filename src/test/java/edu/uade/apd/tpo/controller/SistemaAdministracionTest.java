package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.*;
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
        sistema.crearUsuario("erica@mail.com", "12345", Rol.TODOS);
    }

    @Test
    public void testCrearCliente() throws BusinessException {
        sistema.crearCliente(1235431125L, "test4@otrocliente.com", "123", "fulano", "4123-4567", "una calle", 1234L, "1406", "Capital", "Bsas", CondicionIva.CONS_FINAL, Zona.CABA, 1000, 500);
    }

    @Test
    public void testCrearPedido() throws BusinessException {
        Pedido p = sistema.generarPedido(1235431125L, "una calle", 123L, "1406", "Capital", "Buenos Aires", Zona.CABA);
        Assert.assertNotNull(p.getId());
    }

    @Test
    public void testAgregarItemPedido() throws BusinessException {
        sistema.agregarItemPedido(39L, 1235431125L, 16L, 700);
        sistema.agregarItemPedido(39L, 1235431125L, 13L, 200);
    }

    @Test
    public void testCerrarPedido() throws BusinessException {
        sistema.cerrarPedido(38L, 30329616250L);
    }

    @Test
    public void testAprobarPedido() throws BusinessException {
        sistema.aprobarPedido(38L, 30329616250L, "");
    }

    @Test
    public void testCrearArticulo(){
        Articulo art = sistema.crearArticulo("54209988", "Berenjena", "Bolsa", "1kg", 15000, 1, 70);
        Assert.assertNotNull(art);
    }
}
