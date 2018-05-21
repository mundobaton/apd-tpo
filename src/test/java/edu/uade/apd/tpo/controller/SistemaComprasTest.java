package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;

public class SistemaComprasTest {

    private static SistemaCompras instance;

    @Before
    public void setup() {
        instance = SistemaCompras.getInstance();
    }

    @Test
    public void testProcesarOrdenCompraOk() throws BusinessException {
        instance.procesarOrdenCompra(new Integer(3), new Integer(1));
    }

}
