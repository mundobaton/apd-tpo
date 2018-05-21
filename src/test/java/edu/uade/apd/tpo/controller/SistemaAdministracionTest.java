package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.ZonaEnvio;
import org.junit.Before;
import org.junit.Test;

public class SistemaAdministracionTest {

    private SistemaAdministracion sistemaAdministracion;

    @Before
    public void setup() {
        this.sistemaAdministracion = SistemaAdministracion.getInstance();
    }

    @Test(expected = BusinessException.class)
    public void testLoginUsuarioNoExiste() throws BusinessException {
        sistemaAdministracion.login("fulano de tal", "asd");
    }

    @Test(expected = BusinessException.class)
    public void testLoginPasswordIncorrecto() throws BusinessException {
        sistemaAdministracion.login("cliente@scratch.com", "pwd2");
    }

    @Test
    public void testLoginOk() throws BusinessException {
        sistemaAdministracion.login("cliente@scratch.com", "pwd");
    }

    @Test
    public void testCrearClienteOk() throws BusinessException {
        //Eliminar el usuario si se necesita ejecutar de nuevo
        sistemaAdministracion.crearCliente("cliente8@scratch.com", "pwd", "fulano", 123L, "4123-4444", CondIva.EXENTO,
                "una calle me separa", 123, "dasasd", "capi", "Buenos aires", ZonaEnvio.CABA, 1000, 1200);
    }

    @Test
    public void testCrearPedidoOk() throws BusinessException {
        Pedido p = sistemaAdministracion.generarPedido("cliente@scratch.com", "una calle me separa", 666, "un cp", "una loc", "una prov", ZonaEnvio.CABA);
    }

    @Test
    public void testAgregarItemPedido() throws BusinessException {
        sistemaAdministracion.agregarItemPedido(new Integer(35), new Integer(1), 50);
    }

    @Test
    public void testFinalizarCargaItems() throws BusinessException {
        sistemaAdministracion.finalizarCargaItems(new Integer(35));
    }

    @Test
    public void aprobarPedido() throws BusinessException {
        sistemaAdministracion.aprobarPedido(new Integer(35));
    }
}
