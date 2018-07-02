package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.exception.BusinessException;
import org.junit.Before;
import org.junit.Test;

public class SistemaFacturacionTest {

    private SistemaFacturacion sistema;

    @Before
    public void setup() {
        sistema = SistemaFacturacion.getInstance();
    }

    @Test
    public void testFacturar() throws BusinessException {
        sistema.facturar(1L);
    }

    @Test
    public void testPagarFactura() throws BusinessException {
        sistema.pagarFactura(1L, 1000f, 1L);
    }

}
