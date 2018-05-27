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
        sistema.crearCliente(30329616250L, "erica@cliente.com", "123", "Erica Nunez", "4123-4567", "Independencia", 467L, "1099", "Capital", "Bs As", CondicionIva.CONS_FINAL, Zona.CABA, 5000, 500);
    }

    @Test
    public void testCrearPedido() throws BusinessException {
        Pedido p = sistema.generarPedido(30329616250L, "Cordoba", 2057L, "1120", "Capital", "Buenos Aires", Zona.CABA);
        Assert.assertNotNull(p.getId());
    }

    @Test
    public void testAgregarItemPedido() throws BusinessException {
        sistema.agregarItemPedido(38L, 30329616250L, 18L, 200);
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
