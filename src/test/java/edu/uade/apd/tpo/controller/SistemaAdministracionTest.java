package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class SistemaAdministracionTest {

    private SistemaAdministracion sistema;

    @Before
    public void setup() {
        sistema = SistemaAdministracion.getInstance();
    }

    @Test
    public void testCrearUsuario() throws BusinessException {
        sistema.crearUsuario("erica@mail.com2", "12345", Rol.TODOS);
    }

    @Test
    public void testCrearCliente() throws BusinessException {
        sistema.crearCliente(27329616253L, "erica@cliente.com", "123", "Erica", "4123-4567", "Av Independencia", 1234L, "1406", "Capital", "Bsas", CondicionIva.CONS_FINAL, Zona.CABA, 5000, 500);
    }

    @Test
    public void testCrearPedido() throws BusinessException {
        Pedido p = sistema.generarPedido(27329616253L, "Av Independencia", 466L, "1099", "CABA", "Buenos Aires", Zona.CABA);
        System.out.print("Pedido id: "+p.getId());
        Assert.assertNotNull(p.getId());
    }

    @Test
    public void testAgregarItemPedido() throws BusinessException {
        sistema.agregarItemPedido(49L, 27329616253L, 37L, 15);
    }

    @Test
    public void testEliminarItemPedido() throws BusinessException{
        sistema.eliminarItemPedido(45L, 29L);
    }

    @Test
    public void testCerrarPedido() throws BusinessException {
        sistema.cerrarPedido(49L, 27329616253L);
    }

    @Test
    public void testAprobarPedido() throws BusinessException {
        sistema.aprobarPedido(49L, 27329616253L, "Cliente fiel");
    }

    @Test
    public void testCrearArticulo(){
        Articulo art = sistema.crearArticulo("234555", "Bombilla", "Caja", "10 unidades", 10, 1, 25);
        Assert.assertNotNull(art);
    }

    @Test
    public void testVerPedido(){
        Pedido p = sistema.buscarPedido(43L);
        if(p.getItems()!=null){
            for(ItemPedido i : p.getItems()){
                System.out.println(i.getArticulo().getDescripcion() + ": "+ i.getArticulo().getStock().calcular());
            }
        }
    }

    @Test
    public void testObtenerClientePorPedido(){
        Cliente cli = sistema.obtenerClientePorPedido(45L);
        Assert.assertNotNull(cli);
    }
}
