package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;

public class SistemaComprasTest {

    private SistemaCompras sistema;

    @Before
    public void setup() {
        sistema = SistemaCompras.getInstance();
    }

    @Test
    public void testProcesarOC() throws BusinessException {
        sistema.procesarOrdenCompra(3L);
    }

}
