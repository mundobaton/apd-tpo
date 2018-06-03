package edu.uade.apd.tpo.controller;

<<<<<<< HEAD
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
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
        Pedido p = sistema.generarPedido(27329616253L, "Av Odas", 466L, "1099", "CABA", "Buenos Aires", Zona.CABA);
        System.out.print("Pedido id: "+p.getId());
        Assert.assertNotNull(p.getId());
    }

    @Test
    public void testAgregarItemPedido() throws BusinessException {
        sistema.agregarItemPedido(51L, 27329616253L, 37L, 15);
    }

    @Test
    public void testEliminarItemPedido() throws BusinessException{
        sistema.eliminarItemPedido(45L, 29L);
    }

    @Test
    public void testCerrarPedido() throws BusinessException {
        sistema.cerrarPedido(51L, 27329616253L);
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

    @Test
    public void testObtenerPedidosPorEstado(){
        List<Pedido> pedidos = sistema.obtenerPedidoPorEstado(EstadoPedido.ENVIADO);
        Assert.assertNotNull(pedidos);
    }

    @Test
    public void testObtenerPedidosParaAprobar(){
        List<Pedido> pedidos = sistema.obtenerPedidosParaAprobar();
        Assert.assertNotNull(pedidos);
    }

    @Test
    public void testProcesarPedidosPendientes() throws BusinessException{
        sistema.procesarPedidosPendientesCompraIngresada();
    }
=======
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Rol;
import edu.uade.apd.tpo.model.ZonaEnvio;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaAdministracionTest {

    private SistemaAdministracion sistemaAdministracion = SistemaAdministracion.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(SistemaAdministracion.class);

    @Test
    public void testCrearUsuario() {
        sistemaAdministracion.crearUsuario("baton@gmail.com", "un password", Rol.FACTURACION);
    }

    @Test
    public void testCrearCliente() {
        sistemaAdministracion.crearCliente("erica@gmail.com", "otro password", "erica", 123, "4123-3333", CondIva.CONS_FINAL, "una calle", 123, "un cp", "una localidad", "una prov", ZonaEnvio.CABA, 0, 1500);
    }

    @Test
    public void testGenerarPedido() {
        String email = "baton2@gmail.com";
        String calle = "Fake St.";
        int num = 123;
        String codPostal = "un cp";
        String localidad = "una localidad";
        String prov = "Buenos Aires";
        ZonaEnvio zona = ZonaEnvio.CABA;

        Long pedidoId = sistemaAdministracion.generarPedido(email, calle, num, codPostal, localidad, prov, zona);
        logger.info(">> Se genero el pedido: " + pedidoId);

    }

>>>>>>> develop
}
