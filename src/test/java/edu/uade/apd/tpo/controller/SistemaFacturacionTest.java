package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.impl.ClienteDao;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.MedioPago;
import edu.uade.apd.tpo.model.Usuario;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SistemaFacturacionTest {

    private SistemaFacturacion sistemaFacturacion = SistemaFacturacion.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(SistemaAdministracion.class);

    @Test
    public void testprocesarPagoImporte() throws BusinessException {

        Cliente c = ClienteDao.getInstance().findByEmail("baton2@gmail.com");

        Long facturaId = new Long(10);
        Float importe = new Float(2000);

        sistemaFacturacion.procesarPago(facturaId, importe, MedioPago.EFECTIVO, c.getCuentaCorriente().getSaldo(), c.getCuentaCorriente().getLimiteCredito());

    }

}
