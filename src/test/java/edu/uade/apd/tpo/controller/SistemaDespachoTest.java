package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;

public class SistemaDespachoTest {

    private SistemaDespacho sistema;

    @Before
    public void setup() {
        sistema = SistemaDespacho.getInstance().getInstance();
    }

    @Test
    public void testDespachar() throws BusinessException {
        sistema.despacharPedido(1L);
    }

}
