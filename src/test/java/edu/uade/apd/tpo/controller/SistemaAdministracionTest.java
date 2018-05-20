package edu.uade.apd.tpo.controller;

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
        sistemaAdministracion.crearCliente("baton2@gmail.com", "otro password", "fulano", 123, "4123-3333", CondIva.CONS_FINAL, "una calle", 123, "un cp", "una localidad", "una prov", ZonaEnvio.CABA, 0, 1500);
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

}
