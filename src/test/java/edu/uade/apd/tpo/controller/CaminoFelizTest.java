package edu.uade.apd.tpo.controller;

import org.junit.Before;
import org.junit.Test;

public class CaminoFelizTest {

    private SistemaAdministracion adm;
    private SistemaCompras compras;
    private SistemaDeposito depo;
    private SistemaDespacho desp;
    private SistemaFacturacion fac;

    @Before
    public void setup() {
        adm = SistemaAdministracion.getInstance();
        compras = SistemaCompras.getInstance();
        depo = SistemaDeposito.getInstance();
        desp = SistemaDespacho.getInstance();
        fac = SistemaFacturacion.getInstance();
    }

    @Test
    public void testCrearUsuario(){}

    @Test
    public void testCrearCliente(){}

    @Test
    public void testGenerarPedido(){}

    @Test
    public void testGenerarPedidoSinSaldo(){}

    @Test
    public void testGenerarPedidoSinStock(){}

    @Test
    public void testListarPedidosParaAprobar(){}

    @Test
    public void testListarPedidosAprobadosVerificados(){}

    @Test
    public void testListarPedidosCompletos(){}

    @Test
    public void testListarPedidosListos(){}

    @Test
    public void testListarPedidosEnviados(){}

}
