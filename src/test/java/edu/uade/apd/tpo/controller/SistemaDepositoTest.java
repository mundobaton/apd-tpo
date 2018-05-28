package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.OrdenCompraDao;
import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class SistemaDepositoTest {

    private SistemaDeposito sistema;

    @Before
    public void setup() {
        sistema = SistemaDeposito.getInstance();
    }

    @Test
    public void testBuscarArticulo() {
        Articulo art = sistema.buscarArticulo(1L);
        Assert.assertNotNull(art);
    }

    @Test
    public void testCompletarPedido() {
        sistema.completarPedido(1L);
    }

    @Test
    public void testIngresarCompra() throws BusinessException {
        OrdenCompraEntity entity = OrdenCompraDao.getInstance().findById(1L);
        OrdenCompra oc = OrdenCompra.fromEntity(entity);
        List<ItemLote> items = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            String loteCod = UUID.randomUUID().toString();
            Lote lote = new Lote();
            lote.setCodigo(loteCod);
            lote.setFechaVto(new Date());
            lote.setFechaElaboracion(new Date());
            ItemLote item = new ItemLote();
            item.setLote(lote);
            item.setCantidad(oc.getArticulo().getCantCompra() / 5);
            items.add(item);
        }

        sistema.ingresarCompra(1L, items);
    }

    @Test
    public void testAlmacenar() throws BusinessException {
        ArticuloEntity entity = ArticuloDao.getInstance().findById(2L);
        Articulo art = Articulo.fromEntity(entity);
        List<ItemLote> lotes = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            String c = UUID.randomUUID().toString();
            Lote lote = new Lote();
            lote.setCodigo(c);
            lote.setFechaVto(new Date());
            lote.setFechaElaboracion(new Date());
            ItemLote item = new ItemLote();
            item.setLote(lote);
            item.setCantidad(10);
            lotes.add(item);
        }
        sistema.almacenar(art, lotes, 30);
    }

    @Test
    public void testAceptarOrdenCompra() throws BusinessException {
        sistema.aceptarOrdenCompra(1L);
    }

    @Test
    public void testBuscarPosicionPorUbicacion() {
        Posicion p = sistema.buscarPosicionPorUbicacion("A01020110");
        Assert.assertNotNull(p);
    }

    @Test
    public void testLiberarPosicion() {
        int result = sistema.liberarPosicion("B01020102", 1);
        System.out.print(result);
    }

    @Test
    public void testObtenerArticulos() {
        List<Articulo> art = sistema.obtenerArticulos();
        Assert.assertNotNull(art);
    }

    @Test
    public void testCrearLote() {
        Lote l = sistema.crearLote("G03050621", new Date(), new Date(), 3L);
        Assert.assertNotNull(l);
    }

    @Test
    public void testObtenerPedidosACompletar() {
        List<Pedido> pedidos = sistema.obtenerPedidosACompletar();
        Assert.assertNotNull(pedidos);
    }

    ///
    @Test
    public void testAceptarCompra2() throws BusinessException {
        List<ItemLote> lotes = new ArrayList<>();
        ItemLote il = new ItemLote();
        Lote lote = new Lote();
        lote.setFechaVto(new Date());
        lote.setFechaElaboracion(new Date());
        lote.setCodigo("123123asd");
        il.setLote(lote);
        il.setCantidad(1500);
        lotes.add(il);

        sistema.ingresarCompra(3L, lotes);
    }

}
